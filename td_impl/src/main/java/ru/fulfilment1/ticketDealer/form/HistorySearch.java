package ru.fulfilment1.ticketDealer.form;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class HistorySearch {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Начальная дата должна быть не больше, чем текущая")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Конечная дата должна быть не меньше, чем текущая")
    private LocalDate endDate;

    public HistorySearch() {
    }

    public HistorySearch(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
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
}
