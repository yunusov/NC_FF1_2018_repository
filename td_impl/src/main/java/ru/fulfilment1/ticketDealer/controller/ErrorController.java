package ru.fulfilment1.ticketDealer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    private static final Logger LOG = LoggerFactory.getLogger(ErrorController.class);

    @GetMapping("/error")
    public String error(Model model) {
        LOG.debug("ErrorController = " + model.toString());
        return "/error";
    }
}
