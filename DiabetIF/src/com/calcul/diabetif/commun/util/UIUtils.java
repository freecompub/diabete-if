package com.calcul.diabetif.commun.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.widget.EditText;

/**
 * A class containing utility methods for creating/manipulating user interface.
 *
 * @author yannarak_w
 */
public class UIUtils {

    private static final String TAG = UIUtils.class.getSimpleName();

    private final static int SCREEN_HEIGHT_LIMIT_TO_AUTO_RESIZE = 480;

    /**
     * Convert DIP value to Pixel value
     *
     * @param context activity context
     * @param dip     dip value to convert
     * @return pixel value converted
     */
    public static float convertDipToPixel(Context context, int dip) {
        Log.v(TAG, "convertDipToPixel() dip = " + dip);
        Resources r = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());
    }

    /**
     * Compute the scale ratio between image 1 and image 2
     *
     * @param w1 width of image 1
     * @param h1 height of image 1
     * @param w2 width of image 2
     * @param h2 height of image 2
     * @return scale ratio between image 1 and image 2
     */
    public static float computeScaleFactor(int w1, int h1, int w2, int h2) {
        Log.v(TAG, "computeScaleFactor() w1 = " + w1 + ", h1 =" + h1 + ", w2 = " + w2 + ", h2 = "
                + h2);
        float scaleHeight = (float) h1 / (float) h2;
        float scaleWidth = (float) w1 / (float) w2;

        if (scaleWidth < scaleHeight) {
            return scaleWidth;
        } else {
            return scaleHeight;
        }
    }

    /**
     * Sets the orientation of the screen based on the sensor if the screen height is higher than
     * the threshold. Otherwise, the portrait orientation is used.
     *
     * @param activity
     */
    public static void configScreenOrientation(Activity activity) {
        Log.v(TAG, "configScreenOrientation()");
        Display display = activity.getWindowManager().getDefaultDisplay();
        int height = display.getWidth();
        if (height >= SCREEN_HEIGHT_LIMIT_TO_AUTO_RESIZE) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        } else {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }


    public static AlertDialog getSimpleAlertDialog(Context context, String title, String msg) {

        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.setButton(context.getString(android.R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        return dialog;
    }

    /**
     * Get string from resource by identifier
     */
    public static String getStringByIdentifier(Context context, String resName) {
        int resId = context.getResources().getIdentifier(resName, "string",
                context.getPackageName());
        return context.getResources().getString(resId);
    }

    /**
     * Get trimmed string value of editText
     */
    public static String getTrimmedStringFromEditText(EditText editText) {
        return editText.getText().toString().trim();
    }
}
