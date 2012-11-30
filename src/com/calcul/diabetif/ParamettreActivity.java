/**
 * 
 */
package com.calcul.diabetif;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * @author salili
 * 
 */
public class ParamettreActivity extends Activity {
	private EditText ratioMatin;
	private EditText ratioMidi;
	private EditText ratioSoir;
	private EditText sencibilite;
	private EditText sencibilite_insuline_matin;
	private EditText sencibilite_insuline_soir;
	private EditText minGlyc;
	private EditText maxGlyc;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.paramettre_activity);
		// TODO Auto-generated method stub
		initUI();
	}

	void initUI() {
		ratioMatin = (EditText) findViewById(R.id.EditText_ratio_matin);
		ratioMidi = (EditText) findViewById(R.id.EditText_ratio_midi);
		ratioSoir = (EditText) findViewById(R.id.EditText_ratio_soir);
		sencibilite = (EditText) findViewById(R.id.edit_sencibilite);
		sencibilite_insuline_matin = (EditText) findViewById(R.id.EditText_sencibilite_insu_matin);
		sencibilite_insuline_soir = (EditText) findViewById(R.id.EditText_sencibilite_insul_soir);
		minGlyc = (EditText) findViewById(R.id.EditText_minG);
		maxGlyc = (EditText) findViewById(R.id.max_glyf);

		// init Values
		maxGlyc.setText(String.valueOf(UserPreference.getMaxGlycemie()));
		minGlyc.setText(String.valueOf(UserPreference.getMinGlycemie()));
		ratioMatin.setText(String.valueOf(UserPreference.getRatioMatin()));
		ratioMidi.setText(String.valueOf(UserPreference.getRatioMidi()));
		ratioSoir.setText(String.valueOf(UserPreference.getRatioSoir()));
		sencibilite.setText(String.valueOf(UserPreference.getSencibitlite()));
		sencibilite_insuline_matin.setText(String.valueOf(UserPreference
				.getSencibitliteInsulineMatin()));
		sencibilite_insuline_soir.setText(String.valueOf(UserPreference
				.getSencibitliteInsulineSoir()));

	}

	public void goToMainActivity(View view) {

		UserPreference.setMaxGlycemier(Float.parseFloat(maxGlyc.getText().toString().trim()));
		UserPreference.setMinGlycemier(Float.parseFloat(minGlyc.getText().toString().trim()));
		UserPreference.setRatioMatin(Float.parseFloat(ratioMatin.getText().toString().trim()));
		UserPreference.setRatioMidi(Float.parseFloat(ratioMidi.getText().toString().trim()));
		UserPreference.setRatioSoir(Float.parseFloat(ratioSoir.getText().toString().trim()));
		UserPreference.setRatioSencibitlite(Float.parseFloat(sencibilite.getText().toString()
				.trim()));
		UserPreference.setSencibitliteInsulineMatin(Float.parseFloat(sencibilite_insuline_matin
				.getText().toString().trim()));
		UserPreference.setSencibitliteInsulineSoir(Float.parseFloat(sencibilite_insuline_soir
				.getText().toString().trim()));
		// Intent k = new Intent(this, MainActivity.class);
		// startActivity(k);
		finish();
	}
}
