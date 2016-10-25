package cse.hcmut.edu.vn.tripmaster.controller;

import android.content.Intent;

import cse.hcmut.edu.vn.tripmaster.TMApp;
import cse.hcmut.edu.vn.tripmaster.TMPref;
import cse.hcmut.edu.vn.tripmaster.service.internal.TrackingService;

/**
 * Created by AnTD on 10/25/2016.
 */

public class TrackingController {
    public static void toggleTracking() {
        if (TMApp.getPref().getTracking()) {
            stopTracking();
        } else {
            startTracking();
        }
    }


    public static void startTracking(){
        TMApp.getPref().setTracking(true);
        OsmController.enableFollowMode();
        Intent intent = new Intent( TMApp.getInstance() , TrackingService.class);
        TMApp.getInstance().startService(intent);
    }

    public static void stopTracking() {
        Intent intent = new Intent(TMApp.getInstance(), TrackingService.class);
        TMApp.getInstance().stopService(intent);
        TMApp.getPref().setTracking(false);
        OsmController.disableFollowMode();

    }

}
