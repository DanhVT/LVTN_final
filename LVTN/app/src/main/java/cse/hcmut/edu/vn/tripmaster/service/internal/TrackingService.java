package cse.hcmut.edu.vn.tripmaster.service.internal;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import org.osmdroid.LocationListenerProxy;
import org.osmdroid.util.GeoPoint;

import cse.hcmut.edu.vn.tripmaster.business.Constants;
import cse.hcmut.edu.vn.tripmaster.controller.OsmController;
import cse.hcmut.edu.vn.tripmaster.db.TripDetail;
import cse.hcmut.edu.vn.tripmaster.db.TripLocation;
import cse.hcmut.edu.vn.tripmaster.model.Trip;

/**
 * Created by AnTD on 10/23/2016.
 */

public class TrackingService extends Service {
    private final Context mContext;

    private static final String TAG = "TrackingService";
    private Trip mTrip;
    private LocationListener mListener;
    private LocationListenerProxy mListenerProxy;

    public TrackingService(Context mContext) {
        this.mContext = mContext;
    }

    private void initNewTrip() {
        mTrip = new Trip();
        TripDetail.addTrip(mTrip, Constants.TRIP_NEW);
        TripLocation.addLocation(mTrip.getLocalTripId(), new GeoPoint(OsmController.getCurrentLocation()),
                Constants.LOCATION_NEW);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initNewTrip();

        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        mListenerProxy = new LocationListenerProxy(locationManager);

        mListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                updateLocation(OsmController.getCurrentLocation());
                Log.i(TAG, location.getLatitude() + " - " + location.getLongitude());
            }

            @Override
            public void onProviderDisabled(String provider) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mListenerProxy.stopListening();
        stopSelf();
        Log.i(TAG, "Service is destroyed!");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mListenerProxy.startListening(mListener, Constants.MIN_TIME_UPDATE_LOCATION,
                Constants.MIN_DISTANCE_UPDATE_LOCATION);
        mListenerProxy.startListening(mListener, 0, 0);
        return START_REDELIVER_INTENT;
    }

    private void updateLocation(GeoPoint point) {
        mTrip.addPoint(point);
        TripLocation.addLocation(mTrip.getLocalTripId(), point, Constants.LOCATION_NEW);
        Log.i(TAG, "Location: " + point.getLatitude() + " - " + point.getLongitude());
    }

}
