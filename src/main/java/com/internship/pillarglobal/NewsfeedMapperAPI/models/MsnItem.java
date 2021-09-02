package com.internship.pillarglobal.NewsfeedMapperAPI.models;

public abstract class MsnItem implements Item {
    public String getModifiedData(String data) {
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

