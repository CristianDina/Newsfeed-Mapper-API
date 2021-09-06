package com.internship.pillarglobal.NewsfeedMapperAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internship.pillarglobal.NewsfeedMapperAPI.models.MsnUSItem;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static javax.xml.bind.DatatypeConverter.parseDate;

public class TestUtil {
    public static String readFileAsString(String file)throws Exception
    {

        return (new String(Files.readAllBytes(Paths.get(file))).replaceAll(" ","").replaceAll("\n",""));
    }

}
