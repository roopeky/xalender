package com.example.lkesovellus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button addButton;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.listAddButton);
        lv = findViewById(R.id.drugsListView);
        Drug xanor = new Drug("Xanor", Integer.toString(100));
        ArrayList<Drug> drugsList = new ArrayList<>();
        drugsList.add(0, xanor);

        //asettaa aloitusnäkymän ListViewille lv ArrayAdapterin
        lv.setAdapter(new ArrayAdapter<Drug>(
                this, android.R.layout.simple_list_item_1, xanor.getDrugsList()
        ));
    }

    // lisäyspainikkeen kuuntelija
    public void onAddButtonClick(View v) {
        Intent drugAddActivityWindow = new Intent(MainActivity.this, drugAddActivity.class);
        startActivity(drugAddActivityWindow);
    }

}