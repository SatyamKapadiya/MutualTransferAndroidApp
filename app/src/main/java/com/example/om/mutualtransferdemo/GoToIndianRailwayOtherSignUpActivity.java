package com.example.om.mutualtransferdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoToIndianRailwayOtherSignUpActivity extends AppCompatActivity {

    List<StateName> states;
    List<DistrictOtherCentral> districtOtherCentrals;
    Spinner spinnerGoToChoiceState,spinnerGoToChoiceDistrict;
    Button btnNextGoToIndianRailwayCentral; public final String MyPERF = "myper";
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_indian_railway_other_sign_up);

        sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);
        btnNextGoToIndianRailwayCentral= (Button) findViewById(R.id.btnNextGoToIndianRailwayCentral);
        spinnerGoToChoiceState= (Spinner) findViewById(R.id.spinnerGoToChoiceState);
        spinnerGoToChoiceDistrict= (Spinner) findViewById(R.id.spinnerGoToChoiceDistrict);

        btnNextGoToIndianRailwayCentral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                final String district=districtOtherCentrals.get(spinnerGoToChoiceDistrict.getSelectedItemPosition()).getId()+"";
                editor.putString("District",district);
                editor.putString("state",states.get(spinnerGoToChoiceState.getSelectedItemPosition()).getState().toString());
                editor.apply();

                Log.d("errorData", "onClick: "+sharedPreferences.getString("name","piyu")+" "
                        +sharedPreferences.getString("email","email")
                        + " " +sharedPreferences.getString("phoneNumber", "invalid")
                        + " " +sharedPreferences.getString("District","District")
                        + " " +sharedPreferences.getString("Department_state","Department")
                        + " " +sharedPreferences.getString("picture", "avatar")
                        + " " +sharedPreferences.getString("designation", "designation")

                        + " " +districtOtherCentrals.get(spinnerGoToChoiceDistrict.getSelectedItemPosition()).getId()
                        + " " +states.get(spinnerGoToChoiceState.getSelectedItemPosition()).getState().toString()
                );

                Call<okhttp3.ResponseBody> call = AppController.getInstance().getApiInterface().postOtherState(
                        sharedPreferences.getString("name", "piyu"),
                        sharedPreferences.getString("email", "email"),
                        sharedPreferences.getString("phoneNumber", "invalid"),
                        sharedPreferences.getString("District","District"),
                        sharedPreferences.getString("Department_state","Department"),
                        sharedPreferences.getString("picture", "avatar"),
                        sharedPreferences.getString("designation", "designation"),
                        districtOtherCentrals.get(spinnerGoToChoiceDistrict.getSelectedItemPosition()).getId(),
                        states.get(spinnerGoToChoiceState.getSelectedItemPosition()).getState(),
                        0);

                call.enqueue(new Callback<okhttp3.ResponseBody>() {
                    @Override
                    public void onResponse(Call<okhttp3.ResponseBody> call, Response<okhttp3.ResponseBody> response) {
                        try {
                            String responseString = response.body().toString();
                            JSONObject jsonObject = new JSONObject(responseString);
                            sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("user_id", jsonObject.getString("user_id"));
                            editor.apply();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(GoToIndianRailwayOtherSignUpActivity.this,"succesfuly..." + sharedPreferences.getString("user_id", "user_id"), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(GoToIndianRailwayOtherSignUpActivity.this,UserOptionActivity.class));
                        finish();

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

            }
        });

        Call<List<StateName>> call = AppController.getInstance().getApiInterface().getStateName();
        call.enqueue(new Callback<List<StateName>>() {
            @Override
            public void onResponse(Call<List<StateName>> call, Response<List<StateName>> response) {
                ArrayList<String> temp = new ArrayList<String>();

                states = response.body();
                for (int i = 0; i < states.size(); i++) {
                    temp.add(states.get(i).getState());
                }

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(GoToIndianRailwayOtherSignUpActivity.this, android.R.layout.simple_spinner_item, temp); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerGoToChoiceState.setAdapter(spinnerArrayAdapter);
            }

            @Override
            public void onFailure(Call<List<StateName>> call, Throwable t) {

            }
        });

        spinnerGoToChoiceState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String stateName=states.get(position).getState();
                Call<List<DistrictOtherCentral>> callDistrict=AppController.getInstance().getApiInterface().getDistrict(stateName);
                callDistrict.enqueue(new Callback<List<DistrictOtherCentral>>() {
                    @Override
                    public void onResponse(Call<List<DistrictOtherCentral>> call, Response<List<DistrictOtherCentral>> response) {
                        ArrayList<String> tempDistrict = new ArrayList<String>();
                        districtOtherCentrals=response.body();
                        for (int i = 0; i < districtOtherCentrals.size(); i++) {
                            tempDistrict.add(districtOtherCentrals.get(i).getName());
                        }

                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(GoToIndianRailwayOtherSignUpActivity.this, android.R.layout.simple_spinner_item, tempDistrict); //selected item will look like a spinner set from XML
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerGoToChoiceDistrict.setAdapter(spinnerArrayAdapter);

                    }

                    @Override
                    public void onFailure(Call<List<DistrictOtherCentral>> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
