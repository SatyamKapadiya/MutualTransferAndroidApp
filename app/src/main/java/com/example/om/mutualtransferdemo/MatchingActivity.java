package com.example.om.mutualtransferdemo;

import android.*;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchingActivity extends AppCompatActivity {

    List<PrimaryResult> primaryResults;
    RecyclerView rvMatchingActivity;
    public AdapterDemo adapterDemo;

    public static final String MyPERF = "myper";
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);
        sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);
        rvMatchingActivity = (RecyclerView) findViewById(R.id.rvMatchingActivity);
        rvMatchingActivity.setLayoutManager(new LinearLayoutManager(this));
//        Call<List<PrimaryResult>> callPrimary = AppController.getInstance().getApiInterface().getmatching(sharedPreferences.getString("department_id",""), sharedPreferences.getString("user_id","1"));
        Call<Model> callPrimary = AppController.getInstance().getApiInterface().getmatching(sharedPreferences.getString("department_id",""), "15");
        callPrimary.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {

                Log.d("response", "onResponse: " + call.request().url());
                primaryResults = response.body().getPrimaryResult();
                adapterDemo = new AdapterDemo(primaryResults,MatchingActivity.this);
                rvMatchingActivity.setAdapter(adapterDemo);

            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                t.printStackTrace();
                Log.d("response", "FailureonResponse: " + call.request().url());
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.sharemenu)
        {
//            Toast.makeText(ContactActivity.this, "you select 1st option", Toast.LENGTH_LONG).show();
            Intent sendIntent=new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,"This is my text to send....");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
