package com.internship.pillarglobal.NewsfeedMapperAPI.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseUtil {
    private static String dataBaseUrl="jdbc:mysql://newsfeed-api-db.cqqdvxu7dakv.eu-central-1.rds.amazonaws.com/newsfeed_api_db_schema";
    private static String user = "admin";
    private static String password = "admin123";
    public static void verifyConnection() throws SQLException {
         DriverManager.getConnection(dataBaseUrl,user,password);
    }
}
