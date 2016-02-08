package com.calcul.diabetif.app;

import android.app.Application;

import com.calcul.diabetif.journal.dao.DAOFactory;

public class DiabeteIfApplication extends Application {
    DAOFactory journalDAOFactory;

    @Override
    public void onCreate() {
        //super.onCreate();
        journalDAOFactory = DAOFactory.initSingleton(getApplicationContext());
    }

}
