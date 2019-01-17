package ru.fulfilment1.ticketDealer.form;

import org.springframework.format.annotation.DateTimeFormat;
import ru.fulfilment1.ticketDealer.entity.PaymentType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class PaymentSearch {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Начальная дата должна быть не больше, чем текущая")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Конечная дата должна быть не меньше, чем текущая")
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    public PaymentSearch() {
    }

    public PaymentSearch(LocalDate startDate, LocalDate endDate, PaymentType paymentType) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.paymentType = paymentType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
