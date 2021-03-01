package com.ranjan.javaTestApp.dto;

public class GenericResponse {

	private String error_code;
	private String error_msg;
	private String status;
	private String id;
	
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
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
	public GenericResponse(String error_code, String error_msg, String status, String id) {
		this.error_code = error_code;
		this.error_msg = error_msg;
		this.status = status;
		this.id = id;
	}

	public GenericResponse(String error_code, String error_msg, String status) {
		this.error_code = error_code;
		this.error_msg = error_msg;
		this.status = status;
	}
	public GenericResponse() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
