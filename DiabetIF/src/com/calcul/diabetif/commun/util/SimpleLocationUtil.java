package com.calcul.diabetif.commun.util;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;

public class SimpleLocationUtil {

    private static final String TAG = SimpleLocationUtil.class.getSimpleName();
    private static final int MIN_MINUTES = 2;
    private static final Criteria DEFAULT_LOCATION_PROVIDER_CRITERIA = new Criteria();

    static {
        DEFAULT_LOCATION_PROVIDER_CRITERIA.setAccuracy(Criteria.ACCURACY_FINE);
        DEFAULT_LOCATION_PROVIDER_CRITERIA.setPowerRequirement(Criteria.POWER_LOW);
    }

    private Context context;
    private LocationListener listener;
    private LocationManager locationManager;

    /**
     * Constructor
     *
     * @param context
     * @param listener
     */
    public SimpleLocationUtil(Context context, LocationListener listener) {
        Log.v(TAG, "LocationProvider() Constructor");
        this.context = context;
        this.listener = listener;
        initialize();
    }

    /**
     * Initialize Location Manager - Get best location provider GPS or Cellular network. Use best
     * provider to find the current location.
     */
    private void initialize() {
        Log.v(TAG, "initialize()");
        // Acquire a reference to the system Location Manager
        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    /**
     * Gets the last known location from the location manager.
     *
     * @return the last known location.
     */
    public Location getLastKnownLocation() {
        Log.v(TAG, "getLastKnownLocation()");
        Location userLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (userLocation == null) {
            userLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        return userLocation;
    }

    /**
     * Pauses subscribing to the location manager.
     */
    public void pause() {
        Log.v(TAG, "pause()");
        if (listener != null) {
            locationManager.removeUpdates(listener);
        }
    }

    /**
     * Resumes subscribing to the location manager.
     */
    public void resume() {
        Log.v(TAG, "resume()");
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_MINUTES, 0, listener);
    }

}
