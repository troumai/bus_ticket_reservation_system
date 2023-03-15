package kz.iitu.itse1903.abimoldayeva.repository;

import kz.iitu.itse1903.abimoldayeva.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    //Query creation from method names + Locking
    @Lock(LockModeType.PESSIMISTIC_READ)
    Bus findBusByRegisterNumber(String number);

}
