package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.model.Ticket;
import kz.iitu.itse1903.abimoldayeva.model.Trip;
import kz.iitu.itse1903.abimoldayeva.model.dto.TripDTO;

import java.util.List;

public interface TripService {
    Trip save(TripDTO tripDTO);
    Trip getById(Long id);
    List<Trip> getAll();
    Trip update(Long id, Trip ticket);
    void delete(Long id);
    List<Trip> getActiveTrips();
    void inactivateTrips();
}
