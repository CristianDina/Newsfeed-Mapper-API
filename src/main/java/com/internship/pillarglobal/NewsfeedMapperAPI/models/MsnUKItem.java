package com.internship.pillarglobal.NewsfeedMapperAPI.models;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@JacksonXmlRootElement(localName = "item")
public class MsnUKItem extends MsnItem{
    private String title;
    private String link;
    @Id
    private String guid;
    private Date pubDate;
    private String dc_creator;
    private String dc_abstract;
    private String dc_publisher;
    private Date dc_modified;
    private boolean dc_premium;

    private String description;
    @JacksonXmlProperty(localName = "media_content")
    @JacksonXmlElementWrapper(localName = "media_content", useWrapping = false)
    private Content media_content;

    @Override
    public Item getValue(String itemAsXml, XmlMapper xmlMapper) throws JsonProcessingException {
        return xmlMapper.readValue(itemAsXml,MsnUKItem.class);

    }

}
