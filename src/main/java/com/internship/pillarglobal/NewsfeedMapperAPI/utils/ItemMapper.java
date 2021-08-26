package com.internship.pillarglobal.NewsfeedMapperAPI.utils;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.internship.pillarglobal.NewsfeedMapperAPI.models.YahooUKItem;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ItemMapper {
    public static List<YahooUKItem> getItemsList(String data) throws IOException {
        log.info(String.valueOf(data.indexOf("<item>")));
        int beginIndex = data.indexOf("<item>");
        int endIndex = data.indexOf("</channel></rss>");
        data = data.substring(beginIndex, endIndex);
        data = data.replaceAll("<dc:creator>", "<dc_creator>");
        data = data.replaceAll("</dc:creator>", "</dc_creator>");
        data = data.replaceAll("<content:encoded>", "<content_encoded>");
        data = data.replaceAll("</content:encoded>", "</content_encoded>");

        int firstIndex = 0;
        int lastIndex = data.indexOf("</item>");
        String itemAsXml = data.substring(firstIndex, lastIndex+7);
        List<YahooUKItem> itemsList = new ArrayList<YahooUKItem>();
        while (lastIndex <= data.length() - 6) {
            XmlMapper xmlMapper = new XmlMapper();
            YahooUKItem value
                    = xmlMapper.readValue(itemAsXml, YahooUKItem.class);
            itemsList.add(value);
            firstIndex = lastIndex + 8;
            if(firstIndex<data.length()-1) {
                lastIndex = data.indexOf("</item>", firstIndex);
                itemAsXml = "";
                itemAsXml = data.substring(firstIndex, lastIndex+7);
            }
            else lastIndex=data.length()+100;
        }

        return itemsList;
    }
}
