package com.internship.pillarglobal.NewsfeedMapperAPI.services;
import com.internship.pillarglobal.NewsfeedMapperAPI.clients.YahooUKClient;
import com.internship.pillarglobal.NewsfeedMapperAPI.clients.YahooUSClient;
import com.internship.pillarglobal.NewsfeedMapperAPI.exceptions.FailedToStoreInDatabase;
import com.internship.pillarglobal.NewsfeedMapperAPI.models.YahooUKItem;
import com.internship.pillarglobal.NewsfeedMapperAPI.models.YahooUSItem;
import com.internship.pillarglobal.NewsfeedMapperAPI.repositories.YahooUKRepository;
import com.internship.pillarglobal.NewsfeedMapperAPI.repositories.YahooUSRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
@Slf4j
@EnableScheduling
public class NewsFeedMapperService {
    private YahooUSClient yahooUSClient;
    private YahooUKClient yahooUKClient;
    private YahooUKRepository yahooUKRepository;
    private YahooUSRepository yahooUSRepository;
    @Autowired
    public NewsFeedMapperService(YahooUKRepository newsfeedMapperRepository, YahooUKClient yahooUKClient, YahooUSClient yahooUSClient, YahooUSRepository yahooUSRepository) {
        this.yahooUKRepository = newsfeedMapperRepository;
        this.yahooUKClient=yahooUKClient;
        this.yahooUSClient=yahooUSClient;
        this.yahooUSRepository=yahooUSRepository;
    }

    @Scheduled(fixedDelay = 300000)
    public void processYahooUK() throws IOException {
        log.info("YahooUK article mapping has started");
        List<YahooUKItem> yahooUKItemList=yahooUKClient.getRssFeed();
        YahooUKItem currentItem = yahooUKItemList.get(0);
        try {
            for(YahooUKItem item:yahooUKItemList){
                currentItem = item;
                yahooUKRepository.save(currentItem);
            }
        }catch(Exception exception){
           log.error("Failed to store yahoo-uk article in database, article: "+currentItem.toString());
           throw new FailedToStoreInDatabase("Failed to store yahoo-uk article in database");
        }
    }

    @Scheduled(fixedDelay = 300000)
    public void processYahooUS() throws IOException {
        log.info("YahooUS article mapping has started");
        List<YahooUSItem> yahooUSItemList=yahooUSClient.getRssFeedUS();
        YahooUSItem currentItem = yahooUSItemList.get(0);
        try {
            for(YahooUSItem item:yahooUSItemList){
                currentItem = (YahooUSItem) item;
                yahooUSRepository.save(currentItem);
            }
        }catch(Exception exception){
            log.error("Failed to store yahoo-us article in database, article: "+currentItem.toString());
            throw new FailedToStoreInDatabase("Failed to store yahoo-us article in database");
        }
    }
}
