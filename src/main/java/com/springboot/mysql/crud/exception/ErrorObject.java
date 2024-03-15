/*
 * Confidential and Proprietary.
 * Do not distribute without 1-800-Flowers.com, Inc. consent.
 * Copyright 1-800-Flowers.com, Inc. 2019. All rights reserved.
 */

package com.springboot.mysql.crud.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ErrorObject {

    @JsonProperty("ErrorMessage") 
    private String errorMessage;
    
    @JsonProperty("ErrorState") 
    private ErrorState errorState;
}
