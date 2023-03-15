package kz.iitu.itse1903.abimoldayeva.controller;

import kz.iitu.itse1903.abimoldayeva.model.Bus;
import kz.iitu.itse1903.abimoldayeva.model.BusRoute;
import kz.iitu.itse1903.abimoldayeva.model.User;
import kz.iitu.itse1903.abimoldayeva.model.dto.BusRouteDTO;
import kz.iitu.itse1903.abimoldayeva.service.BusRouteService;
import kz.iitu.itse1903.abimoldayeva.service.BusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/busRoute")
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class BusRouteController {
    private final BusRouteService busRouteService;

    @PostMapping("/")
    @ResponseStatus
    public ResponseEntity<BusRoute> createBus(@AuthenticationPrincipal User user, @RequestBody BusRouteDTO busRouteDTO){
        try{
            BusRoute createdBusRoute = busRouteService.save(busRouteDTO);
            return new ResponseEntity(createdBusRoute, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
