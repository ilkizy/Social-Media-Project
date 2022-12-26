package com.ilkiz.exceptions;

import lombok.Getter;

@Getter
public class ElasticSearchException extends RuntimeException{

    private final ErrorType errorType;

    public ElasticSearchException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public ElasticSearchException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}
