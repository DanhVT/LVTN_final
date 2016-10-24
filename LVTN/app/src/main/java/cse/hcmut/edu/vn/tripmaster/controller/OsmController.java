package cse.hcmut.edu.vn.tripmaster.controller;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

/**
 * Created by AnTD on 10/23/2016.
 */

public class OsmController {
    private static MapView map;
    private static MyLocationNewOverlay locationNewOverlay;

    public static GeoPoint getCurrentLocation() {
        return locationNewOverlay.getMyLocation();
    }
}
