package kz.iitu.itse1903.abimoldayeva.model.dto;

import kz.iitu.itse1903.abimoldayeva.model.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDTO {
    private Long tripId;
    private Long userId;
    private int seat;
    private State state;
    private Long ticketId;
}
