package ru.fulfilment1.ticketDealer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.fulfilment1.ticketDealer.entity.Account;
import ru.fulfilment1.ticketDealer.repository.AccountRepository;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping(value = "/account/users")
    public String showUsersPage(Model model, @AuthenticationPrincipal Account account) {
        String username = account.getUsername();

        model.addAttribute("username", username);
        model.addAttribute("accounts", accountRepository.findAll());

        return "/account/users";
    }
}
