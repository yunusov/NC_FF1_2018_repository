package ru.fulfilment1.ticketDealer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.fulfilment1.ticketDealer.entity.Flight;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {
}
