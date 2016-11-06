package cse.hcmut.edu.vn.tripmaster;

/**
 * Created by AnTD on 10/25/2016.
 */

public interface IPreference {
    void setTracking(boolean v);
    boolean getTracking();

    void setToken(String v);
    String getToken();

    void setGravity(String v);
    String getGravity();
}
