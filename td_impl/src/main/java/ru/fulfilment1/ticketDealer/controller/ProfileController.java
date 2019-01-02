package ru.fulfilment1.ticketDealer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fulfilment1.ticketDealer.entity.Account;
import ru.fulfilment1.ticketDealer.form.AccountForm;
import ru.fulfilment1.ticketDealer.repository.AccountRepository;
import ru.fulfilment1.ticketDealer.service.AuthorityService;

@Controller
@RequestMapping(value = {"/account/profile"})
public class ProfileController {

    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping
    public String showMainPage(Model model, @AuthenticationPrincipal Account account) {
        String username = account.getUsername();

        if(authorityService.isAdmin(account)) {
            model.addAttribute("isAdmin", true);
        }

        model.addAttribute("username", username);
        model.addAttribute("accountForm", new AccountForm(account));

        return "/account/profile";
    }

    @PostMapping(value = {"/update"})
    public String updateAccount(Model model, @AuthenticationPrincipal Account account,
                                @ModelAttribute("accountForm") AccountForm accountForm) {

        account.setEmail(accountForm.getEmail());
        accountRepository.save(account);

        return "redirect:/account/profile";
    }

}