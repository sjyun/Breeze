package io.bsnet.breeze;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

abstract public class Service extends EgovAbstractServiceImpl{

	@Autowired
	private View jsonView, excelView, textView;
	
	private Param getParam(HttpServletRequest $req){
		return (Param)$req.getAttribute(Interceptor.PARAM);
	}
	
	abstract protected boolean preHandle(final HttpServletRequest $req, final HttpServletResponse $res);
	
	abstract protected void postHandle(HttpServletRequest $req, HttpServletResponse $res, ModelAndView $modelAndView);
	
	boolean pre(final HttpServletRequest $req, final HttpServletResponse $res) {
		return preHandle($req, $res);
	}
	
	void post(HttpServletRequest $req, HttpServletResponse $res, ModelAndView $modelAndView) {
		postHandle($req, $res, $modelAndView);
		Param param = getParam($req);
		if( param != null ){
			HashMap<String, Object> model = param.getModel();
			if( model != null ){
				for( Map.Entry<String, Object> entry : model.entrySet() ){
					$modelAndView.addObject( entry.getKey(), entry.getValue() );
				}
				View view = param.getView();
				if( view != null ) $modelAndView.setView(view);
			}
		}
	}
	
	void setViewAsJson(HttpServletRequest $req){
		getParam($req).setView(jsonView);
	}
	void setViewAsExcel(HttpServletRequest $req){
		getParam($req).setView(excelView);
	}
	void setViewAsText(HttpServletRequest $req){
		getParam($req).setView(textView);
	}
}
