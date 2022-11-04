package com.mohammed.mosa.eg.sahihain.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.mohammed.mosa.eg.sahihain.database.DatabaseAccess;
import com.mohammed.mosa.eg.sahihain.utilty.Hadith;
import com.mohammed.mosa.eg.sahihain.utilty.Notifications;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;

public class FridayService extends Service {
    public FridayService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ArrayList<Hadith> fridayHadith = new ArrayList<>();
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        fridayHadith = databaseAccess.search("الجمعة");
        databaseAccess.close();
        SecureRandom random = new SecureRandom();

        int result = random.nextInt(fridayHadith.size()) + 1;
        Hadith hadith = fridayHadith.get(result);
        Calendar calendar = Calendar.getInstance();

        if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
            Notifications.showNotification(this, "احاديث يوم الجمعة", hadith.toString());

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}