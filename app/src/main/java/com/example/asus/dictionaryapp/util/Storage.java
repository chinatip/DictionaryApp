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

    public void saveWord(Context context, Word word) throws JSONException {
        editor = context.getSharedPreferences(DB, context.MODE_PRIVATE).edit();
        List<Word> words = loadWords(context);
        words.add(word);
        saveWordsJson(new Gson().toJson(words));
    }

    public void deleteWord(Context context, Word word) throws JSONException {
        editor =  context.getSharedPreferences(DB, Context.MODE_PRIVATE).edit();
        List<Word> words = loadWords(context);
        words.remove(word);
        saveWordsJson(new Gson().toJson(words));
    }

    public  List<Word> loadWords(Context context) throws JSONException {
        String wordsJson = context.getSharedPreferences(DB, Context.MODE_PRIVATE).getString(DB, null);
        if(wordsJson == null || wordsJson.trim().equals("")) {
            return new ArrayList<Word>();
        }
        Type type = new TypeToken< List < Word >>() {}.getType();
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
}