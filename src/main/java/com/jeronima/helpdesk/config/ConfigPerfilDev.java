package com.jeronima.helpdesk.config;

import com.jeronima.helpdesk.service.DBService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class ConfigPerfilDev {

    private final DBService dbService;

    public ConfigPerfilDev(DBService dbService) {
        this.dbService = dbService;
    }

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String value;

    @Bean
    public void instanciaDB(){
        if(value.equals("create")){
            this.dbService.instanciaDB();
        }
    }
}
