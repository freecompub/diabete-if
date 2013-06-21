package com.calcul.diabetif.app;

import com.calcul.diabetif.journal.dao.DAOFactory;

import android.app.Application;

public class DiabeteIfApplication extends Application{
	DAOFactory journalDAOFactory;

	@Override
	public void onCreate() {
		//super.onCreate();
		journalDAOFactory = DAOFactory.initSingleton(getApplicationContext());
	}

}
