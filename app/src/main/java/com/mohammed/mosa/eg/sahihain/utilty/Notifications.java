package com.mohammed.mosa.eg.sahihain.utilty;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.mohammed.mosa.eg.sahihain.R;
import com.mohammed.mosa.eg.sahihain.activity.MainActivity;

public class Notifications {

    public static final String CHANNEL_ID = "com.mohammed.mosa.eg.sahihain";
    public static final String NOTIFICATION_NAME = "HadithOfDay";
    public static final int NOTIFICATION_ID = 999;

    public static void ShowCoustomNotification(Context context, Hadith hadith){
        NotificationChannel notificationChannel;
        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        PendingIntent contentIntent;
        contentIntent = PendingIntent.getActivity(context,
                0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT
                        | PendingIntent.FLAG_IMMUTABLE);
        NotificationManager mNotificationManager = (NotificationManager) context.
                getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(CHANNEL_ID, NOTIFICATION_NAME,
                    NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        Notification.Builder NotificationBuilder;

        // check Android API and do as needed
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationBuilder = new Notification.Builder(context, CHANNEL_ID);
        } else {
            NotificationBuilder = new Notification.Builder(context);
        }

        Notification.Builder mBuilder = NotificationBuilder;
        mBuilder.setSmallIcon(R.drawable.ic_notification_icon);
        mBuilder.setContent(getCoustomDesighin(context.getApplicationContext(), hadith));
        mBuilder.setContentIntent(contentIntent);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mBuilder.setChannelId(CHANNEL_ID);
        }
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());

    }
// TODO FIx this
    private static RemoteViews getCoustomDesighin(Context context, Hadith hadith) {
        RemoteViews remoteViews;
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_item);
        remoteViews.setTextViewText(R.id.tv_maten, hadith.getMaten());
        return remoteViews;
    }

    public static void showNotification(Context context, String title, String data) {
        NotificationChannel notificationChannel;
        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        PendingIntent contentIntent;
        contentIntent = PendingIntent.getActivity(context,
                0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT
                        | PendingIntent.FLAG_IMMUTABLE);
        NotificationManager mNotificationManager = (NotificationManager) context.
                getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(CHANNEL_ID, NOTIFICATION_NAME,
                    NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }

        Notification.BigTextStyle bigText = new Notification.BigTextStyle();
        bigText.bigText(data);
        Notification.Builder NotificationBuilder;

        // check Android API and do as needed
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationBuilder = new Notification.Builder(context, CHANNEL_ID);
        } else {
            NotificationBuilder = new Notification.Builder(context);
        }

        Notification.Builder mBuilder = NotificationBuilder;
        mBuilder.setSmallIcon(R.drawable.ic_notification_icon);
        mBuilder.setContentTitle(title);
        mBuilder.setContentText(data);
        mBuilder.setStyle(bigText);
        mBuilder.setOngoing(true);
        mBuilder.setAutoCancel(true);
        mBuilder.setContentIntent(contentIntent);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mBuilder.setChannelId(CHANNEL_ID);
        }
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

    // show notification normal way
    public static void showSmallNotification(Context context, String title, String data) {
        NotificationChannel notificationChannel;
        Intent notificationIntent = new Intent(context, MainActivity.class);
        Bundle bundle = new Bundle();
        notificationIntent.putExtras(bundle);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        PendingIntent contentIntent;
        contentIntent = PendingIntent.getActivity(context,
                0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT
                        | PendingIntent.FLAG_IMMUTABLE);
        NotificationManager mNotificationManager = (NotificationManager) context.
                getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(CHANNEL_ID, NOTIFICATION_NAME,
                    NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        Notification.Builder NotificationBuilder;

        // check Android API and do as needed
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationBuilder = new Notification.Builder(context, CHANNEL_ID);
        } else {
            NotificationBuilder = new Notification.Builder(context);
        }

        Notification.Builder mBuilder = NotificationBuilder;
        mBuilder.setSmallIcon(R.drawable.ic_notification_icon);
        mBuilder.setContentTitle(title);
        mBuilder.setContentText(data);
        mBuilder.setAutoCancel(true);
        mBuilder.setOngoing(true);
        mBuilder.setContentIntent(contentIntent);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mBuilder.setChannelId(CHANNEL_ID);
        }
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}
