package io.bsnet.breeze;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

@Component("textView")
class Text implements View {
	
	@Override
	public String getContentType() {
		return "text/plain;charset=utf-8";
	}
	
	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/plain;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpServletResponse.SC_OK);
		Object text = model.get("text");
		if( text instanceof String ){
			response.getWriter().write((String)text);	
			response.getWriter().flush();
			response.getWriter().close();
		}
	}

}