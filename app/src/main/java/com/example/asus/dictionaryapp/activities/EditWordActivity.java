/**
 * Created by Chinatip Vichian 5710546551
 */
package com.example.asus.dictionaryapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.asus.dictionaryapp.R;

import com.example.asus.dictionaryapp.model.Word;
import com.example.asus.dictionaryapp.util.Storage;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class EditWordActivity extends AppCompatActivity{
    private Word word;
    private TextView wordText;
    private EditText transText, synsText;
    private ImageButton saveButton,cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_word);
        Intent intent = getIntent();
        word = (Word)intent.getSerializableExtra("word");
        initComponents();
    }

    private void initComponents() {
        wordText = (TextView) findViewById(R.id.wordText);
        wordText.setText(word.getWord());
        transText = (EditText) findViewById(R.id.transText);
        String trans = word.getTranslations().toString();
        transText.setText(trans.substring(1, trans.length() - 1));
        synsText = (EditText) findViewById(R.id.synsText);
        String syns = word.getSynonyms().toString();
        synsText.setText(syns.substring(1, syns.length() - 1));

        saveButton = (ImageButton) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word w = save();
                Intent intent = new Intent(EditWordActivity.this, WordActivity.class);
                intent.putExtra("word", w);
                startActivity(intent);
            }
        });
        cancelButton = (ImageButton) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private Word save(){
        String [] transArray = transText.getText().toString().split(",");
        List<String> transList = new ArrayList<String>();
        for (int i = 0; i< transArray.length; i++) {
            transList.add(transArray[i].trim());
        }
        String [] synsArray = synsText.getText().toString().split(",");
        List<String> synsList = new ArrayList<String>();
        for (int i = 0; i< synsArray.length; i++) {
            synsList.add(synsArray[i].trim());
        }
        Word tempWord = new Word(wordText.getText().toString());
        tempWord.addAllTranslations(transList);
        tempWord.addAllSynonyms(synsList);
        try {
            Storage.getInstance().saveWord(this,tempWord);
            //MainActivity.update();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tempWord;
    }
}
