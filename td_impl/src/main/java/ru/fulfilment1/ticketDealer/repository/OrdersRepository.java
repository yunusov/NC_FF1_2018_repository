package ru.fulfilment1.ticketDealer.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.fulfilment1.ticketDealer.entity.Account;
import ru.fulfilment1.ticketDealer.entity.Order;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrdersRepository extends CrudRepository<Order, Long> {
    List<Order> findAllByAccount(Account account);

    @Query(value = "SELECT * FROM orders WHERE account_id = ?1 and (date between ?2 and ?3)", nativeQuery = true)
    List<Order> findAllByPeriod(long accountId, LocalDate startDate, LocalDate endDate);
}
