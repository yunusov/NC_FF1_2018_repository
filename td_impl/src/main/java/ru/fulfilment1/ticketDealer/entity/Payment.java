package ru.fulfilment1.ticketDealer.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate date;
    private LocalTime time;
    @Enumerated(EnumType.STRING)
    private PaymentType type;
    @Enumerated(EnumType.STRING)
    private PaymentAction paymentAction;
    private int value;
    @ManyToOne
    private Account account;

    public Payment() {

    }

    public Payment(LocalDate date, LocalTime time, PaymentType type, PaymentAction paymentAction, int value, Account account) {
        this.date = date;
        this.time = time;
        this.type = type;
        this.paymentAction = paymentAction;
        this.value = value;
        this.account = account;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public PaymentAction getPaymentAction() {
        return paymentAction;
    }

    public void setPaymentAction(PaymentAction paymentAction) {
        this.paymentAction = paymentAction;
    }
}
