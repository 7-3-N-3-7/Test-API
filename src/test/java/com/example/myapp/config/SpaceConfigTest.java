package com.example.myapp.config;

import org.junit.jupiter.api.Test;
import org.openspaces.core.GigaSpace;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SpaceConfigTest {

    @Test
    public void testGigaSpaceBeanCreation() {
        SpaceConfig config = new SpaceConfig();
        GigaSpace gigaSpace = config.gigaSpace();
        
        assertNotNull(gigaSpace, "GigaSpace bean should not be null");
        assertNotNull(gigaSpace.getSpace(), "Underlying IJSpace should not be null");
    }
}
