package ifdiabetes.freecompub.com.diabetesinsulintherapy.Manager;

import android.util.Log;

import ifdiabetes.freecompub.com.diabetesinsulintherapy.Modele.BloodSample;

/**
 * Created by s826210 on 15/02/2016.
 */
public class DayManager {
    private static final String TAG = DayManager.class.getSimpleName();


    public void addPrelevement(BloodSample prelevement) {
        Log.d(TAG, "addPrelevement");
        prelevement.save();
    }
}
