package ru.fulfilment1.ticketDealer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fulfilment1.ticketDealer.entity.Account;
import ru.fulfilment1.ticketDealer.entity.Passenger;
import ru.fulfilment1.ticketDealer.entity.Ticket;
import ru.fulfilment1.ticketDealer.repository.AccountRepository;
import ru.fulfilment1.ticketDealer.repository.PassengerRepository;
import ru.fulfilment1.ticketDealer.repository.TicketRepository;
import ru.fulfilment1.ticketDealer.service.AuthorityService;

@Controller
@RequestMapping(value = "/account/orders")
public class OrderController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private AuthorityService authorityService;

    @GetMapping
    public String showOrdersPage(Model model, @AuthenticationPrincipal Account account) {
        String username = account.getUsername();
        int balance = account.getBalance();
        Passenger passenger = passengerRepository.findByAccount(account);

        if(authorityService.isAdmin(account)) {
            model.addAttribute("isAdmin", true);
        }

        if (passenger != null) {
            model.addAttribute("tickets", ticketRepository.findAllByPassenger(passenger));
        }

        model.addAttribute("balance", balance);
        model.addAttribute("username", username);
        return "/account/orders";
    }

    @PostMapping(value = {"/cancel"})
    public String cancelOrder(Model model, @RequestParam(name = "id") long id) {
        Ticket ticket = ticketRepository.findById(id);

        ticket.setPassenger(null);
        ticketRepository.save(ticket);

        return "redirect:/account/orders";
    }
}