package io.bsnet.breeze;

import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.*;

import io.bsnet.breeze.util.BreezeConstant;
import io.bsnet.breeze.util.BreezeStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

final public class Interceptor extends HandlerInterceptorAdapter {

	private final Logger logger = LoggerFactory.getLogger(Interceptor.class);

	static final String BREEZE = "BREEZE", PARAM = "BREEZE_PARAM";

	static private ApplicationContext ac;	
	static private ServletContext context;
	static private HashMap<String, Service> _services = new HashMap<String, Service>();
	
	static Object getBean(String $k){
		if($k == null) return null;
		try{
			return ac.getBean($k);	
		}catch(BeansException e){
			return null;
		}
	}
	
	static private Service getService(final HttpServletRequest $req){
		String url = (String)$req.getAttribute("javax.servlet.forward.request_uri");
		
		Service service = null;
		if(_services.containsKey(url)){
			service = _services.get(url);
		}else{
			//TODO : url을 다양한 방법으로 매핑하여 service를 취득함.
			service = (Service)getBean(url);
			
			if( service != null ) _services.put(url, service);
		}
		return service;
	}
	
	@Override
	public boolean preHandle( final HttpServletRequest $req, HttpServletResponse $res, Object $handler ) throws Exception {
		if( context == null ) context = $req.getSession().getServletContext();
		if( ac == null ) ac = (XmlWebApplicationContext)context.getAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.appServlet");
		
		Service service = getService($req);
		
		if( service != null ){
			$req.setAttribute(BREEZE, service);
			$req.setAttribute(PARAM, new Param());
			return service.pre($req, $res);
		}
		return true;
	}
	
	@Override
	public void postHandle( HttpServletRequest $req, HttpServletResponse $res, Object $handler, ModelAndView $modelAndView ) throws Exception {
		Service service = (Service)$req.getAttribute(BREEZE);
		if( service != null ){
			$req.removeAttribute(BREEZE);
			$req.removeAttribute(PARAM);
			service.post($req, $res, $modelAndView);
		}
	}
	
	/*
	@Override
	public void afterCompletion( HttpServletRequest $req, HttpServletResponse $res, Object $handler, Exception $ex ) throws Exception {
	}
	*/


	//log
	@SuppressWarnings("unchecked")
	private void printReq( HttpServletRequest $req){
		logger.info("");
		logger.info("============================");
		logger.info("Breeze 요청 URL -> \"" + $req.getRequestURI() + "\"");

		Map<String, String> paramMap = $req.getParameterMap();
		int size = paramMap.size() -1;
		int idx = 0;

		Enumeration<String> attr = $req.getParameterNames();

		if(attr.hasMoreElements()){
			logger.info("");
			logger.info("[parameter :" + $req.getMethod() + "(" + $req.getCharacterEncoding() + ")]");
		}

		while( attr.hasMoreElements() ){
			String param = attr.nextElement();
			String value = $req.getParameter( param );

			//check UTF-8
			/*
			if(!$req.getCharacterEncoding().equals( BreezeConstant.CHARSET_UTF_8 )){
				value = BreezeStringUtil.parameterEncoding($req.getParameter(param),BreezeConstant.CHARSET_UTF_8 );
			}
			*/

			logger.info( "#name: " +  param);
			logger.info( "#value: " +  value);

			if(idx++ < size)
				logger.info( "" );
		}
		logger.info("-------------------------------------------------------------");
	}
}
