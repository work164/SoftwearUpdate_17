package com.app.update.softwareupdatekkappsstudio.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class SharePrefsClass {

     SharedPreferences preferences;
    public SharePrefsClass(Context appContext) {
        preferences = PreferenceManager.getDefaultSharedPreferences(appContext);
    }

    // Getters
    public int getInt(String key) {
        return preferences.getInt(key, 0);
    }

    public ArrayList<Integer> getListInt(String key) {
        String[] myList = TextUtils.split(preferences.getString(key, ""), "‚‗‚");
        ArrayList<String> arrayToList = new ArrayList<>(Arrays.asList(myList));
        ArrayList<Integer> newList = new ArrayList<>();

        for (String item : arrayToList)
            newList.add(Integer.parseInt(item));

        return newList;
    }

    public String getString(String key) {
        return preferences.getString(key, "");
    }

    public String getStringPack(String key,String pack) {
        return preferences.getString(key, pack);
    }

    public String getStringStorageLink(String key,String pack) {
        return preferences.getString(key, pack);
    }


    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    // Put methods
    public void putStringPack(String key, String value) {
        checkForNullKey(key);
        preferences.edit().putString(key, value).apply();
    }// Put methods

    public void putStringStorageLink(String key, String value) {
        checkForNullKey(key);
        preferences.edit().putString(key, value).apply();
    }
    // Put methods
    public void putInt(String key, int value) {
        checkForNullKey(key);
        preferences.edit().putInt(key, value).apply();
    }

    public void putLong(String key, long value) {
        checkForNullKey(key);
        preferences.edit().putLong(key, value).apply();
    }


    public void putBoolean(String key, boolean value) {
        checkForNullKey(key);
        preferences.edit().putBoolean(key, value).apply();
    }


    public void remove(String key) {
        preferences.edit().remove(key).apply();
    }


    public void clear() {
        preferences.edit().clear().apply();
    }

    private void checkForNullKey(String key){
        if (key == null){
            throw new NullPointerException();
        }
    }


}
