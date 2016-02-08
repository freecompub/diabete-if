package com.calcul.diabetif.commun.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

/**
 * Helper class that interact with the only one EShop Progress Dialog.
 *
 * @author Thibault Duchateau
 */
public class ProgressDialogHelper {
    private static String TAG = ProgressDialogHelper.class.getSimpleName();

    private static ProgressDialog progressDialog;

    /**
     * Shows the progress dialog.
     *
     * @param context        Typically the activity where the progress dialog is started.
     * @param loadingMessage The loading message resource id.
     */
    public static void show(Context context, int loadingMessage) {
        Log.v(TAG, "show(Context context, int loadingMessage)");

        if (progressDialog != null) {
            return;
        }
        progressDialog = ProgressDialog.show(context, "", context.getResources().getText(loadingMessage), true);
    }

    /**
     * Hides the progress dialog.
     */
    public static void hide() {
        Log.v(TAG, "hide()");

        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}