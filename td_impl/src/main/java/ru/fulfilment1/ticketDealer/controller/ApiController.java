package ru.fulfilment1.ticketDealer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fulfilment1.ticketDealer.entity.Visit;
import ru.fulfilment1.ticketDealer.repository.VisitsRepository;

@RestController
@RequestMapping("/api")
public class ApiController {

    final VisitsRepository visitsRepository;

    public ApiController(VisitsRepository visitsRepository) {
        this.visitsRepository = visitsRepository;
    }

    @GetMapping("/visits")
    public Iterable<Visit> getVisits() {
        return visitsRepository.findAll();
    }

}
