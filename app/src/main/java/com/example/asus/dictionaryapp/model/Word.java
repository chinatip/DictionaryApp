/**
 * Created by Chinatip Vichian 5710546551
 */
package com.example.asus.dictionaryapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Word implements Serializable {
    private String word;
    private boolean isChecked = false, isPinned = false;
    private ArrayList<String> translations = new ArrayList<String>();
    private ArrayList<String> synonyms = new ArrayList<String>();

    public Word(String word){
        this.word = word.toLowerCase();
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public void addAllTranslations(Collection<String> trans){
        translations.addAll(trans);
    }

    public void setAllTranslations(Collection<String> trans){
        this.translations= (ArrayList<String>) trans;
    }

    public String getWord() {return word;}

    public ArrayList<String> getTranslations() {return translations;}

    public void addAllSynonyms(Collection<String> syns) {
        synonyms.addAll(syns);
    }

    public void setAllSynonyms(Collection<String> syns) {synonyms = (ArrayList<String>) syns;}

    public ArrayList<String> getSynonyms() {
        return synonyms;
    }

    public boolean getIsPinned() {return isPinned;}

    public void setPinned(boolean b) { this.isPinned = b;}
}
