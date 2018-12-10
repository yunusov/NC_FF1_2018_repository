package ru.fulfilment1.ticketDealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.fulfilment1.ticketDealer.entity.Visit;
import ru.fulfilment1.ticketDealer.repository.VisitsRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    final VisitsRepository visitsRepository  ;

    public IndexController(VisitsRepository visitsRepository)
    {
        this.visitsRepository = visitsRepository;
    }

    @GetMapping("/")
    public ModelAndView index(@RequestParam(name="name", required=false, defaultValue="Username") String name, ModelAndView mav)
    {
        Map<String, String> model = new HashMap<>();
        model.put("name", name); // ?name=YourName
        model.put("visit", "/api/visits");

        Visit visit = new Visit();
        visit.description = String.format("Visited at %s", LocalDateTime.now());
        visitsRepository.save(visit);

        mav.setViewName("index");
        mav.addAllObjects(model);

        return new ModelAndView("index", model);
    }

}
