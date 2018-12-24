package ru.fulfilment1.ticketDealer.form;

import org.springframework.format.annotation.DateTimeFormat;
import ru.fulfilment1.ticketDealer.entity.Passenger;
import java.time.LocalDate;

public class PassengerForm {

    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private String citizenShip;
    private int documentNo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate documentExpiry;

    public PassengerForm() {

    }

    public PassengerForm(Passenger passenger) {
        this.id = passenger.getId();
        this.firstName = passenger.getFirstName();
        this.lastName = passenger.getLastName();
        this.middleName = passenger.getMiddleName();
        this.sex = passenger.getSex();
        this.birthday = passenger.getBirthday();
        this.citizenShip = passenger.getCitizenShip();
        this.documentNo = passenger.getDocumentNo();
        this.documentExpiry = passenger.getDocumentExpiry();
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

    public String getCitizenShip() {
        return citizenShip;
    }

    public void setCitizenShip(String citizenShip) {
        this.citizenShip = citizenShip;
    }

    public int getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(int documentNo) {
        this.documentNo = documentNo;
    }

    public LocalDate getDocumentExpiry() {
        return documentExpiry;
    }

    public void setDocumentExpiry(LocalDate documentExpiry) {
        this.documentExpiry = documentExpiry;
    }

}
