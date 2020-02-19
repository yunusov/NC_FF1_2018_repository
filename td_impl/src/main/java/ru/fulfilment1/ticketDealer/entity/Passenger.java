package ru.fulfilment1.ticketDealer.entity;

import org.springframework.format.annotation.DateTimeFormat;
import ru.fulfilment1.ticketDealer.form.PassengerForm;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private String citizenship;
    private String documentNo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate documentExpiry;

    @OneToOne
    private Account account;

    public Passenger() {

    }

    public Passenger(String firstName, String lastName, String middleName, String sex, LocalDate birthday, String citizenship, String documentNo, LocalDate documentExpiry, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.sex = sex;
        this.birthday = birthday;
        this.citizenship = citizenship;
        this.documentNo = documentNo;
        this.documentExpiry = documentExpiry;
        this.account = account;
    }

    public void setAll(PassengerForm passengerForm) {
        this.firstName = passengerForm.getFirstName();
        this.lastName = passengerForm.getLastName();
        this.middleName = passengerForm.getMiddleName();
        this.sex = passengerForm.getSex();
        this.birthday = passengerForm.getBirthday();
        this.citizenship = passengerForm.getCitizenship();
        this.documentNo = passengerForm.getDocumentNo();
        this.documentExpiry = passengerForm.getDocumentExpiry();
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public LocalDate getDocumentExpiry() {
        return documentExpiry;
    }

    public void setDocumentExpiry(LocalDate documentExpiry) {
        this.documentExpiry = documentExpiry;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
