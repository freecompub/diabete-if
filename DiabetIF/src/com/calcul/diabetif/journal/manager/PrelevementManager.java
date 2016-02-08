package com.calcul.diabetif.journal.manager;

import android.util.Log;

import com.calcul.diabetif.journal.dao.DAOFactory;
import com.calcul.diabetif.journal.model.Prelevement;


public class PrelevementManager {

    private static final String TAG = PrelevementManager.class.getSimpleName();

    public static PrelevementManager manager() {
        return new PrelevementManager();
    }


    public void addPrelevement(Prelevement prelevement) {
        Log.d(TAG, "addPrelevement");
        DAOFactory.getInstance().getPrelevementDAO().addPrelevement(prelevement);
    }


}
