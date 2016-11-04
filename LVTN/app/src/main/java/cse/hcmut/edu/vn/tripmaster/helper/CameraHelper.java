package cse.hcmut.edu.vn.tripmaster.helper;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by danh-vo on 03/11/2016.
 */

public class CameraHelper extends Activity{
    final int TAKE_CAMERA_PIC_CODE = 100;
    final int TAKE_CAMERA_VIDEO_CODE = 101;
    final int RECORD_AUDIO_CODE = 102;
    private String currentPath = null;

    public static String getBrand() {
        String brand;
        try {
            brand = android.os.Build.BRAND;
        } catch (Exception exception) {
            //Log.e(DEBUG_TAG, exception);
            exception.printStackTrace();
            brand = "Unknown";
        }

        return brand;
    }
    public static String getModelName() {
        String modelName;
        try {
            modelName = android.os.Build.MODEL;
        } catch (Exception exception) {
            //Log.e(DEBUG_TAG, exception);
            exception.printStackTrace();
            modelName = "Unknown";
        }

        return modelName;
    }
    public void showCamera() {
        try {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File f = createImageFile();
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
            File f = createVideoFile();
            currentPath = f.getAbsolutePath();
            takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
            startActivityForResult(takeVideoIntent, TAKE_CAMERA_VIDEO_CODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File createImageFile() throws IOException {
        boolean externalStorageAvailable = false;
        boolean externalStorageWriteable = false;
        File root = null;
        File tempFile = null;
        try {
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                externalStorageAvailable = externalStorageWriteable = true;
            } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
                externalStorageAvailable = true;
                externalStorageWriteable = false;
            } else {
                externalStorageAvailable = externalStorageWriteable = false;
            }

            if (externalStorageAvailable && externalStorageWriteable) {
                root = new File(Environment.getExternalStorageDirectory(), "mani");
                if (!root.exists()) {
                    root.mkdirs();
                }
                // Create an image file name
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());
                String imageFileName = "Image_" + timeStamp + "_";
                tempFile = File.createTempFile(imageFileName,".jpg",root);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempFile;
    }

    public File createVideoFile() throws IOException {
        boolean externalStorageAvailable = false;
        boolean externalStorageWriteable = false;
        File root = null;
        File tempFile = null;
        try {
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                externalStorageAvailable = externalStorageWriteable = true;
            } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
                externalStorageAvailable = true;
                externalStorageWriteable = false;
            } else {
                externalStorageAvailable = externalStorageWriteable = false;
            }

            if (externalStorageAvailable && externalStorageWriteable) {
                root = new File(Environment.getExternalStorageDirectory(), "mani");
                if (!root.exists()) {
                    root.mkdirs();
                }
                // Create an image file name
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());
                String imageFileName = "Video_" + timeStamp + "_";
                tempFile = File.createTempFile(imageFileName,".mp4",root);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempFile;
    }

    public void handleCameraPhoto() {
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

    public void handleCameraVideo(Intent intent) {
        System.out.println("####### handleCameraVideo ####### "+currentPath);
        File file = new File (currentPath);
//        new MyTask("video").execute(file); ===================>>>> upload to server
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if( resultCode == Activity.RESULT_OK) {
            if(requestCode == TAKE_CAMERA_PIC_CODE) {
                System.out.println("####### onActivityResult ####### "+data);
                handleCameraPhoto();
            } else if(requestCode == TAKE_CAMERA_VIDEO_CODE) {
                System.out.println("####### onActivityResult ####### "+data);
                handleCameraVideo(data);
            }
        }
    }
}
