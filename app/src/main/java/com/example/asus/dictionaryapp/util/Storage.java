package com.example.asus.dictionaryapp.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.asus.dictionaryapp.model.Word;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static Storage instance;
    private String DB = "WORDS";
    private SharedPreferences.Editor editor;

    public static Storage getInstance() {
        if(instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    private Storage() {}

    public Word SearchWord(Context context, String s) throws JSONException {
        String wordsJson = context.getSharedPreferences(DB, Context.MODE_PRIVATE).getString(DB, null);
        if(wordsJson == null || wordsJson.trim().equals("")) {
            return null;
        }
        Type type = new TypeToken< ArrayList < Word >>() {}.getType();
        ArrayList<Word> words = new Gson().fromJson(wordsJson, type);
        for(Word w:words){
            if(w.getWord().equals(s)) {
                return w;
            }
        }
        return null;
    }

    public void editWord(Context context, Word removeWord, Word saveWord) throws JSONException {
        editor = context.getSharedPreferences(DB, context.MODE_PRIVATE).edit();
        ArrayList<Word> words = loadWords(context);
        deleteWord(context,removeWord);
        saveWord(context,saveWord);
        saveWordsJson(new Gson().toJson(words));
    }

    public void saveWord(Context context, Word word) throws JSONException {
        editor = context.getSharedPreferences(DB, context.MODE_PRIVATE).edit();
        ArrayList<Word> words = loadWords(context);
        boolean isSave = false;
        for (int i = 0; i< words.size(); i++) {
            Word w = words.get(i);
            if(w.getWord().equalsIgnoreCase(word.getWord())){
                w.setAllSynonyms(word.getSynonyms());
                w.setAllTranslations(word.getTranslations());
                isSave = true;
            }
        }
        if(!isSave)
            words.add(word);
        saveWordsJson(new Gson().toJson(words));
    }

    public void deleteWord(Context context, Word word) throws JSONException {
        editor =  context.getSharedPreferences(DB, Context.MODE_PRIVATE).edit();
        ArrayList<Word> words = loadWords(context);
        for (int i = 0; i< words.size(); i++) {
            Word w = words.get(i);
            if(w.getWord().equals(word.getWord())){
                words.remove(w);
            }
        }
        saveWordsJson(new Gson().toJson(words));
    }

    public  ArrayList<Word> loadWords(Context context) throws JSONException {
        String wordsJson = context.getSharedPreferences(DB, Context.MODE_PRIVATE).getString(DB, null);
        if(wordsJson == null || wordsJson.trim().equals("")) {
            return new ArrayList<Word>();
        }
        Type type = new TypeToken< ArrayList < Word >>() {}.getType();
        return new Gson().fromJson(wordsJson, type);
    }

    public void clear(Context context) {
        editor =  context.getSharedPreferences(DB, Context.MODE_PRIVATE).edit();
        saveWordsJson(new Gson().toJson(new ArrayList<Word>()));
    }

    private void saveWordsJson(String wordJson) {
        editor.putString(DB, wordJson);
        editor.commit();
    }
    public void save(Context context,ArrayList<Word> words){
        editor =  context.getSharedPreferences(DB, Context.MODE_PRIVATE).edit();
        saveWordsJson(new Gson().toJson(words));
    }
}
