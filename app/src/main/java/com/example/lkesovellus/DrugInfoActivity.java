package com.example.lkesovellus;

import static android.app.AlarmManager.RTC_WAKEUP;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DrugInfoActivity extends AppCompatActivity {

    private TextView drugName;
    private TextView drugAmount;
    private TextView drugPrice;
    private int taken;
    private TextView number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_info);

        drugName = findViewById(R.id.drugNameTextView);
        drugAmount = findViewById(R.id.drugAmountTextView);
        drugPrice = findViewById(R.id.drugPriceTextView);
        taken = 0;
        number = findViewById(R.id.inputHour);


        Bundle b = getIntent().getExtras();
        int i = b.getInt(MainActivity.EXTRA, 0);

        drugName.setText(Global.getInstance().getDrugs().get(i).getDrugName());
        drugAmount.setText(("Doses left: " + Global.getInstance().getDrugs().get(i).getDrugAmount()) + "/" +
                Global.getInstance().getDrugs().get(i).getDrugAmount());
        drugPrice.setText(Global.getInstance().getDrugs().get(i).getDrugPrice() + "€, Cost per dose:" +
                (Global.getInstance().getDrugs().get(i).getDrugAmount() / Global.getInstance().getDrugs().get(i).getDrugPrice()) + "€");

        Button btSetAlarm2 = findViewById(R.id.btSetAlarm2);

        btSetAlarm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Muistutus asetettu", Toast.LENGTH_SHORT).show();
                int hour = Integer.parseInt(number.getText().toString());                                                                            // Rivin perään listään "* 60 * 60:", jotta tulee tunnit.

                Intent intent = new Intent(DrugInfoActivity.this, ReminderBroadcast.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(DrugInfoActivity.this, 0, intent, 0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                long buttonClickTime = System.currentTimeMillis();
                long dayInMillisec = 1000 * hour;

                alarmManager.set(RTC_WAKEUP, buttonClickTime + dayInMillisec, pendingIntent);
            }
        });

        Button btCancelAlarm2 = findViewById(R.id.btCancelAlarm2);

        btCancelAlarm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Muistutus poistettu", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DrugInfoActivity.this, ReminderBroadcast.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(DrugInfoActivity.this, 0, intent, 0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.cancel(pendingIntent);
            }
        });
    }


}