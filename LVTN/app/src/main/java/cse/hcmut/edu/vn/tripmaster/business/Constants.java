package cse.hcmut.edu.vn.tripmaster.business;

/**
 * Created by AnTD on 10/23/2016.
 */

public class Constants {
    public static final String PREFERENCES_NAME = "TMPref";
    //InternalConstants for location listener
    public static final long MIN_TIME_UPDATE_LOCATION = 1000 * 1 * 5; //2s
    public static final float MIN_DISTANCE_UPDATE_LOCATION = 5; //10m

    // InternalConstants for Map view
    public static final int ZOOM_LVL = 16;

    //Status trip
    public static final int TRIP_NEW = 1;
    public static final int TRIP_PENDING = 2;
    public static final int TRIP_UPLOADED = 3;

    // Status location from tracking
    public static final int LOCATION_NEW = 1;
    public static final int LOCATION_RETRY = 2;
    public static final int LOCATION_UPLOADED = 3;
}
