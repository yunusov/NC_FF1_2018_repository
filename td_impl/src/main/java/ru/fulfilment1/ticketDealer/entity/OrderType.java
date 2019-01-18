package ru.fulfilment1.ticketDealer.entity;

public enum OrderType {
    BOOKING("Бронирование"),
    CANCEL("Возврат");

    private String desc;

    OrderType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
