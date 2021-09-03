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

    public static MsnUKItemForDB mapMsnUKItemToDBItem(MsnUKItem item) {
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


    public static MsnUSItemForDB mapMsnUSItemToDBItem(MsnUSItem item) {
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


    public static List<MsnUSItem> getListMsnUS(String data) throws JsonProcessingException {
        data = getItemsXmlAsString(data);
        data = getModifiedMsnData(data);
        int lastIndex = data.indexOf("</item>");
        String itemAsXml = data.substring(0, lastIndex + 7);
        List<MsnUSItem> itemsList = new ArrayList<>();
        while (lastIndex <= data.length() - 6) {
            XmlMapper xmlMapper = new XmlMapper();
            itemsList.add(xmlMapper.readValue(itemAsXml, MsnUSItem.class));
            int firstIndex = lastIndex + 8;
            if (firstIndex < data.length() - 1) {
                lastIndex = data.indexOf("</item>", firstIndex);
                itemAsXml = data.substring(firstIndex, lastIndex + 7);
            } else lastIndex = data.length() + 10;
        }
        return itemsList;
    }

    public static List<MsnUKItem> getListMsnUK(String data) throws JsonProcessingException {
        data = getItemsXmlAsString(data);
        data = getModifiedMsnData(data);
        int lastIndex = data.indexOf("</item>");
        String itemAsXml = data.substring(0, lastIndex + 7);
        List<MsnUKItem> itemsList = new ArrayList<>();
        while (lastIndex <= data.length() - 6) {
            XmlMapper xmlMapper = new XmlMapper();
            itemsList.add(xmlMapper.readValue(itemAsXml, MsnUKItem.class));
            int firstIndex = lastIndex + 8;
            if (firstIndex < data.length() - 1) {
                lastIndex = data.indexOf("</item>", firstIndex);
                itemAsXml = data.substring(firstIndex, lastIndex + 7);
            } else lastIndex = data.length() + 10;
        }
        return itemsList;
    }

    public static List<YahooUSItem> getListYahooUS(String data) throws JsonProcessingException {
        data = getItemsXmlAsString(data);
        data = getModifiedYahooData(data);
        int lastIndex = data.indexOf("</item>");
        String itemAsXml = data.substring(0, lastIndex + 7);
        List<YahooUSItem> itemsList = new ArrayList<>();
        while (lastIndex <= data.length() - 6) {
            XmlMapper xmlMapper = new XmlMapper();
            itemsList.add(xmlMapper.readValue(itemAsXml, YahooUSItem.class));
            int firstIndex = lastIndex + 8;
            if (firstIndex < data.length() - 1) {
                lastIndex = data.indexOf("</item>", firstIndex);
                itemAsXml = data.substring(firstIndex, lastIndex + 7);
            } else lastIndex = data.length() + 10;
        }
        return itemsList;
    }

    public static List<YahooUKItem> getListYahooUK(String data) throws JsonProcessingException {
        data = getItemsXmlAsString(data);
        data = getModifiedYahooData(data);
        int lastIndex = data.indexOf("</item>");
        String itemAsXml = data.substring(0, lastIndex + 7);
        List<YahooUKItem> itemsList = new ArrayList<>();
        while (lastIndex <= data.length() - 6) {
            XmlMapper xmlMapper = new XmlMapper();
            itemsList.add(xmlMapper.readValue(itemAsXml, YahooUKItem.class));
            int firstIndex = lastIndex + 8;
            if (firstIndex < data.length() - 1) {
                lastIndex = data.indexOf("</item>", firstIndex);
                itemAsXml = data.substring(firstIndex, lastIndex + 7);
            } else lastIndex = data.length() + 10;
        }
        return itemsList;
    }

    public static String getItemsXmlAsString(String data) {
        int beginIndex = data.indexOf("<item>");
        int endIndex = data.indexOf("</channel></rss>");
        data = data.substring(beginIndex, endIndex);
        return data;
    }


    private static String getModifiedYahooData(String data) {
        data = data.replaceAll("<dc:creator>", "<dc_creator>");
        data = data.replaceAll("</dc:creator>", "</dc_creator>");
        data = data.replaceAll("<content:encoded>", "<content_encoded>");
        data = data.replaceAll("</content:encoded>", "</content_encoded>");
        return data;
    }

    private static String getModifiedMsnData(String data) {
        data = data.replaceAll("<dc:creator>", "<dc_creator>");
        data = data.replaceAll("</dc:creator>", "</dc_creator>");

        data = data.replaceAll("<dc:abstract>", "<dc_abstract>");
        data = data.replaceAll("</dc:abstract>", "</dc_abstract>");

        data = data.replaceAll("<dc:publisher>", "<dc_publisher>");
        data = data.replaceAll("</dc:publisher>", "</dc_publisher>");

        data = data.replaceAll("<dc:modified>", "<dc_modified>");
        data = data.replaceAll("</dc:modified>", "</dc_modified>");

        data = data.replaceAll("<dc:premium>", "<dc_premium>");
        data = data.replaceAll("</dc:premium>", "</dc_premium>");

        data = data.replaceAll("<media:content", "<media_content");
        data = data.replaceAll("</media:content>", "</media_content>");

        data = data.replaceAll("<media:thumbnail", "<media_thumbnail");
        data = data.replaceAll("/>", "></media_thumbnail>");

        data = data.replaceAll("<media:credit>", "<media_credit>");
        data = data.replaceAll("</media:credit>", "</media_credit>");

        data = data.replaceAll("<media:text>", "<media_text>");
        data = data.replaceAll("</media:text>", "</media_text>");

        data = data.replaceAll("<media:title>", "<media_title>");
        data = data.replaceAll("</media:title>", "</media_title>");

        data = data.replaceAll("<mi:hasSyndicationRights>", "<mi_hasSyndicationRights>");
        data = data.replaceAll("</mi:hasSyndicationRights>", "</mi_hasSyndicationRights>");

        return data;
    }


}
