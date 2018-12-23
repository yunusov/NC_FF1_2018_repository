package ru.fulfilment1.ticketDealer.entity;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Ticket {

    @Id
    @GeneratedValue
    private long id;
    private int seat;
    private int gate;
    private int price;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh-mm")
    private LocalDateTime departureTime;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalTime flightTime;

    @ManyToOne(optional = true)
    private Passenger passenger;
    @ManyToOne(optional = true)
    private Flight flight;
    @ManyToOne(optional = false)
    private Airline airline;
    @ManyToOne(optional = false)
    private Airport departureAirport;
    @ManyToOne(optional = false)
    private Airport arrivalAirport;

    public Ticket() {
    }

    public Ticket(int seat, int gate, LocalDateTime departureTime, LocalTime flightTime) {
        this.seat = seat;
        this.gate = gate;
        this.departureTime = departureTime;
        this.flightTime = flightTime;
    }

    public long getId() {
        return id;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getGate() {
        return gate;
    }

    public void setGate(int gate) {
        this.gate = gate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(LocalTime flightTime) {
        this.flightTime = flightTime;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }


}
