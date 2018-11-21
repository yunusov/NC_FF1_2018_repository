package ru.fulfilment1.ticketDealer.entity;

import javax.persistence.GeneratedValue;

public class Login
{
    @GeneratedValue
    public String name;
    public String passwd;
}
