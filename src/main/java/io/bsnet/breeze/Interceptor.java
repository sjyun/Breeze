package io.bsnet.breeze;

import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.*;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

final public class Interceptor extends HandlerInterceptorAdapter {
	
	static private ApplicationContext ac;	
	static private ServletContext context;
	static ServletContext context(){ return context; }
	static public Object getBean( String $k ){
		if( $k == null ) return null;
		try{
			return ac.getBean($k);	
		}catch(BeansException e){
			return null;
		}
	}
	static private String calcNameFromURL(String $url ){
		return $url;
	}
	static private HashMap<String, BreezeSerivce> _urlProcesses = new HashMap<String, BreezeSerivce>();
	@Override
	public boolean preHandle( final HttpServletRequest $req, HttpServletResponse $res, Object $handler ) throws Exception {
		System.out.println("[BSInterceptor.preHandle]URL:" + $req.getRequestURI() );
		if( context == null ) context = $req.getSession().getServletContext();
		if( ac == null ) ac = (XmlWebApplicationContext)context.getAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.appServlet");
		ac.getBean(calcNameFromURL((String)$req.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE)));
	
