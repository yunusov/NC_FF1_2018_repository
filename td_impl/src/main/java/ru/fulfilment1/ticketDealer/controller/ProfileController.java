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
import ru.fulfilment1.ticketDealer.form.AccountForm;
import ru.fulfilment1.ticketDealer.repository.AccountRepository;
import ru.fulfilment1.ticketDealer.service.AuthorityService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = {"/account/profile"})
public class ProfileController {

    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping
    public String showMainPage(@AuthenticationPrincipal Account account,
                               Model model) {
        String username = account.getUsername();
        String email = account.getEmail();
        AccountForm accountForm = new AccountForm(username, email);

        if (authorityService.isAdmin(account)) {
            model.addAttribute("isAdmin", true);
        }

        model.addAttribute("username", account.getUsername());
        model.addAttribute("accountForm", accountForm);

        return "/account/profile";
    }

    @PostMapping(params = "update")
    public String updateAccount(@AuthenticationPrincipal Account account,
                                @Valid AccountForm accountForm,
                                BindingResult bindingResult,
                                Model model) {

        if(!bindingResult.hasErrors()) {
            String email = accountForm.getEmail();

            account.setEmail(email);
            accountRepository.save(account);

            model.addAttribute("success", true);
        }

        if (authorityService.isAdmin(account)) {
            model.addAttribute("isAdmin", true);
        }

        model.addAttribute("username", account.getUsername());
        model.addAttribute("accountForm", accountForm);

        return "/account/profile";
    }

    @PostMapping(params = "change")
    public String changePassword(@AuthenticationPrincipal Account account,
                                 @Valid AccountForm accountForm,
                                 BindingResult bindingResult,
                                 Model model) {



        if (authorityService.isAdmin(account)) {
            model.addAttribute("isAdmin", true);
        }

        model.addAttribute("username", account.getUsername());
        model.addAttribute("accountForm", accountForm);

        return "/account/profile";
    }
}