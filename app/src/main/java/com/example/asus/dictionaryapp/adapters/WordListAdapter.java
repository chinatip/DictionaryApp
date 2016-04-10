/**
 * Created by Chinatip Vichian 5710546551
 */
package com.example.asus.dictionaryapp.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.asus.dictionaryapp.R;
import com.example.asus.dictionaryapp.model.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class WordListAdapter extends ArrayAdapter<Word> {
    int state =0;
    //0 - not pin
    //1 - pin
    public WordListAdapter(Context context, int resource, ArrayList<Word> objects){
        super(context, resource, objects);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;

        if(v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.word_list, null);
        }
        TextView word = (TextView) v.findViewById(R.id.word);
        word.setText(getItem(position).getWord());

        if(getItem(position).isChecked())
            word.setTextColor(Color.RED);
//            v.setBackgroundColor(Color.RED); // to change the word cell color
        else
            word.setTextColor(Color.GRAY);
//            v.setBackgroundColor(Color.BLUE);// to change the word cell color
        return v;
    }

    public void setState(int state) {
        this.state = state;
    }
}
