package kz.iitu.itse1903.abimoldayeva.config;

import kz.iitu.itse1903.abimoldayeva.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@PropertySource("classpath:application.properties")
@EnableScheduling
public class AppConfig {
    @Bean(initMethod = "doInit", destroyMethod = "doDestroy", name = "bus")
    public Bus bus(){
        return new Bus();
    }

    @Bean(initMethod = "doInit", destroyMethod = "doDestroy", name = "user")
    public User user(){
        return new User();
    }

    @Bean(initMethod = "doInit", destroyMethod = "doDestroy", name = "busRoute")
    public BusRoute busRoute(){
        return new BusRoute();
    }

    @Bean(initMethod = "doInit", destroyMethod = "doDestroy", name = "ticket")
    public Ticket ticket(){
        return new Ticket();
    }

    @Bean(initMethod = "doInit", destroyMethod = "doDestroy", name = "trip")
    public Trip trip(){
        return new Trip();
    }

}
