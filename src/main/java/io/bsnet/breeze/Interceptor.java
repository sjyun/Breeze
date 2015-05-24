package io.bsnet.breeze;

import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.*;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

final public class Interceptor extends HandlerInterceptorAdapter {
	
	static final String BREEZE = "BREEZE";
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
			return service.preHandle($req, $res);
		}
		return true;
	}
	
	@Override
	public void postHandle( HttpServletRequest $req, HttpServletResponse $res, Object $handler, ModelAndView $modelAndView ) throws Exception {
		Service service = (Service)$req.getAttribute(BREEZE);
		if( service != null ){
			$req.removeAttribute(BREEZE);
			service.postHandle($req, $res, $modelAndView);
		}
	}
	
	/*
	@Override
	public void afterCompletion( HttpServletRequest $req, HttpServletResponse $res, Object $handler, Exception $ex ) throws Exception {
	}
	*/
}
