package kz.iitu.itse1903.abimoldayeva.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.iitu.itse1903.abimoldayeva.model.Ticket;
import kz.iitu.itse1903.abimoldayeva.model.dto.ApproveDTO;
import kz.iitu.itse1903.abimoldayeva.model.dto.TicketDTO;
import lombok.SneakyThrows;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @SneakyThrows
    public void sendMessage(TicketDTO ticket){
        System.out.println("sending message");
        kafkaTemplate.send("booking", new ObjectMapper().writeValueAsString(ticket));
    }

    @Bean
    public NewTopic createTopic(){
        System.out.println("creating topic");
        return  new NewTopic("booking", 1, (short) 1);
    }
}
