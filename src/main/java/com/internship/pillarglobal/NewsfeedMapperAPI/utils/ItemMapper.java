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
        MsnUKItemForDB msnUKItemForDB = new MsnUKItemForDB();

        msnUKItemForDB.setGuid(item.getGuid());
        msnUKItemForDB.setPubDate(item.getPubDate());
        msnUKItemForDB.setDc_creator(item.getDc_creator());
        msnUKItemForDB.setDc_abstract(item.getDc_abstract());
        msnUKItemForDB.setDc_publisher(item.getDc_publisher());
        msnUKItemForDB.setDc_modified(item.getDc_modified());
        msnUKItemForDB.setDc_premium(item.isDc_premium());
        msnUKItemForDB.setDescription(item.getDescription());
        if (item.getMedia_content() != null) {
            msnUKItemForDB.setMedia_content_url(item.getMedia_content().getUrl());
            msnUKItemForDB.setMedia_content_type(item.getMedia_content().getType());
            msnUKItemForDB.setMedia_thumbnail_url(item.getMedia_content().getMedia_thumbnail().getUrl());
            msnUKItemForDB.setMedia_thumbnail_type(item.getMedia_content().getMedia_thumbnail().getType());
            msnUKItemForDB.setMedia_credit(item.getMedia_content().getMedia_credit());
            msnUKItemForDB.setMedia_title(item.getMedia_content().getMedia_title());
            msnUKItemForDB.setMedia_text(item.getMedia_content().getMedia_text());
            msnUKItemForDB.setMi_hasSyndicationRights(item.getMedia_content().getMi_hasSyndicationRights());
        }
        return msnUKItemForDB;
    }


    public static MsnUSItemForDB getMsnDBUS(MsnUSItem item) {
        MsnUSItemForDB msnUSItemForDB = new MsnUSItemForDB();

        msnUSItemForDB.setGuid(item.getGuid());
        msnUSItemForDB.setPubDate(item.getPubDate());
        msnUSItemForDB.setDc_creator(item.getDc_creator());
        msnUSItemForDB.setDc_abstract(item.getDc_abstract());
        msnUSItemForDB.setDc_publisher(item.getDc_publisher());
        msnUSItemForDB.setDc_modified(item.getDc_modified());
        msnUSItemForDB.setDc_premium(item.isDc_premium());
        msnUSItemForDB.setDescription(item.getDescription());
        if (item.getMedia_content() != null) {
            msnUSItemForDB.setMedia_content_url(item.getMedia_content().getUrl());
            msnUSItemForDB.setMedia_content_type(item.getMedia_content().getType());
            msnUSItemForDB.setMedia_thumbnail_url(item.getMedia_content().getMedia_thumbnail().getUrl());
            msnUSItemForDB.setMedia_thumbnail_type(item.getMedia_content().getMedia_thumbnail().getType());
            msnUSItemForDB.setMedia_credit(item.getMedia_content().getMedia_credit());
            msnUSItemForDB.setMedia_title(item.getMedia_content().getMedia_title());
            msnUSItemForDB.setMedia_text(item.getMedia_content().getMedia_text());
            msnUSItemForDB.setMi_hasSyndicationRights(item.getMedia_content().getMi_hasSyndicationRights());
        }
        return msnUSItemForDB;
    }
}
