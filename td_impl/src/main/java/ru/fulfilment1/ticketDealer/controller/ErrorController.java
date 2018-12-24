package ru.fulfilment1.ticketDealer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ErrorController
{
    public ErrorController()
    {
    }

    @GetMapping("/error")
    public ModelAndView error(ModelAndView mav)
    {
        LOG.debug("ErrorController = " + mav.getModel().toString());
        Map<String, String> model = new HashMap<>();
        return mav;
    }

    private static final Logger LOG = LoggerFactory.getLogger(ErrorController.class);
}
