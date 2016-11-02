package cse.hcmut.edu.vn.tripmaster.ui.activity.Maps;

import java.util.List;

/**
 * Created by HOANG DUNG on 10/30/2016.
 */

public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}
