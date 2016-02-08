package com.calcul.diabetif.commun.preference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Date;

public class SingleAppPreference {

    private static final String SINGLE_APP_PREF = "SINGLE_APP_PREF";
    private static final String PREF_SHOW_BADGE = "PREF_SHOW_BADGE";
    private static final String PREF_SHOW_NUMBER_ITEM = "PREF_SHOW_NUMBER_ITEM";
    private static final String PREF_SHOW_PRICE_ITEM = "PREF_SHOW_PRICE_ITEM";
    private static final String PREF_SHOW_LOYALTY_CHECK = "PREF_SHOW_LOYALTY_CHECK";
    private static final String PREF_APP_TOUR = "PREF_APP_TOUR";
    private static final String PREF_LAST_IN_USE_DATE = "PREF_LAST_IN_USE_DATE"; //date when activity go in pause
    private static final String PREF_FACEBOOK_TOKEN = "PREF_FACEBOOK_TOKEN";
    private static final String PREF_FACEBOOK_EXPIRES = "PREF_FACEBOOK_EXPIRES";
    private static SharedPreferences singleAppPreference;

    private SingleAppPreference() {

    }

    public synchronized static void init(Context context) {
        if (singleAppPreference == null && context != null) {
            singleAppPreference = context.getApplicationContext().getSharedPreferences(
                    SINGLE_APP_PREF, Activity.MODE_PRIVATE);
        }
    }

    public static void restoreToDefault() {
        Editor editor = singleAppPreference.edit();
        editor.putBoolean(PREF_SHOW_BADGE, false);
        editor.putBoolean(PREF_SHOW_NUMBER_ITEM, false);
        editor.putBoolean(PREF_SHOW_PRICE_ITEM, false);
        editor.putBoolean(PREF_SHOW_LOYALTY_CHECK, false);
        editor.putBoolean(PREF_APP_TOUR, false);
        editor.putLong(PREF_LAST_IN_USE_DATE, 0);
        editor.commit();
    }

    public static void showBadge() {
        setPreferenceValue(PREF_SHOW_BADGE, true);
    }

    public static void hideBadge() {
        setPreferenceValue(PREF_SHOW_BADGE, false);
    }

    public static void showNumberOfItemOnList() {
        setPreferenceValue(PREF_SHOW_NUMBER_ITEM, true);
    }

    public static void hideNumberOfItemOnList() {
        setPreferenceValue(PREF_SHOW_NUMBER_ITEM, false);
    }

    public static void showPriceOfItemInList() {
        setPreferenceValue(PREF_SHOW_PRICE_ITEM, true);
    }

    public static void hidePriceOfItemInList() {
        setPreferenceValue(PREF_SHOW_PRICE_ITEM, false);
    }

    public static void showAvailableLoyaltyCheck() {
        setPreferenceValue(PREF_SHOW_LOYALTY_CHECK, true);
    }

    public static void hideAvailableLoyaltyCheck() {
        setPreferenceValue(PREF_SHOW_LOYALTY_CHECK, false);
    }

    public static boolean getAppTourEnabled() {
        return singleAppPreference.getBoolean(PREF_APP_TOUR, true);
    }

    public static void setAppTourEnabled(boolean enabled) {
        setPreferenceValue(PREF_APP_TOUR, enabled);
    }

    public static Date getLastInUseDate() {
        long milliseconds = singleAppPreference.getLong(PREF_LAST_IN_USE_DATE, 0);
        if (milliseconds == 0) {
            return new Date();
        } else {
            return new Date(milliseconds);
        }
    }

    public static void setLastInUseDate(Date date) {
        Editor editor = singleAppPreference.edit();
        editor.putLong(PREF_LAST_IN_USE_DATE, date.getTime());
        editor.commit();
    }

    public static String getFacebookToken() {
        return singleAppPreference.getString(PREF_FACEBOOK_TOKEN, null);
    }

    public static void setFacebookToken(String token) {
        Editor editor = singleAppPreference.edit();
        editor.putString(PREF_FACEBOOK_TOKEN, token);
        editor.commit();
    }

    public static long getFacebookExpires() {
        return singleAppPreference.getLong(PREF_FACEBOOK_EXPIRES, 0);
    }

    public static void setFacebookExpires(long expires) {
        Editor editor = singleAppPreference.edit();
        editor.putLong(PREF_FACEBOOK_EXPIRES, expires);
        editor.commit();
    }


    public static void setPreferenceValue(String key, boolean value) {
        Editor editor = singleAppPreference.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getPreferenceValue(String key) {
        return singleAppPreference.getBoolean(key, false);
    }

}
