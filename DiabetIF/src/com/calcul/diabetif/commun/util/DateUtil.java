package com.calcul.diabetif.commun.util;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * A Utility class containing the methods for date manipulation
 *
 * @author yannarak_w
 */
public class DateUtil {
    /**
     * The reference date of the iOS system.
     */
    public static final long NS_DATE_REFERENCE_SEC = 978307200;
    private static final String TAG = DateUtil.class.getSimpleName();
    /**
     * The default formatter for short date without year
     */
    private static final DateFormat SHORT_DATE_WITHOUT_YEAR_FORMATTER = new SimpleDateFormat("dd/MM");
    /**
     * The default formatter for short date
     */
    private static final DateFormat SHORT_DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
    /**
     * The default formatter for full date
     */
    private static final DateFormat FULL_DATE_FORMATTER = new SimpleDateFormat("dd-MM-yyyy");
    /**
     * The formatter for parse date data from web service
     */
    private static final DateFormat ISO8601_DATE_FORMATTER_TYPEI = new SimpleDateFormat("yyyyMMdd'T'HHmmssZ");
    /**
     * The formatter for parse date data from web service without zone
     */
    private static final DateFormat ISO8601_DATE_FORMATTER_TYPEI_WITHOUT_ZONE = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
    /**
     * The formatter for ISO8601
     */
    private static final DateFormat ISO8601_DATE_FORMATTER_TYPEII = new SimpleDateFormat("yyyyMMdd'T'HHmmssZZZ");
    private static final DateFormat ISO8601_DATE_FORMATTER_TYPEII_WITHOUT_ZONE = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    /**
     * The formatter for full text date format
     */
    private static final DateFormat DATE_PATTERN_FULL_TEXT_FORMATTER = new SimpleDateFormat("EEEEE dd MMMMM");
    /**
     * The formatter for full text datetime format
     */
    private static final DateFormat DATETIME_PATTERN_FULL_TEXT_FORMATTER = new SimpleDateFormat("EEEE d MMMM kk:mm");
    /**
     * The formatter for day only
     */
    private static final DateFormat TIME_PATTERN_SHORT_FORMATTER = new SimpleDateFormat("HH:mm");
    /**
     * Separator for time range formatter
     */
    private static final String TIME_RANGE_SEPARATOR = " - ";
    /**
     * The formatter for full text datetime format
     */
    private static final DateFormat DATE_PATTERN_SHORT_TEXT_FORMATTER = new SimpleDateFormat("EEEEE dd-MM", Locale.US);
    /**
     * The default formatter for short date with space
     */
    private static final DateFormat SHORT_DATE_FORMATTER_WITH_SPACE = new SimpleDateFormat("dd / MM / yyyy");
    /**
     * The default formatter for short date without day
     */
    private static final DateFormat SHORT_DATE_FORMATTER_WITHOUT_DAY = new SimpleDateFormat("MMMMMMMM yyyy");
    /**
     * The default formatter for short date separated with dot
     */
    private static final DateFormat SHORT_DATE_FORMATTER_SEPARATED_DOT = new SimpleDateFormat("dd.MM.yyyy");

    private static final DateFormat MONTH_SHORT_FORMATTER = new SimpleDateFormat("MMM");


    static {
        TimeZone timezone = TimeZone.getTimeZone("Europe/Brussels");
        SHORT_DATE_WITHOUT_YEAR_FORMATTER.setTimeZone(timezone);
        SHORT_DATE_FORMATTER.setTimeZone(timezone);
        FULL_DATE_FORMATTER.setTimeZone(timezone);
        ISO8601_DATE_FORMATTER_TYPEI.setTimeZone(timezone);
        ISO8601_DATE_FORMATTER_TYPEI_WITHOUT_ZONE.setTimeZone(timezone);
        ISO8601_DATE_FORMATTER_TYPEII.setTimeZone(timezone);
        TIME_PATTERN_SHORT_FORMATTER.setTimeZone(timezone);
        DATE_PATTERN_FULL_TEXT_FORMATTER.setTimeZone(timezone);
        DATETIME_PATTERN_FULL_TEXT_FORMATTER.setTimeZone(timezone);
        TIME_PATTERN_SHORT_FORMATTER.setTimeZone(timezone);
    }

    /**
     * Private Constructor to avoid initialization
     */
    private DateUtil() {
    }

    /**
     * Converts the timestamp from iOS system to the Date object in Java
     *
     * @param timestamp the timestamp from iOS
     * @return a Date object represents the given timestamp from iOS System
     */
    public static Date getDateFromNSTimestamp(long timestamp) {
        Log.v(TAG, "getDateFromNSTimestamp() : timestamp = " + timestamp);
        long time = (NS_DATE_REFERENCE_SEC + timestamp) * 1000;
        return new Date(time);
    }

    public static Date getDateFromNSTimestampGMT(long timestamp) {
        long time = (NS_DATE_REFERENCE_SEC + timestamp) * 1000;
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        c.add(Calendar.HOUR, -7);
        return c.getTime();
    }

    /**
     * Gets the current timestamp in the iOS system.
     */
    public static long getCurrentNSTimestamp() {
        Log.v(TAG, "getCurrentNSTimestamp()");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        long time = c.getTime().getTime();
        return (time - (NS_DATE_REFERENCE_SEC * 1000)) / 1000;
    }


    public static long getCurrentNSTimestampGMT() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, -7);
        long time = c.getTime().getTime();
        return (time / 1000 - NS_DATE_REFERENCE_SEC);
    }

    /**
     * Converts the Java timestamp to the iOS timestamp and create a Date object
     * from the iOS timestamp.
     */
    public static Date getDateFromTimestamp(long timestamp) {
        Log.v(TAG, "getDateFromTimestamp() timestamp = " + timestamp);
        long time = (timestamp - (NS_DATE_REFERENCE_SEC * 1000)) / 1000;
        return new Date(time);
    }

    /**
     * Formats the given date using the default short date formatter.
     */
    public static String formatShortDateWithoutYear(Date date) {
        Log.v(TAG, "formatShortDate() date = " + date);
        String dateString = "";
        if (date != null) {
            dateString = SHORT_DATE_WITHOUT_YEAR_FORMATTER.format(date);
        }
        return dateString;
    }

    /**
     * Formats the given date using the default short date formatter.
     */
    public static String formatShortDate(Date date) {
        Log.v(TAG, "formatShortDate() date = " + date);
        String dateString = "";
        if (date != null) {
            dateString = SHORT_DATE_FORMATTER.format(date);
        }
        return dateString;
    }

    /**
     * Formats the given date using the default short date formatter.
     */
    public static String formatShortDate(int year, int month, int day) {
        Log.v(TAG, "formatShortDate() ");

        return DateUtil.formatShortDate(new Date(new GregorianCalendar(year, month, day).getTimeInMillis()));
    }

    /**
     * Formats the given date using the default full date formatter.
     */
    public static String formatFullDate(Date date) {
        Log.v(TAG, "formatFullDate() date = " + date);
        String dateString = "";
        if (date != null) {
            dateString = FULL_DATE_FORMATTER.format(date);
        }
        return dateString;
    }

    /**
     * Parse string to date
     *
     * @param date
     * @throws ParseException
     */
    public static Date parseISO8601Date(String date) throws ParseException {
        Log.v(TAG, "parseISO8601Date() date=" + date);
        Date returnDate = null;
        date.replaceAll("\\+0([0-9]){1}\\:00", "+0$100");
        date.replaceAll(":", "");
        returnDate = ISO8601_DATE_FORMATTER_TYPEI.parse(date);
        return returnDate;
    }

    /**
     * Parse string to date without zone
     *
     * @param date
     * @throws ParseException
     */
    public static Date parseISO8601DateWithoutZone(String date) throws ParseException {
        Log.v(TAG, "parseISO8601DateWithoutZone() date=" + date);
        Date returnDate = null;
        date.replaceAll("\\+0([0-9]){1}\\:00", "+0$100");
        date.replaceAll(":", "");
        returnDate = ISO8601_DATE_FORMATTER_TYPEI_WITHOUT_ZONE.parse(date);
        return returnDate;
    }

    /**
     * Converts the Date object to the date string in the ISO 8601 format
     */
    public static final String getISO8601DateString(Date date) {
        Log.v(TAG, "getDateIdo8601() date = " + date);
        return ISO8601_DATE_FORMATTER_TYPEII.format(date);
    }

    public static final String getISO8601DateWithoutZoneString(Date date) {
        Log.v(TAG, "getISO8601DateWithoutZoneString() date = " + date);
        return ISO8601_DATE_FORMATTER_TYPEII_WITHOUT_ZONE.format(date);
    }

    /**
     * Converts the Date object to the date string in the full text format
     */
    public static final String getFullTextDateString(Date date) {
        Log.v(TAG, "getFullTextDateString() date = " + date);
        return DATE_PATTERN_FULL_TEXT_FORMATTER.format(date);
    }

    /**
     * Converts the Date object to the datetime string in the full text format
     */
    public static final String getFullTextDateTimeString(Date date) {
        Log.v(TAG, "getFullTextDateTimeString() date = " + date);
        return DATETIME_PATTERN_FULL_TEXT_FORMATTER.format(date);
    }

    public static final String getShortTextDateTimeString(Date date) {
        return DATE_PATTERN_SHORT_TEXT_FORMATTER.format(date);
    }

    /**
     * Converts the Date object to the time string in the short format
     */
    public static final String getShortTimeString(Date date) {
        Log.v(TAG, "getShortTimeString() date = " + date);
        return TIME_PATTERN_SHORT_FORMATTER.format(date);
    }

    /**
     * Converts the 2 Date objects to the time string in the short range format
     */
    public static final String getShortTimeRangeString(Date start, Date end) {
        Log.v(TAG, "getShortTimeRangeString() start = " + start + ", end=" + end);
        return new StringBuilder(TIME_PATTERN_SHORT_FORMATTER.format(start)).append(TIME_RANGE_SEPARATOR)
                .append(TIME_PATTERN_SHORT_FORMATTER.format(end)).toString();
    }

    public static final String getShortMonth(Date date) {
        return MONTH_SHORT_FORMATTER.format(date);
    }

    public static final Date getNow() {
        return Calendar.getInstance().getTime();
    }

    public static final Date getToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear(Calendar.HOUR);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
        return calendar.getTime();
    }

    public static final Date getTomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    public static boolean isUpdateRequired(Date lastUpdate, long now, long cacheMaxTime) {
        if (lastUpdate == null) {
            return true;
        }

        return now - lastUpdate.getTime() > cacheMaxTime;
    }

    public static boolean isUpdateRequired(long lastUpdateTime, long now, long cacheMaxTime) {
        return now - lastUpdateTime > cacheMaxTime;
    }

    public static boolean isUpdateRequiredMillis(Date lastUpdated, long cacheMaxtime) {
        return isUpdateRequired(lastUpdated, Calendar.getInstance().getTimeInMillis(), cacheMaxtime);
    }

    public static boolean isUpdateRequiredMillis(long lastUpdatedTime, long cacheMaxtime) {
        return isUpdateRequired(lastUpdatedTime, Calendar.getInstance().getTimeInMillis(), cacheMaxtime);
    }

    public static void clearTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public static String formatLastUpdateDate(Date date) {
        return SHORT_DATE_FORMATTER_WITH_SPACE.format(date);
    }

    public static String formatShortDateWithoutDay(Date date) {
        return SHORT_DATE_FORMATTER_WITHOUT_DAY.format(date);
    }

    public static String formatShortDateSeparatedWithDot(Date date) {
        return SHORT_DATE_FORMATTER_SEPARATED_DOT.format(date);
    }
}
