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
import ru.fulfilment1.ticketDealer.repository.OrdersRepository;
import ru.fulfilment1.ticketDealer.repository.PassengerRepository;
import ru.fulfilment1.ticketDealer.repository.TicketRepository;
import ru.fulfilment1.ticketDealer.service.AuthorityService;
import ru.fulfilment1.ticketDealer.service.PaymentService;

import java.time.LocalDate;

@Controller
@RequestMapping(value = "/account/orders")
public class OrderController {

    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public String showOrdersPage(@AuthenticationPrincipal Account account,
                                 Model model) {
        String username = account.getUsername();
        Passenger passenger = passengerRepository.findByAccount(account);

        if(authorityService.isAdmin(account)) {
            model.addAttribute("isAdmin", true);
        }

        if (passenger != null) {
            model.addAttribute("tickets", ticketRepository.findAllByPassenger(passenger));
        }

        model.addAttribute("username", username);
        return "/account/orders";
    }

    @PostMapping(value = {"/cancel"})
    public String cancelOrder(@AuthenticationPrincipal Account account,
                              @RequestParam(name = "id") long id,
                              Model model) {

        Ticket ticket = ticketRepository.findById(id);
        int price = ticket.getPrice();

        if (paymentService.deposit(account, price, PaymentAction.TICKET_CANCEL)) {
            ticket.setPassenger(null);
            Order order = new Order(LocalDate.now(), account, ticket, OrderType.CANCEL);
            ordersRepository.save(order);
            ticketRepository.save(ticket);
        }

        return "redirect:/account/orders";
    }
}