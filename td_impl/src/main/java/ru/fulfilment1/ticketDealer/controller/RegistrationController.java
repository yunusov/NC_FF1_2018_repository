package ru.fulfilment1.ticketDealer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.fulfilment1.ticketDealer.entity.Account;
import ru.fulfilment1.ticketDealer.entity.Role;
import ru.fulfilment1.ticketDealer.form.AccountForm;
import ru.fulfilment1.ticketDealer.repository.AccountRepository;
import ru.fulfilment1.ticketDealer.repository.PassengerRepository;
import ru.fulfilment1.ticketDealer.repository.TicketRepository;
import java.util.HashSet;
import java.util.Set;

@Controller
public class RegistrationController {
//[\w]+[\.][\w]+
    private final String REGEX_EMAIL_VALID = "[\\w][\\w\\d-.]*[\\w\\d]@[\\w]+[.][\\w]+";

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping(value = {"/registration"})
    public String registrationPage(Model model) {
        model.addAttribute("registrationForm", new AccountForm());
        return "/registration";
    }

    @PostMapping(value = {"/register"})
    public String register(Model model,
                           @ModelAttribute("registrationForm") AccountForm accountForm) {
        String username = accountForm.getUsername();
        String email = accountForm.getEmail();
        String password = accountForm.getPassword();
        String secondPassword = accountForm.getSecondPassword();

        if (!password.equals(secondPassword)) {
            model.addAttribute("error", "Пароли не совпадают");
            model.addAttribute("registrationForm", accountForm);
            return "/registration";
        }

        if (accountRepository.existsByUsername(username)) {
            model.addAttribute("error", "Аккаунт с таким именем пользователя уже существует");
            model.addAttribute("registrationForm", accountForm);
            return "/registration";
        }

        if (!email.matches(REGEX_EMAIL_VALID)) {
            model.addAttribute("error", "Неверный формат E-mail");
            model.addAttribute("registrationForm", accountForm);
            return "/registration";
        }

        Account account = new Account(username, email, password);
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        account.setRoles(roles);
        account.setActive(true);

        accountRepository.save(account);

        return "redirect:/account/main";
    }
}
