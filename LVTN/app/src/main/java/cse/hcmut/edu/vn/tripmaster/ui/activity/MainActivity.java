package cse.hcmut.edu.vn.tripmaster.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import cse.hcmut.edu.vn.tripmaster.R;

import static android.graphics.Color.parseColor;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // TODO: Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // TODO: Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        setIconToTab(tabLayout);

    }

    private void setIconToTab(TabLayout tabLayout){
        int[] icons = {
                R.drawable.ic_map_black_24dp, R.drawable.ic_video_library_black_24dp,
                R.drawable.ic_person_black_24dp, R.drawable.ic_notifications_black_24dp
        };
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            if(i != 0)
                tabLayout.getTabAt(i).setIcon(icons[i]).getIcon().setColorFilter(parseColor("#999999"), PorterDuff.Mode.SRC_IN);
            else
                tabLayout.getTabAt(i).setIcon(icons[i]).getIcon().setColorFilter(parseColor("#008080"), PorterDuff.Mode.SRC_IN);
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(parseColor("#008080"), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(parseColor("#999999"), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        private ListView lv;
        private ListView lv1;
        private ImageView imageView;
        private RoundImage roundImage;
        private FloatingActionButton fab, fabTrai, fabTren;
        private Animation Move_Trai, Move_Tren, Back_Trai, Back_Tren;
        private  boolean move_back = false;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // TODO: FRAGMENT 1 - TRIPS
            if(getArguments().getInt(ARG_SECTION_NUMBER) == 1){
                View rootView = inflater.inflate(R.layout.fragment_trips, container, false);

                lv = (ListView) rootView.findViewById(R.id.listViewTrip);

                ArrayList<Trip_Dung> listTrip = new ArrayList<Trip_Dung>();
                listTrip.add(new Trip_Dung("Nguyễn Hoàng Dũng", "10/23/2106", "10/23/2016", "Đồng Nai", "Hồ Chí Minh", 100, 10));
                listTrip.add(new Trip_Dung("VTD", "10/23/2106", "10/23/2016", "HCM", "HCM", 10, 10));
                listTrip.add(new Trip_Dung("NAD", "10/23/2106", "10/23/2016", "HCM", "HCM", 10, 10));
                listTrip.add(new Trip_Dung("NAD", "10/23/2106", "10/23/2016", "HCM", "HCM", 10, 10));
                listTrip.add(new Trip_Dung("NAD", "10/23/2106", "10/23/2016", "HCM", "HCM", 10, 10));

                ListAdapter_Trip adapter = new ListAdapter_Trip(
                        this.getActivity(),
                        R.layout.activity_row_trip,
                        listTrip
                );
                lv.setAdapter(adapter);

                FloatingActionButton fabAddTrip = (FloatingActionButton) rootView.findViewById(R.id.fab_Trip);
//                fabAddTrip.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent goTo_Map = new Intent(getActivity(), MapsActivity.class);
//                        startActivity(goTo_Map);
//                    }
//                });

                return rootView;
            }

            // TODO: FRAGMENT 2 - VIDEOS
            else if(getArguments().getInt(ARG_SECTION_NUMBER) == 2){
                View rootView = inflater.inflate(R.layout.fragment_videos, container, false);
                return rootView;
            }

            // TODO: FRANGMENT 3 - USER
            else if(getArguments().getInt(ARG_SECTION_NUMBER) == 3){
                View rootView = inflater.inflate(R.layout.fragment_user, container, false);

                //=============== Đoạn Code Tự Thêm =================//
                imageView = (ImageView) rootView.findViewById(R.id.imageViewAvartaUser);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avarta_user);
                roundImage = new RoundImage(bitmap);
                imageView.setImageDrawable(roundImage);
                //===================================================//

                lv1 = (ListView) rootView.findViewById(R.id.listViewAttributeUser);
                ArrayList<AttributeUser> listAttribute = new ArrayList<AttributeUser>();
                listAttribute.add(new AttributeUser("Information", R.drawable.ic_cake_black_24dp));
                listAttribute.add(new AttributeUser("My Trip_Dung", R.drawable.ic_map_black_24dp));
                listAttribute.add(new AttributeUser("My Video & Picture", R.drawable.ic_video_library_black_24dp));
                listAttribute.add(new AttributeUser("Friend", R.drawable.ic_group_black_24dp));

                ListAdapterAttributeUser adapter = new ListAdapterAttributeUser(
                        this.getActivity(),
                        R.layout.activity_row_attribute_user,
                        listAttribute
                );
                lv1.setAdapter(adapter);

                lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if(position == 0) {
                            Intent goTo_InformationUser = new Intent(getActivity(), InformationUser.class);
                            startActivity(goTo_InformationUser);
                        }
                        else if(position == 1) {
                            Intent goTo_MyTrip = new Intent(getActivity(), RowTrip.class);
                            startActivity(goTo_MyTrip);
                        }
                    }
                });

                // TODO: Add new Trip_Dung Or An New Video
                fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
                fabTrai = (FloatingActionButton) rootView.findViewById(R.id.fab_Trai);
                fabTren = (FloatingActionButton) rootView.findViewById(R.id.fab_Tren);

                Move_Trai = AnimationUtils.loadAnimation(getActivity(), R.anim.move_trai);
                Move_Tren = AnimationUtils.loadAnimation(getActivity(), R.anim.move_tren);
                Back_Trai = AnimationUtils.loadAnimation(getActivity(), R.anim.back_trai);
                Back_Tren = AnimationUtils.loadAnimation(getActivity(), R.anim.back_tren);

                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(move_back == false) Move();
                        else Back();
                        move_back = !move_back;
                    }
                });
                fabTren.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                fabTrai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                return rootView;
            }

            // TODO: FRAGMENT 4 - NOTIFY
            else {
                View rootView = inflater.inflate(R.layout.fragment_notify, container, false);
//                TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//                textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
                return rootView;
            }
        }

        private void Move(){
            FrameLayout.LayoutParams paramsTrai = (FrameLayout.LayoutParams) fabTrai.getLayoutParams();
            paramsTrai.rightMargin = (int)(fabTrai.getWidth() * 1.5);
            fabTrai.setLayoutParams(paramsTrai);
            fabTrai.startAnimation(Move_Trai);

            FrameLayout.LayoutParams paramsTren = (FrameLayout.LayoutParams) fabTren.getLayoutParams();
            paramsTren.bottomMargin = (int)(fabTren.getWidth() * 1.5);
            fabTren.setLayoutParams(paramsTren);
            fabTren.startAnimation(Move_Tren);
        }
        private void Back(){
            FrameLayout.LayoutParams paramsTrai = (FrameLayout.LayoutParams) fabTrai.getLayoutParams();
            paramsTrai.rightMargin -= (int)(fabTrai.getWidth() * 1.5);
            fabTrai.setLayoutParams(paramsTrai);
            fabTrai.startAnimation(Back_Trai);

            FrameLayout.LayoutParams paramsTren = (FrameLayout.LayoutParams) fabTren.getLayoutParams();
            paramsTren.bottomMargin -= (int)(fabTren.getWidth() * 1.5);
            fabTren.setLayoutParams(paramsTren);
            fabTren.startAnimation(Back_Tren);
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }

}
