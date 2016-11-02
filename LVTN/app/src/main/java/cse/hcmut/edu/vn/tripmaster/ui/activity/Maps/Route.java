package cse.hcmut.edu.vn.tripmaster.ui.activity.Maps;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by HOANG DUNG on 10/30/2016.
 */

public class Route {
    public Distance distance;
    public Duration duration;
    public String endAddress;
    public LatLng endLocation;
    public String startAddress;
    public LatLng startLocation;

    public List<LatLng> points;
}
