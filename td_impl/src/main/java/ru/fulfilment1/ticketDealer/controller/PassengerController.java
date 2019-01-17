package ru.fulfilment1.ticketDealer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fulfilment1.ticketDealer.entity.Account;
import ru.fulfilment1.ticketDealer.entity.Passenger;
import ru.fulfilment1.ticketDealer.form.PassengerForm;
import ru.fulfilment1.ticketDealer.repository.PassengerRepository;
import ru.fulfilment1.ticketDealer.service.AccountService;
import ru.fulfilment1.ticketDealer.service.AuthorityService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = {"/account/passenger"})
public class PassengerController {

    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private AccountService accountService;

    @GetMapping
    public String showPassengerPage(@AuthenticationPrincipal Account account,
                                    Model model) {
        String username = account.getUsername();
        Passenger passenger = passengerRepository.findByAccount(account);
        PassengerForm passengerForm = new PassengerForm();

        if(passenger != null) {
            passengerForm.setAll(passenger);
        }

        if (authorityService.isAdmin(account)) {
            model.addAttribute("isAdmin", true);
        }

        model.addAttribute("username", username);
        model.addAttribute("passengerForm", passengerForm);

        return "/account/passenger";
    }

    @PostMapping(params = "update")
    public String updatePassenger(@AuthenticationPrincipal Account account,
                                  @Valid PassengerForm passengerForm,
                                  BindingResult bindingResult,
                                  Model model) {
        String username = account.getUsername();

        if (!bindingResult.hasErrors()) {
            accountService.setPassenger(account, passengerForm);
            model.addAttribute("success", true);
        }

        if (authorityService.isAdmin(account)) {
            model.addAttribute("isAdmin", true);
        }

        model.addAttribute("username", username);
        model.addAttribute("passengerForm", passengerForm);

        return "/account/passenger";
    }

}