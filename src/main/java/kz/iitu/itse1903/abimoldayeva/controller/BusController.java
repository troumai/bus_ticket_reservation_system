package kz.iitu.itse1903.abimoldayeva.controller;

import kz.iitu.itse1903.abimoldayeva.model.Bus;
import kz.iitu.itse1903.abimoldayeva.model.User;
import kz.iitu.itse1903.abimoldayeva.service.BusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bus")
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class BusController {
    private final BusService busService;


    @PostMapping("/")
    @ResponseStatus
    public ResponseEntity<Bus> createBus(@AuthenticationPrincipal User user, @RequestBody Bus bus){
        try{
            Bus createdBus = busService.save(new Bus(bus.getRegisterNumber(), bus.getSeatsNumber()));
            return new ResponseEntity(createdBus, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus
    public ResponseEntity<Bus> getBusById(@AuthenticationPrincipal User user, @PathVariable("id") long id){
        Bus bus = busService.getBusById(id);
        System.out.println(user.getFullName());
        return ResponseEntity.ok(bus);
    }

    @GetMapping("/")
    @ResponseStatus
    @PreAuthorize("hasAuthority('DRIVER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<Bus>> getBusList(){
        List<Bus> buses = busService.getAllBuses();
        return ResponseEntity.ok(buses);
    }

    @PutMapping("/{id}")
    @ResponseStatus
    public ResponseEntity<Bus> updateBus(@PathVariable("id") long id, @AuthenticationPrincipal User user, @RequestBody Bus bus){
        try{
            Bus createdBus = busService.updateBus(id, bus);
            return new ResponseEntity(createdBus, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus
    public ResponseEntity<String> deleteBus(@PathVariable("id") long id){
        try{
            busService.deleteBus(id);
            return new ResponseEntity<>("Bus with id: " + id + " successfully deleted", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
