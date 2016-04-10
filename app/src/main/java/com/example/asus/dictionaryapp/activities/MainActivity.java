/**
 * Created by Chinatip Vichian 5710546551
 */
package com.example.asus.dictionaryapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.asus.dictionaryapp.R;
import com.example.asus.dictionaryapp.adapters.WordListAdapter;
import com.example.asus.dictionaryapp.model.Word;
import com.example.asus.dictionaryapp.util.Storage;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageButton addButton, editButton;
    private EditText searchText;
    public ListView lv;
    private static WordListAdapter wordsAdapter;
    private ArrayList<Word> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    @Override
    public void onStart() {
        super.onStart();
        loadWords();
    }

    private void initComponents() {
        lv = (ListView)findViewById(R.id.wordListView);
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
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditWordListActivity.class);
                startActivity(intent);
            }
        });

        wordsAdapter = new WordListAdapter(this, R.layout.word_list, words);
        lv.setAdapter(wordsAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, WordActivity.class);
                intent.putExtra("word", words.get(i));
                startActivity(intent);

            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v, int i, long arg3) {
                Word w = words.get(i);
//               change from if(w.getIsPinned()) to if (w.isChecked())
                if (w.isChecked()) {
                    w.setIsChecked(false);
                    wordsAdapter.notifyDataSetChanged();
                } else {
                    w.setIsChecked(true);
                    wordsAdapter.notifyDataSetChanged();
                }
                Storage.getInstance().save(MainActivity.this,words);
                return true;
            }
        });
        searchText = (EditText) findViewById(R.id.searchText);
        searchText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable arg0) {
                ArrayList<Word> src_list = new ArrayList<Word>();
                int textlength = searchText.getText().length();
                for (int i = 0; i < words.size(); i++) {
                    try {
                        if (searchText.getText().toString()
                                .equalsIgnoreCase(words.get(i).getWord()
                                        .subSequence(0, textlength)
                                        .toString())) {
                            src_list.add(words.get(i));
                        }
                    } catch (Exception e) {
                    }
                }
                lv.setAdapter(new WordListAdapter(MainActivity.this
                        , R.layout.word_list
                        , src_list));
            }

            public void beforeTextChanged(CharSequence s, int start
                    , int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start
                    , int before, int count) { }

        });
    }

    public void loadWords() {
        try {
            words.clear();
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
