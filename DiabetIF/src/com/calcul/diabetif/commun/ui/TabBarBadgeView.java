package com.calcul.diabetif.commun.ui;




import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TabWidget;

import com.calcul.diabetif.R;
import com.readystatesoftware.viewbadger.BadgeView;

public class TabBarBadgeView extends BadgeView {

    public TabBarBadgeView(Context context, View view) {
        super(context, view);
        init();
    }
    
    public TabBarBadgeView(Context context, TabWidget target, int index) {
        super(context, target,  index);
        init();
    }
    
    private void init(){
        setTextSize(10);
        setBackgroundResource(R.drawable.eshop_badge_selector);
        setGravity(Gravity.CENTER);
    }
    
}
