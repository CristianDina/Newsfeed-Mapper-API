package com.internship.pillarglobal.NewsfeedMapperAPI;

import com.internship.pillarglobal.NewsfeedMapperAPI.clients.MsnUKClient;
import com.internship.pillarglobal.NewsfeedMapperAPI.clients.MsnUSClient;
import com.internship.pillarglobal.NewsfeedMapperAPI.clients.YahooUKClient;
import com.internship.pillarglobal.NewsfeedMapperAPI.clients.YahooUSClient;
import com.internship.pillarglobal.NewsfeedMapperAPI.models.*;
import com.internship.pillarglobal.NewsfeedMapperAPI.repositories.MsnUKRepository;
import com.internship.pillarglobal.NewsfeedMapperAPI.repositories.MsnUSRepository;
import com.internship.pillarglobal.NewsfeedMapperAPI.repositories.YahooUKRepository;
import com.internship.pillarglobal.NewsfeedMapperAPI.repositories.YahooUSRepository;
import com.internship.pillarglobal.NewsfeedMapperAPI.services.NewsFeedMapperService;
import com.internship.pillarglobal.NewsfeedMapperAPI.utils.ItemMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class NewsfeedMapperServiceTest {
    private YahooUKRepository yahooUKRepository= Mockito.mock(YahooUKRepository.class);
    private YahooUSRepository yahooUSRepository=Mockito.mock(YahooUSRepository.class);
    private MsnUKRepository msnUKRepository=Mockito.mock(MsnUKRepository.class);
    private MsnUSRepository msnUSRepository=Mockito.mock(MsnUSRepository.class);
    private MsnUKClient msnUKClient=Mockito.mock(MsnUKClient.class);
    private MsnUSClient msnUSClient=Mockito.mock(MsnUSClient.class);
    private YahooUKClient yahooUKClient=Mockito.mock(YahooUKClient.class);
    private YahooUSClient yahooUSClient=Mockito.mock(YahooUSClient.class);
    private NewsFeedMapperService newsFeedMapperService=new NewsFeedMapperService(yahooUKRepository,yahooUSRepository,msnUKRepository,msnUSRepository,yahooUKClient,yahooUSClient,msnUKClient,msnUSClient);
    @Test
    public void processYahooUK() throws IOException {
        List<YahooUKItem> provided= Arrays.asList(new YahooUKItem("a","a",new Date(),true,"a1","ana","a","a","a"),new YahooUKItem("b","b",new Date(),true,"b1","bob","b","b","b"));
        Mockito.when(yahooUKClient.getRssFeed()).thenReturn(provided);
        newsFeedMapperService.processYahooUK();
        Mockito.verify(yahooUKRepository,Mockito.times(1)).save(provided.get(0));
        Mockito.verify(yahooUKRepository,Mockito.times(1)).save(provided.get(1));
    }

    @Test
    public void processYahooUS() throws IOException {
        List<YahooUSItem> provided= Arrays.asList(new YahooUSItem("a","a",new Date(),true,"a1","ana","a","a","a"),new YahooUSItem("b","b",new Date(),true,"b1","bob","b","b","b"));
        Mockito.when(yahooUSClient.getRssFeed()).thenReturn(provided);
        newsFeedMapperService.processYahooUS();
        Mockito.verify(yahooUSRepository,Mockito.times(1)).save(provided.get(0));
        Mockito.verify(yahooUSRepository,Mockito.times(1)).save(provided.get(1));
    }

    @Test
    public void processMsnUK() throws IOException {
        List<MsnUKItem> provided= Arrays.asList(new MsnUKItem("a","a","a",new Date(),"a","a","a",new Date(),true,"aa",new Content("s","s","d","s","s",3,new Thumbnail("t","t"))));
        Mockito.when(msnUKClient.getRssFeed()).thenReturn(provided);
        newsFeedMapperService.processMsnUK();
        List<MsnUKItemForDB> msnUKItemForDB = new ArrayList<>();
        msnUKItemForDB.add(ItemMapper.mapMsnUKItemToDBItem(provided.get(0)));
        //msnUKItemForDB.add(ItemMapper.mapMsnUKItemToDBItem(provided.get(1)));
        Mockito.verify(msnUKRepository,Mockito.times(1)).save(msnUKItemForDB.get(0));
       // Mockito.verify(msnUKRepository,Mockito.times(1)).save(msnUKItemForDB.get(1));
    }


    @Test
    public void processMsnUS() throws IOException {
        List<MsnUSItem> provided= Arrays.asList(new MsnUSItem("a","a","a",new Date(),"a","a","a",new Date(),true,"aa",new Content("s","s","d","s","s",3,new Thumbnail("t","t"))),new MsnUSItem("a","a","a",new Date(),"a","a","a",new Date(),true,"aa",new Content("s","s","d","s","s",3,new Thumbnail("a","a"))));
        Mockito.when(msnUSClient.getRssFeed()).thenReturn(provided);
        newsFeedMapperService.processMsnUS();
        List<MsnUSItemForDB> msnUSItemForDB = new ArrayList<>();
        msnUSItemForDB.add(ItemMapper.mapMsnUSItemToDBItem(provided.get(0)));
        msnUSItemForDB.add(ItemMapper.mapMsnUSItemToDBItem(provided.get(1)));
        Mockito.verify(msnUSRepository,Mockito.times(1)).save(msnUSItemForDB.get(0));
        Mockito.verify(msnUSRepository,Mockito.times(1)).save(msnUSItemForDB.get(1));
    }
}
