package com.zversal.api.error;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
/**
 * This class exposes the REST API for the system.
 * 
 * @author bhupinder
 *
 */
@RestController
public class CustomErrorController implements ErrorController {

	private static final String PATH = "/error";

	@Autowired
	private ErrorAttributes errorAttributes;
	/**
	 * This method will be used to get error Information
	 * 
	 * @param ticker unique id
	 * @return an instance of {@link ErrorInfo}
	 * @param HttpServletResponse
	 */
	@RequestMapping(value = PATH)
	private ErrorInfo error(HttpServletResponse response, WebRequest webrequest) {
		return new ErrorInfo(response.getStatus(), errorAttributes.getErrorAttributes(webrequest, false));
	}

	/**
	 * @return Error path
	 */
	@Override
	public String getErrorPath() {
		return PATH;
	}

}
