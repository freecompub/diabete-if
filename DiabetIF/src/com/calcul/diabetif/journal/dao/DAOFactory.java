package com.calcul.diabetif.journal.dao;

import android.content.Context;
import android.util.Log;

import com.calcul.diabetif.commun.database.DatabaseUtil;
import com.calcul.diabetif.journal.database.PrelevementListDBHelper;
import com.calcul.diabetif.journal.model.Prelevement;

import java.sql.SQLException;

public class DAOFactory {
    private static final String TAG = DAOFactory.class.getSimpleName();
    private static DAOFactory instance = null;

    protected Context context;
    protected PrelevementListDBHelper prelevementListDBHelper;


    protected PrelevementDAO prelevementDAO;


    /* Constructors */
    private DAOFactory(Context context) {
        Log.v(TAG, "DAOFactory(Context context)");
        this.context = context;

        this.prelevementListDBHelper = new PrelevementListDBHelper(context);
    }

    public static synchronized DAOFactory initSingleton(Context context) {
        Log.v(TAG, "DAOFactory.getInstance(Context context)");
        if (instance == null && context != null) {
            Context appContext = context.getApplicationContext();
            instance = new DAOFactory(appContext);
        }
        return instance;
    }

    public static synchronized DAOFactory getInstance() {
        return instance;
    }

    /* Transaction-oriented methods */
    public synchronized void beginTransaction() {

        prelevementListDBHelper.beginTransaction();
    }

    public synchronized void commitTransaction() {

        prelevementListDBHelper.commit();
    }

    public synchronized void rollbackTransaction() {

        prelevementListDBHelper.rollBack();
    }


    public synchronized PrelevementDAO getPrelevementDAO() {
        Log.v(TAG, "getPrelevementDAO()");
        if (prelevementDAO == null) {
            try {
                prelevementDAO = prelevementListDBHelper.getDao(Prelevement.class);
            } catch (SQLException e) {
                DatabaseUtil.throwAndroidSQLException(TAG, e);
            }
        }
        return prelevementDAO;
    }
}
