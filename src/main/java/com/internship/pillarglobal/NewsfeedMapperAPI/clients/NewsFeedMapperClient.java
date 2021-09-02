package com.internship.pillarglobal.NewsfeedMapperAPI.clients;

import com.internship.pillarglobal.NewsfeedMapperAPI.exceptions.FailedToReadDataFromXml;
import com.internship.pillarglobal.NewsfeedMapperAPI.exceptions.MalformedUrlWhenXmlisRead;
import com.internship.pillarglobal.NewsfeedMapperAPI.models.Item;
import com.internship.pillarglobal.NewsfeedMapperAPI.utils.ItemMapper;
import lombok.AllArgsConstructor;
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
@NoArgsConstructor
@Slf4j
public abstract class NewsFeedMapperClient {
    private String urlString;
    private String clientName;
    public  NewsFeedMapperClient(String urlString, String clientName){
        this.urlString=urlString;
        this.clientName=clientName;

    }
    public  List<? extends Item> getRssFeed(){
        URL yahoo=null;
        try {
            yahoo = new URL(urlString);
            URLConnection yc = yahoo.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            yc.getInputStream()));
            String inputLine;
            String data = "";
            while ((inputLine = in.readLine()) != null)
                data = data.concat(inputLine);
            in.close();
            return getItemsList(data);
        }catch (MalformedURLException malformedURLException){
            log.error("An malformed URL has occurred at "+this.clientName+" client: "+yahoo.getPath());
            throw new MalformedUrlWhenXmlisRead("An malformed URL has occurred at "+this.clientName+" client:"+yahoo.getPath());
        }
        catch (IOException ioException){
            log.error("Failed to read data from "+this.clientName+ "xml.");
            throw new FailedToReadDataFromXml("Failed to read data from "+this.clientName+ "xml.");
        }
    }

    protected abstract List<? extends Item> getItemsList(String data) throws IOException;
}
