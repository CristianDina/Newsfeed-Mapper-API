package com.internship.pillarglobal.NewsfeedMapperAPI.clients;

import com.internship.pillarglobal.NewsfeedMapperAPI.exceptions.FailedToReadDataFromXml;
import com.internship.pillarglobal.NewsfeedMapperAPI.exceptions.MalformedUrlWhenXmlisRead;
import com.internship.pillarglobal.NewsfeedMapperAPI.models.MsnUKItem;
import com.internship.pillarglobal.NewsfeedMapperAPI.utils.ItemMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

@Component
@Slf4j
@NoArgsConstructor
public class MsnUKClient{
    public List<MsnUKItem> getRssFeed(){
        URL url=null;
        try {
            url = new URL("https://msn-backend.platforms-prod-gcp.telegraph.co.uk/rss.xml");
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            yc.getInputStream()));
            String inputLine;
            String data = "";
            while ((inputLine = in.readLine()) != null)
                data = data.concat(inputLine);
            in.close();
            return ItemMapper.getListMsnUK(data);
        }catch (MalformedURLException malformedURLException){
            log.error("An malformed URL has occurred at msn-uk client: "+url.getPath());
            throw new MalformedUrlWhenXmlisRead("An malformed URL has occurred at msn-uk client:"+url.getPath());
        }
        catch (IOException ioException){
            log.error("Failed to read data from msn-uk xml.");
            throw new FailedToReadDataFromXml("Failed to read data from msn-uk xml.");
        }
    }

}
