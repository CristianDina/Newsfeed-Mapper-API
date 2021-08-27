package com.internship.pillarglobal.NewsfeedMapperAPI.services;
import com.internship.pillarglobal.NewsfeedMapperAPI.clients.YahooUKClient;
import com.internship.pillarglobal.NewsfeedMapperAPI.exceptions.FailedToStoreInDatabase;
import com.internship.pillarglobal.NewsfeedMapperAPI.models.YahooUKItem;
import com.internship.pillarglobal.NewsfeedMapperAPI.repositories.NewsfeedMapperRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
@Slf4j
public class NewsFeedMapperService {
    private YahooUKClient yahooUKClient;
    private NewsfeedMapperRepository newsfeedMapperRepository;

    @Autowired
    public NewsFeedMapperService(NewsfeedMapperRepository newsfeedMapperRepository, YahooUKClient yahooUKClient) {
        this.newsfeedMapperRepository = newsfeedMapperRepository;
        this.yahooUKClient=yahooUKClient;
    }

    public List<YahooUKItem> processYahooUK() throws IOException, InterruptedException {
        List<YahooUKItem> yahooUKItemList=yahooUKClient.getRssFeed();
        try {
            newsfeedMapperRepository.saveAll(yahooUKItemList);
        }catch(Exception exception){
           log.error("Failed to store yahoo-uk articles in database");
           throw new FailedToStoreInDatabase("Failed to store yahoo-uk articles in database");
        }
        return yahooUKItemList;
    }
}
