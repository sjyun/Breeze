package io.bsnet.breeze;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

@Component("jsonView")
class Json implements View {

	@Override
	public String getContentType() {
		return "application/json";
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		/*
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(model);
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().write(jsonString);
		response.getWriter().flush();
		response.getWriter().close();
		*/
	}

}
