package com.calcul.diabetif.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

import com.calcul.diabetif.R;
import com.calcul.diabetif.commun.ui.TabBarBadgeView;


public class IfTabActivity extends TabActivity {

    /**
     * Member fields
     */
    public static final int TAB_MAIN_SCREEN = 0;
    public static final int TAB_SETTING = 1;
    public static final int TAB_CALCULATOR = 2;
    public static final int TAB_GRAPHIC = 3;
    public static final String BROADCAST_BASKET_CHANGED = "com.carrefour.android.app.eshop.intent.action.BASKET_CHANGED";
    private static final String TAG = IfTabActivity.class.getSimpleName();
    private TabBarBadgeView badgeView;
    private OnTouchListener onTabTouchListener = new OnTouchListener() {

        //@Override
        public boolean onTouch(View v, MotionEvent event) {
            Log.v(TAG, "onTouch view:" + v.getId());
            if (v.getId() == getTabHost().getCurrentTab()) {
                Log.v(TAG, "onTouch on current tab");
                TabGroupActivity tabGroup = (TabGroupActivity) getLocalActivityManager()
                        .getActivity(v.getId() + "");
                if (tabGroup != null) {
                    tabGroup.goToFirstChild();
                }
            }
            return false;
        }
    };
    /* inner classes */
    private OnTabChangeListener onTabChangeListener = new OnTabChangeListener() {

        //		@Override
        public void onTabChanged(String tabId) {
            Log.v(TAG, "onTabChanged " + tabId);
            //updateTopBarButton(tabId);
            updateCurrentActivity(tabId);
        }


        private void updateCurrentActivity(String tabId) {
            if (TAB_GRAPHIC == Integer.parseInt(tabId)) {
                TabGroupActivity tabGroup = (TabGroupActivity) getLocalActivityManager()
                        .getActivity(tabId);
                if (tabGroup != null) {
                    tabGroup.goToSecondChild();
                }
            } else if (TAB_SETTING == Integer.parseInt(tabId)) {
                TabGroupActivity tabGroup = (TabGroupActivity) getLocalActivityManager()
                        .getActivity(tabId);
                if (tabGroup != null) {
                    tabGroup.goToFirstChild();
                }
            } else if (TAB_MAIN_SCREEN == Integer.parseInt(tabId)) {
                TabGroupActivity tabGroup = (TabGroupActivity) getLocalActivityManager()
                        .getActivity(tabId);
                if (tabGroup != null) {
                    tabGroup.goToSecondChild();
                }
            }
            // else if (TAB_SHOPPING_LIST == Integer.parseInt(tabId)) {
            // TabGroupActivity tabGroup =
            // (TabGroupActivity)getLocalActivityManager().getActivity(tabId);
            // tabGroup.goToFirstChild();
            // }
            else if (TAB_CALCULATOR == Integer.parseInt(tabId)) {
                TabGroupActivity tabGroup = (TabGroupActivity) getLocalActivityManager()
                        .getActivity(tabId);
                if (tabGroup != null) {
                    tabGroup.goToFirstChild();
                }
            }
        }


    };

    /**
     * Life-cycle method
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        Log.v(TAG, "onCreate(Bundle savedInstanceState)");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eshop_eshoptab);
        setTabs();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.v(TAG, "onConfigurationChanged(Configuration newConfig)");
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.v(TAG, "onSaveInstanceState(Bundle outState)");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        Log.v(TAG, "onRestoreInstanceState");
        super.onRestoreInstanceState(state);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.v(TAG, "onNewIntent");
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.v(TAG, "onActivityResult() requestCode=" + requestCode);
    }

    @Override
    public void onResume() {
        Log.v(TAG, "onResume()");
        super.onResume();
        TabHost tabHost = getTabHost();
        tabHost.setOnTabChangedListener(onTabChangeListener);

        int selectedTab = getIntent().getIntExtra("extra_selected_tab",
                -1);
        if (selectedTab != -1) {
            tabHost.setCurrentTab(selectedTab);
        }

    }

    @Override
    public void onPause() {
        Log.v(TAG, "onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.v(TAG, "onStop()");
        super.onStop();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.v(TAG, "onKeyDown() keyCode:" + keyCode);
        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {

            return true;

        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Set tabs.
     */
    private void setTabs() {
        Log.v(TAG, "setTabs()");

        TabHost tabHost = getTabHost();
        tabHost.clearAllTabs();

        if (tabHost.getTabWidget().getChildCount() <= 0) {
            addTab(String.valueOf(TAB_MAIN_SCREEN),
                    getString(R.string.eshop_eshoptab_product),
                    R.drawable.eshop_eshoptab_tabbar_product,
                    MainScreenGroupActivity.class);

            addTab(String.valueOf(TAB_SETTING),
                    getString(R.string.eshop_eshoptab_department),
                    R.drawable.eshop_eshoptab_tabbar_department,
                    SettingGroupActivity.class);

            addTab(String.valueOf(TAB_CALCULATOR),
                    getString(R.string.eshop_eshoptab_my_account),
                    R.drawable.eshop_eshoptab_tabbar_my_account,
                    CalculatorGroupActivity.class);

            addTab(String.valueOf(TAB_GRAPHIC),
                    getString(R.string.eshop_eshoptab_basket),
                    R.drawable.eshop_eshoptab_tabbar_basket,
                    GraphicGroupActivity.class);
        }

        Log.v(TAG, "setTabs() childCount:" + getTabWidget().getChildCount());
        for (int i = 0; i < getTabWidget().getChildCount(); i++) {
            getTabWidget().getChildAt(i).setOnTouchListener(onTabTouchListener);
        }
    }

    /**
     * Create a tab.
     *
     * @param labelId
     * @param drawableId
     * @param uri
     */
    private void addTab(String tabId, String labelId, int drawableId, Intent intent) {
        Log.v(TAG, "addTab(String labelId, int drawableId, Uri uri)");
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec = tabHost.newTabSpec(tabId);

        View tabIndicator = LayoutInflater.from(this).inflate(
                R.layout.eshop_eshoptab_indicator, getTabWidget(), false);
        tabIndicator.setId(Integer.valueOf(tabId));
        TextView title = (TextView) tabIndicator
                .findViewById(R.id.eshop_eshoptab_textview_title);
        title.setText(labelId);
        ImageView icon = (ImageView) tabIndicator
                .findViewById(R.id.eshop_eshoptab_imageview_icon);
        icon.setImageResource(drawableId);
        spec.setIndicator(tabIndicator);
        spec.setContent(intent);
        tabHost.addTab(spec);
    }

    /**
     * Create a tab.
     *
     * @param labelId
     * @param drawableId
     * @param uri
     */
    private void addTab(String tabId, String labelId, int drawableId,
                        Class<?> clazz) {
        Log.v(TAG, "addTab(String labelId, int drawableId, Uri uri)");
        Intent intent = new Intent(this, clazz);
        addTab(tabId, labelId, drawableId, intent);
    }

}
