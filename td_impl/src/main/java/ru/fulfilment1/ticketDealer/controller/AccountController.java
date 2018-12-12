package ru.fulfilment1.ticketDealer.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fulfilment1.ticketDealer.entity.Account;
import ru.fulfilment1.ticketDealer.form.AccountForm;
import ru.fulfilment1.ticketDealer.repository.AccountRepository;

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

        String userName = accountForm.getUserName();
        String password = accountForm.getPassword();
        Account account = accountRepository.findByUserName(userName);

        if (account != null) {
            if (password.equals(account.getPassword())) {
                model.addAttribute("account", account);
                return "/account/info";
            }
            else {
                loginError = "Неправильный пароль";
                return "redirect:/account/sign";
            }
        }

        loginError = "Учетной записи с таким логином не существует";
        return "redirect:/account/sign";

    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String registry(Model model,
                           @ModelAttribute("registryForm") AccountForm accountForm) {

        String userName = accountForm.getUserName();
        String email = accountForm.getEmail();
        String password = accountForm.getPassword();

        if (StringUtils.isAnyBlank(userName, email, password)) {
            registryError = "Необходимо заполнить все поля";
            return "redirect:/account/sign";
        }

        Account newAccount = new Account(userName, email, password);

        accountRepository.save(newAccount);
        return "redirect:/account/sign";
    }

}