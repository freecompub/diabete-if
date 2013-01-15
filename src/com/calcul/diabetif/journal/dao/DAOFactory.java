package com.calcul.diabetif.journal.dao;

import java.sql.SQLException;

import android.content.Context;
import android.util.Log;

import com.calcul.diabetif.commun.database.DatabaseUtil;
import com.calcul.diabetif.journal.database.ShoppingListDBHelper;
import com.calcul.diabetif.journal.model.GenericProduct;

public class DAOFactory {
    private static final String TAG = DAOFactory.class.getSimpleName();
    private static DAOFactory instance = null;

    protected Context context;
    protected ShoppingListDBHelper shoppingListDBHelper;
    

    protected GenericProductDAO genericProductDAO;


    /* Constructors */
    private DAOFactory(Context context) {
        Log.v(TAG, "DAOFactory(Context context)");
        this.context = context;

        this.shoppingListDBHelper = new ShoppingListDBHelper(context);
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


	
	public synchronized GenericProductDAO getGenericProductDao() {
		Log.v(TAG, "getGenericProductDAO()");
		if (genericProductDAO == null) {
			try {
				genericProductDAO = (GenericProductDAO) shoppingListDBHelper.getDao(GenericProduct.class);
			} catch (SQLException e) {
				DatabaseUtil.throwAndroidSQLException(TAG, e);
			}
		}
		return genericProductDAO;
	}
}
