package ru.fulfilment1.ticketDealer.entity;

import javax.persistence.*;

@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    private City city;

    public Airport() {
    }

    public Airport(String name, City city) {
        this.name = name;
        this.city = city;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
