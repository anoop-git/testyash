package com.mckesson.producer.utilities;

import java.io.IOException;

public class HttpRestTemplateException extends IOException {

	private static final long serialVersionUID = 1L;

	private String message;
	private String statusCode;

	public HttpRestTemplateException(String statusCode, String message) {
		super(message);
		this.message = message;
		this.statusCode = statusCode;
	}

	public GeneralResponse getResponse() {
		return new GeneralResponse(statusCode, message, null);
	}

}
