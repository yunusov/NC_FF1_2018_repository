package ru.fulfilment1.ticketDealer.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate date;
    @ManyToOne
    private Account account;
    @ManyToOne
    private Ticket ticket;
    @Enumerated(EnumType.STRING)
    private OrderType type;

    public Order() {
    }

    public Order(LocalDate date, Account account, Ticket ticket, OrderType type) {
        this.date = date;
        this.account = account;
        this.ticket = ticket;
        this.type = type;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }
}
