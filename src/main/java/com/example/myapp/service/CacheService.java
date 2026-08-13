package com.example.myapp.service;

import com.example.myapp.model.CachedItem;
import org.openspaces.core.GigaSpace;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    private final GigaSpace gigaSpace;

    public CacheService(GigaSpace gigaSpace) {
        this.gigaSpace = gigaSpace;
    }

    public void writeToCache(String id, String value) {
        CachedItem item = new CachedItem(id, value);
        gigaSpace.write(item);
    }

    public String readFromCache(String id) {
        CachedItem template = new CachedItem();
        template.setId(id);
        CachedItem item = gigaSpace.read(template);
        return item != null ? item.getValue() : null;
    }
}
