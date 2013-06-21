package com.calcul.diabetif.journal.database;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.calcul.diabetif.commun.database.DatabaseTransactionOpenHelper;
import com.calcul.diabetif.commun.database.DatabaseUtil;
import com.calcul.diabetif.journal.model.Prelevement;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class PrelevementListDBHelper extends DatabaseTransactionOpenHelper {

	private static final String TAG = PrelevementListDBHelper.class.getSimpleName();

	// The database version
	private static final int DB_VERSION = 1;
	// The database file name
	private static final String DB_NAME = "prelevement_ormlite.db";

	public PrelevementListDBHelper(Context context) {
		
		super(context, DB_NAME, null, DB_VERSION);
		Log.v(TAG, "PrelevementListDBHelper()");
		
	}

	private static void createTables(ConnectionSource connectionSource, SQLiteDatabase db) {
		Log.v(TAG, "createTables()");
		try {
			TableUtils.createTableIfNotExists(connectionSource, Prelevement.class);
		} catch (SQLException e) {
			DatabaseUtil.throwAndroidSQLException(TAG, e);
		}
	}

	@Override
	public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
		Log.v(TAG, "onCreate()");
		createTables(connectionSource, sqliteDatabase);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqliteDatabase,
			ConnectionSource connectionSource, int oldVer, int newVer) {
		Log.e(TAG, "onUpgrade() oldVersion:" + oldVer + ", newVersion:" + newVer);
	}

	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
	}
}
