package cse.hcmut.edu.vn.tripmaster.controller;

import org.osmdroid.api.IMapController;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import cse.hcmut.edu.vn.tripmaster.business.Constants;

/**
 * Created by AnTD on 10/23/2016.
 */

public class OsmController {
    private static MapView map;
    private static MyLocationNewOverlay locationNewOverlay;
    private static IMapController mapController;

    public static GeoPoint getCurrentLocation() {
        return locationNewOverlay.getMyLocation();
    }

    public static void enableFollowMode() {
        locationNewOverlay.enableFollowLocation();
    }

    public static void disableFollowMode() {
        locationNewOverlay.disableFollowLocation();
    }

    public static void animateToCurrentLocation() {
        mapController.setZoom(Constants.ZOOM_LVL);
        if (locationNewOverlay.getMyLocation() != null) {
            mapController.animateTo(locationNewOverlay.getMyLocation());
        } else {
            return;
        }
    }

}
