package com.rnpaxdemo.payment.models;

public class SalePayment extends BasePayment {
    private String amount = "0";
    private String tip = "0";

    public void setAmount(double amount) {
        this.amount = String.valueOf(Math.round(amount * 100));
    }

    public void setTip(double tip) {
        this.tip = String.valueOf(Math.round(tip * 100));
    }

    public String getAmount() {
        return amount;
    }

    public String getTip() {
        return tip;
    }
}
