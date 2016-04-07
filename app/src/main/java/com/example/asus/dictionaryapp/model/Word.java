/**
 * Created by Chinatip Vichian 5710546551
 */
package com.example.asus.dictionaryapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Word implements Serializable {
    private String word;
    private int resource;
    private boolean isChecked = false;
    private List<String> translations = new ArrayList<String>();

    public Word(String word){
        this.word = word.toLowerCase();
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
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
