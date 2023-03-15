package kz.iitu.itse1903.abimoldayeva.service.impl;

import kz.iitu.itse1903.abimoldayeva.model.Bus;
import kz.iitu.itse1903.abimoldayeva.model.BusRoute;
import kz.iitu.itse1903.abimoldayeva.model.User;
import kz.iitu.itse1903.abimoldayeva.model.dto.BusRouteDTO;
import kz.iitu.itse1903.abimoldayeva.repository.BusRepository;
import kz.iitu.itse1903.abimoldayeva.repository.BusRouteRepository;
import kz.iitu.itse1903.abimoldayeva.repository.UserRepository;
import kz.iitu.itse1903.abimoldayeva.service.BusRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusRouteServiceImpl implements BusRouteService {

    private final BusRouteRepository busRouteRepository;
    private final UserRepository userRepository;
    private final BusRepository busRepository;

    @Override
    @Transactional
    public BusRoute save(BusRouteDTO busRouteDTO) {
        User driver = userRepository.findById(busRouteDTO.getUserId()).get();
        Bus bus = busRepository.findById(busRouteDTO.getBusId()).get();
        BusRoute busRoute = new BusRoute(driver, bus);
        return busRouteRepository.save(busRoute);
    }

    @Override
    public BusRoute getBusRouteById(Long id) {
        return busRouteRepository.getById(id);
    }

    @Override
    public List<BusRoute> getAllBusRoutes() {
        return busRouteRepository.findAll();
    }

    @Override
    @Transactional
    public BusRoute updateBusRoute(Long id, BusRoute busRoute) {
        BusRoute updatedBusRoute = busRouteRepository.getById(id);

        updatedBusRoute.setBus(busRoute.getBus());
        updatedBusRoute.setUser(busRoute.getUser());

        return busRouteRepository.save(updatedBusRoute);
    }

    @Override
    @Transactional
    public void deleteBusRoute(Long id) {
        busRouteRepository.deleteById(id);
    }
}
