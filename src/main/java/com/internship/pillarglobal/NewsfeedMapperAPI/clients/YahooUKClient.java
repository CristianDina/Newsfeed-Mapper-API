package com.internship.pillarglobal.NewsfeedMapperAPI.clients;

import com.internship.pillarglobal.NewsfeedMapperAPI.exceptions.FailedToReadDataFromXml;
import com.internship.pillarglobal.NewsfeedMapperAPI.exceptions.FailedToStoreInDatabase;
import com.internship.pillarglobal.NewsfeedMapperAPI.exceptions.MalformedUrlWhenXmlisRead;
import com.internship.pillarglobal.NewsfeedMapperAPI.models.YahooUKItem;
import com.internship.pillarglobal.NewsfeedMapperAPI.services.NewsFeedMapperService;
import com.internship.pillarglobal.NewsfeedMapperAPI.utils.ItemMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class YahooUKClient {
    public List<YahooUKItem> getRssFeed() throws IOException {
        URL yahoo=null;
        try {
            yahoo = new URL("https://yahoo-uk-feed.platforms-prod-gcp.telegraph.co.uk/feed.xml");
            URLConnection yc = yahoo.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            yc.getInputStream()));
            String inputLine;
            String data = "";
            while ((inputLine = in.readLine()) != null)
                data = data.concat(inputLine);
            in.close();
            return ItemMapper.getItemsList(data);
        }catch (MalformedURLException malformedURLException){
            log.error("An malformed URL has occurred at yahoo-uk client: "+yahoo.getPath());
            throw new MalformedUrlWhenXmlisRead("An malformed URL has occurred at yahoo-uk client: "+yahoo.getPath());
            }
        catch (IOException ioException){
            log.error("Failed to read data from yahoo-uk xml.");
            throw new FailedToReadDataFromXml("Failed to read data from yahoo-uk xml.");
        }
    }
}
