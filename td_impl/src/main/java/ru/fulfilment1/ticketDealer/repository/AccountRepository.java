package ru.fulfilment1.ticketDealer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.fulfilment1.ticketDealer.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByUserName(String userName);
    Account findById(long id);
}

