package com.capella.exceptions;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode= FaultCode.CLIENT)
public class SpringWsException extends Exception {
	private static final long serialVersionUID = 7032105108349026185L;
	public SpringWsException(String message) {
		super(message);
	}

}
