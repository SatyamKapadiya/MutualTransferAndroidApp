package com.example.om.mutualtransferdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class GoToOtherStateSignUpActivity extends AppCompatActivity {

    Spinner spinnerGoToChoiceDistrictOtherState;
    Button btnNextGoToOtherState;
    List<DistrictOtherCentral> districtOtherCentrals;
    public final String MyPERF = "myper";
    public SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_other_state_sign_up);

        spinnerGoToChoiceDistrictOtherState= (Spinner) findViewById(R.id.spinnerGoToChoiceDistrictOtherState);
        btnNextGoToOtherState= (Button) findViewById(R.id.btnNextGoToOtherState);
//        String s=getIntent().getStringExtra("District");
        btnNextGoToOtherState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                final String district=districtOtherCentrals.get(spinnerGoToChoiceDistrictOtherState.getSelectedItemPosition()).getId()+"";
                editor.putString("District",district);
                editor.apply();

                Log.d("errorData", "onClick: "+sharedPreferences.getString("name","piyu")+" "
                        +sharedPreferences.getString("email","email")
                        + " " +sharedPreferences.getString("phoneNumber", "invalid")
                        + " " +sharedPreferences.getString("District","District")
                        + " " +sharedPreferences.getString("Department_state","Departmentstate")
                        + " " +sharedPreferences.getString("picture", "avatar")
                        + " " +sharedPreferences.getString("designation", "designation")

                        + " " +districtOtherCentrals.get(spinnerGoToChoiceDistrictOtherState.getSelectedItemPosition()).getId()
                        + " " +sharedPreferences.getString("state","state")
                );

                Call<okhttp3.ResponseBody> call = AppController.getInstance().getApiInterface().postOtherState(
                        sharedPreferences.getString("name", "piyu"),
                        sharedPreferences.getString("email", "email"),
                        sharedPreferences.getString("phoneNumber", "invalid"),
                        sharedPreferences.getString("District","District"),
                        sharedPreferences.getString("Department_state","Departmentstate"),
                        sharedPreferences.getString("picture", "avatar"),
                        sharedPreferences.getString("designation", "designation"),
                        districtOtherCentrals.get(spinnerGoToChoiceDistrictOtherState.getSelectedItemPosition()).getId(),
                        sharedPreferences.getString("state","state"),
                        1);

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
                        Toast.makeText(GoToOtherStateSignUpActivity.this,"successfully.."+ sharedPreferences.getString("user_id", "user_id"), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(GoToOtherStateSignUpActivity.this,UserOptionActivity.class));
                        finish();

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });


            }
        });

        Call<List<DistrictOtherCentral>> callDistrict = AppController.getInstance().getApiInterface().getDistrict(getIntent().getStringExtra("state"));
        callDistrict.enqueue(new Callback<List<DistrictOtherCentral>>() {
            @Override
            public void onResponse(Call<List<DistrictOtherCentral>> call, Response<List<DistrictOtherCentral>> response) {
                ArrayList<String> tempDistrict = new ArrayList<String>();
//                String s= getIntent().getStringExtra("state");

                districtOtherCentrals = response.body();
                for (int i = 0; i < districtOtherCentrals.size(); i++) {
                    tempDistrict.add(districtOtherCentrals.get(i).getName());
                }

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(GoToOtherStateSignUpActivity.this, android.R.layout.simple_spinner_item, tempDistrict); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerGoToChoiceDistrictOtherState.setAdapter(spinnerArrayAdapter);
            }

            @Override
            public void onFailure(Call<List<DistrictOtherCentral>> call, Throwable t) {

            }
        });

    }
}
