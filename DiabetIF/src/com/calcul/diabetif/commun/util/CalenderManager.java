package com.calcul.diabetif.commun.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.calcul.diabetif.R;

import java.util.Date;
import java.util.TimeZone;

/**
 * @author salili
 */
public class CalenderManager {

    /**
     * delete Rdv in the calendar
     *
     * @param activity
     * @param slot
     */
    final static String TAG = CalenderManager.class.getName();

    public static void deleteRdv(final Activity activity, final Date startDate,
                                 final Date endDate) {

        long eventId = getEventId(activity, startDate, endDate);
        final String calendarUriBase = getCalendarUriBase(activity);
        if (calendarUriBase != null && eventId > 0) {
            final Uri eventUri = ContentUris.withAppendedId(
                    Uri.parse(calendarUriBase + "/events"), eventId);
            final ContentResolver cr = activity.getContentResolver();
            cr.delete(eventUri, null, null);
        }

    }

    /**
     * add RDV to calendar
     *
     * @param activity
     * @param startDate
     * @param endDate
     * @param storeName
     * @return
     */
    public static Long addRdv(final Activity activity, final Date startDate,
                              final Date endDate, final String storeName) {
        Log.i(TAG, "addRdv");
        // get calendar
        final String calendarUriBase = getCalendarUriBase(activity);
        if (calendarUriBase != null) {
            Log.i(TAG, "addRdv ==> (calendarUriBase != null)");

            final String title = activity.getString(R.string.ratio_matin);
            final long startTime, endTime;
            startTime = startDate.getTime();
            endTime = startTime + 3600000;
            Log.i(TAG, "addRdv ==> startDate = " + startTime);
            Log.i(TAG, "addRdv ==> endDate = " + endTime);
            final Uri EVENTS_URI = Uri.parse(calendarUriBase + "/events");
            final ContentResolver cr = activity.getContentResolver();

            // event insert
            ContentValues values = new ContentValues();
            final int calendarId = getCalendar(activity, calendarUriBase);
            if (calendarId != -1) {
                values.put("calendar_id", calendarId);
                values.put("title", title);
                values.put("allDay", 0);
                values.put("dtstart", startTime);
                values.put("dtend", endTime);
                values.put("eventLocation", storeName);
                values.put("eventTimezone", TimeZone.getDefault()
                        .getDisplayName());
                // status: 0~ tentative; 1~ confirmed; 2~ canceled
                values.put("eventStatus", 1);
                values.put("hasAlarm", 1);
                final Uri event = cr.insert(EVENTS_URI, values);

                // reminder insert
                final Uri REMINDERS_URI = Uri.parse(calendarUriBase
                        + "/reminders");
                values = new ContentValues();
                values.put("event_id",
                        Long.parseLong(event.getLastPathSegment()));
                values.put("method", 1);
                values.put("minutes", 15);
                cr.insert(REMINDERS_URI, values);

                return Long.parseLong(event.getLastPathSegment());
            }
        }
        return null;

    }

    /**
     * update rdv in calendar
     *
     * @param activity
     * @param newSlot
     * @param startDate
     * @param endDate
     */
    public static void updateRdv(final Activity activity,
                                 final int newSlot, final Date startDate, final Date endDate) {

        Log.i(TAG, "updateRdv");

        final String calendarUriBase = getCalendarUriBase(activity);
        if (calendarUriBase != null) {
            final String title = activity.getString(R.string.resucrage);
            final long startTime, endTime;
            startTime = 0;//newSlot.getDateBegin().getTime();
            endTime = startTime + 3600000;

            long enventID = getEventId(activity, startDate, endDate);

            // test if the event that match slot exist
            if (enventID > 0) {

                final Uri eventUri = ContentUris.withAppendedId(
                        Uri.parse(calendarUriBase + "/events"), enventID);
                final ContentResolver cr = activity.getContentResolver();

                // event insert
                final ContentValues values = new ContentValues();
                values.put("title", title);
                values.put("dtstart", startTime);
                values.put("dtend", endTime);

                cr.update(eventUri, values, null, null);
            }

        }
    }

    // public static void updateRdv(final Activity activity,
    // final Date oldStartDate,final Date oldEndDate, final Date startDate,
    // final Date endDate) {
    //
    //
    // final String calendarUriBase = getCalendarUriBase(activity);
    // if (calendarUriBase != null) {
    // final String title = activity.getString(R.string.calender_title);
    // final long startTime, endTime;
    // startTime = startDate.getTime();
    // endTime = startTime+ 3600000;;
    //
    // long enventID = getEventId(activity, oldStartDate, oldEndDate);
    // //test if the event that match slot exist
    // if (enventID>0) {
    // final Uri eventUri = ContentUris.withAppendedId(
    // Uri.parse(calendarUriBase + "/events"), enventID);
    // final ContentResolver cr = activity.getContentResolver();
    //
    // // event insert
    // final ContentValues values = new ContentValues();
    // values.put("title", title);
    // values.put("dtstart", startTime);
    // values.put("dtend", endTime);
    //
    // cr.update(eventUri, values, null, null);
    // }
    //
    // }
    // }

    /**
     * return id of event that match the slot
     *
     * @param activity
     * @param slot
     * @return
     */
    public static long getEventId(final Activity activity,
                                  final Date pStartDate, final Date pEndDate) {
        final String calendarUriBase = getCalendarUriBase(activity);
        final String testTitel = activity.getString(R.string.resucrage);
        final String beginDate = String.valueOf(pStartDate.getTime());
        final String dateEnd = String.valueOf(pStartDate.getTime() + 3600000);

        Uri CALENDAR_URI = Uri.parse(calendarUriBase + "/events");
        Cursor c = activity.getContentResolver().query(CALENDAR_URI, null,
                null, null, null);
        if (c.moveToFirst()) {
            while (c.moveToNext()) {
                String title = c.getString(c.getColumnIndex("title"));
                String startDate = c.getString(c.getColumnIndex("dtstart"));
                String endDate = c.getString(c.getColumnIndex("dtend"));
                // event id
                String id = c.getString(c.getColumnIndex("_id"));
                if ((title == null) || (startDate == null) || (endDate == null)) {
                } else {
                    if (title.equals(testTitel)) {
                        Log.i(TAG, "title =" + title + " \n beginDate = "
                                + beginDate + " \n dateEnd = " + dateEnd
                                + "\n****************************\n");
                    }

                    if (title.equals(testTitel) && startDate.equals(beginDate)
                            && endDate.equals(dateEnd)) {
                        Log.i(TAG, "title =" + title + " \n beginDate = "
                                + beginDate + " \n dateEnd = " + dateEnd
                                + "\n****************************\n");

                        return Long.parseLong(id);

                    }
                }
            }
        }

        return -1;
    }

    /**
     * Indicates if calendar is available on device
     *
     * @param act
     * @return
     */
    public static boolean isCalendarAvailable(final Activity act) {
        return getCalendarUriBase(act) != null;
    }

    /**
     * Return the base URI depending on android version, and if Calendar is
     * present on device
     *
     * @param act
     * @return
     */

    private static String getCalendarUriBase(final Activity act) {

        String calendarUriBase = null;
        // android 2.2 and above
        Uri calendars = Uri.parse("content://com.android.calendar/calendars");
        Cursor managedCursor = null;
        try {
            managedCursor = act.managedQuery(calendars, null, null, null, null);
        } catch (final Exception e) {
        }
        if (managedCursor != null) {
            calendarUriBase = "content://com.android.calendar";
            managedCursor.close();
        } else {
            // before 2.2
            calendars = Uri.parse("content://calendar/calendars");
            try {
                managedCursor = act.managedQuery(calendars, null, null, null,
                        null);
            } catch (final Exception e) {
            }
            if (managedCursor != null) {
                calendarUriBase = "content://calendar";
                managedCursor.close();
            }
        }
        return calendarUriBase;
    }

    /**
     * Gets the calendar Id for the current google user
     *
     * @param context
     * @return
     */
    private static int getCalendar(final Activity context,
                                   final String calendarUriBase) {
        final String[] l_projection = new String[]{"_id"};
        int callId = -1;

        if (calendarUriBase != null) {
            final Uri l_calendars = Uri.parse(calendarUriBase + "/calendars");
            // get the first calendar
            final Cursor l_managedCursor = context.getContentResolver().query(
                    l_calendars, l_projection, null, null, null);

            if (l_managedCursor != null) {
                if (l_managedCursor.moveToFirst()) {
                    callId = l_managedCursor.getInt(0);
                }
                l_managedCursor.close();
            }
        }
        return callId;

    }
}
