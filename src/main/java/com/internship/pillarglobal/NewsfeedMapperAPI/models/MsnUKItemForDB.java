package com.internship.pillarglobal.NewsfeedMapperAPI.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
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
    private String media_content;
}
