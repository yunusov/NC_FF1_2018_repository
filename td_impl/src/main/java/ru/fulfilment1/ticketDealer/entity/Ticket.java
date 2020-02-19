package ru.fulfilment1.ticketDealer.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int seat;
    private String gate;
    private int price;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh-mm")
    private LocalDateTime departureDate;
    @DateTimeFormat(pattern = "yyyy-mm-dd hh-mm")
    private LocalDateTime arrivalDate;

    @ManyToOne
    private Passenger passenger;
    @ManyToOne
    private Flight flight;
    @ManyToOne(optional = false)
    private Airline airline;
    @ManyToOne(optional = false)
    private Airport departureAirport;
    @ManyToOne(optional = false)
    private Airport arrivalAirport;

    @Transient
    private LocalTime flightDuration;

    public Ticket() {
    }

    public Ticket(int seat, String gate, int price, LocalDateTime departureDate, LocalDateTime arrivalDate,
                  Passenger passenger, Flight flight, Airline airline, Airport departureAirport, Airport arrivalAirport) {
        this.seat = seat;
        this.gate = gate;
        this.price = price;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.passenger = passenger;
        this.flight = flight;
        this.airline = airline;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
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

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
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

    public LocalTime getFlightDuration() {
        Duration duration = Duration.between(departureDate, arrivalDate);

        return LocalTime.ofNanoOfDay(duration.toNanos());
    }
}
