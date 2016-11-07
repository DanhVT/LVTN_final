package cse.hcmut.edu.vn.tripmaster.ui.activity.Trips;

/**
 * Created by HOANG DUNG on 10/23/2016.
 */

public class Trip_Dung {
    public String nameUser;
    public String timeStart;
    public String timeFinish;
    public String addrStart;
    public String addrFinish;
    public int like;
    public int cmt;

    public Trip_Dung(String nameUser, String timeStart, String timeFinish, String addrStart, String addrFinish, int like, int cmt) {
        this.nameUser = nameUser;
        this.timeStart = timeStart;
        this.timeFinish = timeFinish;
        this.addrStart = addrStart;
        this.addrFinish = addrFinish;
        this.like = like;
        this.cmt = cmt;
    }

    public Trip_Dung() {

    }
}
