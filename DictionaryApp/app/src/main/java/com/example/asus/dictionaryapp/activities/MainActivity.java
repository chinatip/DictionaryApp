package com.example.asus.dictionaryapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.asus.dictionaryapp.R;
import com.example.asus.dictionaryapp.adapters.WordAdapter;
import com.example.asus.dictionaryapp.model.Word;
import com.example.asus.dictionaryapp.util.Storage;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageButton addButton, editButton;
    public ListView wordsList;
    private static WordAdapter wordsAdapter;
    private List<Word> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadWords();
    }

    private void initComponents() {
        wordsList = (ListView)findViewById(R.id.wordListView);
        words = new ArrayList<Word>();
        addButton = (ImageButton) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddWordActivity.class);
                startActivity(intent);
            }
        });
        editButton = (ImageButton) findViewById(R.id.editButton);

        wordsAdapter = new WordAdapter(this, R.layout.word_list, words);
        wordsList.setAdapter(wordsAdapter);
    }

    private void loadWords() {
        try {
            words.clear();
            int i=0;
            for(Word n: Storage.getInstance().loadWords(this)) {

                words.add(n);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void update() {
        wordsAdapter.notifyDataSetChanged();
    }
}
