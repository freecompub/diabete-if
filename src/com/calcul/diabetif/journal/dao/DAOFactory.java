package com.calcul.diabetif.journal.dao;

import java.sql.SQLException;

import android.content.Context;
import android.util.Log;

import com.calcul.diabetif.commun.database.DatabaseUtil;
import com.calcul.diabetif.journal.database.PrelevementListDBHelper;
import com.calcul.diabetif.journal.model.Prelevement;

public class DAOFactory {
    private static final String TAG = DAOFactory.class.getSimpleName();
    private static DAOFactory instance = null;

    protected Context context;
    protected PrelevementListDBHelper shoppingListDBHelper;
    

    protected PrelevementDAO prelevementDAO;


    /* Constructors */
    private DAOFactory(Context context) {
        Log.v(TAG, "DAOFactory(Context context)");
        this.context = context;

        this.shoppingListDBHelper = new PrelevementListDBHelper(context);
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

    	shoppingListDBHelper.beginTransaction();
    }

    public synchronized void commitTransaction() {

    	shoppingListDBHelper.commit();
    }

    public synchronized void rollbackTransaction() {

    	shoppingListDBHelper.rollBack();
    }


	
	public synchronized PrelevementDAO getPrelevementDAO() {
		Log.v(TAG, "getGenericProductDAO()");
		if (prelevementDAO == null) {
			try {
				prelevementDAO = (PrelevementDAO) shoppingListDBHelper.getDao(Prelevement.class);
			} catch (SQLException e) {
				DatabaseUtil.throwAndroidSQLException(TAG, e);
			}
		}
		return prelevementDAO;
	}
}
