package com.example.lkesovellus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Aktiviteetti joka sisältää toiminnallisuuden lääkkeiden lisäämiseen
 * Sisältää viittaukset EditText tyyppisiin tekstikenttiin
 * Sisältää onClick kuuntelijan painikkeelle SaveButton
 * @Author Emil Suuronen
 */
public class DrugAddActivity extends AppCompatActivity {

    private Button saveButton;  // Muuttuja aktiviteetin painikkeelle save
    private EditText drugName;  // Muuttuja aktiviteetin tekstikentälle josta saa tekstisyötettä
    private EditText drugPrice; // Muuttuja aktiviteetin tekstikentälle josta saa tekstisyötettä
    private EditText drugAmount;// Muuttuja aktiviteetin tekstikentälle josta saa tekstisyötettä

    /**
     * Metodia kutsutaan aktiviteetin luonnin yhteydessä
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_add);

        saveButton = findViewById(R.id.buttonAddToList);    // Määrittää saveButton muuttujan viittaamaan Save painikkeen tiedostosijaintiin
        drugName = findViewById(R.id.drugNameInput);        // Määrittää drugName muuttujan viittaamaan drugNameInput tekstikentän tiedostosijaintiin
        drugPrice = findViewById(R.id.drugPriceInput);      // Määrittää drugPrice muuttujan viittaamaan drugPriceInput tekstikentän tiedostosijaintiin
        drugAmount = findViewById(R.id.amountTextEdit);     // Määrittää drugAmount muuttujan viittaamaan drugAmountInput tekstikentän tiedostosijaintiin
    }

    /**
     * Metodi, jota aktiviteetin painike buttonAddToList käyttää
     * Kutsuu Global luokan instanssia
     * Luo Drug tyyppisen olion parametriarvoilla, jotka saa syötelaatikoista drugName, drugPrice, drugAmount
     * Lisää luomansa Drug-olion Global luokan instanssin ArrayListille drugs
     * Luo uuden intentin drugSaveActivity, joka palauttaa käyttäjän MainActivity aktiviteettiin
     * Aloittaa aktiviteetin MainActivity.class kutsumalla drugSaveActivity intentiä
     * Esittää ilmoituksen mikäli kaikissa kentissä ei ole syötettä
     */
    public void onSaveButtonClick (View v){
        if (!drugName.getText().toString().equals("") || !drugPrice.getText().toString().equals("") || // if-lauseke joka tarkistaa onko kaikissa tekstikentissä syötettä
                !drugAmount.getText().toString().equals(""))  {
            Drug newDrug = new Drug(drugName.getText().toString(),      // Luo uuden Drug olion jossa olion parametrit haetaan tekstikenttien syötteestä
                    Double.parseDouble(drugPrice.getText().toString()), // toString muuttaa tekstikentän syötteen merkkijonoksi
                    Integer.parseInt(drugAmount.getText().toString())); // Double.parseDouble & Integer.parseInt muuttavat tekstikentän syötteen Drug olion vaatimaksi
            Global.getInstance().getDrugs().add(newDrug);               // Kutsuu Global luokan instanssia ja sen tekemää listaa sekä lisää luodun Drug-olion sille
            Intent drugSaveActivity = new Intent(DrugAddActivity.this, MainActivity.class);
            startActivity(drugSaveActivity);
        } else {
            Toast.makeText(getApplicationContext(),"Täytä kaikki kentät!", Toast.LENGTH_SHORT).show();
        }
    }
}