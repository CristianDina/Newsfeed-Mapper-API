package com.internship.pillarglobal.NewsfeedMapperAPI.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="yahoo_uk_items",schema = "newsfeed_api_db_schema")


public class YahooUKItem {
    @Column(name = "title")
    private String title;
    @Column(name = "link")
    private String link;
    @Column(name = "pubDate")
    private Date pubDate;
    @Column(name = "premium")
    private boolean premium;
    @Id
    @Column(name = "guid")

    private String guid;
    @Column(name = "dc_creator")
    private String dc_creator;
    @Column(length = 300000)
    private String content_encoded;
    private String description;
    private String category;

}
