package cse.hcmut.edu.vn.tripmaster.helper;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by danh-vo on 03/11/2016.
 */

public class BasicHelper {
    private static TelephonyManager telephonyManager;

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
    public static File createImageFile() throws IOException {
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

    public static File createVideoFile() throws IOException {
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
}
