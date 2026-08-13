package com.example.myapp.model;

import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceClass;

@SpaceClass
public class CachedItem {

    private String id;
    private String value;

    public CachedItem() {
    }

    public CachedItem(String id, String value) {
        this.id = id;
        this.value = value;
    }

    @SpaceId(autoGenerate = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
