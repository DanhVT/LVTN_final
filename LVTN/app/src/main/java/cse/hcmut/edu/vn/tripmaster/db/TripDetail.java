package cse.hcmut.edu.vn.tripmaster.db;

import com.orm.SugarRecord;
import com.orm.dsl.Table;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import cse.hcmut.edu.vn.tripmaster.model.Trip;

/**
 * Created by AnTD on 10/23/2016.
 */
@Table
public class TripDetail extends SugarRecord {
    long id;
    String mTripId;
    String mLocalTripId;
    String mSrc;
    String mDest;
    long mStartTime;
    long mFinishTime;
    int mStatus;
    String mTimeStamp;

    public TripDetail() {
    }

    public TripDetail(Trip trip, int status) {
        mTripId = trip.getTripId();
        mLocalTripId = trip.getLocalTripId();
        mSrc = trip.getSrc();
        mDest = trip.getDest();
        mStartTime = trip.getStartTime();
        mFinishTime = trip.getFinishTime();
        mStatus = status;
        mTimeStamp = getCurrentTime();
    }

    public static void addTrip(Trip trip, int status) {
        TripDetail tripDetail = new TripDetail(trip, status);
        tripDetail.save();
    }

    public static String getIdFromLocalId(String localId) {
        ArrayList<TripDetail> tmpTrip = (ArrayList<TripDetail>) Select.from(TripDetail.class)
                .where(Condition.prop("M_LOCAL_TRIP_ID").eq(localId))
                .list();
        return tmpTrip.get(0).getTripId();
    }

    public String getTripId() {
        return mTripId;
    }

    public Trip getTrip() {
        return new Trip(mTripId, mLocalTripId, mSrc, mDest);
    }

    public Trip getTripFromLocalId(String localId) {
        ArrayList<TripDetail> tmpTrip = (ArrayList<TripDetail>) Select.from(TripDetail.class)
                .where(Condition.prop("M_LOCAL_TRIP_ID").eq(localId))
                .list();
        return tmpTrip.get(0).getTrip();
    }

    public String getLocalTripId() {
        return mLocalTripId;
    }

    private String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd_HHmmss");
        return sdf.format(cal.getTime());
    }

    public void updateTripStatus(int status) {
        mStatus = status;
        save();
    }

}
