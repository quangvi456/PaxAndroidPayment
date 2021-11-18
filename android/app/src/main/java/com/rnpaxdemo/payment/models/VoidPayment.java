package com.rnpaxdemo.payment.models;

public class VoidPayment extends BasePayment {
    private String orgRefNum = "";

    public void setOrgRefNum(String refNum) {
        this.orgRefNum = refNum;
    }

    public String getOrgRefNum() {
        return orgRefNum;
    }
}
