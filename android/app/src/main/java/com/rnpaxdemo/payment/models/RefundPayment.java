package com.rnpaxdemo.payment.models;

public class RefundPayment extends BasePayment {
    private String amount = "0";

    public void setAmount(double amount) {
        this.amount = String.valueOf(Math.round(amount * 100));
    }
    public String getAmount() {
        return amount;
    }
}
