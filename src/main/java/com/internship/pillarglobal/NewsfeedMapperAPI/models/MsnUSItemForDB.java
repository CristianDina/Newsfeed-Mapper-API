package com.internship.pillarglobal.NewsfeedMapperAPI.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="msn_us_items",schema = "newsfeed_api_db_schema")
public class MsnUSItemForDB {
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
    private String media_thumbnail_type;
    private String media_credit;
    @Column(length = 100000)
    private String media_title;
    @Column(length = 100000)
    private String media_text;
    private int mi_hasSyndicationRights;

}
