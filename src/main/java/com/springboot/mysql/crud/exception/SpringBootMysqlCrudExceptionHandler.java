/*
 * Confidential and Proprietary.
 * Do not distribute without 1-800-Flowers.com, Inc. consent.
 * Copyright 1-800-Flowers.com, Inc. 2019. All rights reserved.
 */

package com.springboot.mysql.crud.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class SpringBootMysqlCrudExceptionHandler {

    @ExceptionHandler(SpringBootMysqlCrudException.class)
    public ResponseEntity<SpringBootMysqlCrudErrorResponse> handleAddressServiceException(SpringBootMysqlCrudException springBootMysqlCrudException) {
    	
    	SpringBootMysqlCrudErrorResponse springBootMysqlCrudErrorResponse = buildAddressBookEntriesErrorResponse(springBootMysqlCrudException);
    	log.info("SpringBootMongoCrudAPI springBootMongoCrudAPIException :" + springBootMysqlCrudException.getMessage());
        return new ResponseEntity<>(springBootMysqlCrudErrorResponse, springBootMysqlCrudException.getHttpStatus());
    }

	private SpringBootMysqlCrudErrorResponse buildAddressBookEntriesErrorResponse(SpringBootMysqlCrudException springBootMysqlCrudException) {
		
		SpringBootMysqlCrudErrorResponse springBootMysqlCrudErrorResponse = new SpringBootMysqlCrudErrorResponse();
		
		ErrorObject errorObject = new ErrorObject();
		errorObject.setErrorMessage(springBootMysqlCrudException.getMessage());
		
		ErrorState errorState = new ErrorState();
		errorState.setMessage(springBootMysqlCrudException.getMessage());
		errorObject.setErrorState(errorState);
		
		springBootMysqlCrudErrorResponse.setErrorObject(errorObject);
		springBootMysqlCrudErrorResponse.setStatusCode(springBootMysqlCrudException.getHttpStatus().value());
		
		return springBootMysqlCrudErrorResponse;
	}

}
