package com.example.elena.eden.DATA;


import com.example.elena.eden.ItemMenu.ItemMenuStructure;

import java.util.ArrayList;

public class DataApp {
    static public ArrayList<ItemMenuStructure> LISTDATA;
    static public String HOST = "http://192.168.1.109:7777/";
    static public String REST_USER_POST = HOST + "api/vo1.0/propiedad";
    static public String REST_USERIMG_POST = HOST + "api/vo1.0/propiedadimg";
    static public String REST_HOME_PATCH = HOST + "api/v1.0/propiedad";
    static public String REST_VECINDARIO_POST = HOST + "api/vo1.0/vecindario";
    static public String REST_CORDENADAS = HOST + "api/vo1.0/sendcoloords";
}
