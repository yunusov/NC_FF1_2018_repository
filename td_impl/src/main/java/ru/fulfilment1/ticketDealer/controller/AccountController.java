package ru.fulfilment1.ticketDealer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fulfilment1.ticketDealer.entity.Account;
import ru.fulfilment1.ticketDealer.form.AccountForm;
import ru.fulfilment1.ticketDealer.repository.AccountRepository;

import java.util.Optional;

@Controller
@RequestMapping(value = {"/account"})
public class AccountController {

    private String registryError = null;
    private String loginError = null;

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(value = {"/sign"}, method = RequestMethod.GET)
    public String sign(Model model) {

        model.addAttribute("registryForm", new AccountForm());
        model.addAttribute("loginForm", new AccountForm());
        model.addAttribute("accounts", accountRepository.findAll());

        if (registryError != null) {
            model.addAttribute("registryError", registryError);
            registryError = null;
        }

        if (loginError != null) {
            model.addAttribute("loginError", loginError);
            loginError = null;
        }

        return "/account/sign";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String login(Model model,
                        @ModelAttribute("loginForm") AccountForm accountForm) {

        Account account = null;
        String userName = accountForm.getUserName();
        String password = accountForm.getPassword();

        Optional<Account> optional = accountRepository.findByUserName(userName);

        if (optional.isPresent()) {
            account = optional.get();
        }

        if (account != null && password.equals(account.getPassword())) {
            model.addAttribute("account", account);
            return "/account/info";
        }

        loginError = "Аккаунт не найден или неправильно введен пароль";
        return "redirect:/account/sign";

    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String registry(Model model,
                           @ModelAttribute("registryForm") AccountForm accountForm) {

        String userName = accountForm.getUserName();
        String email = accountForm.getEmail();
        String password = accountForm.getPassword();

        if (checkFields(userName, email, password)) {
            Account newAccount = new Account(userName, email, password);
            accountRepository.save(newAccount);
            return "redirect:/account/sign";
        }
        registryError = "Необходимо заполнить все поля";
        return "redirect:/account/sign";

    }

    private boolean checkFields(String... fields) {
        for (String field : fields) {
            if (field == null || field.length() <= 0) {
                return false;
            }
        }
        return true;
    }
}