package com.gluonhq.helloandroid;

import android.app.Activity;
import android.util.Log;

public class DalvikSmsService {
    private static final String TAG = "DalvikSmsService";
    private final Activity activity;

    public DalvikSmsService(Activity activity) {
        this.activity = activity;
    }

    public static void receiveSms(String phone, String sms) {
        Log.i(TAG, String.format("sms receiced, phone: %s, sms: %s", phone, sms));
        processReceivedSms(phone, sms);
    }
    
    private static native void processReceivedSms(String key, String value);
}
