package com.calcul.diabetif.commun.ui;

import android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ListAdapter;

public class AlertDialogHelper {

    private static AlertDialog alertDialog;

    public static void showAlertDialogForException(Context context, C4Exception exception) {
        showAlertDialog(context, null, exception.getLocalizedMessage(context), null);
    }

    public static void showAlertDialogForExceptionWithDelegate(Context context,
                                                               C4Exception exception, DialogInterface.OnClickListener onClicklistener) {
        showAlertDialog(context, null, exception.getLocalizedMessage(context), onClicklistener);
    }

    public static void showAlertDialogForException(Context context, String title,
                                                   C4Exception exception) {
        showAlertDialog(context, title, exception.getLocalizedMessage(context), null);
    }

    public static void showAlertDialogForExceptionWithDelegate(Context context, String title,
                                                               C4Exception exception, DialogInterface.OnClickListener onClicklistener) {
        showAlertDialog(context, title, exception.getLocalizedMessage(context), onClicklistener);
    }

    public static void showAlertDialogForInfomation(Context context, String message) {
        showAlertDialog(context, null, message, null);
    }

    public static void showAlertDialog(Context context, String title, String message) {
        showAlertDialog(context, title, message, null);
    }

    public static void showAlertDialog(Context context, int title, int message) {
        showAlertDialog(context, context.getString(title), context.getString(message), null);
    }

    public static void showAlertDialog(Context context, String title, String message,
                                       DialogInterface.OnClickListener onClicklistener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (title != null) {
            builder.setTitle(title);
        }
        if (message != null) {
            builder.setMessage(message);
        }
        builder.setCancelable(false);
        if (onClicklistener == null) {
            onClicklistener = new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }

            };
        }
        builder.setNeutralButton(R.string.ok, onClicklistener);
        alertDialog = builder.show();
    }

    public static void showAlertDialogWithItems(Context context, String title, String[] items,
                                                DialogInterface.OnClickListener onClicklistener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (title != null) {
            builder.setTitle(title);
        }
        builder.setItems(items, onClicklistener);
        builder.show();
    }

    public static void showAlertDialogWithAdapter(Context context,
                                                  String title, ListAdapter adapter,
                                                  DialogInterface.OnClickListener onClicklistener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (title != null) {
            builder.setTitle(title);
        }
        builder.setSingleChoiceItems(adapter, 0, onClicklistener);
        builder.show();
    }

    public static void showAlertDialogWithView(Context context, String title, View view, DialogInterface.OnClickListener onClicklistener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (title != null) {
            builder.setTitle(title);
        }
        builder.setCancelable(false);
        builder.setView(view);
        builder.setNeutralButton(R.string.ok, onClicklistener);
        builder.show();
    }

    public static void cancelCurrentAlertDialog() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.cancel();
        }
    }

    public static void showAlertDialogForChoice(Context context, String title, String message, String oktext, String cancelText,
                                                DialogInterface.OnClickListener onClicklistener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (title != null) {
            builder.setTitle(title);
        }
        if (message != null) {
            builder.setMessage(message);
        }
        builder.setCancelable(false);
        if (onClicklistener == null) {
            onClicklistener = new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }

            };
        }

        builder.setPositiveButton(oktext, onClicklistener);
        builder.setNegativeButton(cancelText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        alertDialog = builder.show();
    }

    public interface C4Exception {
        String getLocalizedMessage(Context context);
    }
}