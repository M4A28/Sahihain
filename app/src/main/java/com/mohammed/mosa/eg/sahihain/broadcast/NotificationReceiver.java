package com.mohammed.mosa.eg.sahihain.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mohammed.mosa.eg.sahihain.utilty.Hadith;
import com.mohammed.mosa.eg.sahihain.utilty.Notifications;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Hadith hadith = Hadith.getInstance(context);
//        Notifications.showNotification(context, "حديث اليوم", hadith.toString());
        Notifications.ShowCoustomNotification(context, hadith);
    }
}