package ru.fulfilment1.ticketDealer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fulfilment1.ticketDealer.entity.Account;
import ru.fulfilment1.ticketDealer.entity.Payment;
import ru.fulfilment1.ticketDealer.entity.PaymentAction;
import ru.fulfilment1.ticketDealer.entity.PaymentType;
import ru.fulfilment1.ticketDealer.repository.AccountRepository;
import ru.fulfilment1.ticketDealer.repository.PaymentRepository;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class PaymentService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    public boolean deposit(Account account, int amount, PaymentAction paymentAction) {
        int balance = account.getBalance();

        if (account != null) {
            Payment payment = new Payment(LocalDate.now(), LocalTime.now(), PaymentType.DEPOSIT, paymentAction, amount, account);
            account.setBalance(balance + amount);
            accountRepository.save(account);
            paymentRepository.save(payment);
            return true;
        } else {
            return false;
        }
    }

    public boolean withdraw(Account account, int amount, PaymentAction paymentAction) {
        int balance = account.getBalance();

        if (balance >= amount) {
            Payment payment = new Payment(LocalDate.now(), LocalTime.now(), PaymentType.WITHDRAW, paymentAction, amount, account);
            account.setBalance(balance - amount);
            accountRepository.save(account);
            paymentRepository.save(payment);
            return true;
        } else {
            return false;
        }
    }

}
