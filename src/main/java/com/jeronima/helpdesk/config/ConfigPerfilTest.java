package com.jeronima.helpdesk.config;

import com.jeronima.helpdesk.service.DBService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class ConfigPerfilTest {

    private final DBService dbService;

    public ConfigPerfilTest(DBService dbService) {
        this.dbService = dbService;
    }

    @Bean
    public void instanciaDB(){
        this.dbService.instanciaDB();
    }
}
