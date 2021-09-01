package com.internship.pillarglobal.NewsfeedMapperAPI.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="msn_uk_items",schema = "newsfeed_api_db_schema")
public class MsnUKItemForDB {

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
    @Column(length = 100000)
    private String description;
    private String media_content_url;
    private String media_content_type;
    private String media_thumbnail_url;
    private String getMedia_thumbnail_type;
    private String media_credit;
    private String media_title;
    private String media_text;
    private int mi_hasSyndicationRights;

}
