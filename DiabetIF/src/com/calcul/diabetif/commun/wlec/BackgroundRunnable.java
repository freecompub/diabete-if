package com.calcul.diabetif.commun.wlec;

import android.os.Handler;
import android.os.Message;

public abstract class BackgroundRunnable implements Runnable {

    private static final int SUCCESS_WHAT = 1;
    private static final int FAILURE_WHAT = 2;
    static boolean isTestProject = false;

    private Handler handler;

    // Ugly hack to test the background runnable.
    // TODO: Find a better way to set the test environnement.
    public static void setTestEnvironnement(boolean isTestProject1) {
        isTestProject = isTestProject1;
    }

    /** Constructor is called in the Main thread. */
    public BackgroundRunnable() {
        handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == SUCCESS_WHAT) {
                    success();
                    return;
                }

                failure();
            }
        };
    }

    /**
     * Call success() in the main Thread.
     * This function is called within the background thread.
     */
    protected final void sendSuccessMessage() {
        if (isTestProject) {
            success();
            return;
        }
        handler.sendEmptyMessage(SUCCESS_WHAT);
    }

    /**
     * Call failure(AlertDialogHelper.C4Exception) in the main Thread.
     * This function is called within the background thread.
     */
    protected final void sendFailureMessage() {
        if (isTestProject) {
            failure();
            return;
        }
        handler.sendEmptyMessage(FAILURE_WHAT);
    }

    abstract protected void success();

    abstract protected void failure();

}
