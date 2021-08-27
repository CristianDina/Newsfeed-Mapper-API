package com.internship.pillarglobal.NewsfeedMapperAPI.exceptions;

public class FailedToReadDataFromXml extends RuntimeException{
    public FailedToReadDataFromXml(String errorMessage){
        super(errorMessage);
    }
}
