package cse.hcmut.edu.vn.tripmaster.model;

import org.osmdroid.util.GeoPoint;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by AnTD on 10/23/2016.
 */

public class Trip {
    private String mTripId;
    private String mLocalTripId;
    private String mSrc;
    private String mDest;
    private long mStartTime;
    private long mFinishTime;
    private ArrayList<GeoPoint> mListPoint;

    public Trip() {
        mTripId = "";
        mLocalTripId = generateLocalTripId();
        mListPoint = new ArrayList<>();
        mSrc = "Sài Gòn";
        mDest = "Hà Nội";
    }

    public Trip(String tripId, String localTripId, String src, String dest) {
        mTripId = tripId;
        mLocalTripId = localTripId;
        mSrc = src;
        mDest = dest;
    }

    public void setTripId(String id) {
        mTripId = id;
    }
    public String getTripId() {
        return mTripId;
    }

    public String getLocalTripId() {
        return mLocalTripId;
    }

    public ArrayList<GeoPoint> getListPoint() {
        return mListPoint;
    }

    public void addPoint(GeoPoint point) {
        mListPoint.add(point);
    }

    public String getSrc() {
        return mSrc;
    }

    public void setSrc(String Src) {
        this.mSrc = Src;
    }

    public String getDest() {
        return mDest;
    }

    public void setDest(String Dest) {
        this.mDest = Dest;
    }

    public long getStartTime() {
        return mStartTime;
    }

    public void setStartTime(long StartTime) {
        this.mStartTime = StartTime;
    }

    public long getFinishTime() {
        return mFinishTime;
    }

    public void setFinishTime(long FinishTime) {
        this.mFinishTime = FinishTime;
    }


    private String generateLocalTripId() {
        return "TM-" + getCurrentTime();
    }

    private String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd_HHmmss");
        return sdf.format(cal.getTime());
    }

}
