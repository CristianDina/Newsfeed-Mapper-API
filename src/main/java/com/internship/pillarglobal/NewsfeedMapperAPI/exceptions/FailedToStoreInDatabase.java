package com.internship.pillarglobal.NewsfeedMapperAPI.exceptions;

public class FailedToStoreInDatabase extends RuntimeException{
    public FailedToStoreInDatabase(String errorMessage){
        super(errorMessage);
    }
}
