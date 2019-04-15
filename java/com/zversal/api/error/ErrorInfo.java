package com.zversal.api.error;

import java.util.Date;
import java.util.Map;
/**
 * This Class is used to define Error Attributes
 * 
 * @author bhupinder
 *
 */
public class ErrorInfo {

	private int status;
	private String error;
	private String message;
	private Date timeStamp;
	/**
	 * Constructor to set Error Attributes
	 * 
	 * @param status          to set Http Status code
	 * @param errorAttributes of type Map<String,Object> contains error Attributes
	 */
	public ErrorInfo(int status, Map<String, Object> errorAttributes) {
		this.status = status;
		error = (String) errorAttributes.get("error");
		message = (String) errorAttributes.get("message");
		timeStamp = (Date) errorAttributes.get("timestamp");
	}
	/**
	 * This method is used to get Status code
	 * 
	 * @return status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * This method is used to set Http Status code
	 * 
	 * @param status - Http Status Code
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * This method is used to get Error Type
	 * 
	 * @return error - Error Type
	 */
	public String getError() {
		return error;
	}
	/**
	 * This method is used to set Error type
	 * 
	 * @param error - Error Type
	 */
	public void setError(String error) {
		this.error = error;
	}
	/**
	 * This method is used to get Error Message
	 * 
	 * @return message- Error message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * This method is used to set Error Message
	 * 
	 * @param message- Error Message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * This method is used to get time Stamp
	 * 
	 * @return time stamp
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}
	/**
	 * this method is used to set Time Stamp
	 * 
	 * @param timeStamp
	 */
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
}
