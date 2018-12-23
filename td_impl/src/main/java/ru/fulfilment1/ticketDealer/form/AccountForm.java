package ru.fulfilment1.ticketDealer.form;

import ru.fulfilment1.ticketDealer.entity.Account;

public class AccountForm {

    private long id;
    private String username;
    private String password;
    private String secondPassword;
    private String email;

    public AccountForm() {

    }

    public AccountForm(Account account) {
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.email = account.getEmail();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecondPassword() {
        return secondPassword;
    }

    public void setSecondPassword(String secondPassword) {
        this.secondPassword = secondPassword;
    }
}

