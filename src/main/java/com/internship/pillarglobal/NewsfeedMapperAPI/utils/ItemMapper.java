package com.internship.pillarglobal.NewsfeedMapperAPI.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.internship.pillarglobal.NewsfeedMapperAPI.clients.MsnUKClient;
import com.internship.pillarglobal.NewsfeedMapperAPI.models.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ItemMapper {
    public static List<? extends Item> getList(String data, Item item) throws JsonProcessingException {
        int beginIndex = data.indexOf("<item>");
        int endIndex = data.indexOf("</channel></rss>");
        data = data.substring(beginIndex, endIndex);
        data = item.getModifiedData(data);
        int firstIndex = 0;
        int lastIndex = data.indexOf("</item>");
        String itemAsXml = data.substring(firstIndex, lastIndex + 7);
        List<Item> itemsList = new ArrayList<>();
        while (lastIndex <= data.length() - 6) {
            XmlMapper xmlMapper = new XmlMapper();
            Item value = item.getValue(itemAsXml, xmlMapper);
            itemsList.add(value);
            firstIndex = lastIndex + 8;
            if (firstIndex < data.length() - 1) {
                lastIndex = data.indexOf("</item>", firstIndex);
                itemAsXml = "";
                itemAsXml = data.substring(firstIndex, lastIndex + 7);
            } else lastIndex = data.length() + 100;
        }
        log.info(String.valueOf(itemsList));
        return itemsList;
    }



    public static MsnUKItemForDB getMsnDB(MsnUKItem item) {
        return new MsnUKItemForDB(item.getTitle(),
                item.getLink(),
                item.getGuid(),
                item.getPubDate(),
                item.getDc_creator(),
                item.getDc_abstract(),
                item.getDc_publisher(),
                item.getDc_modified(),
                item.isDc_premium(),
                item.getDescription(),
                item.getMedia_content().getUrl(),
                item.getMedia_content().getType(),
                item.getMedia_content().getMedia_thumbnail().getUrl(),
                item.getMedia_content().getMedia_thumbnail().getType(),
                item.getMedia_content().getMedia_credit(),
                item.getMedia_content().getMedia_title(),
                item.getMedia_content().getMedia_text(),
                item.getMedia_content().getMi_hasSyndicationRights());
    }



    public static MsnUSItemForDB getMsnDBUS(MsnUSItem item) {
        return new MsnUSItemForDB(item.getTitle(),
                item.getLink(),
                item.getGuid(),
                item.getPubDate(),
                item.getDc_creator(),
                item.getDc_abstract(),
                item.getDc_publisher(),
                item.getDc_modified(),
                item.isDc_premium(),
                item.getDescription(),
                item.getMedia_content().getUrl(),
                item.getMedia_content().getType(),
                item.getMedia_content().getMedia_thumbnail().getUrl(),
                item.getMedia_content().getMedia_thumbnail().getType(),
                item.getMedia_content().getMedia_credit(),
                item.getMedia_content().getMedia_title(),
                item.getMedia_content().getMedia_text(),
                item.getMedia_content().getMi_hasSyndicationRights());
    }
}
