package kz.iitu.itse1903.abimoldayeva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration
@ComponentScan
@EnableTransactionManagement
@EnableCaching
@EnableKafka
@EnableJpaRepositories(basePackages = {"kz.iitu.itse1903.abimoldayeva.repository"})
@EntityScan(basePackageClasses = { BusTicketReservationSystemApplication.class, Jsr310JpaConverters.class })
public class BusTicketReservationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusTicketReservationSystemApplication.class, args);
    }

}
