package ru.fulfilment1.ticketDealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.fulfilment1.ticketDealer.entity.Visit;
import ru.fulfilment1.ticketDealer.repository.VisitsRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    final VisitsRepository visitsRepository;

    public IndexController(VisitsRepository visitsRepository)
    {
        this.visitsRepository = visitsRepository;
    }

    @GetMapping("/")
    public ModelAndView index()
    {
        Map<String, String> model = new HashMap<>();
        model.put("name", "Username");
        model.put("visit", "/api/visits");

        Visit visit = new Visit();
        visit.description = String.format("Visited at %s", LocalDateTime.now());
        visitsRepository.save(visit);

        return new ModelAndView("index", model);
    }

    @GetMapping("/login")
    public ModelAndView login()
    {
        Map<String, String> model = new HashMap<>();
        return new ModelAndView("login", model);
    }
}