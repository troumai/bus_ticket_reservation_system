package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.model.Bus;
import kz.iitu.itse1903.abimoldayeva.model.BusRoute;
import kz.iitu.itse1903.abimoldayeva.model.dto.BusRouteDTO;

import java.util.List;

public interface BusRouteService {
    BusRoute save(BusRouteDTO busRouteDTO);
    BusRoute getBusRouteById(Long id);
    List<BusRoute> getAllBusRoutes();
    BusRoute updateBusRoute(Long id, BusRoute busRoute);
    void deleteBusRoute(Long id);
}
