package com.example.lkesovellus.Activities;

import static android.app.AlarmManager.RTC_WAKEUP;
import static com.example.lkesovellus.Activities.MainActivity.EXTRA;
import static java.lang.Math.round;
import static java.lang.String.valueOf;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lkesovellus.classes.DrugData;
import com.example.lkesovellus.R;
import com.example.lkesovellus.classes.ReminderBroadcast;

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
    public static final String SHARED_PREFS = "sharedprefs";
    public static String VALUE = "taken";


    /**
     * Kutsutaan aktiviteetiin luonnin yhteydessä
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_info);

        drugName = findViewById(R.id.drugNameTextView);
        drugAmount = findViewById(R.id.drugAmountTextView);
        drugPrice = findViewById(R.id.drugPriceTextView);
        amountProgress = findViewById(R.id.amountProgressBar);

        Bundle b = getIntent().getExtras();
        int i = b.getInt(EXTRA, 0);

        VALUE = DrugData.getInstance().getDrugs().get(i).getDrugName();

        final int[] drugAmountEditable = {DrugData.getInstance().getDrugs().get(i).getDrugAmount()};
        int drugAmountStatic = DrugData.getInstance().getDrugs().get(i).getDrugAmount();
        double infoPriceOfDrug = DrugData.getInstance().getDrugs().get(i).getDrugPrice();

        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        taken = Integer.parseInt(sp.getString(VALUE, "0"));

        DecimalFormat moneyFormat = new DecimalFormat("0.00");

        drugName.setText(DrugData.getInstance().getDrugs().get(i).getDrugName());
        drugAmount.setText("Annoksia: " + ((drugAmountEditable[0]) - taken) + "/" + drugAmountStatic);
        drugPrice.setText(infoPriceOfDrug + "€, Hinta/kpl: " + moneyFormat.format(infoPriceOfDrug / drugAmountStatic) + "€");
        amountProgress.setMax(drugAmountStatic);
        amountProgress.setProgress(drugAmountEditable[0] - taken);

        Button addButton = (Button) findViewById(R.id.takeDrugButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    if (!(drugAmountEditable[0] == taken)) {
                        taken++;
                        drugAmount.setText("Annoksia: " + (((drugAmountEditable[0]) - taken) + "/" + drugAmountStatic));
                        amountProgress.setProgress((drugAmountEditable[0]) - taken);
                        Toast.makeText(getApplicationContext(), DrugData.getInstance().getDrugs().get(i).getDrugName() +
                                " otettu", Toast.LENGTH_SHORT).show();
                        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();

                        editor.putString(VALUE, valueOf(taken));
                        editor.apply();
                    } else {
                        Toast.makeText(getApplicationContext(),"Lääkkeet loppu!", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        Button btSetAlarm2 = findViewById(R.id.btSetAlarm2);

        btSetAlarm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                number = findViewById(R.id.inputHour);

                if (!(number.getText().toString().equals(""))) {
                    int hours = Integer.parseInt(number.getText().toString());
                    Intent intent = new Intent(DrugInfoActivity.this, ReminderBroadcast.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(DrugInfoActivity.this, 0, intent, 0);

                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    long buttonClickTime = System.currentTimeMillis();
                    long dayInMillisecond = 1000 * hours;

                    Toast.makeText(getApplicationContext(), "Muistutus asetettu", Toast.LENGTH_SHORT).show();

                    alarmManager.set(RTC_WAKEUP, buttonClickTime + dayInMillisecond, pendingIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "Anna aika!", Toast.LENGTH_SHORT).show();
                }
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
