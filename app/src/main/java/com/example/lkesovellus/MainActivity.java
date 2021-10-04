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
    List<Drug> drugsList;                   // ArrayList johon lääkkeet lisätään (tulee olla myöhemmin <Drug> tyyppiä)
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.listAddButton);
        lv = findViewById(R.id.drugsListView);

        drugsList = Global.getInstance().getDrugs();

        //asettaa aloitusnäkymän ListViewille lv ArrayAdapterin ja hakee tiedot listasta drugsList
        lv.setAdapter(new ArrayAdapter<Drug>(
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