package cse.hcmut.edu.vn.tripmaster.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import cse.hcmut.edu.vn.tripmaster.R;

public class ListAdapter_Trip extends ArrayAdapter<Trip_Dung> {

    public ListAdapter_Trip(Context context, int resource, List<Trip_Dung> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.activity_row_trip, null);
        }

        final Trip_Dung trip = getItem(position);
        if (trip != null) {
            // Anh xa + Gan gia tri
            final TextView txtv_nameUser = (TextView) view.findViewById(R.id.textViewNameUser);
            txtv_nameUser.setText(trip.nameUser);
            txtv_nameUser.setTextColor(Color.parseColor("#000000"));

            TextView txtv_timeStart = (TextView) view.findViewById(R.id.textViewtimeStart);
            txtv_timeStart.setText("From: " + trip.timeStart);

            TextView txtv_timeFinish = (TextView) view.findViewById(R.id.textViewtimeFinish);
            txtv_timeFinish.setText("To: " + trip.timeFinish);

            TextView txtv_addrStart = (TextView) view.findViewById(R.id.textViewaddrStart);
            txtv_addrStart.setText(trip.addrStart);
            txtv_addrStart.setTextColor(Color.parseColor("#000000"));

            TextView txtv_addrFinish = (TextView) view.findViewById(R.id.textViewaddrFinish);
            txtv_addrFinish.setText(trip.addrFinish);
            txtv_addrFinish.setTextColor(Color.parseColor("#000000"));

            Button btnLike = (Button) view.findViewById(R.id.buttonLike);

//            final TextView txtv_like = (TextView) view.findViewById(R.id.textViewLike);
//            txtv_like.setText(trip.like+" Like");
//            txtv_like.setTextColor(Color.parseColor("#000000"));
//
//            TextView txtv_cmt = (TextView) view.findViewById(R.id.textViewCmt);
//            txtv_cmt.setText(trip.cmt+" Comment");
//            txtv_cmt.setTextColor(Color.parseColor("#000000"));
//
//            final ImageButton imageButtonLike = (ImageButton) view.findViewById(R.id.imageButtonLike);
//            imageButtonLike.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    trip.like = trip.like + 1;
//                    txtv_like.setText(trip.like+" Like");
//                }
//            });
        }
        return view;
    }

}
