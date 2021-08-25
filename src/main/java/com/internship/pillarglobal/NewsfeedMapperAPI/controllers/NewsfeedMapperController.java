package com.internship.pillarglobal.NewsfeedMapperAPI.controllers;

import com.internship.pillarglobal.NewsfeedMapperAPI.services.NewsFeedMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@RestController
public class NewsfeedMapperController {
    @Autowired
    public NewsFeedMapperService newsFeedMapperService;
    @PostMapping("/triggerYahooUK")
    public String triggerYahooUK() throws IOException, InterruptedException {
       return newsFeedMapperService.processYahooUK();
    }


}
