package com.internship.pillarglobal.NewsfeedMapperAPI.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name="yahoo_uk_items",schema = "newsfeed_api_db_schema")
@AllArgsConstructor
@NoArgsConstructor
public class YahooUKItem {


    private String title;
    private String link;
    private Date pubDate;
    private boolean premium;
    @Id
    private String guid;
    private String dc_creator;
    @Column(length = 100000)
    private String content_encoded;
    @Column(length = 5000)
    private String description;
    private String category;


}
