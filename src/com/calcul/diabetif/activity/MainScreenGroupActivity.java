package com.calcul.diabetif.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainScreenGroupActivity extends TabGroupActivity {
	 private static final String TAG = CalculatorGroupActivity.class.getSimpleName();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
      Log.v(TAG, "onCreate()");
      super.onCreate(savedInstanceState);
      Intent intent = new Intent(this, MainActivity.class);
      startChildActivity("MainActivity", intent);
	}

	
	
}
