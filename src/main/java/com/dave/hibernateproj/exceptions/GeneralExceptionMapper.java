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
 * @author sergeykargopolov
 */
@Provider
public class GeneralExceptionMapper implements ExceptionMapper<GeneralException> {
	String errorCode = "06";
	
    public Response toResponse(GeneralException exception) {
    	WSResponse response = new WSResponse(errorCode, exception.getMessage());
        
        return Response.status(Response.Status.BAD_REQUEST).
                entity(new Gson().toJson(response)).
                build();
    }
    
}