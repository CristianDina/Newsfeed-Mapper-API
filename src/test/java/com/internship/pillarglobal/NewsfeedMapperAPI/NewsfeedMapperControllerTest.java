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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = NewsfeedMapperApiApplication.class)
public class NewsfeedMapperControllerTest {
    @MockBean
    private YahooUKClient yahooUKClient;
    @MockBean
    private YahooUSClient yahooUSClient;
    @MockBean
    private MsnUKClient msnUKClient;
    @MockBean
    private MsnUSClient msnUSClient;
    @MockBean
    private YahooUKRepository yahooUKRepository;
    @MockBean
    private YahooUSRepository yahooUSRepository;
    @MockBean
    private MsnUKRepository msnUKRepository;
    @MockBean
    private MsnUSRepository msnUSRepository;
    @Autowired
    private NewsFeedMapperService newsFeedMapperService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void triggerYahooUK_happy() throws Exception {
        List<YahooUKItem> provided= Arrays.asList(new YahooUKItem("a","a",new Date(),true,"a1","ana","a","a","a"),new YahooUKItem("b","b",new Date(),true,"b1","bob","b","b","b"));
        Mockito.when(yahooUKClient.getRssFeed()).thenReturn(provided);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/triggerYahooUK"))
                .andExpect(status().isOk());
        Thread.sleep(1000);
        Mockito.verify(yahooUKClient, Mockito.times(1)).getRssFeed();
        Mockito.verify(yahooUKRepository, Mockito.times(1)).save(provided.get(0));
        Mockito.verify(yahooUKRepository, Mockito.times(1)).save(provided.get(1));
    }
    @Test
    public void triggerYahooUS_happy() throws Exception {
        List<YahooUSItem> provided= Arrays.asList(new YahooUSItem("a","a",new Date(),true,"a1","ana","a","a","a"),new YahooUSItem("b","b",new Date(),true,"b1","bob","b","b","b"));
        Mockito.when(yahooUSClient.getRssFeed()).thenReturn(provided);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/triggerYahooUS"))
                .andExpect(status().isOk());
        Thread.sleep(1000);
        Mockito.verify(yahooUSClient, Mockito.times(1)).getRssFeed();
        Mockito.verify(yahooUSRepository, Mockito.times(1)).save(provided.get(0));
        Mockito.verify(yahooUSRepository, Mockito.times(1)).save(provided.get(1));
    }


    @Test
    public void triggerMsnUK_happy() throws Exception {
        List<MsnUKItem> provided= Arrays.asList(new MsnUKItem("aaa","a","a",new Date(),"a","a","a",new Date(),true,"aa",null),new MsnUKItem("b","a","a",new Date(),"a","a","a",new Date(),true,"aa",new Content("s","s","d","s","s",3,new Thumbnail("t","t"))));
        Mockito.when(msnUKClient.getRssFeed()).thenReturn(provided);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/triggerMsnUK"))
                .andExpect(status().isOk());
        Thread.sleep(1000);
        Mockito.verify(msnUKClient, Mockito.times(1)).getRssFeed();
        Mockito.verify(msnUKRepository, Mockito.times(1)).save(ItemMapper.mapMsnUKItemToDBItem(provided.get(0)));
        Mockito.verify(msnUKRepository, Mockito.times(1)).save(ItemMapper.mapMsnUKItemToDBItem(provided.get(1)));
    }

    @Test
    public void triggerMsnUS_happy() throws Exception {
        List<MsnUSItem> provided= Arrays.asList(new MsnUSItem("abb","a","a",new Date(),"a","a","a",new Date(),true,"aa",new Content("s","s","d","s","s",3,new Thumbnail("t","t"))),new MsnUSItem("a","a","a",new Date(),"a","a","a",new Date(),true,"aa",new Content("s","s","d","s","s",3,new Thumbnail("t","t"))));
        Mockito.when(msnUSClient.getRssFeed()).thenReturn(provided);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/triggerMsnUS"))
                .andExpect(status().isOk());
        Thread.sleep(1000);
        Mockito.verify(msnUSClient, Mockito.times(1)).getRssFeed();
        Mockito.verify(msnUSRepository, Mockito.times(1)).save(ItemMapper.mapMsnUSItemToDBItem(provided.get(0)));
        Mockito.verify(msnUSRepository, Mockito.times(1)).save(ItemMapper.mapMsnUSItemToDBItem(provided.get(1)));
    }

}
