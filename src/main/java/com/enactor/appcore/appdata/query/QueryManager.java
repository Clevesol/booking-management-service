package com.enactor.appcore.appdata.query;

import com.enactor.appcore.util.BaseSingleton;

public class QueryManager extends BaseSingleton {


    public static QueryManager getInstance() {
        return BaseSingleton.getInstance(QueryManager.class);
    }
}
