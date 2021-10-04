package com.example.lkesovellus;

import static android.app.AlarmManager.*;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button addButton;               // painike jolla lääkkeitä voi lisätä ja
    private ListView lv;                    //päänäkymän lista johon lääkkeet lisätään
    List<Drug> drugsList;                   // Lista johon lääkkeet lisätään (tulee olla myöhemmin <Drug> tyyppiä)
    public TextView tl;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.listAddButton);
        lv = findViewById(R.id.drugsListView);
        tl = findViewById(R.id.testilaatikko);

        drugsList = Global.getInstance().getDrugs();

        //asettaa aloitusnäkymän ListViewille lv ArrayAdapterin ja hakee tiedot listasta drugsList
        lv.setAdapter(new ArrayAdapter<Drug>(
                this, android.R.layout.simple_list_item_1, drugsList
        ));

        //ListView lv kuuntelija joka vaihtaa aktiviteetin drugInfoActivityyn
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent drugInfoActivityWindow = new Intent(MainActivity.this, DrugInfoActivity.class);
                startActivity(drugInfoActivityWindow);
            }
        });

        createNotificationChannel();


        Button btSetAlarm = findViewById(R.id.btSetAlarm);

        btSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Muistutus asetettu", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, ReminderBroadcast.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                long buttonClickTime = System.currentTimeMillis();
                long dayInMillisec = 1000 * 10;

                alarmManager.set(RTC_WAKEUP, buttonClickTime + dayInMillisec, pendingIntent);
            }
        });

        Button btCancelAlarm = findViewById(R.id.btCancelAlarm);

        btCancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Muistutus poistettu", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ReminderBroadcast.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.cancel(pendingIntent);
            }
        });


    }

    // lisäyspainikkeen metodi joka vaihtaa aktiviteettiin drugAddActivity
    public void onAddButtonClick(View v) {
        Intent drugAddActivityWindow = new Intent(MainActivity.this, DrugAddActivity.class);
        startActivity(drugAddActivityWindow);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
            CharSequence name = "Lääkesovellus";
            String description = "Channel 1";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("channel1", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
    }

}