package cse.hcmut.edu.vn.tripmaster.service.internal;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

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
    private static final String TAG = "TrackingService";
    private Trip mTrip;

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

    }



}
