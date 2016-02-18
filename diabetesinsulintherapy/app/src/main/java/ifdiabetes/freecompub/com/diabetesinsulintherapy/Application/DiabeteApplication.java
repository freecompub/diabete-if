package ifdiabetes.freecompub.com.diabetesinsulintherapy.Application;

import android.app.Application;


import io.realm.RealmConfiguration;

/**
 * Created by s826210 on 15/02/2016.
 */
public class DiabeteApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration myConfig = new RealmConfiguration.Builder(this.getApplicationContext())
                .name("diabete.realm")
                .schemaVersion(1)
                .build();
    }
}
