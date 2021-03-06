package com.example.asus.dictionaryapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.asus.dictionaryapp.R;
import com.example.asus.dictionaryapp.model.Word;
import com.example.asus.dictionaryapp.util.Storage;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class AddWordActivity extends AppCompatActivity {
    private Button saveButton,cancelButton;
    private EditText AddWord, trans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        initComponent();
    }

    private void initComponent(){
        AddWord = (EditText) findViewById(R.id.AddWord);
        trans = (EditText) findViewById(R.id.trans);
        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                save();
                MainActivity.update();
                finish();
            }
        });
        cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void save(){
        String [] transArray = trans.getText().toString().split(",");
        List<String> transList = new ArrayList<String>();
        for (int i = 0; i< transArray.length; i++) {
            transList.add(transArray[i].trim());
        }
        Word word = new Word(AddWord.getText().toString());
        word.addAllTranslations(transList);
        try {
            Storage.getInstance().saveWord(this, word);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}