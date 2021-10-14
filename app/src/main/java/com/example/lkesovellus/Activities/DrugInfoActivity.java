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

    private TextView drugName;   //tekstikenttä lääkkeen nimelle
    private TextView drugAmount; //Tekstikenttä lääkkeen määrälle
    private TextView drugPrice;  // tekstikenttä lääkkeen hinnalle
    private int taken;           // kuvaa otettujen lääkkeiden määrää
    int infoAmountOfDrug;        // muuttuja Drug-oliolta haetulle määrälle
    double infoPriceOfDrug;      // muuttuja Drug-oliolta haetulle hinnalle
    private ProgressBar amountProgress;
    private Button addButton;
    /**
     *
     */
    private EditText number;
    public static final String SHARED_PREFS = "sharedprefs";
    public static String VALUE = "taken";


    /**
     * Kutsutaan aktiviteetiin luonnin yhteydessä
     * @Bundle b hakee aikasemmassa aktiviteetissä määritellyt extrat
     * @int i hakee extroista avaimen EXTRA avulla määritellyn arvon, kertoo
     * Drug olion sijainnin MainActivityn listvievillä
     * @VALUE on avain jota käytetään SharedPreferences sp avaimena,
     * @VALUE määritellään käytössä olevan Drug olion nimen mukaan
     * @drugAmountEditable hakee käytössä olevan lääkkeen ja käyttää sitä listana
     * @drugAmountEditable hakee käytössä olevan lääkkeen
     * @infoPriceOfDrug hakee käytössä olevan lääkkeen hinnan
     * int Taken arvo määritellään luomalla luomalla sharedPreferences sp,
     * ja hakemalla sille arvo
     * @DecimalFormat moneyFormat määritellään uusi formaatti, joka rajoittaa tulosteen 2 desimaaliin
     *
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

        /**
         * Kuuntelija painikkeelle takeDrugButton
         * Lisää +1 int taken arvoon
         * asettaa tekstin drugAmount arvojen taken, drugAmountEditable, drugAmountStatic perusteella
         * Asettaa ProgressBar amountProgressille Integer arvon drugAmountEditablen[] ja taken-arvon perusteella
         * Tallentaa arvon taken SharedPreferences sp:hen
         */

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
        /**
         * Määrittää mistä btSetAlarm2 nappi löytyy
         */
        Button btSetAlarm2 = findViewById(R.id.btSetAlarm2);
        /**
         * Kutsuu onClick metodia klikatessa
         */
        btSetAlarm2.setOnClickListener(new View.OnClickListener() {
            /**
             * Tarkastaa, että number EditTextissä on numero ja tekee muistutuksen tietyn ajan päähän
             * @param view nykyinen näkymä
             */
            @Override
            public void onClick(View view) {
                number = findViewById(R.id.inputHour);                                              // Määrittää mistä number löytyy

                if (!(number.getText().toString().equals(""))) {                                    // Tarkastaa että number edittextin kohdalla on joku numero
                    int hours = Integer.parseInt(number.getText().toString());
                    Intent intent = new Intent(DrugInfoActivity.this, ReminderBroadcast.class);   // Uusi intentti
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(DrugInfoActivity.this, 0, intent, 0);

                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);     // Luodaan AlarmManager, jonka avulla saadaan ilmoitus tulemaan tiettyyn aikaan
                    long buttonClickTime = System.currentTimeMillis();                              // Kertoo kuinka monta millisekunttia on mennyt 1.1.1970 - nykypäivään asti
                    long dayInMillisecond = 1000 * 60 * 60 * hours;                                 // Kertoo ajan minkä päähän ilmoitus lopulta tulee

                    Toast.makeText(getApplicationContext(), "Muistutus asetettu", Toast.LENGTH_SHORT).show(); // Pop-up ilmoitus, kun muistutus asetetaan oikealla number arvolla

                    alarmManager.set(RTC_WAKEUP, buttonClickTime + dayInMillisecond, pendingIntent);   // RTC-wakeup herättää sovelluksen kun ilmoitus tulee
                } else {
                    Toast.makeText(getApplicationContext(), "Anna aika!", Toast.LENGTH_SHORT).show();  // Pop-up ilmoitus, kun number edittextiin laitetaan väärä arvo
                }
            }
        });
        /**
         * Määrittää mistä btCancelAlarm2 nappi löytyy
         */
        Button btCancelAlarm2 = findViewById(R.id.btCancelAlarm2);
        /**
         * Kutsuu onClick metodia klikatessa
         */
        btCancelAlarm2.setOnClickListener(new View.OnClickListener() {
            /**
             * Poistaa aktiivisen muistutuksen. Metodi hylkää kaikki samat asiat ja arvot, mitä ilmoituksen tekemiseen on laitettu
             * @param view nykyinen näkymä
             */
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Muistutus poistettu", Toast.LENGTH_SHORT).show();      // pop-up ilmoitus muistutuksen poistumisesta
                Intent intent = new Intent(DrugInfoActivity.this, ReminderBroadcast.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(DrugInfoActivity.this, 0, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.cancel(pendingIntent);
            }
        });
    }
}
