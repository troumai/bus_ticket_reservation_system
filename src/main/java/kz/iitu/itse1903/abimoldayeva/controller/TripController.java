package kz.iitu.itse1903.abimoldayeva.controller;

import kz.iitu.itse1903.abimoldayeva.model.Bus;
import kz.iitu.itse1903.abimoldayeva.model.Trip;
import kz.iitu.itse1903.abimoldayeva.model.User;
import kz.iitu.itse1903.abimoldayeva.model.dto.TripDTO;
import kz.iitu.itse1903.abimoldayeva.service.TripService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequestMapping(value = "/trips")
@Slf4j
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;

    @PostMapping("/")
    @ResponseStatus
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Trip> createBus(@AuthenticationPrincipal User user, @RequestBody TripDTO tripDTO){
        try{
            Trip createdTrip = tripService.save(tripDTO);
            return new ResponseEntity(createdTrip, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus
    @PermitAll
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Trip> getTripById(@AuthenticationPrincipal User user, @PathVariable("id") long id){
        Trip trip = tripService.getById(id);
        return ResponseEntity.ok(trip);
    }
//
    @GetMapping("/")
    @ResponseStatus
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Trip>> getAllTrips(){
        List<Trip> trips = tripService.getAll();
        return ResponseEntity.ok(trips);
    }
    @GetMapping("/active")
    @ResponseStatus
    public ResponseEntity<List<Trip>> getActiveTrips(){
        List<Trip> trips = tripService.getActiveTrips();
        return ResponseEntity.ok(trips);
    }

    @PutMapping("/{id}")
    @ResponseStatus
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Trip> updateTrip(@PathVariable("id") long id, @AuthenticationPrincipal User user, @RequestBody Trip trip){
        try{
            Trip createdTrip = tripService.update(id, trip);
            return new ResponseEntity(createdTrip, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteTrip(@PathVariable("id") long id){
        try{
            tripService.delete(id);
            return new ResponseEntity<>("Trip with id: " + id + " successfully deleted", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
