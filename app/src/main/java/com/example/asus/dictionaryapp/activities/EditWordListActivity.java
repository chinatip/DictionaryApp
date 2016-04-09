/**
 * Created by Chinatip Vichian 5710546551
 */
package com.example.asus.dictionaryapp.activities;

import android.support.v7.app.AppCompatActivity;
import com.example.asus.dictionaryapp.R;
import com.example.asus.dictionaryapp.adapters.EditWordListAdapter;
import com.example.asus.dictionaryapp.model.Word;
import com.example.asus.dictionaryapp.util.Storage;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;

public class EditWordListActivity extends AppCompatActivity implements android.widget.CompoundButton.OnCheckedChangeListener {
    private ImageButton deleteButton;
    private ListView lv;
    private EditWordListAdapter adapter;
    private ArrayList<Word> wordList = new ArrayList<>();
    private CheckBox markAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_word_list);
        initComponents();
        loadWords();
    }

    private void initComponents(){
        lv = (ListView)findViewById(R.id.wordListView);
        displayWordList();
        markAll = (CheckBox) findViewById(R.id.markAllCheckBox);
        markAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (markAll.isChecked()) {
                    for (int i =0;i<wordList.size();i++) {
                        Word w = wordList.get(i);
                        w.setIsChecked(true);
                        CheckBox box = (CheckBox)lv.getAdapter().getView(i,v,lv).findViewById(R.id.checkBox);
                        //CheckBox b = (CheckBox)adapter.getHolder().

                    }
                } else {
                    for (int i =0;i<wordList.size();i++) {
                        Word w = wordList.get(i);
                        w.setIsChecked(false);

                    }
                }
            }
        });
        deleteButton = (ImageButton) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteWords();
                finish();
            }
        });
    }
    private void displayWordList() {
        adapter = new EditWordListAdapter(this, wordList);
        lv.setAdapter(adapter);
    }
    private void loadWords() {
        try {
            wordList.clear();
            for(Word n: Storage.getInstance().loadWords(this)) {
                n.setIsChecked(false);
                wordList.add(n);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void deleteWords() {
        int count=0;
        for(Word w:wordList){
            if(w.isChecked()) {
                try {
                    Storage.getInstance().deleteWord(this,w);
                    MainActivity.update();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int pos = lv.getPositionForView(buttonView);
        if(pos != ListView.INVALID_POSITION) {
            Word w = wordList.get(pos);
            w.setIsChecked(isChecked);

            Toast.makeText(this, "Clicked on word : " + w.getWord(), Toast.LENGTH_SHORT).show();
        }

    }

}

