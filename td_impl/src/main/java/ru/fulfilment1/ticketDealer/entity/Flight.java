package ru.fulfilment1.ticketDealer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int flightNo;

    public Flight() {
    }

    public Flight(int flightNo) {
        this.flightNo = flightNo;
    }

    public long getId() {
        return id;
    }

    public int getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(int flightNo) {
        this.flightNo = flightNo;
    }
}
