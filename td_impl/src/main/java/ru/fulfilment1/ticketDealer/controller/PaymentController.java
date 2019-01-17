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
import ru.fulfilment1.ticketDealer.entity.Payment;
import ru.fulfilment1.ticketDealer.entity.PaymentType;
import ru.fulfilment1.ticketDealer.form.PaymentSearch;
import ru.fulfilment1.ticketDealer.repository.PaymentRepository;
import ru.fulfilment1.ticketDealer.service.AuthorityService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "/account/payment")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private AuthorityService authorityService;

    @GetMapping
    public String showMoneyPage(@AuthenticationPrincipal Account account,
                                Model model) {
        String username = account.getUsername();
        int balance = account.getBalance();
        PaymentSearch paymentSearch = new PaymentSearch(LocalDate.now(), LocalDate.now(), PaymentType.ALL);
        List<Payment> payments = getPayments(account, paymentSearch);

        if (authorityService.isAdmin(account)) {
            model.addAttribute("isAdmin", true);
        }
        model.addAttribute("paymentSearch", paymentSearch);
        model.addAttribute("username", username);
        model.addAttribute("balance", balance);
        model.addAttribute("payments", payments);

        return "/account/payment";
    }

    @PostMapping(params = "find")
    public String findPayments(@AuthenticationPrincipal Account account,
                               @Valid PaymentSearch paymentSearch,
                               BindingResult bindingResult,
                               Model model) {
        String username = account.getUsername();
        int balance = account.getBalance();

        if(!bindingResult.hasErrors()) {
            List<Payment> payments = getPayments(account, paymentSearch);
            model.addAttribute("payments", payments);
        }
        if (authorityService.isAdmin(account)) {
            model.addAttribute("isAdmin", true);
        }
        model.addAttribute("paymentSearch", paymentSearch);
        model.addAttribute("username", username);
        model.addAttribute("balance", balance);

        return "/account/payment";
    }

    private List<Payment> getPayments(Account account, PaymentSearch paymentSearch) {
        long accountId = account.getId();
        LocalDate startDate = paymentSearch.getStartDate();
        LocalDate endDate = paymentSearch.getEndDate();
        PaymentType paymentType = paymentSearch.getPaymentType();
        List<Payment> payments;

        if (paymentType == PaymentType.ALL) {
            payments = paymentRepository.findAllByPeriod(accountId, startDate, endDate);
        } else {
            payments = paymentRepository.findAllByPeriodType(accountId, startDate, endDate, paymentType.name());
        }

        return payments;
    }
}