/*
 * Confidential and Proprietary.
 * Do not distribute without 1-800-Flowers.com, Inc. consent.
 * Copyright 1-800-Flowers.com, Inc. 2019. All rights reserved.
 */

package com.springboot.mysql.crud.exception;

import org.springframework.http.HttpStatus;

public class SpringBootMysqlCrudException extends Exception {

    private static final long serialVersionUID = 1L;
    
    private HttpStatus httpStatus;
 
	public SpringBootMysqlCrudException() {
        super();
    }

    public SpringBootMysqlCrudException(String message) {
        super(message);
    }

    public SpringBootMysqlCrudException(String message, HttpStatus httpStatus) {
        super(message);
        setHttpStatus(httpStatus);
    }
    
    public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}


    
}
