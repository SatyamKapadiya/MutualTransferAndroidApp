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

import com.twitter.sdk.android.core.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoToWestBangalSignUpActivity extends AppCompatActivity {
    Spinner spinnerGoToChoiceDistrictWestBangal;
    Button btnNextGoToStateWestBangalPrimary;
    List<WestBangalState> westBangalStates;

    public final String MyPERF = "myper";
    public SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_west_bangal_sign_up);
        sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);

        spinnerGoToChoiceDistrictWestBangal= (Spinner) findViewById(R.id.spinnerGoToChoiceDistrictWestBangal);
        btnNextGoToStateWestBangalPrimary= (Button) findViewById(R.id.btnNextGoToStateWestBangalPrimary);

        btnNextGoToStateWestBangalPrimary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("district",westBangalStates.get(spinnerGoToChoiceDistrictWestBangal.getSelectedItemPosition()).getId()+"");
                editor.apply();

//                Log.d("errorData", "onClick: "+sharedPreferences.getString("name","piyu")+" " +sharedPreferences.getString("email","email")
//                    + " " +sharedPreferences.getString("phoneNumber", "invalid")
//                +" "+sharedPreferences.getInt("department_state",43)
//                        + " "+sharedPreferences.getString("avatar","avatar")
//                        + " " +sharedPreferences.getString("designation","designation")
//                        +" "+westBangalStates.get(spinnerGoToChoiceDistrictWestBangal.getSelectedItemPosition()).getId()
//                        + " "+sharedPreferences.getString("category","Assistent" )
//                        + " "+sharedPreferences.getString("designation", "NA")
//                        + " "+sharedPreferences.getString("qulification","B.E")
//                        + " "+sharedPreferences.getString("trained","trained")
//                        + " "+sharedPreferences.getString("district","Bareli")
//                        + " "+sharedPreferences.getString("SchoolType","1")
//                );
                if(sharedPreferences.getString("SchoolType","Primary").equals("0"))
                {

                    Call<okhttp3.ResponseBody> call = AppController.getInstance().getApiInterface().postWestBangal(
                            sharedPreferences.getString("name", "piyu"),
                            sharedPreferences.getString("email", "email"),
                            sharedPreferences.getString("phoneNumber", "invalid"),
                            "1",
                            1788,
                            sharedPreferences.getString("picture", "avatar"),
                            sharedPreferences.getString("designation", "designation"),
                            westBangalStates.get(spinnerGoToChoiceDistrictWestBangal.getSelectedItemPosition()).getId(),
                            "West Bengal (WB)",
                            sharedPreferences.getString("category", "Assistent"),
                            sharedPreferences.getString("designation", "NA"),
                            sharedPreferences.getString("qulification", "B.E"),
                            sharedPreferences.getString("trained", "trained"),
                            sharedPreferences.getString("district", "bankura"),
                            "0",
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
                            Toast.makeText(GoToWestBangalSignUpActivity.this, "UserId succesfully saved...." + sharedPreferences.getString("user_id", "user_id"), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(GoToWestBangalSignUpActivity.this,UserOptionActivity.class));
                            finish();

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });

                }
                else {

                Log.d("errorData", "onClick: "+sharedPreferences.getString("name","piyu")+" " +sharedPreferences.getString("email","email")
                    + " " +sharedPreferences.getString("phoneNumber", "invalid")
                     +" "+sharedPreferences.getInt("department_state",43)
                        + " "+sharedPreferences.getString("avatar","avatar")
                        + " " +sharedPreferences.getString("designation","designation")
                        +" "+westBangalStates.get(spinnerGoToChoiceDistrictWestBangal.getSelectedItemPosition()).getId()
                        + " "+sharedPreferences.getString("category","Assistent" )
                        + " "+sharedPreferences.getString("subject","WestBengal")
                        + " "+sharedPreferences.getString("postCategory","PASS")
                        + " "+sharedPreferences.getString("comission", "NA")
                        + " "+sharedPreferences.getString("wbSchoolType","B.E")
                        + " "+sharedPreferences.getString("trained","trained")
                        + " "+sharedPreferences.getString("district","Bareli")
                );

                    Call<okhttp3.ResponseBody> callHighSchool = AppController.getInstance().getApiInterface().postWestBengalHighSchool(
                            sharedPreferences.getString("name", "piyu"),
                            sharedPreferences.getString("email", "email"),
                            sharedPreferences.getString("phoneNumber", "invalid"),
                            "1",
                            1788,
                            sharedPreferences.getString("picture", "avatar"),
                            sharedPreferences.getString("designation", "designation"),
                            westBangalStates.get(spinnerGoToChoiceDistrictWestBangal.getSelectedItemPosition()).getId(),
                            "West Bengal (WB)",
                            sharedPreferences.getString("category", "Assistent"),
                            sharedPreferences.getString("subject", "WestBengal"),
                            sharedPreferences.getString("postCategory", "PASS"),
                            sharedPreferences.getString("comission", "NA"),
                            sharedPreferences.getString("wbSchoolType", "Boysschool"),
                            sharedPreferences.getString("trained", "trained"),
                            sharedPreferences.getString("district", "bareli"),
                            "1",
                            1);

                    callHighSchool.enqueue(new Callback<okhttp3.ResponseBody>() {
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
                            Toast.makeText(GoToWestBangalSignUpActivity.this, "UserId succesfully saved...." + sharedPreferences.getString("user_id", "user_id"), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(GoToWestBangalSignUpActivity.this,UserOptionActivity.class));
                            finish();

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });

                }

            }
        });

        Call<List<WestBangalState>> callDistrict = AppController.getInstance().getApiInterface().getWestBangalDistrict("West Bengal (WB)");
        callDistrict.enqueue(new Callback<List<WestBangalState>>() {
            @Override
            public void onResponse(Call<List<WestBangalState>> call, Response<List<WestBangalState>> response) {
                ArrayList<String> tempDistrict = new ArrayList<String>();
//                String s= getIntent().getStringExtra("State");

                westBangalStates = response.body();
                for (int i = 0; i < westBangalStates.size(); i++) {
                    tempDistrict.add(westBangalStates.get(i).getName());
                }

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(GoToWestBangalSignUpActivity.this, android.R.layout.simple_spinner_item, tempDistrict); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerGoToChoiceDistrictWestBangal.setAdapter(spinnerArrayAdapter);
            }

            @Override
            public void onFailure(Call<List<WestBangalState>> call, Throwable t) {

            }
        });
    }
}
