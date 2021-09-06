package com.internship.pillarglobal.NewsfeedMapperAPI;

import com.internship.pillarglobal.NewsfeedMapperAPI.clients.MsnUKClient;
import com.internship.pillarglobal.NewsfeedMapperAPI.models.Content;
import com.internship.pillarglobal.NewsfeedMapperAPI.models.MsnUKItem;
import com.internship.pillarglobal.NewsfeedMapperAPI.models.Thumbnail;
import com.internship.pillarglobal.NewsfeedMapperAPI.repositories.MsnUKRepository;
import com.internship.pillarglobal.NewsfeedMapperAPI.services.NewsFeedMapperService;
import com.internship.pillarglobal.NewsfeedMapperAPI.utils.ItemMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = NewsfeedMapperApiApplication.class)
public class NewsfeedMapperControllerTest {
    @MockBean
    private MsnUKClient msnUKClient;
    @MockBean
    private MsnUKRepository msnUKRepository;
    @Autowired
    private NewsFeedMapperService newsFeedMapperService;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void triggerYahooUK_happy() throws Exception {
        List<MsnUKItem> provided= Arrays.asList(new MsnUKItem("a","a","a",new Date(),"a","a","a",new Date(),true,"aa",new Content("s","s","d","s","s",3,new Thumbnail("t","t"))),new MsnUKItem("a","a","a",new Date(),"a","a","a",new Date(),true,"aa",new Content("s","s","d","s","s",3,new Thumbnail("t","t"))));
        Mockito.when(msnUKClient.getRssFeed()).thenReturn(provided);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/triggerYahooUK")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Mockito.verify(msnUKClient, Mockito.times(1)).getRssFeed();
        Mockito.verify(msnUKRepository, Mockito.times(1)).save(ItemMapper.mapMsnUKItemToDBItem(provided.get(0)));
        Mockito.verify(msnUKRepository, Mockito.times(1)).save(ItemMapper.mapMsnUKItemToDBItem(provided.get(1)));

    }

}
