package com.internship.pillarglobal.NewsfeedMapperAPI.controllers;

import com.internship.pillarglobal.NewsfeedMapperAPI.exceptions.FailedToReadDataFromXml;
import com.internship.pillarglobal.NewsfeedMapperAPI.exceptions.FailedToStoreInDatabase;
import com.internship.pillarglobal.NewsfeedMapperAPI.services.NewsFeedMapperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.MalformedInputException;

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
            return new ResponseEntity<String>(failedToStoreInDatabase.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (FailedToReadDataFromXml failedToReadDataFromXml){

            return new ResponseEntity<String>(failedToReadDataFromXml.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/triggerYahooUS")
    public ResponseEntity<?> triggerYahooUS() throws IOException, InterruptedException {
        try {
            newsFeedMapperService.processYahooUS();
            return new ResponseEntity<String>("Trigger to yahoo-us was done successfully.", HttpStatus.OK);
        } catch (FailedToStoreInDatabase failedToStoreInDatabase) {
            return new ResponseEntity<String>(failedToStoreInDatabase.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (FailedToReadDataFromXml failedToReadDataFromXml){

            return new ResponseEntity<String>(failedToReadDataFromXml.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/triggerMsnUK")
    public ResponseEntity<?> triggerMsnUK() throws IOException, InterruptedException {
        try {
            newsFeedMapperService.processMsnUK();
            return new ResponseEntity<String>("Trigger to msn-uk was done successfully.", HttpStatus.OK);
        } catch (FailedToStoreInDatabase failedToStoreInDatabase) {
            return new ResponseEntity<String>(failedToStoreInDatabase.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (FailedToReadDataFromXml failedToReadDataFromXml){
            return new ResponseEntity<String>(failedToReadDataFromXml.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/triggerMsnUS")
    public ResponseEntity<?> triggerMsnUS() throws IOException, InterruptedException {
        try {
            newsFeedMapperService.processMsnUS();
            return new ResponseEntity<String>("Trigger to msn-us was done successfully.", HttpStatus.OK);
        } catch (FailedToStoreInDatabase failedToStoreInDatabase) {
            return new ResponseEntity<String>(failedToStoreInDatabase.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (FailedToReadDataFromXml failedToReadDataFromXml) {

            return new ResponseEntity<String>(failedToReadDataFromXml.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getHello")
    public String getHello(){
        return "Hello";
    }

}
