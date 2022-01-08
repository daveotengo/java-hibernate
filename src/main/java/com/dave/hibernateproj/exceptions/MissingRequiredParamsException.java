/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dave.hibernateproj.exceptions;

/**
 *
 * @author sergeykargopolov
 */
public class MissingRequiredParamsException extends RuntimeException {
	private static final long serialVersionUID = 5217139014827667672L;

	public MissingRequiredParamsException(String message) {
        super(message);
    }

}
