package com.rnpaxdemo.payment.models;

public class AdjustPayment extends BasePayment {
    private String amount = "0";
    private String orgRefNum = "";

    public void setAmount(double amount) {
        this.amount = String.valueOf(Math.round(amount * 100));
    }
    public String getAmount() {
        return amount;
    }

    public void setOrgRefNum(String refNum) {
        this.orgRefNum = refNum;
    }
    public String getOrgRefNum() {
        return orgRefNum;
    }
}
