package cse.hcmut.edu.vn.tripmaster.ui.activity;

/**
 * Created by HOANG DUNG on 11/3/2016.
 */

public class VideoObject {
    public String nameVideo;
    public String nameUser;
    public String timeSubmit;
    public int countView;

    public VideoObject(){

    }

    public VideoObject(String nameVideo, String nameUser, String timeSubmit, int countView) {
        this.nameVideo = nameVideo;
        this.nameUser = nameUser;
        this.timeSubmit = timeSubmit;
        this.countView = countView;
    }
}
