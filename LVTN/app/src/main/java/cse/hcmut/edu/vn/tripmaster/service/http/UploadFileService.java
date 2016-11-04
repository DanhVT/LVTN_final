package cse.hcmut.edu.vn.tripmaster.service.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.android.internal.http.multipart.MultipartEntity;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;

import java.io.File;

/**
 * Created by danh-vo on 24/10/2016.
 */

public class UploadFileService extends AsyncTask<File,Void,Void> {
    Context context;
    String type;
    ProgressDialog mProgress;

    public UploadFileService(Context context, String type) {
        this.type = type;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
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

    public void uploadFile(String type, File file) {
        HttpClient httpClient = new DefaultHttpClient();
//        HttpPost httppost = new HttpPost(uploadurl);
//        MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);

    }
}
