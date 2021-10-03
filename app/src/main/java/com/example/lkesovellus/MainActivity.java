package com.example.lkesovellus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button addButton;               // painike jolla lääkkeitä voi lisätä ja
    private ListView lv;                    //päänäkymän lista johon lääkkeet lisätään
    ArrayList<String> drugsList;            // ArrayList johon lääkkeet lisätään (tulee olla myöhemmin <Drug> tyyppiä)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.listAddButton);
        lv = findViewById(R.id.drugsListView);
        Drug burana = new Drug("Burana", Integer.toString(100), 30);
        drugsList = new ArrayList<>();
        drugsList.add("burana");
        drugsList.add("panadol");
        drugsList.add("Lääke 3");


        //asettaa aloitusnäkymän ListViewille lv ArrayAdapterin ja hakee tiedot listasta drugsList
        lv.setAdapter(new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, drugsList
        ));

        //ListView lv kuuntelija joka vaihtaa aktiviteetin drugInfoActivityyn
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent drugInfoActivityWindow = new Intent(MainActivity.this, DrugInfoActivity.class);
                startActivity(drugInfoActivityWindow);
            }
        });
    }

    // lisäyspainikkeen metodi joka vaihtaa aktiviteettiin drugAddActivity
    public void onAddButtonClick(View v) {
        Intent drugAddActivityWindow = new Intent(MainActivity.this, drugAddActivity.class);
        startActivity(drugAddActivityWindow);
    }
}