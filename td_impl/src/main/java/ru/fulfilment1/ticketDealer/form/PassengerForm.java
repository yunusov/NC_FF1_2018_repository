package ru.fulfilment1.ticketDealer.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import ru.fulfilment1.ticketDealer.entity.Passenger;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class PassengerForm {

    final private String NAME_REGEX = "[a-zA-Zа-яА-Я]+";

    @Pattern(regexp = NAME_REGEX, message = "Имя должно состоять только из букв")
    private String firstName;
    @Pattern(regexp = NAME_REGEX, message = "Фамилия должна состоять только из букв")
    private String lastName;
    @Pattern(regexp = NAME_REGEX, message = "Отчество должно состоять только из букв")
    private String middleName;
    private String sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Дата рождения указана неверно")
    private LocalDate birthday;
    private String citizenship;
    @Size(min = 10, max = 10, message = "Номер паспорта должен состоять из 10 цифр")
    @Pattern(regexp = "\\d+", message = "Номер паспорта должен состоять только из цифр")
    private String documentNo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Срок действия документа указан неверно")
    private LocalDate documentExpiry;

    public PassengerForm() {

    }

    public PassengerForm(Passenger passenger) {
        this.firstName = passenger.getFirstName();
        this.lastName = passenger.getLastName();
        this.middleName = passenger.getMiddleName();
        this.sex = passenger.getSex();
        this.birthday = passenger.getBirthday();
        this.citizenship = passenger.getCitizenship();
        this.documentNo = passenger.getDocumentNo();
        this.documentExpiry = passenger.getDocumentExpiry();
    }

    public void setAll(Passenger passenger) {
        this.firstName = passenger.getFirstName();
        this.lastName = passenger.getLastName();
        this.middleName = passenger.getMiddleName();
        this.sex = passenger.getSex();
        this.birthday = passenger.getBirthday();
        this.citizenship = passenger.getCitizenship();
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

}
