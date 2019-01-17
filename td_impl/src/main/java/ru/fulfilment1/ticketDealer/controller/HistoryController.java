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
import ru.fulfilment1.ticketDealer.entity.Order;
import ru.fulfilment1.ticketDealer.entity.Ticket;
import ru.fulfilment1.ticketDealer.form.HistorySearch;
import ru.fulfilment1.ticketDealer.repository.OrdersRepository;
import ru.fulfilment1.ticketDealer.service.AuthorityService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/account/history")
public class HistoryController {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private AuthorityService authorityService;

    @GetMapping
    public String showHistoryPage(@AuthenticationPrincipal Account account,
                                  Model model) {
        String username = account.getUsername();
        HistorySearch historySearch = new HistorySearch(LocalDate.now(), LocalDate.now());
        List<Ticket> tickets = getTickets(account, historySearch);

        if (authorityService.isAdmin(account)) {
            model.addAttribute("isAdmin", true);
        }

        model.addAttribute("username", username);
        model.addAttribute("historySearch", historySearch);
        model.addAttribute("tickets", tickets);
        return "/account/history";
    }

    @PostMapping(params = "find")
    public String findHistory(@AuthenticationPrincipal Account account,
                              @Valid HistorySearch historySearch,
                              BindingResult bindingResult,
                              Model model) {
        String username = account.getUsername();

        if (!bindingResult.hasErrors()) {
            List<Ticket> tickets = getTickets(account, historySearch);
            model.addAttribute("tickets", tickets);
        }
        if (authorityService.isAdmin(account)) {
            model.addAttribute("isAdmin", true);
        }
        model.addAttribute("username", username);
        model.addAttribute("historySearch", historySearch);
        return "/account/history";
    }

    private List<Ticket> getTickets(Account account, HistorySearch historySearch) {
        LocalDate startDate = historySearch.getStartDate();
        LocalDate endDate = historySearch.getEndDate();
        List<Order> orders = ordersRepository.findAllByPeriod(account.getId(), startDate, endDate);

        List<Ticket> tickets = new ArrayList<>();
        for (Order order : orders) {
            tickets.add(order.getTicket());
        }
        return tickets;
    }
}