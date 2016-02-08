package com.calcul.diabetif.journal.database;

import android.util.Log;

import java.sql.SQLException;


public class DatabaseUtil {
    private DatabaseUtil() {
    }

    public static void throwAndroidSQLException(String tag, SQLException e) {
        String error = "" + e.getMessage();
        Log.e(tag, error, e);
        throw new android.database.SQLException(error);
    }
}
