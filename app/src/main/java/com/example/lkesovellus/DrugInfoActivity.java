package com.example.lkesovellus;

import static android.app.AlarmManager.RTC_WAKEUP;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class DrugInfoActivity extends AppCompatActivity {

    private TextView drugName;
    private TextView drugAmount;
    private TextView drugPrice;
    private int taken;
    int infoAmountOfDrug;
    double infoPriceOfDrug;
    private ProgressBar amountProgress;
    int i;
    private EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_info);

        drugName = findViewById(R.id.drugNameTextView);
        drugAmount = findViewById(R.id.drugAmountTextView);
        drugPrice = findViewById(R.id.drugPriceTextView);
        amountProgress = findViewById(R.id.amountProgressBar);
        Log.d("TAG", "OnCreate()");
        Bundle b = getIntent().getExtras();
        int i = b.getInt(MainActivity.EXTRA, 0);

        int infoAmountOfDrug = Global.getInstance().getDrugs().get(i).getDrugAmount();
        double infoPriceOfDrug = Global.getInstance().getDrugs().get(i).getDrugPrice();

        drugName.setText(Global.getInstance().getDrugs().get(i).getDrugName());
        drugAmount.setText("Doses left: " + (infoAmountOfDrug - 1) + "/" + infoAmountOfDrug);
        drugPrice.setText(infoPriceOfDrug + "€, Cost per dose: " + (infoPriceOfDrug / infoAmountOfDrug) + "€");
        amountProgress.setMax(infoAmountOfDrug);
        amountProgress.setProgress(infoAmountOfDrug);

        Button btSetAlarm2 = findViewById(R.id.btSetAlarm2);

        btSetAlarm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Muistutus asetettu", Toast.LENGTH_SHORT).show();
                number = findViewById(R.id.inputHour);
                int hours = Integer.parseInt(number.getText().toString());

                Intent intent = new Intent(DrugInfoActivity.this, ReminderBroadcast.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(DrugInfoActivity.this, 0, intent, 0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                long buttonClickTime = System.currentTimeMillis();
                long dayInMillisecond = 1000 * hours;

                alarmManager.set(RTC_WAKEUP, buttonClickTime + dayInMillisecond, pendingIntent);
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
    public void takeDrugButtonOnClick(View v) {
        infoAmountOfDrug = infoAmountOfDrug - 1;
        Log.d("TAG", String.valueOf(infoAmountOfDrug));
        Global.getInstance().getDrugs().get(i).setAmount(infoAmountOfDrug);
        amountProgress.setProgress(infoAmountOfDrug);
        drugAmount.setText(String.valueOf(infoAmountOfDrug - 1));
        Toast.makeText(getApplicationContext(),Global.getInstance().getDrugs().get(i).getDrugName() +
                " taken",Toast.LENGTH_SHORT).show();
    }

}
