package ru.fulfilment1.ticketDealer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fulfilment1.ticketDealer.entity.*;
import ru.fulfilment1.ticketDealer.repository.AccountRepository;
import ru.fulfilment1.ticketDealer.repository.OrdersRepository;
import ru.fulfilment1.ticketDealer.repository.PassengerRepository;
import ru.fulfilment1.ticketDealer.repository.TicketRepository;
import ru.fulfilment1.ticketDealer.service.PaymentService;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping(value = {"/account/booking"})
public class BookingController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private PaymentService paymentService;

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
    public String buy(@AuthenticationPrincipal Account account,
                      @RequestParam(name = "id") long id,
                      Model model) {

        Passenger passenger = passengerRepository.findByAccount(account);

        if (passenger == null) {
            error = "Информация о пассажире не заполнена";
            return "redirect:/account/booking";
        }

        Ticket ticket = ticketRepository.findById(id);
        int price = ticket.getPrice();
        boolean isPaid = paymentService.withdraw(account, price, PaymentAction.TICKET_BOOKING);

        if (isPaid) {
            ticket.setPassenger(passenger);
            Order order = new Order(LocalDate.now(), account, ticket, OrderType.BOOKING);
            ordersRepository.save(order);
            ticketRepository.save(ticket);
        } else {
            error = "Недостаточно средств на балансе";
        }

        return "redirect:/account/booking";
    }

}