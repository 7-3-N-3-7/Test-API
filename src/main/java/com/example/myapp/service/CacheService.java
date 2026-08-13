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
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        CachedItem item = new CachedItem(id, value);
        gigaSpace.write(item);
    }

    public String readFromCache(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        CachedItem template = new CachedItem();
        template.setId(id);
        CachedItem item = gigaSpace.read(template);
        return item != null ? item.getValue() : null;
    }

    public void deleteFromCache(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        CachedItem template = new CachedItem();
        template.setId(id);
        gigaSpace.take(template);
    }
}
