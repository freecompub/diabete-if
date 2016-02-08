package com.calcul.diabetif.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class UserPreference {

    private static final String USER_PREF = "USER_PREF";
    private static final String RATIO_MATIN = "ratioMatin";
    private static final String RATIO_MIDI = "ratioMidi";
    private static final String RATIO_SOIR = "ratiosoir";
    private static final String SENCIBITLITE = "Sencibilite";
    private static final String SENCIBITLITE_INSULINE_MATIN = "Sencibilite_Insuline_Matin";
    private static final String SENCIBITLITE_INSULINE_SOIR = "Sencibilite_Insuline_Soir";
    private static final String MIN_GLYCEMIE = "MinG";
    private static final String MAX_GLYCEMIE = "MaxG";
    private static SharedPreferences userPreference;

    private UserPreference() {
    }

    public synchronized static void init(Context context) {
        if (userPreference == null && context != null) {
            userPreference = context.getApplicationContext()
                    .getSharedPreferences(USER_PREF, Activity.MODE_PRIVATE);
        }
    }

    synchronized public static float getRatioMatin() {
        return userPreference.getFloat(RATIO_MATIN, 0);
    }

    synchronized public static void setRatioMatin(float ratioMatin) {
        SharedPreferences.Editor editor = userPreference.edit();
        editor.putFloat(RATIO_MATIN, ratioMatin);
        editor.commit();
    }

    synchronized public static float getRatioMidi() {
        return userPreference.getFloat(RATIO_MIDI, 0);
    }

    synchronized public static void setRatioMidi(float ratioMidi) {
        SharedPreferences.Editor editor = userPreference.edit();
        editor.putFloat(RATIO_MIDI, ratioMidi);
        editor.commit();
    }

    synchronized public static float getRatioSoir() {
        return userPreference.getFloat(RATIO_SOIR, 0);
    }

    synchronized public static void setRatioSoir(float ratioSoir) {
        SharedPreferences.Editor editor = userPreference.edit();
        editor.putFloat(RATIO_SOIR, ratioSoir);
        editor.commit();
    }

    synchronized public static float getSencibitlite() {
        return userPreference.getFloat(SENCIBITLITE, 0);
    }

    synchronized public static void setRatioSencibitlite(float sencibitlite) {
        SharedPreferences.Editor editor = userPreference.edit();
        editor.putFloat(SENCIBITLITE, sencibitlite);
        editor.commit();
    }

    synchronized public static float getSencibitliteInsulineMatin() {

        return userPreference.getFloat(SENCIBITLITE_INSULINE_MATIN, 0);
    }

    synchronized public static void setSencibitliteInsulineMatin(
            float sencibitliteInsulineMatin) {
        SharedPreferences.Editor editor = userPreference.edit();
        editor.putFloat(SENCIBITLITE_INSULINE_MATIN, sencibitliteInsulineMatin);
        editor.commit();
    }

    synchronized public static float getSencibitliteInsulineSoir() {
        return userPreference.getFloat(SENCIBITLITE_INSULINE_SOIR, 0);
    }

    synchronized public static void setSencibitliteInsulineSoir(
            float sencibitliteInsulineSoir) {
        SharedPreferences.Editor editor = userPreference.edit();
        editor.putFloat(SENCIBITLITE_INSULINE_SOIR, sencibitliteInsulineSoir);
        editor.commit();
    }

    synchronized public static float getMinGlycemie() {

        return userPreference.getFloat(MIN_GLYCEMIE, 0);
    }

    synchronized public static void setMinGlycemier(float minGlycemie) {
        SharedPreferences.Editor editor = userPreference.edit();
        editor.putFloat(MIN_GLYCEMIE, minGlycemie);
        editor.commit();
    }

    synchronized public static float getMaxGlycemie() {
        return userPreference.getFloat(MAX_GLYCEMIE, 0);
    }

    synchronized public static void setMaxGlycemier(float maxGlycemie) {
        SharedPreferences.Editor editor = userPreference.edit();
        editor.putFloat(MAX_GLYCEMIE, maxGlycemie);
        editor.commit();
    }

}
