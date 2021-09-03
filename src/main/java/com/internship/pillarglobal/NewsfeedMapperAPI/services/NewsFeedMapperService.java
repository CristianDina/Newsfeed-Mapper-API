package com.internship.pillarglobal.NewsfeedMapperAPI.services;

import com.internship.pillarglobal.NewsfeedMapperAPI.clients.MsnUKClient;
import com.internship.pillarglobal.NewsfeedMapperAPI.clients.MsnUSClient;
import com.internship.pillarglobal.NewsfeedMapperAPI.clients.YahooUKClient;
import com.internship.pillarglobal.NewsfeedMapperAPI.clients.YahooUSClient;
import com.internship.pillarglobal.NewsfeedMapperAPI.exceptions.FailedToStoreInDatabase;
import com.internship.pillarglobal.NewsfeedMapperAPI.models.*;
import com.internship.pillarglobal.NewsfeedMapperAPI.repositories.MsnUKRepository;
import com.internship.pillarglobal.NewsfeedMapperAPI.repositories.MsnUSRepository;
import com.internship.pillarglobal.NewsfeedMapperAPI.repositories.YahooUKRepository;
import com.internship.pillarglobal.NewsfeedMapperAPI.repositories.YahooUSRepository;
import com.internship.pillarglobal.NewsfeedMapperAPI.utils.ItemMapper;
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
    private MsnUKClient msnUKClient;
    private MsnUKRepository msnUKRepository;
    private MsnUSClient msnUSClient;
    private MsnUSRepository msnUSRepository;

    @Autowired
    public NewsFeedMapperService(YahooUKRepository newsfeedMapperRepository, YahooUSRepository yahooUSRepository, MsnUKRepository msnUKRepository, MsnUSRepository msnUSRepository, YahooUKClient yahooUKClient, YahooUSClient yahooUSClient, MsnUKClient msnUKClient, MsnUSClient msnUSClient) {
        this.yahooUKRepository = newsfeedMapperRepository;
        this.yahooUSRepository = yahooUSRepository;
        this.msnUKRepository = msnUKRepository;
        this.msnUSRepository = msnUSRepository;
        this.msnUKClient = msnUKClient;
        this.msnUSClient = msnUSClient;
        this.yahooUSClient = yahooUSClient;
        this.yahooUKClient = yahooUKClient;
    }

    @Scheduled(fixedDelay = 300000)
    public void processYahooUK() throws IOException {
        log.info("YahooUK article mapping has started");
        List<YahooUKItem> yahooUKItemList = (List<YahooUKItem>) yahooUKClient.getRssFeed();
        for (YahooUKItem item : yahooUKItemList) {
            try {
                yahooUKRepository.save(item);
            } catch (Exception exception) {
                log.error("Failed to store yahoo-uk article in database, article: " + item.toString());
                throw new FailedToStoreInDatabase("Failed to store yahoo-uk article in database");
            }
        }
    }

    @Scheduled(fixedDelay = 300000)
    public void processYahooUS() throws IOException {
        log.info("YahooUS article mapping has started");
        List<YahooUSItem> yahooUSItemList = yahooUSClient.getRssFeed();
        for (YahooUSItem item : yahooUSItemList) {
            try {
                yahooUSRepository.save(item);
            } catch (Exception exception) {
                log.error("Failed to store yahoo-us article in database, article: " + item.toString());
                throw new FailedToStoreInDatabase("Failed to store yahoo-us article in database");
            }
        }
    }

    @Scheduled(fixedDelay = 300000)
    public void processMsnUK() throws IOException {
        log.info("MsnUK article mapping has started");
        List<MsnUKItem> msnUKItems = msnUKClient.getRssFeed();
        for (MsnUKItem item : msnUKItems) {
            MsnUKItemForDB msnUKItemForDB = ItemMapper.mapMsnUKItemToDBItem(item);
            try {
                msnUKRepository.save(msnUKItemForDB);
            } catch (Exception exception) {
                log.error("Failed to store msn-uk article in database, article: " + item.toString());
                throw new FailedToStoreInDatabase("Failed to store msn-uk article in database" + item.toString());
            }
        }
    }

    @Scheduled(fixedDelay = 300000)
    public void processMsnUS() throws IOException {
        log.info("MsnUS article mapping has started");
        List<MsnUSItem> msnUSItems = (List<MsnUSItem>) msnUSClient.getRssFeed();
        for (MsnUSItem item : msnUSItems) {
            MsnUSItemForDB msnUSItemForDB = ItemMapper.mapMsnUSItemToDBItem(item);
            try {
                msnUSRepository.save(msnUSItemForDB);
            } catch (Exception exception) {
                log.error("Failed to store msn-us article in database, article: " + item.toString());
                throw new FailedToStoreInDatabase("Failed to store msn-us article in database" + item.toString());
            }
        }
    }
}

