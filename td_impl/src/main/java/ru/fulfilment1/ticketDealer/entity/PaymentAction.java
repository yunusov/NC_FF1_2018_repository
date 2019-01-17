package ru.fulfilment1.ticketDealer.entity;

public enum PaymentAction {
    TICKET_CANCEL("Отмена бронирования"),
    TICKET_BOOKING("Бронирование билета"),
    REG_BONUS("Бонус за регистрацию"),
    DEPOSIT("Депозит"),
    WITHDRAW("Вывод со счёта");

    private String name;

    PaymentAction(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}
