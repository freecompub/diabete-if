package com.calcul.diabetif;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements OnItemSelectedListener,
		OnCheckedChangeListener,TextWatcher {

	private LinearLayout layaoutInsuline;
	private LinearLayout layaoutResucrage;
	private EditText glycimieActuelle;
	private EditText glycemie;
	private Spinner peroide;
	private String selected_Periode;
	private double insulineCorrection = 0, insulineManger = 0, resucrage = 0;
	private TextView results_insuline_pour_manger;
	private TextView results_correction_pour_manger;
	private TextView results_resucrage;
	private int selectionRadio = -1;
	private RadioGroup radioGroup;
	protected static final String TAG = MainActivity.class.getSimpleName();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		UserPreference.init(getApplicationContext());

		initUI();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	void initUI() {

		layaoutInsuline = (LinearLayout) findViewById(R.id.layout_insuline);
		layaoutInsuline.setVisibility(View.GONE);
		layaoutResucrage = (LinearLayout) findViewById(R.id.layout_resucrage);
		layaoutResucrage.setVisibility(View.GONE);
		glycimieActuelle = (EditText) findViewById(R.id.texteEdit_glycemie);
		glycimieActuelle.addTextChangedListener(this);
		glycemie = (EditText) findViewById(R.id.glucide);
		glycemie.addTextChangedListener(this);
		peroide = (Spinner) findViewById(R.id.spinner1);
		results_insuline_pour_manger = (TextView) findViewById(R.id.textView2);
		results_correction_pour_manger = (TextView) findViewById(R.id.TextView08);
		results_resucrage = (TextView) findViewById(R.id.TextView03);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.period_array,
				android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		peroide.setAdapter(adapter);
		peroide.setOnItemSelectedListener(this);

		radioGroup = (RadioGroup) findViewById(R.id.radiogroup_selection);
		radioGroup.setOnCheckedChangeListener(this);
		getOperation();
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		goToSetting();
		return true;
		// switch (item.getItemId()) {
		// case R.id.new_game:
		// newGame();
		// return true;
		// case R.id.help:
		// showHelp();
		// return true;
		// default:
		// return super.onOptionsItemSelected(item);
		// }
	}

	void goToSetting() {
		Log.v("test", "test");
		Intent k = new Intent(this, ParamettreActivity.class);
		startActivity(k);
		// finish();
	}

	void calculerInsulinePourManger() {
		double glucide = 0, ratio = 0;
		if (selected_Periode.equals((String) "Matin")) {
			ratio = UserPreference.getRatioMatin();
		}

		if (selected_Periode.equals((String) "Midi")) {
			ratio = UserPreference.getRatioMidi();
		}
		if (selected_Periode.equals((String) "Soir")) {
			ratio = UserPreference.getRatioSoir();
		}

		if (glycemie.getText().toString().trim() != null) {
			String tmp = glycemie.getText().toString().trim();
			Log.v("MainActivity: ", tmp);
			if (tmp == null || tmp.equals("")) {
				tmp = "0";
			}
			glucide = Double.parseDouble(tmp);
		}

		insulineManger = (glucide * ratio) / 10;

	}

	void calculerCorrectionInsuline() {
		double maxGlycimie = 0, minGlycimie = 0, sencibilite = 0, glycimie = -1, sencibilite_resucrage = 0;

		if (selected_Periode.equals((String) "Matin")) {
			sencibilite = UserPreference.getSencibitliteInsulineMatin();
		}

		else {
			sencibilite = UserPreference.getSencibitliteInsulineSoir();
		}

		maxGlycimie = UserPreference.getMaxGlycemie();
		minGlycimie = UserPreference.getMinGlycemie();
		sencibilite_resucrage = UserPreference.getSencibitlite();

		if (glycimieActuelle.getText().toString().trim() != null) {
			String tmp = glycimieActuelle.getText().toString().trim();
			Log.v("MainActivity: ", tmp);
			if (tmp == null || tmp.equals("")) {
				tmp = "0";
			}
			glycimie = Double.parseDouble(tmp);
		}

		if (glycimie <= maxGlycimie && glycimie >= minGlycimie) {
			insulineCorrection=0;
			return;
		}

		else if (glycimie > maxGlycimie) {
			insulineCorrection = (glycimie - maxGlycimie) / (sencibilite * 100);
		} else if (glycimie < minGlycimie) {
			insulineCorrection = (glycimie - minGlycimie) / (sencibilite * 100);
			resucrage = (minGlycimie - glycimie)
					/ (sencibilite_resucrage * 100);
		}
	}

	public void makeCalculOnUI() {

		if (selectionRadio == 1) {
			layaoutResucrage.setVisibility(View.VISIBLE);
			layaoutInsuline.setVisibility(View.GONE);
			results_resucrage.setText(String.valueOf(resucrage));
			
		} else if (selectionRadio == 2) {
			layaoutResucrage.setVisibility(View.GONE);
			layaoutInsuline.setVisibility(View.VISIBLE);
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			df.setMinimumFractionDigits(2);
			df.setDecimalSeparatorAlwaysShown(true);

			String temp = df.format(insulineManger);
			results_insuline_pour_manger.setText(temp);
			
			temp = df.format(insulineCorrection);
			results_correction_pour_manger.setText(temp);

		}
	}

	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		selected_Periode = parent.getItemAtPosition(pos).toString();
		calculerCorrectionInsuline();
		calculerInsulinePourManger();
		makeCalculOnUI();
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case -1:
			Log.v(TAG, "Choices cleared!");
			break;
		case R.id.radioButton_correction:
			Log.v(TAG, "calculs pour correction");
			this.selectionRadio = 1;
			layaoutResucrage.setVisibility(View.VISIBLE);
			layaoutInsuline.setVisibility(View.GONE);
			break;
		case R.id.radioButton_manger:
			Log.v(TAG, "calcule pour insuline");
			this.selectionRadio = 2;
			layaoutResucrage.setVisibility(View.GONE);
			layaoutInsuline.setVisibility(View.VISIBLE);
			break;
		default:
			Log.v(TAG, "Huh?");
			break;
		}

	}

	private void getOperation() {
		Log.v(TAG, "getNamePrefix()");

		switch (radioGroup.getCheckedRadioButtonId()) {
		case -1:
			Log.v(TAG, "Choices cleared!");
			this.selectionRadio = -1;
			break;
		case R.id.radioButton_correction:
			Log.v(TAG, "calculs pour correction");
			this.selectionRadio = 1;
			layaoutResucrage.setVisibility(View.VISIBLE);
			layaoutInsuline.setVisibility(View.GONE);
			break;
		case R.id.radioButton_manger:
			Log.v(TAG, "calcule pour insuline");
			this.selectionRadio = 2;
			layaoutResucrage.setVisibility(View.GONE);
			layaoutInsuline.setVisibility(View.VISIBLE);
			break;
		default:
			Log.v(TAG, "Huh?");
			this.selectionRadio = -1;
			break;
		}

	}

	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
		
	}

	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	public void onTextChanged(CharSequence s, int start, int before, int count) {
		selected_Periode = peroide.getSelectedItem().toString();
		calculerCorrectionInsuline();
		calculerInsulinePourManger();
		makeCalculOnUI();
		
	}

}
