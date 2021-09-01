package com.internship.pillarglobal.NewsfeedMapperAPI.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Thumbnail {
    @JacksonXmlProperty(isAttribute=true)
    private String url;
    @JacksonXmlProperty(isAttribute=true)
    private String type;
}
