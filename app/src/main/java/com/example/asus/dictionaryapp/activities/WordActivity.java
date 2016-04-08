/**
 * Created by Chinatip Vichian 5710546551
 */
package com.example.asus.dictionaryapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asus.dictionaryapp.R;
import com.example.asus.dictionaryapp.adapters.SynonymsAdapter;
import com.example.asus.dictionaryapp.adapters.WordListAdapter;
import com.example.asus.dictionaryapp.model.Word;
import com.example.asus.dictionaryapp.util.Storage;

import org.json.JSONException;

import java.util.ArrayList;

public class WordActivity extends AppCompatActivity{
    private TextView wordText, transText;
    private ListView lv;
    private ImageButton deleteButton,editButton;
    private Word word;
    private SynonymsAdapter adapter;
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
        lv = (ListView) findViewById(R.id.synonymsListView);
        final ArrayList<String> synonyms = word.getSynonyms();
        adapter = new SynonymsAdapter(this, R.layout.word_list, synonyms );
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String w = synonyms.get(i);
                Word word;
                try {
                    word = Storage.getInstance().SearchWord(view.getContext(), w);
                } catch (JSONException e) {
                    e.printStackTrace();
                    word = null;
                }
                if(word != null) {
                    Intent intent = new Intent(WordActivity.this, WordActivity.class);
                    intent.putExtra("word", word );
                    startActivity(intent);
                }


            }
        });
        wordText.setText(word.getWord());
        transText.setText(word.getTranslations().toString());
        deleteButton = (ImageButton) findViewById(R.id.deleteWordButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteThisWord();
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

    private void deleteThisWord() {
        try {
            Storage.getInstance().deleteWord(this, word);
            MainActivity.update();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
