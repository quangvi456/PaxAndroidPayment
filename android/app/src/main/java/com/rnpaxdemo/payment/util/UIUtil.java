package com.rnpaxdemo.payment.util;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.EditText;
import android.widget.Toast;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by linhb on 2015-08-21.
 */
public class UIUtil {
    public static int findStringId(String[] arr, String targetValue) {
        return Arrays.asList(arr).indexOf(targetValue);
    }

    public static String paddingLine(String left, String right) {
        return "<tr><td align=\"left\">" + left + "</td><td align=\"right\">" + right + "</td></tr>";
    }

    public static String paddingLine(String text) {
        return "<tr><td colspan=\"2\" align=\"center\">" + text + "</td></tr>";
    }

    /**
     * Parse XML with dom4j
     * 
     * @param data xml data
     * @param node node
     * @return value
     */
    public static String findXMl(String data, String node) {
        String extData = "<root>" + data + "</root>";
        ByteArrayInputStream input;
        input = new ByteArrayInputStream(extData.getBytes());
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(input);
            Element eleRoot = document.getRootElement();
            Iterator iterator = eleRoot.elementIterator();
            while (iterator.hasNext()) {
                Element ele = (Element) iterator.next();
                if (node.equals(ele.getName())) {
                    return ele.getText();
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * get value from input text
     *
     * @param edt ""
     * @param def if edt is null, or with no value
     * @return input value
     */
    public static String getStringFromEdit(EditText edt, String def) {
        if (null == edt) {
            return def;
        }
        String value = edt.getText().toString();
        if (value.length() == 0)
            return def;
        return value;
    }

    public static float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static void showToast(Context context, final String msg, final int time) {
        final WeakReference<Context> contextWeakReference = new WeakReference<>(context);
        Runnable run = new Runnable() {
            @Override
            public void run() {
                Context ctx = contextWeakReference.get();
                if (ctx != null) {
                    Toast toast = Toast.makeText(ctx, msg, time);
                    toast.show();
                }
            }
        };
        // Ensure that toast only show in main thread
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(run);
        } else {
            Handler mHandler = new Handler(Looper.getMainLooper());
            mHandler.post(run);
        }
    }
}
