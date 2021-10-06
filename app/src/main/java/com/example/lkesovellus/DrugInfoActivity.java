package com.example.lkesovellus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DrugInfoActivity extends AppCompatActivity {

    private TextView drugName;
    private TextView drugAmount;
    private TextView drugPrice;
    private int taken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_info);

        drugName = findViewById(R.id.drugNameTextView);
        drugAmount = findViewById(R.id.drugAmountTextView);
        drugPrice = findViewById(R.id.drugPriceTextView);
        taken = 0;

        Bundle b = getIntent().getExtras();
        int i = b.getInt(MainActivity.EXTRA, 0);

        drugName.setText(Global.getInstance().getDrugs().get(i).getDrugName());
        drugAmount.setText(("Doses left: " + Global.getInstance().getDrugs().get(i).getDrugAmount()) + "/" +
                Global.getInstance().getDrugs().get(i).getDrugAmount());
        drugPrice.setText(Global.getInstance().getDrugs().get(i).getDrugPrice() + "€, Cost per dose:" +
                (Global.getInstance().getDrugs().get(i).getDrugAmount() / Global.getInstance().getDrugs().get(i).getDrugPrice()) + "€");
    }


}