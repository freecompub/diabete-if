package com.calcul.diabetif.commun.preference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class LoyaltyCardPreference {

    private static final String PREFS_CHECK_FILE = "checkedCheckFile";
    private static final String CARD_NUMBER = "CARD_NUMBER";
    private static SharedPreferences loyaltyCardPreference;
    private static SharedPreferences checkFilePreference;

//    private static final String IS_NOT_CMC = "IS_NOT_CMC";

    private LoyaltyCardPreference() {
    }

    public synchronized static void init(Context context) {
        if (loyaltyCardPreference == null && context != null) {
            loyaltyCardPreference = PreferenceManager.getDefaultSharedPreferences(context);
            checkFilePreference = context.getApplicationContext().getSharedPreferences(
                    PREFS_CHECK_FILE, Activity.MODE_PRIVATE);
        }
    }

    public static String getCardNumber() {
        return loyaltyCardPreference.getString(CARD_NUMBER, null);
    }

    /* Card Number */
    public static void setCardNumber(String cardNumber) {
        SharedPreferences.Editor editor = loyaltyCardPreference.edit();
        editor.putString(CARD_NUMBER, cardNumber);
        editor.commit();
    }

    public static boolean hasCardNumber() {
        return getCardNumber() != null && getCardNumber().length() > 0;
    }


//    public static void setCmcMode(boolean isNotCMC) {
//    	SharedPreferences.Editor editor = loyaltyCardPreference.edit();
//        editor.putBoolean(IS_NOT_CMC, isNotCMC);
//        editor.commit();
//    }
//    
//    public static boolean isNotCmc() {
//    	return loyaltyCardPreference.getBoolean(IS_NOT_CMC, false);
//    }
    










    /* Unitary check */

    /* Other */
    public static void clearPreference() {
        SharedPreferences.Editor editor = loyaltyCardPreference.edit();
        editor.clear();
        editor.commit();
    }

    public static void putCheckFile(String key, long value) {
        SharedPreferences.Editor editor = checkFilePreference.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static long getCheckFile(String key) {
        return checkFilePreference.getLong(key, 0);
    }
}
