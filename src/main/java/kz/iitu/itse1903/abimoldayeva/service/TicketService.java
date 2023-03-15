package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.model.Bus;
import kz.iitu.itse1903.abimoldayeva.model.Ticket;
import kz.iitu.itse1903.abimoldayeva.model.dto.ApproveDTO;
import kz.iitu.itse1903.abimoldayeva.model.dto.TicketDTO;

import java.util.List;

public interface TicketService {
    Ticket save(TicketDTO ticketDTO, Long id);
    Ticket getById(Long id);
    List<Ticket> getAll();
    Ticket update(Long id, TicketDTO ticketDTO);
    void delete(Long id);
    Ticket permitBooking(ApproveDTO approveDTO, Long id);
}
