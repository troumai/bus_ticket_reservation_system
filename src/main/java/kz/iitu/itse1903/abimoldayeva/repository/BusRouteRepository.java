package kz.iitu.itse1903.abimoldayeva.repository;

import kz.iitu.itse1903.abimoldayeva.model.BusRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRouteRepository extends JpaRepository<BusRoute, Long> {
}
