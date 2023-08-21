package com.example.om.mutualtransferdemo.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by om on 12/17/2016.
 */

public class ApiClient {

    private static final String BASE_URL="http://antarikshtech.com/api/mutualtransfer/";
    private static Retrofit retrofit=null;

    public static Retrofit getRetrofit(){

        if (retrofit==null)
        {
                retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }





}
