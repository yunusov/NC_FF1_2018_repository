package ru.fulfilment1.ticketDealer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fulfilment1.ticketDealer.entity.Account;
import ru.fulfilment1.ticketDealer.entity.Passenger;
import ru.fulfilment1.ticketDealer.form.PassengerForm;
import ru.fulfilment1.ticketDealer.repository.AccountRepository;
import ru.fulfilment1.ticketDealer.repository.PassengerRepository;
import ru.fulfilment1.ticketDealer.service.AuthorityService;

@Controller
@RequestMapping(value = {"/account/passenger"})
public class PassengerController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private AuthorityService authorityService;

    @GetMapping
    public String showPassengerPage(Model model, @AuthenticationPrincipal Account account) {
        String username = account.getUsername();
        PassengerForm passengerForm;

        if(authorityService.isAdmin(account)) {
            model.addAttribute("isAdmin", true);
        }

        if (passengerRepository.existsByAccount(account)) {
            Passenger passenger = passengerRepository.findByAccount(account);
            passengerForm = new PassengerForm(passenger);
        }
        else {
            passengerForm = new PassengerForm();
        }

        model.addAttribute("username", username);
        model.addAttribute("passengerForm", passengerForm);

        return "/account/passenger";
    }

    @PostMapping(value = {"/update"})
    public String updatePassenger(Model model, @AuthenticationPrincipal Account account,
                         @ModelAttribute("passengerForm") PassengerForm passengerForm) {
        String username = account.getUsername();
        Passenger passenger;

        if (passengerRepository.existsByAccount(account)) {
            passenger = passengerRepository.findByAccount(account);
        }
        else {
            passenger = new Passenger();
            passenger.setAccount(account);
        }
        passenger.setAll(passengerForm);
        passengerRepository.save(passenger);

        return "redirect:/account/passenger";
    }

}