package kz.iitu.itse1903.abimoldayeva.repository;

import kz.iitu.itse1903.abimoldayeva.model.Bus;
import kz.iitu.itse1903.abimoldayeva.model.Role;
import kz.iitu.itse1903.abimoldayeva.model.State;
import kz.iitu.itse1903.abimoldayeva.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    //Annotation based named query configuration
    Trip findByPrice(String email);

    List<Trip> findTripsByState(State state);

    void deleteTripById(Long id);
}
