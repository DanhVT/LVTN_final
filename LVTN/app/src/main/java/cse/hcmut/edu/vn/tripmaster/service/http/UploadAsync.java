package cse.hcmut.edu.vn.tripmaster.service.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import cse.hcmut.edu.vn.tripmaster.helper.ApiCall;

/**
 * Created by danh-vo on 04/11/2016.
 */

public class UploadAsync extends AsyncTask<File,Void,Void> {
    ProgressDialog mProgress;
    String type;
    Context context;
    String url = HttpConstant.UPLOAD_LINK;
    public UploadAsync(String type, Context context) {
        this.type = type;
        this.context = context;
    }
    @Override
    protected void onPreExecute()  {
        super.onPreExecute();
        mProgress = ProgressDialog.show(context, "", "Uploading...");
    }
    @Override
    protected Void doInBackground(File... params) {
        try {
            System.out.println("@######## Http starting #########  "+params[0].getAbsolutePath() );
            uploadFile(type,params[0]);
        } catch ( Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        System.out.println("@######## Http onPostExecute##########  " );
        mProgress.dismiss();

    }
    public void uploadFile(String type, File file)  {
        try {
            String response;
            response = ApiCall.POST(url, RequestBuilder.uploadRequestBody("title", "png", "someUploadToken", file));
            Log.d("Response", response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
