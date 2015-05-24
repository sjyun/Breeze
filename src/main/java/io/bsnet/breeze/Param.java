package io.bsnet.breeze;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.web.servlet.View;

@SuppressWarnings("serial")
public class Param extends LinkedHashMap<String, Object> {
	
	private static final String VIEW = "VIEW";
	
	HashMap<String, Object> getModel(){
		return null;
	}
	View getView(){
		return (View)get(VIEW);
	}
	void setView(View $view){
		put(VIEW, $view);
	}
}
