package com.enactor.appdata.schema;

import java.util.concurrent.ConcurrentHashMap;

public class DataSource {

    private static DataSource dataSource;

    private final ConcurrentHashMap<String, Table> dataBase;

    public DataSource(){
        this.dataBase = new ConcurrentHashMap<>();
    }


    public static DataSource getInstance(){
        if(dataSource == null) dataSource = new DataSource();
        return dataSource;
    }


}
