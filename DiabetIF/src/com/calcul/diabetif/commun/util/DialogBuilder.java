package com.calcul.diabetif.commun.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;

/**
 * Helper class for building alert dialog with OK/Cancel button
 * 
 * @author saravuth
 * 
 */
public class DialogBuilder {

    private static final String TAG = DialogBuilder.class.getSimpleName();
    private static AlertDialog.Builder builder;

    /**
     * Build and Show alert dialog with OK/Cancel button
     * @param context
     * @param title
     * @param contentView
     * @param okClickListener
     * @param cancelClickListener
     * @return
     */
    public static AlertDialog show(Context context, int titleId, View contentView, DialogInterface.OnClickListener okClickListener,
            DialogInterface.OnClickListener cancelClickListener) {
        Log.v(TAG, "show(Context context, int titleId, View contentView, DialogInterface.OnClickListener okClickListener, DialogInterface.OnClickListener cancelClickListener)");
        init(context);
        /*
         * Create alert dialog
         */
        builder.setTitle(titleId);
        builder.setView(contentView);
        builder.setCancelable(true);
        /*
         * Set OK Button
         */
        builder.setPositiveButton(android.R.string.ok, okClickListener);
        /*
         * Set Cancel Button
         */
        builder.setNegativeButton(android.R.string.cancel, cancelClickListener);
        return builder.show();
    }

    private static void init(Context context) {
        Log.v(TAG, "init(Context context)");
        builder = new AlertDialog.Builder(context);
    }
}
