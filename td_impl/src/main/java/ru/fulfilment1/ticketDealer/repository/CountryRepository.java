package ru.fulfilment1.ticketDealer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.fulfilment1.ticketDealer.entity.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {
}
