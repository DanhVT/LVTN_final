package cse.hcmut.edu.vn.tripmaster.ui.widget;

import cse.hcmut.edu.vn.tripmaster.ui.activity.VideoPlayer;

/**
 * Created by kunal.bhatia on 05-05-2016.
 */
public interface HpLib_RendererBuilder {
    void buildRenderers(VideoPlayer player);
    void cancel();
}
