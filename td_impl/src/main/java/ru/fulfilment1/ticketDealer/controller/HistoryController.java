package ru.fulfilment1.ticketDealer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fulfilment1.ticketDealer.entity.Account;
import ru.fulfilment1.ticketDealer.entity.Order;
import ru.fulfilment1.ticketDealer.entity.Ticket;
import ru.fulfilment1.ticketDealer.form.SearchForm;
import ru.fulfilment1.ticketDealer.repository.AccountRepository;
import ru.fulfilment1.ticketDealer.repository.OrdersRepository;
import ru.fulfilment1.ticketDealer.repository.TicketRepository;
import ru.fulfilment1.ticketDealer.service.AuthorityService;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/account/history")
public class HistoryController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private AuthorityService authorityService;

    private List<Order> orders = null;
    private SearchForm currentForm = null;

    @GetMapping
    public String showHistoryPage(Model model, @AuthenticationPrincipal Account account) {
        String username = account.getUsername();
        SearchForm searchForm;

        if (authorityService.isAdmin(account)) {
            model.addAttribute("isAdmin", true);
        }

        if (currentForm != null) {
            searchForm = currentForm;
            orders = ordersRepository.findAllByPeriod(searchForm.getStartDate(), searchForm.getEndDate());
        }
        else {
            searchForm = new SearchForm();
            orders = ordersRepository.findAllByAccount(account);
        }

        List<Ticket> tickets = new ArrayList<>();
        for (Order order : orders) {
            tickets.add(order.getTicket());
        }
        orders = null;

        model.addAttribute("searchForm", searchForm);
        model.addAttribute("username", username);
        model.addAttribute("tickets", tickets);

        return "/account/history";
    }

    @PostMapping(value = "search")
    public String findHistory(Model model, SearchForm searchForm) {

        currentForm = searchForm;

        return "redirect:/account/history";
    }
}