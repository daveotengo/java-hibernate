package com.dave.hibernateproj.DTO;

import java.util.List;



public class WSResponse {
	private String status;
	private String message;
	private String data;
	private String reference;

	
	public WSResponse() {
		super();
	}

	public WSResponse(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public WSResponse(String status, String message, String data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	
}
