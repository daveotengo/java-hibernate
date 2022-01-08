/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dave.hibernateproj.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.dave.hibernateproj.DTO.WSResponse;
import com.google.gson.Gson;

/**
 *
 * @author dave
 */
@Provider
public class MissingRequiredParamsExceptionMapper implements ExceptionMapper<MissingRequiredParamsException> {
	String errorCode = "30";
	
    public Response toResponse(MissingRequiredParamsException exception) {
    	WSResponse response = new WSResponse(errorCode, exception.getMessage());
        
        return Response.status(Response.Status.BAD_REQUEST).
                entity(new Gson().toJson(response)).
                build();
    }
    
}