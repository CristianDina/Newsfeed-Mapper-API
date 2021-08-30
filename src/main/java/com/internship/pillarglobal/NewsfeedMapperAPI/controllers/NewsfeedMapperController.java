package com.internship.pillarglobal.NewsfeedMapperAPI.controllers;

import com.internship.pillarglobal.NewsfeedMapperAPI.exceptions.FailedToReadDataFromXml;
import com.internship.pillarglobal.NewsfeedMapperAPI.exceptions.FailedToStoreInDatabase;
import com.internship.pillarglobal.NewsfeedMapperAPI.models.YahooUKItem;
import com.internship.pillarglobal.NewsfeedMapperAPI.services.NewsFeedMapperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@Slf4j
public class NewsfeedMapperController {
    @Autowired
    public NewsFeedMapperService newsFeedMapperService;

    @PostMapping("/triggerYahooUK")
    public ResponseEntity<?> triggerYahooUK() throws IOException, InterruptedException {
        try {
            newsFeedMapperService.processYahooUK();
            return new ResponseEntity<String>("Trigger to yahoo-uk was done successfully.", HttpStatus.OK);
        } catch (FailedToStoreInDatabase failedToStoreInDatabase) {
            return new ResponseEntity<String>(failedToStoreInDatabase.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (FailedToReadDataFromXml failedToReadDataFromXml){

            return new ResponseEntity<String>(failedToReadDataFromXml.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (MalformedInputException malformedInputException){
            return new ResponseEntity<>(malformedInputException.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
