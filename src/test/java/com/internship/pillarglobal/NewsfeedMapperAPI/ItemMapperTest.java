package com.internship.pillarglobal.NewsfeedMapperAPI;

import com.internship.pillarglobal.NewsfeedMapperAPI.models.*;
import com.internship.pillarglobal.NewsfeedMapperAPI.utils.ItemMapper;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.*;

import static javax.xml.bind.DatatypeConverter.parseDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemMapperTest {

    @Test
    public void mapMsnUKItemToDBItem() {
        MsnUKItem provided = new MsnUKItem("a", "a", "a", new Date(), "a", "a", "a", new Date(), true, "aa", new Content("s", "s", "d", "s", "s", 3, new Thumbnail("t", "t")));
        MsnUKItemForDB expected = new MsnUKItemForDB(provided.getTitle(), provided.getLink(), provided.getGuid(), provided.getPubDate(), provided.getDc_creator(), provided.getDc_abstract(), provided.getDc_publisher(), provided.getDc_modified(), provided.isDc_premium(), provided.getDescription(), provided.getMedia_content().getUrl(), provided.getMedia_content().getUrl(), provided.getMedia_content().getMedia_thumbnail().getUrl(), provided.getMedia_content().getMedia_thumbnail().getType(), provided.getMedia_content().getMedia_credit(), provided.getMedia_content().getMedia_title(), provided.getMedia_content().getMedia_text(), provided.getMedia_content().getMi_hasSyndicationRights());
        MsnUKItemForDB result = ItemMapper.mapMsnUKItemToDBItem(provided);
        assertEquals( expected,result);
    }

    @Test
    public void mapMsnUSItemToDBItem() {
        MsnUSItem provided = new MsnUSItem("a", "a", "a", new Date(), "a", "a", "a", new Date(), true, "aa", new Content("s", "s", "d", "s", "s", 3, new Thumbnail("t", "t")));
        MsnUSItemForDB expected = new MsnUSItemForDB(provided.getTitle(), provided.getLink(), provided.getGuid(), provided.getPubDate(), provided.getDc_creator(), provided.getDc_abstract(), provided.getDc_publisher(), provided.getDc_modified(), provided.isDc_premium(), provided.getDescription(), provided.getMedia_content().getUrl(), provided.getMedia_content().getUrl(), provided.getMedia_content().getMedia_thumbnail().getUrl(), provided.getMedia_content().getMedia_thumbnail().getType(), provided.getMedia_content().getMedia_credit(), provided.getMedia_content().getMedia_title(), provided.getMedia_content().getMedia_text(), provided.getMedia_content().getMi_hasSyndicationRights());
        MsnUSItemForDB result = ItemMapper.mapMsnUSItemToDBItem(provided);
        assertEquals(result, expected);
    }

    @Test
    public void getListMsnUS() throws Exception {
        String provided=TestUtil.readFileAsString("src/test/resources/msnStringItems.txt");
        List<MsnUSItem> expected=new ArrayList<>();
        List<MsnUSItem> result=ItemMapper.getListMsnUS(provided);
        Calendar calendar=parseDate("2021-09-01T23:31:28");
        calendar.add(11, ZonedDateTime.now().getOffset().getTotalSeconds()/3600);
        Date date1 = calendar.getTime();
        calendar=parseDate("2021-09-02T11:44:39");
        calendar.add(11, ZonedDateTime.now().getOffset().getTotalSeconds()/3600);
        Date date2 = calendar.getTime();
        expected.add(new MsnUSItem("a",
                "a",
                "a",
                date1,
                "a",
                "a",
                "a",
                date2,
                true,
                "a", null
        ));
        assertEquals(result,expected);
    }

    @Test
    public void getListMsnUK() throws Exception {
        String provided=TestUtil.readFileAsString("src/test/resources/msnStringItems.txt");
        List<MsnUKItem> expected=new ArrayList<>();
        List<MsnUKItem> result=ItemMapper.getListMsnUK(provided);
        Calendar calendar=parseDate("2021-09-01T23:31:28");
        calendar.add(11, ZonedDateTime.now().getOffset().getTotalSeconds()/3600);
        Date date1 = calendar.getTime();
        calendar=parseDate("2021-09-02T11:44:39");
        calendar.add(11, ZonedDateTime.now().getOffset().getTotalSeconds()/3600);
        Date date2 = calendar.getTime();

        expected.add(new MsnUKItem("a",
                "a",
                "a",
                date1,
                "a",
                "a",
                "a",
                date2,
                true,
                "a", null
        ));
        assertEquals(result,expected);
    }
    @Test
    public void getListYahooUK() throws Exception {
        String provided=TestUtil.readFileAsString("src/test/resources/yahooStringItems.txt");
        List<YahooUKItem> expected=new ArrayList<>();
        List<YahooUKItem> result=ItemMapper.getListYahooUK(provided);
        Calendar calendar=parseDate("2021-09-01T23:31:28");
        calendar.add(11, ZonedDateTime.now().getOffset().getTotalSeconds()/3600);
        Date date = calendar.getTime();

        expected.add(new YahooUKItem("a",
                "a",
                date,
                true,
                "a",
                "a",
                "a",
                "a",
                "a"
        ));
        assertEquals(result,expected);
    }

    @Test
    public void getListYahooUS() throws Exception {
        String provided=TestUtil.readFileAsString("src/test/resources/yahooStringItems.txt");
        List<YahooUSItem> expected=new ArrayList<>();
        List<YahooUSItem> result=ItemMapper.getListYahooUS(provided);
        Calendar calendar=parseDate("2021-09-01T23:31:28");
        calendar.add(11, ZonedDateTime.now().getOffset().getTotalSeconds()/3600);
        Date date = calendar.getTime();

        expected.add(new YahooUSItem("a",
                "a",
                date,
                true,
                "a",
                "a",
                "a",
                "a",
                "a"
        ));
        assertEquals(result,expected);
    }
}
