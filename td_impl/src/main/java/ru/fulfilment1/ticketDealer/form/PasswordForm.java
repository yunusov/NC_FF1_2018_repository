package ru.fulfilment1.ticketDealer.form;

import javax.validation.constraints.Size;

public class PasswordForm {
    private String oldPassword;
    @Size(min = 4, max = 16, message = "Новый пароль должен быть в диапазоне от 4 до 16 символов")
    private String newPassword;
    private String confirmPassword;

    public PasswordForm() {
    }

    public PasswordForm(String oldPassword, String newPassword, String confirmPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
