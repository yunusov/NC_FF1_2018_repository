package ru.fulfilment1.ticketDealer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.fulfilment1.ticketDealer.entity.Account;
import ru.fulfilment1.ticketDealer.entity.Passenger;

@Repository
public interface PassengerRepository extends CrudRepository<Passenger, Long> {
    Passenger findByAccount(Account account);
    boolean existsByAccount(Account account);
}