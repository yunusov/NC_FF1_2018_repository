package ru.fulfilment1.ticketDealer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.fulfilment1.ticketDealer.repository.AccountRepository;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping(value = "/account/users")
    public String showUsers(Model model, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getRemoteUser();
        model.addAttribute("username", username);
        model.addAttribute("accounts", accountRepository.findAll());

        return "/account/users";
    }
}
