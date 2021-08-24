package com.internship.pillarglobal.NewsfeedMapperAPI.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="yahoo_uk_items",schema = "newsfeed_api_db_schema")
@Data
public class YahooUKItem {
    private String title;
    private String link;
    private Date pubDate;
    private boolean premium;
    @Id
    private String guid;
    private String dc_creator;
    private String content;
    private String description;
    private String category;

}
