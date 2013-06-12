package com.calcul.diabetif.journal.dao;

import java.sql.SQLException;

import android.util.Log;

import com.calcul.diabetif.commun.dao.BaseCommonDAOImpl;
import com.calcul.diabetif.journal.model.Prelevement;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;



public class PrelevementDAOImpl extends
		BaseCommonDAOImpl<Prelevement, Long> implements PrelevementDAO {

	private static final String TAG = PrelevementDAOImpl.class
			.getSimpleName();

	public PrelevementDAOImpl(Class<Prelevement> dataClass)
			throws SQLException {
		super(dataClass);
	}

	public PrelevementDAOImpl(ConnectionSource connectionSource,
			Class<Prelevement> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}

	public PrelevementDAOImpl(ConnectionSource connectionSource,
			DatabaseTableConfig<Prelevement> tableConfig)
			throws SQLException {
		super(connectionSource, tableConfig);
	}

	
	public void addPrelevement(Prelevement prelevement) {
		Log.v(TAG, "addGenericProduct()");
		createOrFail(prelevement);
	}


}