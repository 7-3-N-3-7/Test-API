package com.example.myapp.config;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.EmbeddedSpaceConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpaceConfig {

    @Bean
    public GigaSpace gigaSpace() {
        // Create an embedded space named "mySpace"
        // For production, you might want a UrlSpaceConfigurer connecting to a remote space
        EmbeddedSpaceConfigurer spaceConfigurer = new EmbeddedSpaceConfigurer("mySpace");
        return new GigaSpaceConfigurer(spaceConfigurer).gigaSpace();
    }
}
