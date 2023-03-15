package kz.iitu.itse1903.abimoldayeva.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.iitu.itse1903.abimoldayeva.model.State;
import kz.iitu.itse1903.abimoldayeva.model.Ticket;
import kz.iitu.itse1903.abimoldayeva.model.dto.TicketDTO;
import kz.iitu.itse1903.abimoldayeva.service.TicketService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @Autowired
    private TicketService ticketService;

    @SneakyThrows
    @KafkaListener(topics = "booking", groupId = "group_id")
    public void consumeMessage(String ticket){
        TicketDTO ticket1 = new ObjectMapper().readValue(ticket, TicketDTO.class);
        ticket1.setState(State.ACTIVE);
        Ticket ticket2 = ticketService.update(ticket1.getTicketId(), ticket1);

        System.out.println("ticket" + ticket2.getState());
    }
}
