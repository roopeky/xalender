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

public class MemoActivity extends AppCompatActivity {
    private TextView textView;
    private EditText editText;
    private Button memoSaveButton;
    private Button memoClearButton;
    private String text;

    public static final String SHARED_PREFS = "sharedprefs";
    public static final String TEXT = "text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        memoSaveButton = findViewById(R.id.memoSaveButton);
        memoClearButton = findViewById(R.id.memoClearButton);

        memoSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(editText.getText().toString());
                saveData();
            }
        });

        memoClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearText();
            }
        });

        loadData();
        updateText();
    }

    public void saveData() {
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(TEXT, textView.getText().toString());
        editor.apply();
        Toast.makeText(this, "Tallennettu!", Toast.LENGTH_SHORT).show();

    }
    public void loadData() {
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sp.getString(TEXT, "");
    }
    public void updateText() {
        textView.setText(text);
    }
    public void clearText() {
        textView.setText("");
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(TEXT, textView.getText().toString());
        editor.apply();
        Toast.makeText(this, "Tyhjennetty!", Toast.LENGTH_SHORT).show();
    }
}