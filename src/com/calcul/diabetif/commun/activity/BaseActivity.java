package com.calcul.diabetif.commun.activity;

import android.os.Bundle;

public interface BaseActivity {

    public void onCreateActivity(Bundle savedInstanceState);

    public void onResumeActivity();

    public void onPauseActivity();

    public void onDestroyActivity();

    public void onPortrait();

}
