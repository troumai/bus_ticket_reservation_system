package kz.iitu.itse1903.abimoldayeva.controller;

import kz.iitu.itse1903.abimoldayeva.model.Ticket;
import kz.iitu.itse1903.abimoldayeva.model.User;
import kz.iitu.itse1903.abimoldayeva.model.dto.ApproveDTO;
import kz.iitu.itse1903.abimoldayeva.model.dto.TicketDTO;
import kz.iitu.itse1903.abimoldayeva.service.impl.KafkaProducerService;
import kz.iitu.itse1903.abimoldayeva.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/ticket")
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('DRIVER') or hasAuthority('USER')")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/buy")
    @ResponseStatus
    public ResponseEntity<Ticket> createTicket(@AuthenticationPrincipal User user, @RequestBody TicketDTO ticketDTO){
        try{
            Ticket ticket = ticketService.save(ticketDTO, user.getId());
            return new ResponseEntity(ticket, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/permit")
    @ResponseStatus
    public ResponseEntity<String> permitTicket(@AuthenticationPrincipal User user, @RequestBody ApproveDTO approveDTO){
        try{
            if (user.getFullName().equals(approveDTO.getUserName())){
                ticketService.permitBooking(approveDTO, user.getId());
                return new ResponseEntity("booking permitted", HttpStatus.CREATED);
            }
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
