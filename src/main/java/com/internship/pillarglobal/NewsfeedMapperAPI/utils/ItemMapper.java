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
    public static List<YahooUKItem> getItemsList(String data) throws IOException {
        //log.info(String.valueOf(data.indexOf("<item>")));
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
    public static List<YahooUSItem> getItemsListUS(String data) throws IOException {
        //log.info(String.valueOf(data.indexOf("<item>")));
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
        List<YahooUSItem> itemsList = new ArrayList<YahooUSItem>();
        while (lastIndex <= data.length() - 6) {
            XmlMapper xmlMapper = new XmlMapper();
            YahooUSItem value
                    = xmlMapper.readValue(itemAsXml, YahooUSItem.class);
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

    public static List getItemsListMsnUK(String data) throws IOException {
        int beginIndex = data.indexOf("<item>");
        int endIndex = data.indexOf("</channel></rss>");
        data = data.substring(beginIndex, endIndex);
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



        log.info(data.substring(0,200));
        int firstIndex = 0;
        int lastIndex = data.indexOf("</item>");
        String itemAsXml = data.substring(firstIndex, lastIndex+7);
        List<MsnUKItem> itemsList = new ArrayList<MsnUKItem>();
        while (lastIndex <= data.length() - 6) {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            MsnUKItem value
                    = xmlMapper.readValue(itemAsXml, MsnUKItem.class);
            log.info(String.valueOf(value));
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
}
