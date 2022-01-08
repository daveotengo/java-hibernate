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
public class GeneralException extends RuntimeException {
	private static final long serialVersionUID = 8245842082153908236L;

	public GeneralException(String message) {
        super(message);
    }

}
