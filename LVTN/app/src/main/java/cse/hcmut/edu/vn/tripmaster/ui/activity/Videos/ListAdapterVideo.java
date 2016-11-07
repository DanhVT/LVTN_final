package cse.hcmut.edu.vn.tripmaster.ui.activity.Videos;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cse.hcmut.edu.vn.tripmaster.R;

/**
 * Created by HOANG DUNG on 11/3/2016.
 */

public class ListAdapterVideo extends ArrayAdapter<VideoObject> {

    public ListAdapterVideo(Context context, int resource, List<VideoObject> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.activity_row_video, null);
        }

        final VideoObject video = getItem(position);
        if (video != null) {
            // Anh xa + Gan gia tri
            TextView txtvNameVideo = (TextView) view.findViewById(R.id.textViewNameVideo);
            TextView txtvNameUser = (TextView) view.findViewById(R.id.textViewNameUser);
            TextView txtvTimeSubmit = (TextView) view.findViewById(R.id.textViewTimeSubmit);
            TextView txtvCountView = (TextView) view.findViewById(R.id.textViewCountView);

            txtvNameVideo.setText(video.nameVideo);
            txtvNameVideo.setTextColor(Color.parseColor("#000000"));
            //txtvNameVideo.setTypeface(null, Typeface.BOLD);

            txtvNameUser.setText(video.nameUser);
            txtvNameVideo.setTypeface(null, Typeface.ITALIC);

            txtvTimeSubmit.setText(video.timeSubmit);
            txtvCountView.setText(video.countView+"");
        }
        return view;
    }
}
