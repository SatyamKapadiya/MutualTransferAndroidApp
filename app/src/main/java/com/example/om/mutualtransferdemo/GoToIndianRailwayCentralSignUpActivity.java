package com.example.om.mutualtransferdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.okhttp.ResponseBody;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoToIndianRailwayCentralSignUpActivity extends AppCompatActivity {

    List<RailwayZone> railwayZones;
    List<RailwayDivision> railwayDivision;
    Spinner spinnerGoToRailwayZone,spinnerGoToRailwayDivision;
    Button btnNextGoToIndianRailwayCentral;

    public static final String MyPERF = "myper";
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_indian_railway_central_sign_up);

        spinnerGoToRailwayZone= (Spinner) findViewById(R.id.spinnerGoToRailwayZone);
        spinnerGoToRailwayDivision= (Spinner) findViewById(R.id.spinnerGoToRailwayDivision);
        btnNextGoToIndianRailwayCentral= (Button) findViewById(R.id.btnNextGoToIndianRailwayCentral);

        btnNextGoToIndianRailwayCentral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                String railwayZone=spinnerGoToRailwayZone.getSelectedItem().toString();
                String railwayDiv=spinnerGoToRailwayDivision.getSelectedItem().toString();
                editor.putString("Railway_Zone",railwayZone);
                editor.putString("Railway_Division",railwayDiv);
                editor.apply();

                String railway=getIntent().getStringExtra("railwayZone");
                String division=getIntent().getStringExtra("railwayDivision");
//                Integer category=getIntent().getIntExtra("category");
                String GradePay=getIntent().getStringExtra("gradPay");

                Call<okhttp3.ResponseBody> call = AppController.getInstance().getApiInterface().postIndianRailway(
                        sharedPreferences.getString("name","piyu"),
                        sharedPreferences.getString("email","email"),
                        sharedPreferences.getString("phoneNumber", "invalid"),
                        "1",
                        sharedPreferences.getInt("department",43),
                        sharedPreferences.getString("picture","avatar"),
                        sharedPreferences.getString("designation","designation"),
                        railwayZones.get(spinnerGoToRailwayZone.getSelectedItemPosition()).getId()+"",
                        railwayDivision.get(spinnerGoToRailwayDivision.getSelectedItemPosition()).getId()+"",
                        sharedPreferences.getString("railwayZone",railway),
                        sharedPreferences.getString("railwayDivision",division),
                        sharedPreferences.getInt("category",123),
                        sharedPreferences.getInt("gradPay",250),
                        sharedPreferences.getString("railwayDepartment","railwayDepartment"),
                        sharedPreferences.getString("basicPay","basicPay"),
                        sharedPreferences.getString("appoinmentDate","appoinmentDate"),
                        sharedPreferences.getString("State","State"));

                call.enqueue(new Callback<okhttp3.ResponseBody>() {
                    @Override
                    public void onResponse(Call<okhttp3.ResponseBody> call, Response<okhttp3.ResponseBody> response) {
                        try {
                            String responseString = response.body().string();
                            JSONObject jsonObject = new JSONObject(responseString);
                            sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);

                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putString("user_id",jsonObject.getString("user_id"));
                            editor.apply();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(GoToIndianRailwayCentralSignUpActivity.this,"suceesfully..."+sharedPreferences.getString("user_id","user_id"),Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(GoToIndianRailwayCentralSignUpActivity.this,UserOptionActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailure(Call<okhttp3.ResponseBody> call, Throwable t) {

                    }
                });

            }
        });


        Call<List<RailwayZone>> call = AppController.getInstance().getApiInterface().getRailwayZone();
        call.enqueue(new Callback<List<RailwayZone>>() {
            @Override
            public void onResponse(Call<List<RailwayZone>> call, Response<List<RailwayZone>> response) {
                ArrayList<String> tempRailwayZone = new ArrayList<String>();

                railwayZones = response.body();
                for (int i = 0; i < railwayZones.size(); i++) {
                    tempRailwayZone.add(railwayZones.get(i).getRailwayZoneName());
                }


                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(GoToIndianRailwayCentralSignUpActivity.this, android.R.layout.simple_spinner_item, tempRailwayZone); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerGoToRailwayZone.setAdapter(spinnerArrayAdapter);

            }

            @Override
            public void onFailure(Call<List<RailwayZone>> call, Throwable t) {

            }
        });

        Call<List<RailwayDivision>> callRailwayDivision = AppController.getInstance().getApiInterface().getRailwayDivision("3");
        callRailwayDivision.enqueue(new Callback<List<RailwayDivision>>() {
            @Override
            public void onResponse(Call<List<RailwayDivision>> call, Response<List<RailwayDivision>> response) {
                ArrayList<String> tempRailwayDivision = new ArrayList<String>();
                railwayDivision = response.body();
                for (int i = 0; i < railwayDivision.size(); i++) {
                    tempRailwayDivision.add(railwayDivision.get(i).getDivision());
                }

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(GoToIndianRailwayCentralSignUpActivity.this, android.R.layout.simple_spinner_item, tempRailwayDivision); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerGoToRailwayDivision.setAdapter(spinnerArrayAdapter);
            }

            @Override
            public void onFailure(Call<List<RailwayDivision>> call, Throwable t) {

            }
        });

        spinnerGoToRailwayZone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Call<List<RailwayDivision>> callRailwayDivision = AppController.getInstance().getApiInterface().getRailwayDivision(railwayZones.get(position).getId().toString()+"");
                callRailwayDivision.enqueue(new Callback<List<RailwayDivision>>() {
                    @Override
                    public void onResponse(Call<List<RailwayDivision>> call, Response<List<RailwayDivision>> response) {
                        ArrayList<String> tempRailwayDivision = new ArrayList<String>();
                        railwayDivision = response.body();
                        for (int i = 0; i < railwayDivision.size(); i++) {
                            tempRailwayDivision.add(railwayDivision.get(i).getDivision());
                        }

                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(GoToIndianRailwayCentralSignUpActivity.this, android.R.layout.simple_spinner_item, tempRailwayDivision); //selected item will look like a spinner set from XML
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerGoToRailwayDivision.setAdapter(spinnerArrayAdapter);

                    }

                    @Override
                    public void onFailure(Call<List<RailwayDivision>> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerGoToRailwayZone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Call<List<RailwayDivision>> callRailwayDivision = AppController.getInstance().getApiInterface().getRailwayDivision(railwayZones.get(position).getId().toString()+"");
                callRailwayDivision.enqueue(new Callback<List<RailwayDivision>>() {
                    @Override
                    public void onResponse(Call<List<RailwayDivision>> call, Response<List<RailwayDivision>> response) {
                        ArrayList<String> tempRailwayDivision = new ArrayList<String>();
                        railwayDivision = response.body();
                        for (int i = 0; i < railwayDivision.size(); i++) {
                            tempRailwayDivision.add(railwayDivision.get(i).getDivision());
                        }

                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(GoToIndianRailwayCentralSignUpActivity.this, android.R.layout.simple_spinner_item, tempRailwayDivision); //selected item will look like a spinner set from XML
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerGoToRailwayDivision.setAdapter(spinnerArrayAdapter);

                    }

                    @Override
                    public void onFailure(Call<List<RailwayDivision>> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
