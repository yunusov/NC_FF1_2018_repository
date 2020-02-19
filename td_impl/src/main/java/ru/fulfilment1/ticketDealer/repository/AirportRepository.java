package ru.fulfilment1.ticketDealer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.fulfilment1.ticketDealer.entity.Airport;

@Repository
public interface AirportRepository extends CrudRepository<Airport, Long> {
}
