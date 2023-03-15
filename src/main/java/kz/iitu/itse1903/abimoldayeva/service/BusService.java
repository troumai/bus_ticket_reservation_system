package kz.iitu.itse1903.abimoldayeva.service;

import kz.iitu.itse1903.abimoldayeva.model.Bus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BusService {
    Bus save(Bus bus);
    Bus getBusById(Long id);
    List<Bus> getAllBuses();
    Bus updateBus(Long id, Bus bus);
    void deleteBus(Long id);

}
