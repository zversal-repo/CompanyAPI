package com.zversal.api.error;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class CustomErrorController implements ErrorController {

	private static final String PATH = "/error";

	@Autowired
	private ErrorAttributes errorAttributes;

	@RequestMapping(value = PATH)
	private ErrorInfo error(HttpServletResponse response, WebRequest webrequest) {
		return new ErrorInfo(response.getStatus(), errorAttributes.getErrorAttributes(webrequest, false));
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}

}
