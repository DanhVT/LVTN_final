package cse.hcmut.edu.vn.tripmaster.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cse.hcmut.edu.vn.tripmaster.R;


public class ListAdapterAttributeUser extends ArrayAdapter<AttributeUser> {

    public ListAdapterAttributeUser(Context context, int resource, List<AttributeUser> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.activity_row_attribute_user, null);
        }

        final AttributeUser attributeUser = getItem(position);
        if (attributeUser != null) {
            // Anh xa + Gan gia tri
            TextView txtv = (TextView) view.findViewById(R.id.textViewAttributeuser);
            txtv.setText(attributeUser.attributeUser);
            txtv.setTextColor(Color.parseColor("#000000"));

            ImageView imageView = (ImageView) view.findViewById(R.id.imageViewAttributeUser);
            imageView.setImageResource(attributeUser.iconAttributeuser);
        }
        return view;
    }
}
