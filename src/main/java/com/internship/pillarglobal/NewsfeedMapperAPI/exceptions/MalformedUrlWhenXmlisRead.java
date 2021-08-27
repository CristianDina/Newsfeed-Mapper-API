package com.internship.pillarglobal.NewsfeedMapperAPI.exceptions;

public class MalformedUrlWhenXmlisRead extends RuntimeException{
    public MalformedUrlWhenXmlisRead(String errorMessage){
        super(errorMessage);
    }
}
