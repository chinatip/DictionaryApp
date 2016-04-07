/**
 * Created by Chinatip Vichian 5710546551
 */
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

import java.util.Observable;
import java.util.Observer;

public class WordActivity extends AppCompatActivity{
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
        deleteButton = (ImageButton) findViewById(R.id.deleteWordButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteThisWord(word);
                MainActivity.update();
                finish();
            }
        });
        editButton = (ImageButton) findViewById(R.id.editWordButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WordActivity.this, EditWordActivity.class);
                intent.putExtra("word", word);
                startActivity(intent);
            }
        });
    }

    private void deleteThisWord(Word word) {
        try {
            Storage.getInstance().deleteWord(this, word);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
