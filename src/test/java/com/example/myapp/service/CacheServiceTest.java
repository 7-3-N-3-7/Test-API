package com.example.myapp.service;

import com.example.myapp.model.CachedItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.openspaces.core.GigaSpace;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CacheServiceTest {

    private GigaSpace gigaSpaceMock;
    private CacheService cacheService;

    @BeforeEach
    public void setUp() {
        gigaSpaceMock = mock(GigaSpace.class);
        cacheService = new CacheService(gigaSpaceMock);
    }

    @Test
    public void writeToCache_Success() {
        cacheService.writeToCache("id1", "val1");
        verify(gigaSpaceMock, times(1)).write(any(CachedItem.class));
    }

    @Test
    public void writeToCache_NullId() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            cacheService.writeToCache(null, "val");
        });
        assertEquals("ID cannot be null or empty", ex.getMessage());
    }

    @Test
    public void writeToCache_EmptyId() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            cacheService.writeToCache("   ", "val");
        });
        assertEquals("ID cannot be null or empty", ex.getMessage());
    }

    @Test
    public void readFromCache_Success() {
        CachedItem mockItem = new CachedItem("id1", "val1");
        when(gigaSpaceMock.read(any(CachedItem.class))).thenReturn(mockItem);

        String result = cacheService.readFromCache("id1");
        assertEquals("val1", result);
    }

    @Test
    public void readFromCache_NotFound() {
        when(gigaSpaceMock.read(any(CachedItem.class))).thenReturn(null);

        String result = cacheService.readFromCache("id1");
        assertNull(result);
    }

    @Test
    public void readFromCache_NullId() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            cacheService.readFromCache(null);
        });
        assertEquals("ID cannot be null or empty", ex.getMessage());
    }

    @Test
    public void readFromCache_EmptyId() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            cacheService.readFromCache("");
        });
        assertEquals("ID cannot be null or empty", ex.getMessage());
    }

    @Test
    public void deleteFromCache_Success() {
        cacheService.deleteFromCache("id1");
        verify(gigaSpaceMock, times(1)).take(any(CachedItem.class));
    }

    @Test
    public void deleteFromCache_NullId() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            cacheService.deleteFromCache(null);
        });
        assertEquals("ID cannot be null or empty", ex.getMessage());
    }

    @Test
    public void deleteFromCache_EmptyId() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            cacheService.deleteFromCache(" ");
        });
        assertEquals("ID cannot be null or empty", ex.getMessage());
    }
}
