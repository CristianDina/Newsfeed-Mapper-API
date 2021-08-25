package com.internship.pillarglobal.NewsfeedMapperAPI.controllers;

import com.internship.pillarglobal.NewsfeedMapperAPI.models.YahooUKItem;
import com.internship.pillarglobal.NewsfeedMapperAPI.services.NewsFeedMapperService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<YahooUKItem> triggerYahooUK() throws IOException, InterruptedException {
       return newsFeedMapperService.processYahooUK();
               //.processYahooUK().stream().flatMap(p-> Stream.of(p.getTitle())).collect(Collectors.toList());

    }


}
