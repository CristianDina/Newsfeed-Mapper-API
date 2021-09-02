package com.internship.pillarglobal.NewsfeedMapperAPI.models;

public abstract class YahooItem implements Item{
    public  String getModifiedData(String data) {
        data = data.replaceAll("<dc:creator>", "<dc_creator>");
        data = data.replaceAll("</dc:creator>", "</dc_creator>");
        data = data.replaceAll("<content:encoded>", "<content_encoded>");
        data = data.replaceAll("</content:encoded>", "</content_encoded>");
        return data;
    }
}
