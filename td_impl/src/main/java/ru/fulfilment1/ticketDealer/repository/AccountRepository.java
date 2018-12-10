package ru.fulfilment1.ticketDealer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.fulfilment1.ticketDealer.entity.Account;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findByUserName(String string);
}

