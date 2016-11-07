package cse.hcmut.edu.vn.tripmaster.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

import cse.hcmut.edu.vn.tripmaster.R;
import cse.hcmut.edu.vn.tripmaster.helper.BasicHelper;
import cse.hcmut.edu.vn.tripmaster.service.http.UploadAsync;
import cse.hcmut.edu.vn.tripmaster.ui.activity.Maps.MapsActivity;
import cse.hcmut.edu.vn.tripmaster.ui.activity.Trips.ListAdapter_Trip;
import cse.hcmut.edu.vn.tripmaster.ui.activity.Trips.RowTrip;
import cse.hcmut.edu.vn.tripmaster.ui.activity.Trips.Trip_Dung;
import cse.hcmut.edu.vn.tripmaster.ui.activity.UserAttribute.AttributeUser;
import cse.hcmut.edu.vn.tripmaster.ui.activity.UserAttribute.ListAdapterAttributeUser;
import cse.hcmut.edu.vn.tripmaster.ui.activity.Videos.ListAdapterVideo;
import cse.hcmut.edu.vn.tripmaster.ui.activity.Videos.VideoObject;
import okhttp3.OkHttpClient;

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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

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
        private  ListView lvVideo;
        private ImageView imageView;
        private RoundImage roundImage;
        private FloatingActionButton fab, fabDuoi, fabTren;
        private Animation Move_Duoi, Move_Tren, Back_Duoi, Back_Tren;
        private  boolean move_back = false;
        private Button myButton;

        private String currentPath = null;
        final int TAKE_CAMERA_PIC_CODE = 100;
        final int TAKE_CAMERA_VIDEO_CODE = 101;
        public OkHttpClient client;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            client = new OkHttpClient();
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
                fabAddTrip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent goTo_Map = new Intent(getActivity(), MapsActivity.class);
                        startActivity(goTo_Map);
                    }
                });

                return rootView;
            }

            // TODO: FRAGMENT 2 - VIDEOS
            else if(getArguments().getInt(ARG_SECTION_NUMBER) == 2){
                View rootView = inflater.inflate(R.layout.fragment_videos, container, false);

                lvVideo = (ListView) rootView.findViewById(R.id.listViewVideo);

                ArrayList<VideoObject> listVideo = new ArrayList<VideoObject>();
                listVideo.add(new VideoObject("Vũng Tàu - bãi Biển mơ mộng", "Hoàng Dũng", "10/23/2016", 100));
                listVideo.add(new VideoObject("Đà lạt", "Thanh Danh", "10/23/2016", 10));
                listVideo.add(new VideoObject("Nha Trang", "Anh Đức", "10/23/2016", 54));
                listVideo.add(new VideoObject("Vịnh Hạ Long", "Bách Khoa", "10/23/2016", 10));
                listVideo.add(new VideoObject("Đà Nẵng - Quảng Nam", "LVTN", "10/23/2016", 10));
//
                ListAdapterVideo adapter = new ListAdapterVideo(
                        this.getActivity(),
                        R.layout.activity_row_video,
                        listVideo
                );
                lvVideo.setAdapter(adapter);

                FloatingActionButton fabAddTrip = (FloatingActionButton) rootView.findViewById(R.id.fabVideo);
                fabAddTrip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showCameraVideo();
                    }
                });

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
                fabDuoi = (FloatingActionButton) rootView.findViewById(R.id.fab_Duoi);
                fabTren = (FloatingActionButton) rootView.findViewById(R.id.fab_Tren);

                Move_Duoi = AnimationUtils.loadAnimation(getActivity(), R.anim.move_duoi);
                Move_Tren = AnimationUtils.loadAnimation(getActivity(), R.anim.move_tren);
                Back_Duoi = AnimationUtils.loadAnimation(getActivity(), R.anim.back_duoi);
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
                        showCameraVideo();
                    }
                });
                fabDuoi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent goTo_Map = new Intent(getActivity(), MapsActivity.class);
                        startActivity(goTo_Map);
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
            FrameLayout.LayoutParams paramsTrai = (FrameLayout.LayoutParams) fabDuoi.getLayoutParams();
            paramsTrai.topMargin = (int)(fabDuoi.getWidth() * 1.5);
            fabDuoi.setLayoutParams(paramsTrai);
            fabDuoi.startAnimation(Move_Duoi);

            FrameLayout.LayoutParams paramsTren = (FrameLayout.LayoutParams) fabTren.getLayoutParams();
            paramsTren.bottomMargin = (int)(fabTren.getWidth() * 1.5);
            fabTren.setLayoutParams(paramsTren);
            fabTren.startAnimation(Move_Tren);
        }
        private void Back(){
            FrameLayout.LayoutParams paramsTrai = (FrameLayout.LayoutParams) fabDuoi.getLayoutParams();
            paramsTrai.topMargin -= (int)(fabDuoi.getWidth() * 1.5);
            fabDuoi.setLayoutParams(paramsTrai);
            fabDuoi.startAnimation(Back_Duoi);

            FrameLayout.LayoutParams paramsTren = (FrameLayout.LayoutParams) fabTren.getLayoutParams();
            paramsTren.bottomMargin -= (int)(fabTren.getWidth() * 1.5);
            fabTren.setLayoutParams(paramsTren);
            fabTren.startAnimation(Back_Tren);
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            // TODO Auto-generated method stub
            super.onActivityResult(requestCode, resultCode, data);
            if( resultCode == Activity.RESULT_OK) {
                if(requestCode == TAKE_CAMERA_PIC_CODE) {
                    System.out.println("####### onActivityResult ####### "+data);
                    handleCameraPhoto(currentPath);
                } else if(requestCode == TAKE_CAMERA_VIDEO_CODE) {
                    System.out.println("####### onActivityResult ####### "+data);
                    handleCameraVideo(currentPath);
                }
            }
        }
        public void showCamera() {
            try {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File f = BasicHelper.createImageFile();
                currentPath = f.getAbsolutePath();
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                startActivityForResult(takePictureIntent, TAKE_CAMERA_PIC_CODE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void showCameraVideo() {
            try {
                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                File f = BasicHelper.createVideoFile();
                currentPath = f.getAbsolutePath();
                takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                startActivityForResult(takeVideoIntent, TAKE_CAMERA_VIDEO_CODE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void handleCameraPhoto(String currentPath) {
            //Load picture from sdcard
            try {
                File file = new File (currentPath);
                System.out.println("####### handleSmallCameraPhoto ####### "+file.getAbsolutePath());
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(),options);
                System.out.println("####### handleSmallCameraPhoto ####### "+bitmap);
    //            imageTaken.setImageBitmap(bitmap); ===================>>>> se gan len map
    //            new MyTask("image").execute(file); ===================>>>> upload to server
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void handleCameraVideo(String currentPath) {
            System.out.println("####### handleCameraVideo ####### "+currentPath);
            File file = new File (currentPath);
            new UploadAsync(client, "video", getActivity()).execute(file);
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
