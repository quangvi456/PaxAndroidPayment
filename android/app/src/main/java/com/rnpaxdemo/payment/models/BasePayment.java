package com.rnpaxdemo.payment.models;

public class BasePayment {
    protected String transType = "SALE";
    protected String tenderType = "CREDIT";
    protected String ecrRefNum = "1";

    public BasePayment() {
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public void setTenderType(String tenderType) {
        this.tenderType = tenderType;
    }

    public void setEcrRefNum(String ecrRefNum) {
        this.ecrRefNum = ecrRefNum;
    }

    public String getTransType() {
        return transType;
    }

    public String getTenderType() {
        return tenderType;
    }

    public String getEcrRefNum() {
        return ecrRefNum;
    }
}
