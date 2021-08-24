package com.internship.pillarglobal.NewsfeedMapperAPI.controllers;

import com.internship.pillarglobal.NewsfeedMapperAPI.services.NewsFeedMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
public class NewsfeedMapperController {
    @Autowired
    public NewsFeedMapperService newsFeedMapperService;
    @PostMapping("/triggerYahooUK")
    public void triggerYahooUK() throws IOException, InterruptedException {
        newsFeedMapperService.processYahooUK();
    }


}
