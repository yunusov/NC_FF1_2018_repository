package ru.fulfilment1.ticketDealer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fulfilment1.ticketDealer.entity.Account;
import ru.fulfilment1.ticketDealer.entity.Passenger;
import ru.fulfilment1.ticketDealer.entity.Ticket;
import ru.fulfilment1.ticketDealer.form.AccountForm;
import ru.fulfilment1.ticketDealer.form.PassengerForm;
import ru.fulfilment1.ticketDealer.repository.AccountRepository;
import ru.fulfilment1.ticketDealer.repository.PassengerRepository;
import ru.fulfilment1.ticketDealer.repository.TicketRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"/account"})
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping(value = {"/main"})
    public String mainPage(Model model, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getRemoteUser();
        Account account = accountRepository.findByUsername(username);
        AccountForm accountForm = new AccountForm(account);

        model.addAttribute("username", username);
        model.addAttribute("accountForm", accountForm);

        return "/account/main";
    }

    @PostMapping(value = {"/updateAccount"})
    public String updateAccount(Model model, HttpServletRequest httpServletRequest,
                                @ModelAttribute("accountForm") AccountForm accountForm) {
        String username = httpServletRequest.getRemoteUser();
        Account account = accountRepository.findByUsername(username);
        account.setEmail(accountForm.getEmail());
        accountRepository.save(account);

        return "redirect:/account/main";
    }

    @GetMapping(value = {"/passenger"})
    public String passengerPage(Model model, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getRemoteUser();
        Account account = accountRepository.findByUsername(username);
        PassengerForm passengerForm;
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

    @PostMapping(value = {"/updatePassenger"})
    public String updatePassenger(Model model, HttpServletRequest httpServletRequest,
                         @ModelAttribute("passengerForm") PassengerForm passengerForm) {
        String username = httpServletRequest.getRemoteUser();
        Account account = accountRepository.findByUsername(username);
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

    @GetMapping(value = {"/buy"})
    public String showBuyPage(Model model) {
        model.addAttribute("tickets", ticketRepository.findAll());
        return "/account/buy";
    }

    @PostMapping(value = {"/buy"})
    public String buy(Model model, HttpServletRequest httpServletRequest,
                      @RequestParam(name = "id") long id) {
        String username = httpServletRequest.getRemoteUser();
        Account account = accountRepository.findByUsername(username);
        Passenger passenger = passengerRepository.findByAccount(account);

        Ticket ticket = ticketRepository.findById(id);
        if (passenger != null) {
            ticket.setPassenger(passenger);
        }
        ticketRepository.save(ticket);

        return "redirect:/account/buy";
    }

    @GetMapping(value = {"/orders"})
    public String showOrdersPage(Model model, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getRemoteUser();
        Account account = accountRepository.findByUsername(username);
        Passenger passenger = passengerRepository.findByAccount(account);

        if(passenger != null) {
            model.addAttribute("tickets", ticketRepository.findAllByPassenger(passenger));
        }

        model.addAttribute("username", username);

        return "/account/orders";
    }

    @PostMapping(value = {"/cancelOrder"})
    public String cancelOrder(Model model, @RequestParam(name = "id") long id) {
        Ticket ticket = ticketRepository.findById(id);

        ticket.setPassenger(null);
        ticketRepository.save(ticket);

        return "redirect:/account/orders";
    }

}