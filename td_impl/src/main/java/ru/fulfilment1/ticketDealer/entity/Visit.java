package ru.fulfilment1.ticketDealer.entity;

import javax.persistence.*;

@Entity
public class Visit
{
    @Id
    @GeneratedValue
    public Long id;
    public String description;
}