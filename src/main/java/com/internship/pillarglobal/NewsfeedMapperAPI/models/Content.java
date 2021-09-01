package com.internship.pillarglobal.NewsfeedMapperAPI.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JacksonXmlRootElement(localName= "media_content")
public class Content {
    @JacksonXmlProperty(isAttribute = true)
    private String url;
    @JacksonXmlProperty(isAttribute = true)
    private String type;
    @JacksonXmlProperty(localName = "media_credit")
    private String media_credit;
    @JacksonXmlProperty(localName = "media_title")
    private String media_title;
    @JacksonXmlProperty(localName = "media_text")
    private String media_text;
    @JacksonXmlProperty(localName = "mi_hasSyndicationRights")
    private int mi_hasSyndicationRights;

    @JacksonXmlProperty(localName = "media_thumbnail")
    @JacksonXmlElementWrapper(localName = "media_thumbnail", useWrapping = false)
    private Thumbnail media_thumbnail;

}
