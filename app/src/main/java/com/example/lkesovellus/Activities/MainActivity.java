package com.example.lkesovellus.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lkesovellus.classes.Drug;
import com.example.lkesovellus.classes.DrugData;
import com.example.lkesovellus.R;
/**
 * Luokka luo etusivu näkymän
 * @author Roope Kylli, Topias Koskinen, Emil Suuronen
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    public static final String EXTRA = "list position";
    private Button addButton;               // painike jolla lääkkeitä voi lisätä ja
    private ListView lv;                    //päänäkymän lista johon lääkkeet lisätään
    public TextView tl;
    /**
     * Nappi muistiinpano näkymään
     */
    private Button memoActivityButton;
    /**
     * Kutsutaan aktiviteetiin luonnin yhteydessä
     */
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
                this, R.layout.mytextview, DrugData.getInstance().getDrugs()
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

        createNotificationChannel();                                                                // metodia kutsutaan, kun laite avataan

        memoActivityButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Klikatessa avaa näkymän memoActivity
             * @param view on mainactivy näkymä
             */
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

    /**
     * Metodi luo kanavan reminderChannel muistutukselle
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        CharSequence name = "Lääkesovellus";                                                        // Channelin nimi
        String description = "Channel 1";                                                           // Channelin kuvaus
        int importance = NotificationManager.IMPORTANCE_HIGH;                                       // Importance level. IMPORTANCE_HIGH = ilmoitus tekee äänen ja näkyy kaikkialla puhelimessa
        NotificationChannel channel = new NotificationChannel("channel1", name, importance);     // Luodaan olio muuttuja channel, jolla on id, nimi ja importance level
        channel.setDescription(description);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);                                     // Rakentaa kanavan olio channelin arvoilla
    }
}
