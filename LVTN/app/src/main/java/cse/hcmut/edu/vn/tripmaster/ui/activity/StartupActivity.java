package cse.hcmut.edu.vn.tripmaster.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import cse.hcmut.edu.vn.tripmaster.R;
import cse.hcmut.edu.vn.tripmaster.helper.StartupListAdapter;

/**
 * Created by AnTD on 11/1/2016.
 */

public class StartupActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup_activity);

        ListView startupList = (ListView) findViewById(R.id.startup_activity_list);
        startupList.setAdapter(new StartupListAdapter(this));
        startupList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
