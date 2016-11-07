package cse.hcmut.edu.vn.tripmaster.service.http;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.IOException;

import cse.hcmut.edu.vn.tripmaster.R;
import cse.hcmut.edu.vn.tripmaster.helper.ApiCall;
import cse.hcmut.edu.vn.tripmaster.helper.BasicHelper;
import okhttp3.OkHttpClient;

/**
 * Created by danh-vo on 04/11/2016.
 */

public class UploadAsync extends AsyncTask<File,Void,String> {
    ProgressDialog mProgress;
    OkHttpClient client;
    String type, MIME;
    Context context;

    String url = HttpConstant.UPLOAD_LINK;
    public UploadAsync(OkHttpClient client, String type, String MIME,Context context) {
        this.client = client;
        this.type = type;
        this.context = context;
        this.MIME = MIME;
    }
    @Override
    protected void onPreExecute()  {
        super.onPreExecute();
        mProgress = ProgressDialog.show(context, "", "Uploading...");
    }
    @Override
    protected String doInBackground(File... params) {
        try {
            System.out.println("@######## Http starting #########  "+params[0].getAbsolutePath() );
            return uploadFile(type,params[0]);
        } catch ( Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {


        mProgress.dismiss();
        try {

            JSONObject json= new JSONObject(result);

            System.out.println("@######## Http onPostExecute##########  " + json.getString("state"));
            if(json.getString("state").equals("success")){
                showAlert(true, "Upload success");
            }
            else {
                showAlert(false, "Upload failed ");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        super.onPostExecute(result);

    }
    public String uploadFile(String type, File file)  {
        String responseString = null;
        try {
            String title;
            title= BasicHelper.getCurrentTime();
            if(type=="video"){
                title = "VID_"+ title;
            }
            else if(type=="image"){
                title = "IMG_"+ title;
            }
            else{
                title = "ORTHER_"+ title;
            }
            responseString = ApiCall.POST(client, url, RequestBuilder.uploadRequestBody(title, MIME, type, file)); //======

        } catch (IOException e) {
            responseString = e.toString();
        }
        return  responseString;
    }

    private void showAlert(Boolean isSuccess, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        int img_src = R.drawable.ic_highlight_off_red_600_24dp;
        if(isSuccess){
            img_src = R.drawable.ic_check_green_500_24dp;
        }
        builder.setMessage(message)
                .setIcon(img_src)
                .setTitle("Response from Servers")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // do nothing
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
