package ru.fulfilment1.ticketDealer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fulfilment1.ticketDealer.entity.Account;
import ru.fulfilment1.ticketDealer.entity.Authority;
import ru.fulfilment1.ticketDealer.form.AccountForm;
import ru.fulfilment1.ticketDealer.repository.AccountRepository;
import ru.fulfilment1.ticketDealer.repository.PassengerRepository;
import ru.fulfilment1.ticketDealer.repository.TicketRepository;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    private final String REGEX_EMAIL_VALID = "[\\w][\\w\\d-.]*[\\w\\d]@[\\w]+[.][\\w]+";

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private String error;
    private AccountForm savedForm;

    @GetMapping
    public String registrationPage(Model model) {
        if (error != null) {
            model.addAttribute("error", error);
            error = null;
        }
        if (savedForm != null) {
            model.addAttribute("registrationForm", savedForm);
        }
        else {
            model.addAttribute("registrationForm", new AccountForm());
        }

        return "/registration";
    }

    @PostMapping(value = {"/register"})
    public String register(Model model, @ModelAttribute("registrationForm") AccountForm accountForm) {
        String username = accountForm.getUsername();
        String email = accountForm.getEmail();
        String password = accountForm.getPassword();
        String secondPassword = accountForm.getSecondPassword();

        if (!password.equals(secondPassword)) {
            error = "Пароли не совпадают";
            savedForm = accountForm;
            return "redirect:/registration";
        }

        if (accountRepository.existsByUsername(username)) {
            error = "Аккаунт с таким именем пользователя уже существует";
            savedForm = accountForm;
            return "redirect:/registration";
        }

        if (!email.matches(REGEX_EMAIL_VALID)) {
            error = "Неверный формат E-mail";
            savedForm = accountForm;
            return "redirect:/registration";
        }

        Account account = new Account(username, email, passwordEncoder.encode(password));
        Set<Authority> authorities = new HashSet<>();
        authorities.add(Authority.USER);
        account.setAuthorities(authorities);
        account.setActive(true);

        accountRepository.save(account);

        return "redirect:/account/profile";
    }
}
