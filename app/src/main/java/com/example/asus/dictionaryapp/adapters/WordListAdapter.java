/**
 * Created by Chinatip Vichian 5710546551
 */
package com.example.asus.dictionaryapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.asus.dictionaryapp.R;
import com.example.asus.dictionaryapp.model.Word;

import java.util.List;


public class WordListAdapter extends ArrayAdapter<Word> {

    public WordListAdapter(Context context, int resource, List<Word> objects){
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;

        if(v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.word_list, null);
        }
        TextView word = (TextView) v.findViewById(R.id.word);

        word.setText(getItem(position).getWord());
        return v;
    }
}
