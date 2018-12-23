package ru.fulfilment1.ticketDealer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.fulfilment1.ticketDealer.entity.*;

import java.util.List;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    List<Ticket> findAll();
    List<Ticket> findAllByPassenger(Passenger passenger);
    Ticket findById(long id);
}
