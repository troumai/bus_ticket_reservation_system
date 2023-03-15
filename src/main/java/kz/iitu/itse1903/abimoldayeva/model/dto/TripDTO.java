package kz.iitu.itse1903.abimoldayeva.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TripDTO {
    private String destination;
    private double price;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private Long routeId;
}
