package com.projet.demo.config;

import com.projet.demo.Entity.Jewelry;
import com.projet.demo.filter.CORSFilter;
import com.projet.demo.service.JewelryService;
import com.projet.demo.service.UserService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    @Bean
    public ResourceConfig resourceConfig(){
        ResourceConfig resourceConfig= new ResourceConfig();
        resourceConfig.register(UserService.class);
        resourceConfig.register(JewelryService.class);
        resourceConfig.register(CORSFilter.class);
        return resourceConfig;
    }
}