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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA = "list position";
    private Button addButton;               // painike jolla lääkkeitä voi lisätä ja
    private ListView lv;                    //päänäkymän lista johon lääkkeet lisätään
    public TextView tl;
    private Button memoActivityButton;
    int i;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.listAddButton);
        lv = findViewById(R.id.drugsListView);
        memoActivityButton = findViewById(R.id.memoActivityButton);

        //asettaa aloitusnäkymän ListViewille lv ArrayAdapterin ja hakee tiedot listasta drugsList
        lv.setAdapter(new ArrayAdapter<Drug>(
                this, R.layout.mytextview, Global.getInstance().getDrugs()
        ));

        //ListView lv kuuntelija joka vaihtaa aktiviteetin drugInfoActivityyn
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent drugInfoActivityWindow = new Intent(MainActivity.this, DrugInfoActivity.class);
                drugInfoActivityWindow.putExtra(EXTRA, i);
                startActivity(drugInfoActivityWindow);
            }
        });

        createNotificationChannel();

        memoActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent memoActivity = new Intent(MainActivity.this, MemoActivity.class);
                startActivity(memoActivity);
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
