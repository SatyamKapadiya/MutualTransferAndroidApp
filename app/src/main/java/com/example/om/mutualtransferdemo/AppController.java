package com.example.om.mutualtransferdemo;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.digits.sdk.android.Digits;
import com.squareup.okhttp.ResponseBody;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import java.util.List;

import com.example.om.mutualtransferdemo.Api.ApiClient;
import com.example.om.mutualtransferdemo.Api.ApiInterface;
import io.fabric.sdk.android.Fabric;
import retrofit2.Call;

/**
 * Created by om on 1/4/2017.
 */
public class AppController extends Application {

    static AppController instance;
    ApiInterface apiInterface;
    public static AppController getInstance()
    {
        return instance;
    }

    public ApiInterface getApiInterface()
    {
        return apiInterface;
    }

    public Call<List<ResponseBody>> setApiInterface(ApiInterface apiInterface)
    {
        this.apiInterface=apiInterface;
        return null;
    }

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "iOafEhnPbDn76L3FjZqO2XuLU";
    private static final String TWITTER_SECRET = "mMN0TgB8262NqCw41Ynov5u7ky14iIUGJiYaZYiGhx7CZCAhAz";

    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits.Builder().build());
        FacebookSdk.sdkInitialize(getApplicationContext());

        instance=this;
        apiInterface= ApiClient.getRetrofit().create(ApiInterface.class);
        setApiInterface(apiInterface);
    }
}
