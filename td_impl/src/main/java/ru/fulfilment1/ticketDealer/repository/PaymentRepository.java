package ru.fulfilment1.ticketDealer.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.fulfilment1.ticketDealer.entity.Account;
import ru.fulfilment1.ticketDealer.entity.Payment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {

    List<Payment> findAllByAccount(Account account);

    @Query(value = "SELECT * FROM payment WHERE account_id = ?1 and (date between ?2 and ?3) and type = ?4",
            nativeQuery = true)
    List<Payment> findAllByPeriodType(long accountId, LocalDate startDateTime, LocalDate endDate, String paymentType);

    @Query(value = "SELECT * FROM payment WHERE account_id = ?1 and (date between ?2 and ?3)", nativeQuery = true)
    List<Payment> findAllByPeriod(long accountId, LocalDate startDate, LocalDate endDate);

}
