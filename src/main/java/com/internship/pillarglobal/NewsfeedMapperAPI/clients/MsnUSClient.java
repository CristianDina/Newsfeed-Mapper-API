package com.internship.pillarglobal.NewsfeedMapperAPI.clients;

import com.internship.pillarglobal.NewsfeedMapperAPI.exceptions.FailedToReadDataFromXml;
import com.internship.pillarglobal.NewsfeedMapperAPI.exceptions.MalformedUrlWhenXmlisRead;
import com.internship.pillarglobal.NewsfeedMapperAPI.models.Item;
import com.internship.pillarglobal.NewsfeedMapperAPI.models.MsnUKItem;
import com.internship.pillarglobal.NewsfeedMapperAPI.models.MsnUSItem;
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
public class MsnUSClient extends NewsFeedMapperClient{

    public MsnUSClient(String urlString, String clientName) {
        super(urlString, clientName);
    }

    @Override
    protected List<? extends Item> getItemsList(String data) throws IOException {
        return ItemMapper.getList(data,new MsnUSItem());
    }
}
