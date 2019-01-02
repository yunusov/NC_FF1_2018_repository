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

import java.time.LocalDate;

@Controller
@RequestMapping(value = {"/account/booking"})
public class BookingController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private TicketRepository ticketRepository;

    private String error = null;

    @GetMapping
    public String showBookingPage(Model model) {

        if (error != null) {
            model.addAttribute("error", error);
            error = null;
        }

        model.addAttribute("tickets", ticketRepository.findAll());

        return "/account/booking";
    }

    @PostMapping(value = {"/book"})
    public String buy(Model model, @AuthenticationPrincipal Account account,
                      @RequestParam(name = "id") long id) {

        Passenger passenger = passengerRepository.findByAccount(account);
        Ticket ticket = ticketRepository.findById(id);

        int balance = account.getBalance();
        int price = ticket.getPrice();

        if (passenger == null) {
            error = "Информация о пассажире не заполнена";
            return "redirect:/account/booking";
        }

        if (balance < price) {
            error = "Недостаточно средств на балансе";
            return "redirect:/account/booking";
        }

        balance = balance - price;
        account.setBalance(balance);
        ticket.setPassenger(passenger);

        ticketRepository.save(ticket);
        accountRepository.save(account);

        return "redirect:/account/booking";
    }

}