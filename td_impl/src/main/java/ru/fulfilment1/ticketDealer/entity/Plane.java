package ru.fulfilment1.ticketDealer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Plane {
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private int seatsQuantity;

    @ManyToOne(optional = true)
    private Airline airline;

    public Plane() {
    }

    public Plane(String name, int seatsQuantity, Airline airline) {
        this.name = name;
        this.seatsQuantity = seatsQuantity;
        this.airline = airline;
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

    public int getSeatsQuantity() {
        return seatsQuantity;
    }

    public void setSeatsQuantity(int seatsQuantity) {
        this.seatsQuantity = seatsQuantity;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}
