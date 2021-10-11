package com.example.lkesovellus;

import static java.lang.Math.round;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

        DecimalFormat moneyFormat = new DecimalFormat("0.00");

        drugName.setText(Global.getInstance().getDrugs().get(i).getDrugName());
        drugAmount.setText("Doses left: " + (infoAmountOfDrug) + "/" + infoAmountOfDrug);
        drugPrice.setText(infoPriceOfDrug + "€, Cost per dose: " + moneyFormat.format(infoPriceOfDrug / infoAmountOfDrug)+ "€");
        amountProgress.setMax(infoAmountOfDrug);
        amountProgress.setProgress(infoAmountOfDrug);
        Log.d("TAG", String.valueOf(infoAmountOfDrug));
    }

    public void takeDrugButtonOnClick(View v) {
        Log.d("TAG", String.valueOf(infoAmountOfDrug));
        Log.d("TAG", String.valueOf(infoAmountOfDrug));
        Global.getInstance().getDrugs().get(i).takeDrug();
        drugName.setText(Global.getInstance().getName() + "testi");
        amountProgress.setProgress(infoAmountOfDrug);
        drugAmount.setText(String.valueOf(infoAmountOfDrug));
        Toast.makeText(getApplicationContext(),Global.getInstance().getDrugs().get(i).getDrugName() +
                " taken",Toast.LENGTH_SHORT).show();
    }
}
