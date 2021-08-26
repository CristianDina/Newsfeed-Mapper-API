package com.internship.pillarglobal.NewsfeedMapperAPI.controllers;

import com.internship.pillarglobal.NewsfeedMapperAPI.models.YahooUKItem;
import com.internship.pillarglobal.NewsfeedMapperAPI.services.NewsFeedMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class NewsfeedMapperController {
    @Autowired
    public NewsFeedMapperService newsFeedMapperService;
    @PostMapping("/triggerYahooUK")
    public ResponseEntity<?> triggerYahooUK() throws IOException, InterruptedException {
        if(!newsFeedMapperService.processYahooUK().isEmpty())
            return new ResponseEntity<String>("Trigger to yahoo-uk was done successfully.", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Trigger to yahoo-uk was done unsuccessfully.", HttpStatus.NOT_FOUND);
    }
}
