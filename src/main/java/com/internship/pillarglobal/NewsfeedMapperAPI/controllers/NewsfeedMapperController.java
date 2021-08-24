package com.internship.pillarglobal.NewsfeedMapperAPI.controllers;

import com.internship.pillarglobal.NewsfeedMapperAPI.services.NewsFeedMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
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
