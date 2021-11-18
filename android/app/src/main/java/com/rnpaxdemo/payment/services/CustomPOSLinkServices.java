package com.rnpaxdemo.payment.services;

import com.facebook.react.bridge.ReactApplicationContext;
import com.pax.poslink.CommSetting;
import com.pax.poslink.PosLink;
import com.pax.poslink.poslink.POSLinkCreator;

public class CustomPOSLinkServices {

    private static CustomPOSLinkServices instance;
    private static PosLink poslink;

    public static CustomPOSLinkServices getInstance() {
        if (instance == null) {
            instance = new CustomPOSLinkServices();
        }

        return instance;
    }

    public void init(ReactApplicationContext context, CommSetting commSetting) {
        poslink = POSLinkCreator.createPoslink(context);
        poslink.SetCommSetting(commSetting);
    }

    public PosLink getPoslink() {
        return poslink;
    }
}
