package com.calcul.diabetif.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;

import com.calcul.diabetif.commun.config.AppConfig;


public class TabGroupActivity extends ActivityGroup {
	/*
	 * Manage many activity called under TabHost.
	 * http://ericharlow.blogspot.com/
	 * 2010/09/experience-multiple-android-activities.html
	 */
	private static final String TAG = TabGroupActivity.class.getSimpleName();
	private static final int DIALOG_EXIT_CONFIRM = 0;
	public static final int SEARCH_NOT_FOUND = 10;
	public static final int SEARCH_AGIN = 11;

	/* Member fields */
	protected ArrayList<String> memberIdList;

	/* Life-cycle methods */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		Log.v(TAG, "onCreate()");
		super.onCreate(savedInstanceState);
		if (memberIdList == null) {
			memberIdList = new ArrayList<String>();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	/*
	 * This is called when a child activity of this one calls its finish method.
	 * This implementation calls {@link LocalActivityManager#destroyActivity} on
	 * the child activity and starts the previous activity. If the last child
	 * activity just called finish(),this activity (the parent), calls finish to
	 * finish the entire group.
	 * 
	 * @param child a child activity.
	 */
	@Override
	public void finishFromChild(Activity child) {
		Log.v(TAG, "finishFromChild()");

		int index = memberIdList.size() - 1;
		Log.v(TAG, "index " + index);
		if (index < 1) {
			finish();
			return;
		}

		LocalActivityManager manager = getLocalActivityManager();
		manager.destroyActivity(memberIdList.get(index), true);
		memberIdList.remove(index);
		index--;
		String memberId = memberIdList.get(index);
		Intent intent = manager.getActivity(memberId).getIntent();
		Window newWindow = manager.startActivity(memberId, intent);
		setContentView(newWindow.getDecorView());
	}

	public void getDefaultActivity() {
		Log.v(TAG, "getDefaultActivity()");
		Intent intent = new Intent(this, MainActivity.class);
		Window window = getLocalActivityManager().startActivity("MainActivity",
				intent);

		setContentView(window.getDecorView());
	}

	/**
	 * Starts an Activity as a child Activity to this.
	 * 
	 * @param Id
	 *            Unique identifier of the activity to be started.
	 * @param intent
	 *            The Intent describing the activity to be started.
	 * @throws android.content.ActivityNotFoundException.
	 */
	public void startChildActivity(String memberId, Intent intent) {
		Log.v(TAG, "startChildActivity()");
		memberId += System.currentTimeMillis();
		Window window = getLocalActivityManager().startActivity(memberId,
				intent);
		// .addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP )
		if (window != null) {
			memberIdList.add(memberId);
			setContentView(window.getDecorView());
		}
	}

	/**
	 * Finish all children activity except first child.
	 */
	public void goToFirstChild() {
		Log.v(TAG, "goToFirstChild()");
		int size = memberIdList.size();
		for (int i = size; i > 1; i--) {
			Activity currentActivity = getLocalActivityManager().getActivity(
					memberIdList.get(i - 1));
			currentActivity.finish();
		}
	}

	public void goToSecondChild() {
		Log.v(TAG, "goToFirstChild()");
		int size = memberIdList.size();
		for (int i = size; i > 2; i--) {
			Activity currentActivity = getLocalActivityManager().getActivity(
					memberIdList.get(i - 1));
			currentActivity.finish();
		}
	}

	/* User Interaction */

//	@Override
//	protected Dialog onCreateDialog(int id) {
//		CharSequence positiveBtnMessage = getResources().getText(
//				R.string.Y);
//		CharSequence negativeBtnMessage = getResources().getText(
//				R.string.eshop_no);
//		CharSequence title = getResources().getText(R.string.eshop_exit_title);
//		CharSequence message = getResources().getText(
//				R.string.eshop_exit_message);
//		AlertDialog.Builder builder = new AlertDialog.Builder(this);
//		builder.setCancelable(false);
//
//		switch (id) {
//		case DIALOG_EXIT_CONFIRM:
//			builder.setMessage(message);
//			builder.setTitle(title);
//			builder.setPositiveButton(positiveBtnMessage, exitPositiveClick);
//			builder.setNegativeButton(negativeBtnMessage, exitNegativeClick);
//			break;
//		}
//		AlertDialog dialog = builder.create();
//
//		return dialog;
//	}

//	/* Confirm to exit the application */
//	private OnClickListener exitPositiveClick = new OnClickListener() {
//		@Override
//		public void onClick(DialogInterface dialog, int which) {
//			Log.v(TAG, "exitPositiveClick.onClick()");
//			// android.os.Process.killProcess(android.os.Process.myPid());
//
//			finishAll();
//
//		}
//	};
//
//	/* Cancel to exit the application */
//	private OnClickListener exitNegativeClick = new OnClickListener() {
//
//		@Override
//		public void onClick(DialogInterface dialog, int which) {
//			Log.v(TAG, "exitNegativeClick.onClick()");
//			dialog.cancel();
//		}
//	};

	/**
	 * The primary purpose is to prevent systems before
	 * android.os.Build.VERSION_CODES.ECLAIR from calling their default
	 * KeyEvent.KEYCODE_BACK during onKeyDown.
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.v(TAG, "onKeyDown()");
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// preventing default implementation previous to
			// android.os.Build.VERSION_CODES.ECLAIR
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/*
	 * Overrides the default implementation for KeyEvent.KEYCODE_BACK so that
	 * all systems call onBackPressed()
	 */
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		Log.v(TAG, "onKeyUp()");
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			onBackPressed();
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}

	/**
	 * If a Child Activity handles KeyEvent.KEYCODE_BACK. Simply override and
	 * add this method.
	 */
	@Override
	public void onBackPressed() {
		Log.v(TAG, "onBackPressed()");
		int memberCount = memberIdList.size();
		Log.v(TAG, "Current activity:"
				+ getLocalActivityManager().getCurrentActivity());
		if (memberCount > 1) {
			Log.v(TAG, "finish current activity");
			Activity currentActivity = getLocalActivityManager().getActivity(
					memberIdList.get(memberCount - 1));
			currentActivity.finish();
		} else {
			Log.v(TAG, "onBackPressed() exit?");
			showDialog(DIALOG_EXIT_CONFIRM);
		}
	}

	public void finishPreviousActivityFromChild(int requestCode) {
		Log.v(TAG, "finishActivityFromChild()");
		Log.v(TAG, "memberIdList.size(): " + memberIdList.size());
		if (memberIdList.size() >= 2) {
			Log.v(TAG, "memberIdList.size(): " + memberIdList.size());
			int index = memberIdList.size() - 1;
			Log.v(TAG, "index " + index);

		}
	}

	private void removeActivity(int index) {
		LocalActivityManager manager = getLocalActivityManager();
		manager.destroyActivity(memberIdList.get(index), true);
		memberIdList.remove(index);
		index--;
		String memberId = memberIdList.get(index);
		Log.v(TAG, "memberId " + memberId);
		Intent intent = manager.getActivity(memberId).getIntent();
		Log.v(TAG, "intent " + intent);
		Window newWindow = manager.startActivity(memberId, intent);
		Log.v(TAG, "newWindow " + newWindow);
		setContentView(newWindow.getDecorView());
	}

//	private void finishAll() {
//		Intent intent = new Intent(this, FinshActivity.class);
//		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		startActivity(intent);
//		finish();
//	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		Log.i(TAG, "onConfigurationChanged");
		super.onConfigurationChanged(newConfig);


	}

}
