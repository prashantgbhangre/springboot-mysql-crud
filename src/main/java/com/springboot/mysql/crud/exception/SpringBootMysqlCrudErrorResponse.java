/*
 * Confidential and Proprietary.
 * Do not distribute without 1-800-Flowers.com, Inc. consent.
 * Copyright 1-800-Flowers.com, Inc. 2019. All rights reserved.
 */

package com.springboot.mysql.crud.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SpringBootMysqlCrudErrorResponse {

	private String user_agent;
    
    @JsonProperty("Version") 
    private String version;
    
    @JsonProperty("StatusCode") 
    private int statusCode;
    
    @JsonProperty("ServiceTime") 
    private int serviceTime;
    
    @JsonProperty("ErrorObject") 
    private ErrorObject errorObject;

}
