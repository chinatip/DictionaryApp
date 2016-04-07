package com.example.asus.dictionaryapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Asus on 9/3/2559.
 */
public class Word implements Serializable {
    private String word;
    private List<String> translations = new ArrayList<String>();

    public Word(String word){
        this.word = word.toLowerCase();
    }

    public void addTranslation(String trans){
        translations.add(trans);
    }

    public void addAllTranslations(Collection<String> trans){
        translations.addAll(trans);
    }

    public void deleteTranslation(String trans){
        translations.remove(trans);
    }

    public void deleteAllTranslations(){
        translations = new ArrayList<String>();
    }

    public String getWord() {return word;}

    public List<String> getTranslations() {return translations;}


}
