package com.example.lkesovellus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class drugAddActivity extends AppCompatActivity {

    private Button saveButton;
    private EditText drugName;
    private EditText drugPrice;
    private String name;
    private String price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_add);

        saveButton = findViewById(R.id.buttonAddToList);
        drugName = findViewById(R.id.drugNameInput);
        drugPrice = findViewById(R.id.drugPriceInput);
    }

    public void onSaveButtonClick (View v){

        Drug newDrug = new Drug(drugName.getText().toString(), drugPrice.getText().toString(), 1);
        Global.getInstance().drugs.add(newDrug);

        Intent drugSaveActivity = new Intent(drugAddActivity.this, MainActivity.class);
        startActivity(drugSaveActivity);
    }
}