package kz.iitu.itse1903.abimoldayeva.service.impl;

import kz.iitu.itse1903.abimoldayeva.model.State;
import kz.iitu.itse1903.abimoldayeva.model.Ticket;
import kz.iitu.itse1903.abimoldayeva.model.Trip;
import kz.iitu.itse1903.abimoldayeva.model.User;
import kz.iitu.itse1903.abimoldayeva.model.dto.ApproveDTO;
import kz.iitu.itse1903.abimoldayeva.model.dto.TicketDTO;
import kz.iitu.itse1903.abimoldayeva.repository.TicketRepository;
import kz.iitu.itse1903.abimoldayeva.repository.TripRepository;
import kz.iitu.itse1903.abimoldayeva.repository.UserRepository;
import kz.iitu.itse1903.abimoldayeva.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TripRepository tripRepository;
    private final KafkaProducerService kafkaProducerService;

    @Override
    @Transactional
    public Ticket save(TicketDTO ticketDTO, Long id) {
        User user = userRepository.findById(id).get();
        Trip trip = tripRepository.findById(ticketDTO.getTripId()).get();

        Ticket ticket = new Ticket(trip, ticketDTO.getSeat(), State.NEW, user );
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket getById(Long id) {
        return ticketRepository.getById(id);
    }

    @Override
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    @Override
    @Transactional
    public Ticket update(Long id, TicketDTO ticketDTO) {
        Ticket updatedTicket = ticketRepository.getById(id);
        Trip trip = tripRepository.findById(ticketDTO.getTripId()).get();
        updatedTicket.setTrip(trip);
        updatedTicket.setSeat(ticketDTO.getSeat());
        updatedTicket.setState(ticketDTO.getState());


        return ticketRepository.save(updatedTicket);
    }

    @Override
    @Transactional
    public Ticket permitBooking(ApproveDTO approveDTO, Long userId){
        Ticket ticket = ticketRepository.findById(approveDTO.getTicketId()).get();
            TicketDTO ticketDTO = new TicketDTO();
            ticketDTO.setTripId(ticket.getTrip().getId());
            ticketDTO.setState(ticket.getState());
            ticketDTO.setSeat(ticket.getSeat());
            ticketDTO.setUserId(userId);
            ticketDTO.setTicketId(ticket.getId());
            kafkaProducerService.sendMessage(ticketDTO);
            System.out.println("equals");
        return ticket;
    }

    @Override
    public void delete(Long id) {
        ticketRepository.deleteById(id);
    }
}
