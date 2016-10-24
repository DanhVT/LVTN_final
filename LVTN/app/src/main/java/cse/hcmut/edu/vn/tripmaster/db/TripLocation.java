package cse.hcmut.edu.vn.tripmaster.db;

import com.orm.SugarRecord;
import com.orm.dsl.Table;
import com.orm.query.Condition;
import com.orm.query.Select;

import org.osmdroid.util.GeoPoint;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by AnTD on 10/23/2016.
 */
@Table
public class TripLocation extends SugarRecord {
    long id;
    String mLocalTripId;
    String mPoint;
    String mTimestamp;
    int mStatus;

    public TripLocation() {
    }

    public TripLocation(String tripLocalId, GeoPoint point, int status) {
        mLocalTripId = tripLocalId;
        mPoint = point.toDoubleString();
        mStatus = status;
        mTimestamp = getCurrentTime();
    }

    public static void addLocation(String tripLocalId, GeoPoint point, int status) {
        TripLocation saveLocation = new TripLocation(tripLocalId, point, status);
        saveLocation.save();
    }

    public GeoPoint getPoint() {
        return GeoPoint.fromDoubleString(mPoint, ',');
    }

    public static ArrayList<GeoPoint> getListPointFromLocalId(String localId){
        ArrayList<TripLocation> listLocation = (ArrayList<TripLocation>) Select.from(TripLocation.class)
                .where(Condition.prop("M_LOCAL_TRIP_ID").eq(localId))
                .orderBy("ID")
                .list();
        ArrayList<GeoPoint> listPoint = new ArrayList<>();
        if (listLocation.size() > 0) {
            for (TripLocation tripLocation : listLocation) {
                listPoint.add(tripLocation.getPoint());
                //Log.i("TripLocation", "Geopoint: " + tripLocation.getPoint().toString());
            }
        }
        return listPoint;
    }
    private String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd_HHmmss");
        return sdf.format(cal.getTime());
    }
}
