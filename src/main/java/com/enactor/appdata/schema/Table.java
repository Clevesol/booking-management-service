package com.enactor.appdata.schema;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Table {

    private final ConcurrentHashMap<UUID, DBRow> table;

    public Table(){
        this.table = new ConcurrentHashMap<UUID, DBRow>();
    }

    public void insert(UUID uuid, DBRow row){
        this.table.put(uuid,row);
    }

    public DBRow getById(UUID uuid){
        return this.table.get(uuid);
    }


}
