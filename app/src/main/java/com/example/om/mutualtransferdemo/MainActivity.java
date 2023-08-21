package com.example.om.mutualtransferdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONObject;

import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private static final int RC_SIGN_IN = 1;
    Button btngoogleMainActivity;
    GoogleApiClient mGoogleApiClient;

    Button btnfbMainActivity;
    CallbackManager callbackManager;
    String email, name, picUrl;
    public static final String MyPERF = "myper";
    public SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);
        btnfbMainActivity = (Button) findViewById(R.id.btnfbMainActivity);
        btngoogleMainActivity = (Button) findViewById(R.id.btngoogleMainActivity);
        callbackManager = CallbackManager.Factory.create();

        if(sharedPreferences.getBoolean("islogin",false))
        {
            Intent intent=new Intent(MainActivity.this,UserOptionActivity.class);
            startActivity(intent);
            finish();
        }
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        grabResultFromFacebook(loginResult);
//                        startActivity(new Intent(MainActivity.this, UserOptionActivity.class));
//                        finish();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity.this, "Login Canceled", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        btnfbMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("public_profile", "email"));
            }
        });

        btngoogleMainActivity = (Button) findViewById(R.id.btngoogleMainActivity);
        createGoogleSignInUser();
        btngoogleMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);

                                startActivityForResult(signInIntent, RC_SIGN_IN);
                            }
                        });
            }
        });
    }

    private void createGoogleSignInUser() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(MainActivity.this)
                .enableAutoManage(MainActivity.this, (GoogleApiClient.OnConnectionFailedListener) MainActivity.this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == RC_SIGN_IN) {

                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                if (result.isSuccess()) {
                    final GoogleSignInAccount googleSignInAccount = result.getSignInAccount();
                    assert googleSignInAccount != null;
                    final Uri url = googleSignInAccount.getPhotoUrl();
                    final String email = googleSignInAccount.getEmail();
                    final String name = googleSignInAccount.getDisplayName();
                    if (url != null) {
                        Log.d("userData", "handleResult: " + googleSignInAccount.getEmail() + " " + url.toString() + " " + name);
                        sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("picture", url.toString());
                        editor.putString("name", name);
                        editor.putString("email", email);
                        editor.apply();
                        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Please try again later.", Toast.LENGTH_SHORT).show();
                    }
                    Call<okhttp3.ResponseBody> callCheck = AppController.getInstance().getApiInterface().postCheckRegistration(email);
                    callCheck.enqueue(new Callback<okhttp3.ResponseBody>() {
                        @Override
                        public void onResponse(Call<okhttp3.ResponseBody> call, Response<okhttp3.ResponseBody> response) {

                            try {
                                String responseString = response.body().string();
                                JSONObject jsonObject = new JSONObject(responseString);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("user_id", jsonObject.getString("id"));
                                editor.putString("name",jsonObject.getString("name"));
                                editor.putString("phoneNumber",jsonObject.getString("mobile_number"));
                                editor.putString("designation",jsonObject.getString("designation"));
                                editor.putString("department_id",jsonObject.getString("department"));
                                editor.putString("email",jsonObject.getString("email"));
                                editor.putBoolean("islogin",true);
                                editor.apply();
                                startActivity(new Intent(MainActivity.this, UserOptionActivity.class));
                                finish();
                            } catch (Exception e) {

                            }

                        }
                        @Override
                        public void onFailure(Call<okhttp3.ResponseBody> call, Throwable t) {
                        }
                    });
                }
            }else {
                callbackManager.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
    private void grabResultFromFacebook(LoginResult loginResult) {
        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                final JSONObject profile = response
                        .getJSONObject();
                if (profile != null) {
                    try {
                        picUrl = profile
                                .getJSONObject("picture")
                                .getJSONObject("data")
                                .getString("url");
                        name = profile.getString("name");
                        email = profile.getString("email");
                        Log.d("facebookData", "onCompleted: " + name + " " + email + " " + picUrl);

                        sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("picture", picUrl);
                        editor.putString("name", name);
                        editor.putString("email", email);
                        editor.apply();

                    } catch (Exception es) {
                        es.printStackTrace();
                        Toast.makeText(MainActivity.this, "Error while login", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Profile null", Toast.LENGTH_SHORT).show();
                }
                Call<ResponseBody> callCheck = AppController.getInstance().getApiInterface().postCheckRegistration(email);
                callCheck.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            Log.d("profileJson", "try: " );
                            String responseString = response.body().string();
                            JSONObject jsonObject = new JSONObject(responseString);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("user_id", jsonObject.getString("id"));
                            editor.putString("name",jsonObject.getString("name"));
                            editor.putString("phoneNumber",jsonObject.getString("mobile_number"));
                            editor.putString("designation",jsonObject.getString("designation"));
                            editor.putString("department_id",jsonObject.getString("department"));
                            editor.putString("email",jsonObject.getString("email"));
                            editor.putBoolean("islogin",true);
                            editor.apply();
                            startActivity(new Intent(MainActivity.this,UserOptionActivity.class));
                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                            startActivity(new Intent(MainActivity.this, SignUpActivity.class));
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"Not sucessfully registered...",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        Bundle parameters = new Bundle();
        parameters
                .putString("fields",
                        "id,name,email,picture.width(300)");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}



