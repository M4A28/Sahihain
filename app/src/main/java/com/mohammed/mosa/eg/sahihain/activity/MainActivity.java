package com.mohammed.mosa.eg.sahihain.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mohammed.mosa.eg.sahihain.R;
import com.mohammed.mosa.eg.sahihain.adapter.MainMenuAdapter;
import com.mohammed.mosa.eg.sahihain.broadcast.NotificationReceiver;
import com.mohammed.mosa.eg.sahihain.service.FridayService;
import com.mohammed.mosa.eg.sahihain.utilty.MainMenuItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(this.getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custum_actionbar_layout);

        View view = getSupportActionBar().getCustomView();
        TextView name = view.findViewById(R.id.name);
        name.setText("القائمة الرئيسية");

        NotificationReceiver notificationReceiver = new NotificationReceiver();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BOOT_COMPLETED);
        registerReceiver(notificationReceiver, intentFilter);
        notificationAlarm();
        startService(new Intent(this, FridayService.class));

        ArrayList<MainMenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MainMenuItem("بحث",
                "بحث عن احاديث في الصحيحين",
                R.drawable.ic_search,
                K -> { Intent intent = new Intent(getBaseContext(), SearchActivity.class);
                startActivity(intent);
        }));

        menuItems.add(new MainMenuItem("الصحيحين",
                "جميع احاديث الصحيحين",
                R.drawable.book,
                K -> { Intent intent = new Intent(getBaseContext(), SahihaenActivity.class);
                startActivity(intent);
        }));

        menuItems.add(new MainMenuItem("الاربعين النووية",
                "احاديث الامام النووي رحمه الله",
                R.drawable.ic_arbaeen_book,
                K -> { Intent intent = new Intent(getBaseContext(), ArbaeenActivity.class);
                    startActivity(intent);
                }));

        menuItems.add(new MainMenuItem("احاديث لا تصح",
                "احاديث منتشرة ضعيفة او مكذوبة",
                R.drawable.ic_not,
                K -> { Intent intent = new Intent(getBaseContext(), FakeHadithActivity.class);
                    startActivity(intent);
                }));

        menuItems.add(new MainMenuItem("حديث اليوم",
                "اختيارنا اليومي من الاحاديث",
                R.drawable.ic_hadit_of_day,
                K -> { Intent intent = new Intent(getBaseContext(), HadithOfDay.class);
                startActivity(intent);
        }));

        menuItems.add(new MainMenuItem("المفضلة",
                "جميع الاحاديث التي تفضلها",
                R.drawable.ic_bookmark,
               K -> { Intent intent = new Intent(getBaseContext(), FavourtActivty.class);
                startActivity(intent);
        }));

        menuItems.add(new MainMenuItem("عنا",
                "معلومات عن مطور التطبيق",
                R.drawable.ic_info,
                K -> { Intent intent = new Intent(getBaseContext(), AboutActivity.class);
                startActivity(intent);
        }));

        RecyclerView recyclerView = findViewById(R.id.menu_rv);
        MainMenuAdapter mainMenuAdapter = new MainMenuAdapter(menuItems,this);
        recyclerView.setAdapter(mainMenuAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void notificationAlarm(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 30);
        if(calendar.getTime().compareTo(new Date()) < 0){
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if(alarmManager != null)
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent);
    }

}
