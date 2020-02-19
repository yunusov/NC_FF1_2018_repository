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
import ru.fulfilment1.ticketDealer.form.PasswordForm;
import ru.fulfilment1.ticketDealer.repository.AccountRepository;
import ru.fulfilment1.ticketDealer.service.AccountService;
import ru.fulfilment1.ticketDealer.service.AuthorityService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = {"/account/profile"})
public class ProfileController {

    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private AccountService accountService;
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
        model.addAttribute("balance", account.getBalance());
        model.addAttribute("accountForm", accountForm);
        model.addAttribute("passwordForm", new PasswordForm());

        return "/account/profile";
    }

    @PostMapping(params = "update")
    public String updateAccount(@AuthenticationPrincipal Account account,
                                @Valid AccountForm accountForm,
                                BindingResult bindingResult,
                                Model model) {

        if (!bindingResult.hasErrors()) {
            String email = accountForm.getEmail();

            account.setEmail(email);
            accountRepository.save(account);

            model.addAttribute("emailSuccess", true);
        }

        if (authorityService.isAdmin(account)) {
            model.addAttribute("isAdmin", true);
        }

        model.addAttribute("username", account.getUsername());
        model.addAttribute("balance", account.getBalance());
        model.addAttribute("accountForm", accountForm);
        model.addAttribute("passwordForm", new PasswordForm());

        return "/account/profile";
    }

    @PostMapping(params = "change")
    public String changePassword(@AuthenticationPrincipal Account account,
                                 @Valid PasswordForm passwordForm,
                                 BindingResult bindingResult,
                                 Model model) {

        String username = account.getUsername();
        String email = account.getEmail();
        AccountForm accountForm = new AccountForm(username, email);
        String oldPassword = passwordForm.getOldPassword();
        String newPassword = passwordForm.getNewPassword();
        String confirmPassword = passwordForm.getConfirmPassword();

        if (!bindingResult.hasErrors()) {
            if (newPassword.equals(confirmPassword)) {
                boolean isChanged = accountService.changePassword(account, oldPassword, newPassword);

                if (isChanged) {
                    model.addAttribute("passwordSuccess", true);
                } else {
                    model.addAttribute("passwordError", "Текущий пароль введен неверно");
                }
            } else {
                model.addAttribute("passwordError", "Пароли не совпадают");
            }
        }

        if (authorityService.isAdmin(account)) {
            model.addAttribute("isAdmin", true);
        }

        model.addAttribute("username", account.getUsername());
        model.addAttribute("balance", account.getBalance());
        model.addAttribute("accountForm", accountForm);

        return "/account/profile";
    }
}