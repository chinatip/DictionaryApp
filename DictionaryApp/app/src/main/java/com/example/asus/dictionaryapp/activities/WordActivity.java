package com.example.asus.dictionaryapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.asus.dictionaryapp.R;
import com.example.asus.dictionaryapp.model.Word;
import com.example.asus.dictionaryapp.util.Storage;

import org.json.JSONException;

public class WordActivity extends AppCompatActivity {
    private TextView wordText;
    private TextView transText;
    private ImageButton deleteButton,editButton;
    private Word word;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        Intent intent = getIntent();
        word = (Word)intent.getSerializableExtra("word");
        initComponents();
    }

    private void initComponents() {
        wordText = (TextView) findViewById(R.id.wordText);
        transText = (TextView) findViewById(R.id.transText);
        wordText.setText(word.getWord());
        transText.setText(word.getTranslations().toString());

    }

    private void deleteThisWord() {
        try {
            Storage.getInstance().deleteWord(this, word);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
