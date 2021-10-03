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

    private Button addButton;               // painike jolla lääkkeitä voi lisätä ja
    private ListView lv;                    //päänäkymän lista johon lääkkeet lisätään
    private ArrayList<String> drugsList;    // ArrayList johon lääkkeet lisätään (tulee olla myöhemmin <Drug> tyyppiä)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.listAddButton);
        lv = findViewById(R.id.drugsListView);
        Drug burana = new Drug("Burana 400mg", Integer.toString(100));
        ArrayList<String> drugsList = new ArrayList<>();
        drugsList.add("Burana");
        drugsList.add("Lääke 2");
        drugsList.add("Lääke 3");

        //asettaa aloitusnäkymän ListViewille lv ArrayAdapterin ja hakee tiedot listasta drugsList
        lv.setAdapter(new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, drugsList
        ));
    }

    // lisäyspainikkeen kuuntelija joka vaihtaa aktiviteettiin drugAddActivity
    public void onAddButtonClick(View v) {
        Intent drugAddActivityWindow = new Intent(MainActivity.this, drugAddActivity.class);
        startActivity(drugAddActivityWindow);
    }
}