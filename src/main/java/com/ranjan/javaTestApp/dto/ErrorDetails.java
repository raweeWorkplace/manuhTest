package com.ranjan.javaTestApp.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(value=Include.NON_NULL)
public class ErrorDetails {
	
	@DateTimeFormat
	private Date timestamp;
	private String detailed_msg;
    private String error_msg;
    private String details;
    private Throwable error;    
    private String status;
	private String error_code;
    
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	public ErrorDetails(Date timestamp, String error_msg, String details, String status, String error_code) {
		super();
		this.timestamp = timestamp;
		this.detailed_msg = error_msg;
		this.error_msg="Something went wrong. Contact Admin !";
		this.details = details;
		this.status = status;
		this.error_code = error_code;
	}
	
	
	public ErrorDetails(Date timestamp, Throwable error, String details, String status, String error_code) {
		super();
		this.timestamp = timestamp;
		this.error = error;
		this.details = details;
		this.status = status;
		this.error_code = error_code;
	}

	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public Throwable getError() {
		return error;
	}
	public void setError(Throwable error) {
		this.error = error;
	}
	public String getError_msg() {
		return error_msg;
	}
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDetailed_msg() {
		return detailed_msg;
	}
	public void setDetailed_msg(String detailed_msg) {
		this.detailed_msg = detailed_msg;
	}
	@Override
	public String toString() {
		return "ErrorDetails [timestamp=" + timestamp + ", detailed_msg=" + detailed_msg + ", error_msg=" + error_msg
				+ ", details=" + details + ", error=" + error + ", status=" + status + ", error_code=" + error_code
				+ "]";
	}  
	
	
    
}
