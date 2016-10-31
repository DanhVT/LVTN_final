package cse.hcmut.edu.vn.tripmaster.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cse.hcmut.edu.vn.tripmaster.R;

/**
 * Created by AnTD on 11/1/2016.
 */

public class StartupListAdapter extends BaseAdapter {
    public static final int INDEX_AUDIO_PLAYBACK = 0;
    public static final int INDEX_VIDEO_PLAYBACK = 1;

    private List<String> listPages;
    private LayoutInflater inflater;

    public StartupListAdapter(Context context){
        listPages = new ArrayList<>();
        listPages.add(INDEX_AUDIO_PLAYBACK, "Audio Playback");
        listPages.add(INDEX_VIDEO_PLAYBACK, "Video Playback");
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return listPages.size();
    }

    @Override
    public Object getItem(int position) {
        return listPages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.simple_text_item, null);

            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.simple_text_text_view);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(listPages.get(position));
        return convertView;

    }

    private static class ViewHolder {
        TextView text;
    }
}
