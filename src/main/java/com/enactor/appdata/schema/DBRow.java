package com.enactor.appdata.schema;

import java.util.HashMap;
import java.util.Map;

public class DBRow {

    private final Map<String, Object> rows;

    public DBRow(){
        this.rows = new HashMap<>();
    }

}
