package com.internship.pillarglobal.NewsfeedMapperAPI.clients;

import com.internship.pillarglobal.NewsfeedMapperAPI.models.YahooUKItem;
import com.internship.pillarglobal.NewsfeedMapperAPI.services.NewsFeedMapperService;
import com.internship.pillarglobal.NewsfeedMapperAPI.utils.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

@Component
public class YahooUKClient {
    public List<YahooUKItem> getRssFeed() throws IOException {
        URL yahoo = new URL("https://yahoo-uk-feed.platforms-prod-gcp.telegraph.co.uk/feed.xml");
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String inputLine;
        String data= new String("");
        while ((inputLine = in.readLine()) != null)
            data=data.concat(inputLine);
        in.close();
        return ItemMapper.getItemsList(data);

    }

}
