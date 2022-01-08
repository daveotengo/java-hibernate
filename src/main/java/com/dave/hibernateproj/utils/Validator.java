package com.dave.hibernateproj.utils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;

import com.dave.hibernateproj.exceptions.MissingRequiredParamsException;



public class Validator {
	static final Logger log = Logger.getLogger("HibWS");
	
	

	public static void validateRequiredParameters(String[] parameters) {
		for (String parameter : parameters) {
			if (parameter == null || parameter.isEmpty()) {
				log.error("Validator:: Required paramter is missing..Refer to documentation");
				throw new MissingRequiredParamsException(ErrorMessages.REQUIRED_PARAMS_MISSING.getErrorMessage());
			}	
		}
	}


}
