package io.bsnet.breeze;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

public class Service extends EgovAbstractServiceImpl{
	
	public boolean preHandle( final HttpServletRequest $req, HttpServletResponse $res ){
		return true;
	}
	
	public void postHandle(HttpServletRequest $req, HttpServletResponse $res, ModelAndView $modelAndView) {
	
	}
}
