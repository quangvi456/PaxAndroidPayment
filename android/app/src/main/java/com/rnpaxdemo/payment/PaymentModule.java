package com.rnpaxdemo.payment;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;

import com.facebook.react.bridge.ReadableMap;
import com.pax.poslink.CommSetting;
import com.rnpaxdemo.payment.models.AdjustPayment;
import com.rnpaxdemo.payment.models.RefundPayment;
import com.rnpaxdemo.payment.models.SalePayment;
import com.rnpaxdemo.payment.models.VoidPayment;
import com.rnpaxdemo.payment.services.CustomPOSLinkServices;
import com.rnpaxdemo.payment.services.PaymentServices;

public class PaymentModule extends ReactContextBaseJavaModule {
    private ReactApplicationContext context;

    PaymentModule(ReactApplicationContext context) {
        super(context);

        this.context = context;
    }

    @Override
    public String getName() {
        return "PaymentModule";
    }

    @ReactMethod
    public void init(ReadableMap options, Promise promise) {
        if (CustomPOSLinkServices.getInstance().getPoslink() != null) {
            promise.reject("Already initialize POSlink.");
            return;
        }

        CommSetting commSetting = new CommSetting();
        if (options.hasKey("type")) {
            commSetting.setType(options.getString("type"));
        }

        if (options.hasKey("timeOut")) {
            commSetting.setTimeOut(String.valueOf(options.getInt("timeOut")));
        }

        if (options.hasKey("serialPort")) {
            commSetting.setSerialPort(options.getString("serialPort"));
        }

        if (options.hasKey("baudRate")) {
            commSetting.setBaudRate(options.getString("baudRate"));
        }

        if (options.hasKey("destIP")) {
            commSetting.setDestIP(options.getString("destIP"));
        }

        if (options.hasKey("destPort")) {
            commSetting.setDestPort(options.getString("destPort"));
        }

        if (options.hasKey("enableProxy")) {
            commSetting.setEnableProxy(options.getBoolean("enableProxy"));
        }

        CustomPOSLinkServices.getInstance().init(this.context, commSetting);
//        InitializePOSLinkResult result = new InitializePOSLinkResult();
//        result.Message = "Initialize successful.";
//        result.IsSuccess = true;
        promise.resolve("Initialize successful.");
    }

    @ReactMethod
    public void sale(ReadableMap options, Promise promise) {
        if (CustomPOSLinkServices.getInstance().getPoslink() == null) {
            promise.reject("Please initialize POSlink first.");
            return;
        }

        SalePayment input = new SalePayment();
        if (options.hasKey("amount")) {
            input.setAmount(options.getDouble("amount"));
        }
        if (options.hasKey("tip")) {
            input.setTip(options.getDouble("tip"));
        }
        if (options.hasKey("transType")) {
            input.setTransType(options.getString("transType"));
        }
        if (options.hasKey("tenderType")) {
            input.setTenderType(options.getString("tenderType"));
        }
        if (options.hasKey("ecrRefNum")) {
            input.setEcrRefNum(options.getString("ecrRefNum"));
        }

        PaymentServices.getInstance().sale(input, promise);
    }

    @ReactMethod
    public void voidPayment(ReadableMap options, Promise promise) {
        if (CustomPOSLinkServices.getInstance().getPoslink() == null) {
            promise.reject("Please initialize POSlink first.");
            return;
        }

        VoidPayment input = new VoidPayment();
        if (options.hasKey("refNum")) {
            input.setOrgRefNum(options.getString("refNum"));
        }
        if (options.hasKey("transType")) {
            input.setTransType(options.getString("transType"));
        }
        if (options.hasKey("tenderType")) {
            input.setTenderType(options.getString("tenderType"));
        }
        if (options.hasKey("ecrRefNum")) {
            input.setEcrRefNum(options.getString("ecrRefNum"));
        }

        PaymentServices.getInstance().voidPayment(input, promise);
    }

    @ReactMethod
    public void closeBatch(Promise promise) {
        if (CustomPOSLinkServices.getInstance().getPoslink() == null) {
            promise.reject("Please initialize POSlink first.");
            return;
        }

        PaymentServices.getInstance().closeBatch(promise);
    }

    @ReactMethod
    public void cancel(Promise promise) {
        if (CustomPOSLinkServices.getInstance().getPoslink() == null) {
            promise.reject("Please initialize POSlink first.");
            return;
        }

        PaymentServices.getInstance().cancel(promise);
    }

    @ReactMethod
    public void adjust(ReadableMap options, Promise promise) {
        if (CustomPOSLinkServices.getInstance().getPoslink() == null) {
            promise.reject("Please initialize POSlink first.");
            return;
        }

        AdjustPayment input = new AdjustPayment();
        if (options.hasKey("refNum")) {
            input.setOrgRefNum(options.getString("refNum"));
        }
        if (options.hasKey("transType")) {
            input.setTransType(options.getString("transType"));
        }
        if (options.hasKey("tenderType")) {
            input.setTenderType(options.getString("tenderType"));
        }
        if (options.hasKey("ecrRefNum")) {
            input.setEcrRefNum(options.getString("ecrRefNum"));
        }
        if (options.hasKey("amount")) {
            input.setAmount(options.getDouble("amount"));
        }

        PaymentServices.getInstance().adjust(input, promise);
    }

    @ReactMethod
    public void refund(ReadableMap options, Promise promise) {
        if (CustomPOSLinkServices.getInstance().getPoslink() == null) {
            promise.reject("Please initialize POSlink first.");
            return;
        }

        RefundPayment input = new RefundPayment();
        if (options.hasKey("transType")) {
            input.setTransType(options.getString("transType"));
        }
        if (options.hasKey("tenderType")) {
            input.setTenderType(options.getString("tenderType"));
        }
        if (options.hasKey("ecrRefNum")) {
            input.setEcrRefNum(options.getString("ecrRefNum"));
        }
        if (options.hasKey("amount")) {
            input.setAmount(options.getDouble("amount"));
        }

        PaymentServices.getInstance().refund(input, promise);
    }
}