package ru.fulfilment1.ticketDealer.entity;

public enum PaymentType {
    WITHDRAW("Списание"),
    DEPOSIT("Пополнение"),
    ALL("");

    private String desc;

    PaymentType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
