package com.example.lkesovellus;

import static android.app.AlarmManager.RTC_WAKEUP;
import static java.lang.Math.round;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.text.style.UpdateLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class DrugInfoActivity extends AppCompatActivity {

    private TextView drugName;
    private TextView drugAmount;
    private TextView drugPrice;
    private int taken;
    int infoAmountOfDrug;
    double infoPriceOfDrug;
    private ProgressBar amountProgress;
    private int i;
    private Button addButton;
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
        final int[] infoAmountOfDrug = {Global.getInstance().getDrugs().get(i).getDrugAmount()};
        double infoPriceOfDrug = Global.getInstance().getDrugs().get(i).getDrugPrice();

        DecimalFormat moneyFormat = new DecimalFormat("0.00");

        drugName.setText(Global.getInstance().getDrugs().get(i).getDrugName());
        drugAmount.setText("Annoksia: " + (infoAmountOfDrug[0]) + "/" + infoAmountOfDrug[0]);
        drugPrice.setText(infoPriceOfDrug + "€, Hinta/kpl: " + moneyFormat.format(infoPriceOfDrug / infoAmountOfDrug[0]) + "€");
        amountProgress.setMax(infoAmountOfDrug[0]);
        amountProgress.setProgress(infoAmountOfDrug[0]);
        Log.d("TAG", String.valueOf(infoAmountOfDrug[0]));

        Button addButton = (Button) findViewById(R.id.takeDrugButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    if (infoAmountOfDrug[0] > 0) {
                        amountProgress.setProgress(infoAmountOfDrug[0]--);
                        taken++;
                        drugAmount.setText("Annoksia: " + infoAmountOfDrug[0] + "/" + (infoAmountOfDrug[0] + taken));
                        Toast.makeText(getApplicationContext(), Global.getInstance().getDrugs().get(i).getDrugName() +
                                " otettu", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(),"Lääkkeet loppu!", Toast.LENGTH_SHORT).show();
                    }
            }
        });

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
}
