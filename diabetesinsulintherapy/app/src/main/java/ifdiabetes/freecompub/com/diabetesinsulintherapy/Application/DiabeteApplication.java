package ifdiabetes.freecompub.com.diabetesinsulintherapy.Application;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by s826210 on 15/02/2016.
 */
public class DiabeteApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
