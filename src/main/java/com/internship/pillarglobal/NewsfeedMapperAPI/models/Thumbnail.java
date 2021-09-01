package com.internship.pillarglobal.NewsfeedMapperAPI.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.Embeddable;


@JacksonXmlRootElement(localName = "media_thumbnail")
public class Thumbnail {
    @JacksonXmlProperty(isAttribute=true)
    private String url;
    @JacksonXmlProperty(isAttribute=true)
    private String type;
}
