package com.example.om.mutualtransferdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtherStateDepartmentSignUpActivity extends AppCompatActivity {

    Spinner spinnerCurrentDistrictOtherState;
    Button btnNextOtherSate;
    EditText edtDesignationOtherState;
    List<DistrictOtherCentral> districtOtherCentrals;

    public final String MyPERF = "myper";
    public SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_state_department_signup);

        spinnerCurrentDistrictOtherState= (Spinner) findViewById(R.id.spinnerCurrentDistrictOtherState);
        btnNextOtherSate= (Button) findViewById(R.id.btnNextOtherSate);
        edtDesignationOtherState= (EditText) findViewById(R.id.edtDesignationOtherState);

        btnNextOtherSate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtDesignationOtherState.getText().length()==0 && edtDesignationOtherState.getText().toString().isEmpty())
                {
                    edtDesignationOtherState.setError("please enter your Designation.");
                    edtDesignationOtherState.requestFocus();
                }
                else {
                    SharedPreferences prefs = getSharedPreferences(MyPERF, MODE_PRIVATE);
                    sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    String designation = prefs.getString("designation", "designation");
//                    edtDesignationOtherState.setText(designation);0)
                    editor.putString("designation",edtDesignationOtherState.getText().toString());
                    editor.putString("District", districtOtherCentrals.get(spinnerCurrentDistrictOtherState.getSelectedItemPosition()).getId()+"");
                    editor.apply();

                    Intent intent = new Intent(OtherStateDepartmentSignUpActivity.this, GoToOtherStateSignUpActivity.class);
                    intent.putExtra("state", getIntent().getStringExtra("state"));
                    startActivity(intent);
                    finish();
                }

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

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(OtherStateDepartmentSignUpActivity.this, android.R.layout.simple_spinner_item, tempDistrict); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCurrentDistrictOtherState.setAdapter(spinnerArrayAdapter);
            }

            @Override
            public void onFailure(Call<List<DistrictOtherCentral>> call, Throwable t) {

            }
        });


    }
}
