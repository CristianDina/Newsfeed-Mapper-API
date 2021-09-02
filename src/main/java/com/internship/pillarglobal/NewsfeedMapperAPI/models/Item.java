package com.internship.pillarglobal.NewsfeedMapperAPI.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public interface Item {
    public  String getModifiedData(String data);
    public Item getValue(String itemAsXml, XmlMapper xmlMapper) throws JsonProcessingException;
}
