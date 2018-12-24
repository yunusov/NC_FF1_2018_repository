package ru.fulfilment1.ticketDealer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Airline {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    public Airline() {
    }

    public Airline(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}