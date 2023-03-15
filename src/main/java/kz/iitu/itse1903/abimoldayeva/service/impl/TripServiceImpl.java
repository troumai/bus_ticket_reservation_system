package kz.iitu.itse1903.abimoldayeva.service.impl;

import kz.iitu.itse1903.abimoldayeva.model.BusRoute;
import kz.iitu.itse1903.abimoldayeva.model.State;
import kz.iitu.itse1903.abimoldayeva.model.Trip;
import kz.iitu.itse1903.abimoldayeva.model.dto.TripDTO;
import kz.iitu.itse1903.abimoldayeva.repository.BusRouteRepository;
import kz.iitu.itse1903.abimoldayeva.repository.TripRepository;
import kz.iitu.itse1903.abimoldayeva.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;
    private final BusRouteRepository busRouteRepository;

    @Override
    @Transactional
    public Trip save(TripDTO tripDTO) {
        BusRoute busRoute = busRouteRepository.findById(tripDTO.getRouteId()).get();
        Trip trip = new Trip(tripDTO.getDestination(), tripDTO.getPrice(), busRoute,
                tripDTO.getArrivalDate(), tripDTO.getDepartureDate());
        trip.setState(State.ACTIVE);
        return tripRepository.save(trip);
    }

    @Override
    public Trip getById(Long id) {
        return tripRepository.findById(id).get();
    }

    @Override
    public List<Trip> getAll() {
        return tripRepository.findAll();
    }

    @Override
    public List<Trip> getActiveTrips() {
        List<Trip> activeTrip = tripRepository.findTripsByState(State.ACTIVE);
        return activeTrip;
    }

    @Override
    @Scheduled(fixedDelay = (1000 * 60 * 24))
    @Transactional
    public void inactivateTrips() {
        List<Trip> trips = tripRepository.findAll();
        for (Trip trip: trips){
            if (trip.getArrivalDate().isBefore(LocalDate.now())){
                trip.setState(State.INACTIVE);
            }else {
                trip.setState(State.ACTIVE);
            }
        }
        System.out.println("Status changing...");
    }

    @Override
    @Transactional
    public Trip update(Long id, Trip trip) {
        Trip updatedTrip = tripRepository.getById(id);

        updatedTrip.setDestination(trip.getDestination());
        updatedTrip.setBusRoute(trip.getBusRoute());
        updatedTrip.setTickets(trip.getTickets());
        updatedTrip.setPrice(trip.getPrice());
        updatedTrip.setArrivalDate(trip.getArrivalDate());
        updatedTrip.setDepartureDate(trip.getDepartureDate());

        return tripRepository.save(updatedTrip);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tripRepository.deleteTripById(id);
    }
}
