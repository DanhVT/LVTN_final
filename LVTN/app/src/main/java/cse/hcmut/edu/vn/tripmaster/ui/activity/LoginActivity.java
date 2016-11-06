package cse.hcmut.edu.vn.tripmaster.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cse.hcmut.edu.vn.tripmaster.R;
import cse.hcmut.edu.vn.tripmaster.TMApp;
import cse.hcmut.edu.vn.tripmaster.TMPref;
import cse.hcmut.edu.vn.tripmaster.helper.ApiCall;
import cse.hcmut.edu.vn.tripmaster.helper.BasicHelper;
import cse.hcmut.edu.vn.tripmaster.service.http.HttpConstant;
import cse.hcmut.edu.vn.tripmaster.service.http.RequestBuilder;
import okhttp3.OkHttpClient;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LOGIN";
    private static final int REQUEST_SIGNUP = 0;
    private OkHttpClient client;
    private Button btnLogin;
    private EditText txtEmail, txtPassword;
    private TextView _signupLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        client = new OkHttpClient();
        txtEmail = (EditText) findViewById(R.id.input_email);
        txtPassword = (EditText) findViewById(R.id.input_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        _signupLink = (TextView) findViewById(R.id.link_signup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                this.finish();
            }
        }
    }


    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void login(){
        Log.d(TAG, "Login");
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        if (!validate()) {
            onLoginFailed();
            return;
        }
        btnLogin.setEnabled(false);

        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        attemptLogin(HttpConstant.LOGIN_LINK, email, password);

    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        btnLogin.setEnabled(true);
    }

    public void onLoginSuccess() {
        btnLogin.setEnabled(true);
        finish();
    }

    private void attemptLogin(String url, String _email, String _password ) {
        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                try {
                    String response = ApiCall.POST(
                            client,
                            params[0],
                            RequestBuilder.LoginBody(params[1], params[2]));
                    JSONObject json = new JSONObject(response);
                    if(json != null){
                        String token = json.getString("_id");
                        String grav = json.getString("grav");
                        TMApp.getPref().setToken(token);
                        TMApp.getPref().setGravity(grav);
//                        progressDialog.dismiss();
                        Intent gotoActivity = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(gotoActivity);
                    }
                    Log.d("Response", response);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(url, _email, _password);
    }

    public boolean validate() {
        boolean valid = true;

        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txtEmail.setError("enter a valid email address");
            valid = false;
        } else {
            txtEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            txtPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            txtPassword.setError(null);
        }

        return valid;
    }
}

