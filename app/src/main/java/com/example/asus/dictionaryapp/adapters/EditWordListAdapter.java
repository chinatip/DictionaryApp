/**
 * Created by Chinatip Vichian 5710546551
 */
package com.example.asus.dictionaryapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.asus.dictionaryapp.R;
import com.example.asus.dictionaryapp.activities.EditWordListActivity;
import com.example.asus.dictionaryapp.model.Word;

import java.util.ArrayList;
import java.util.List;

public class EditWordListAdapter extends ArrayAdapter<Word> {
    private List<Word> wordList;
    private Context context;
    public EditWordListAdapter(Context context,int resource, ArrayList<Word> wordList){
        super(context, R.layout.word_list_edit, wordList);
        this.wordList = wordList;
        this.context = context;
    }
    private static class WordHolder {
        public TextView word;
        public CheckBox checkBox;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        WordHolder holder = new WordHolder();

        if(convertView == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.word_list_edit, null);
            holder.word = (TextView) v.findViewById(R.id.word);
            holder.checkBox = (CheckBox) v.findViewById(R.id.checkBox);

            holder.checkBox.setOnCheckedChangeListener((EditWordListActivity) context);
        }else{
            holder = (WordHolder)v.getTag();
        }

        Word w = wordList.get(position);
        holder.word.setText(w.getWord());
        holder.checkBox.setChecked(w.isChecked());
        holder.checkBox.setTag(w);
        return v;
    }

}
