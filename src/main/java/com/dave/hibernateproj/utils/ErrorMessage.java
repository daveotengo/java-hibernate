/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dave.hibernateproj.utils;

/**
 *
 * @author sergeykargopolov
 */
public class ErrorMessage {
	private String status;
    private String errorMessage;
    private String errorMessageKey;
    
    
    public ErrorMessage(){}
    
    public ErrorMessage(String status, String errorMessage, String errorMessageKey)
    {
        this.status = status;
    	this.errorMessage = errorMessage;
        this.errorMessageKey = errorMessageKey;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessageKey() {
		return errorMessageKey;
	}

	public void setErrorMessageKey(String errorMessageKey) {
		this.errorMessageKey = errorMessageKey;
	}
}
