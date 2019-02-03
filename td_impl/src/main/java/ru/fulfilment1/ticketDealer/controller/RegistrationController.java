package ru.fulfilment1.ticketDealer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fulfilment1.ticketDealer.entity.Account;
import ru.fulfilment1.ticketDealer.entity.Authority;
import ru.fulfilment1.ticketDealer.entity.PaymentAction;
import ru.fulfilment1.ticketDealer.form.AccountForm;
import ru.fulfilment1.ticketDealer.service.AccountService;
import ru.fulfilment1.ticketDealer.service.PaymentService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public String registrationPage(Model model) {
        model.addAttribute("accountForm", new AccountForm());
        return "/registration";
    }

    @PostMapping
    public String register(@Valid AccountForm accountForm,
                           BindingResult bindingResult,
                           Model model) {

        String username = accountForm.getUsername();
        String email = accountForm.getEmail();
        String password = accountForm.getPassword();
        String confirmPassword = accountForm.getConfirmPassword();

        if (!bindingResult.hasErrors()) {
            if (!password.equals(confirmPassword)) {
                model.addAttribute("notMatchError", "Пароли не совпадают");
            } else {
                Account account = new Account(username, email, password);
                boolean isAccountAdded = accountService.addAccount(account, Authority.USER);

                if (isAccountAdded) {
                    // FREE 10.000 rub after registration
                    paymentService.deposit(account, 10000, PaymentAction.REG_BONUS);
                    return "redirect:/login";
                } else {
                    model.addAttribute("existError", "Аккаунт с таким именем уже существует");
                }
            }
        }
        model.addAttribute("accountForm", accountForm);
        return "/registration";
    }
}