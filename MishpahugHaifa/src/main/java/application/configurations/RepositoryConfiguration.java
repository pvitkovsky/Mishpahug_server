package application.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import application.configurations.eventhandlers.PersistenceEventHandler;

@Configuration
public class RepositoryConfiguration{
     
    @Bean
    PersistenceEventHandler persistenceEventHandler(){
        return new PersistenceEventHandler();
    }
    
}