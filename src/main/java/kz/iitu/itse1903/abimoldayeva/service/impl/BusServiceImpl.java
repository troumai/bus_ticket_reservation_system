package kz.iitu.itse1903.abimoldayeva.service.impl;

import kz.iitu.itse1903.abimoldayeva.model.Bus;
import kz.iitu.itse1903.abimoldayeva.repository.BusRepository;
import kz.iitu.itse1903.abimoldayeva.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusServiceImpl implements BusService {
    private final BusRepository busRepository;

    @Override
    @CacheEvict(value = "buses", allEntries = true)
    @Transactional
    public Bus save(Bus bus) {
        return busRepository.save(bus);
    }

    @Override
    public Bus getBusById(Long id) {
        return busRepository.findById(id).get();
    }

    @Override
    @Cacheable(cacheNames = "buses")
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    @Override
    @Transactional
    public Bus updateBus(Long id, Bus bus) {
        Bus updatedBus = busRepository.findById(id).get();

        updatedBus.setRegisterNumber(bus.getRegisterNumber());
        updatedBus.setSeatsNumber(bus.getSeatsNumber());

        return save(updatedBus);
    }

    @Override
    @CacheEvict(value = "buses", allEntries = true)
    @Transactional
    public void deleteBus(Long id) {
        busRepository.deleteById(id);

    }
}
