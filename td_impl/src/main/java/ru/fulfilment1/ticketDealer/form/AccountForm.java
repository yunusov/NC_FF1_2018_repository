package ru.fulfilment1.ticketDealer.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class AccountForm {

    @Size(min = 4, max = 16, message = "Имя пользователя должно быть в диапазоне от 4 до 16 символов")
    private String username;
    @Email(message = "Неправильный формат электронной почты")
    private String email;
    @Size(min = 4, max = 16, message = "Пароль должен быть в диапазоне от 4 до 16 символов")
    private String password;
    private String confirmPassword;

    public AccountForm() {

    }

    public AccountForm(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
