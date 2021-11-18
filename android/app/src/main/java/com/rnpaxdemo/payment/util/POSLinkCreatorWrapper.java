package com.rnpaxdemo.payment.util;

import android.content.Context;
import android.util.Log;

import com.rnpaxdemo.payment.util.thread.AppThreadPool;
import com.pax.poslink.PosLink;
import com.pax.poslink.poslink.POSLinkCreator;

/**
 * Created by Leon on 2017/4/24.
 */

public class POSLinkCreatorWrapper {

    private static PosLink create(Context context) {
        return POSLinkCreator.createPoslink(context);
    }

    public static void createSync(final Context context,
            final AppThreadPool.FinishInMainThreadCallback<PosLink> callback) {
        Log.i("DEBUG", "Start Create POSLink");
        callback.onFinish(POSLinkCreatorWrapper.create(context));
        Log.i("DEBUG", "Finish Create POSLink");

    }
}
