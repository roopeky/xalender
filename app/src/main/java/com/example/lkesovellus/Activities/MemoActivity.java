package com.example.lkesovellus.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lkesovellus.R;

/**
 * Luokka luo muistiinpano näkymän
 * @author Roope Kylli
 * @version 1.0
 */
public class MemoActivity extends AppCompatActivity {
    /**
     * TextView kirjoitetuille muistiinpanoille
     */
    private TextView textView;
    /**
     * EditText käyttäjän syöttöä varten
     */
    private EditText editText;
    /**
     * Nappi tallennusnapille
     */
    private Button memoSaveButton;
    /**
     * Nappi tyhjennysnapille
     */
    private Button memoClearButton;
    /**
     * Teksti joka menee textViewiin
     */
    private String text;
    /**
     * SharedPreferenssin nimi
     */
    public static final String SHARED_PREFS = "sharedprefs";
    /**
     * SharedPreferenssin teksti
     */
    public static final String TEXT = "text";

    /**
     * Kutsutaan aktiviteetiin luonnin yhteydessä
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        memoSaveButton = findViewById(R.id.memoSaveButton);
        memoClearButton = findViewById(R.id.memoClearButton);
        /**
         * memoSaveButtonia klikatessa suorittaa onClick metodin
         */
        memoSaveButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Asettaa ja tallentaa käyttäjän syötteen EditTextistä
             * @param view nykyinen näkymä
             */
            @Override
            public void onClick(View view) {
                textView.setText(editText.getText().toString());
                saveData();
            }
        });
        /**
         * memoClearButtonia klikatessa suorittaa onClick metodin
         */
        memoClearButton.setOnClickListener(new View.OnClickListener() {
            /**
             * klikatessa suorittaa onClick metodin
             * @param view nykyinen näkymä
             */
            @Override
            public void onClick(View view) {
                clearText();
            }
        });

        loadData();
        updateText();
    }
    /**
     * Tallentaa datan SharedPreferenssiin
     */
    public void saveData() {
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);                    // Luodaan olio sp arvoilla teksti mihin kirjoitetut muistiinpanot tallentuu sekä MODE_PRIVATE, joka estää muiden sovellusten vaikutuksen tähän sovellukseen
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(TEXT, textView.getText().toString());
        editor.apply();
        Toast.makeText(this, "Tallennettu!", Toast.LENGTH_SHORT).show();

    }
    /**
     * Lataa datan sp -oliosta
     */
    public void loadData() {
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sp.getString(TEXT, "");                                                           // text saa arvon preferenssin sp tekstikentästä, default arvo on tyhjä
    }
    /**
     * Päivittää textView tekstin
     */
    public void updateText() {
        textView.setText(text);                                                                     // sijoittaa text -arvon textViewiin eli muistiinpano kenttään
    }
    /**
     * Tyhjentää ja tallentaa datan textView -kentästä
     */
    public void clearText() {
        textView.setText("");
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(TEXT, textView.getText().toString());
        editor.apply();
        Toast.makeText(this, "Tyhjennetty!", Toast.LENGTH_SHORT).show();
    }
}