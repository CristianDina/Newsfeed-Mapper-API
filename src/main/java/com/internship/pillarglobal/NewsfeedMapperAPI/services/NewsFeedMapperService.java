package com.internship.pillarglobal.NewsfeedMapperAPI.services;
import com.internship.pillarglobal.NewsfeedMapperAPI.clients.YahooUKClient;
import com.internship.pillarglobal.NewsfeedMapperAPI.models.YahooUKItem;
import com.internship.pillarglobal.NewsfeedMapperAPI.repositories.NewsfeedMapperRepository;
import com.internship.pillarglobal.NewsfeedMapperAPI.utils.DataBaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.List;


@Service
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
            DataBaseUtil.verifyConnection();
            for (YahooUKItem item : yahooUKItemList) {
                newsfeedMapperRepository.save(item);
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return yahooUKItemList;
    }

}
