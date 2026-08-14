package com.example.myapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CachedItemTest {

    @Test
    public void testEmptyConstructorAndSetters() {
        CachedItem item = new CachedItem();
        assertNull(item.getId());
        assertNull(item.getValue());

        item.setId("key1");
        item.setValue("val1");

        assertEquals("key1", item.getId());
        assertEquals("val1", item.getValue());
    }

    @Test
    public void testParameterizedConstructor() {
        CachedItem item = new CachedItem("key2", "val2");
        assertEquals("key2", item.getId());
        assertEquals("val2", item.getValue());
    }
}
