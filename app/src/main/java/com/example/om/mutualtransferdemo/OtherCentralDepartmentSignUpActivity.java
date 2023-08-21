package com.example.om.mutualtransferdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtherCentralDepartmentSignUpActivity extends AppCompatActivity {

    Spinner spinnerCurrentStateCentralDepartmentSignUpActivity,spinnerCurrentDistrictCentralDepartmentSignUpActivity;
    EditText edtDesignationCentralDepartmentSignUpActivity;
    Button btnNextCentralDepartmentSignUpActivity;
    List<StateName> states;
    List<DistrictOtherCentral> districtOtherCentrals;

    public final String MyPERF = "myper";
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_central_department_signup);

        spinnerCurrentStateCentralDepartmentSignUpActivity= (Spinner) findViewById(R.id.spinnerCurrentStateCentralDepartmentSignUpActivity);
        spinnerCurrentDistrictCentralDepartmentSignUpActivity= (Spinner) findViewById(R.id.spinnerCurrentDistrictCentralDepartmentSignUpActivity);
        btnNextCentralDepartmentSignUpActivity= (Button) findViewById(R.id.btnNextCentralDepartmentSignUpActivity);
        edtDesignationCentralDepartmentSignUpActivity= (EditText) findViewById(R.id.edtDesignationCentralDepartmentSignUpActivity);

        btnNextCentralDepartmentSignUpActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences prefs = getSharedPreferences(MyPERF, MODE_PRIVATE);

                sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                final String district=spinnerCurrentDistrictCentralDepartmentSignUpActivity.getSelectedItem().toString();
                final String state=spinnerCurrentStateCentralDepartmentSignUpActivity.getSelectedItem().toString();
//                String designation = prefs.getString("designation", "designation");
                editor.putString("District",district);
                editor.putString("state",state);
//                edtDesignationCentralDepartmentSignUpActivity.setText(designation);
                editor.putString("designation",edtDesignationCentralDepartmentSignUpActivity.getText().toString());
                editor.apply();

                AlertDialog.Builder builder = new AlertDialog.Builder(OtherCentralDepartmentSignUpActivity.this);
                builder.setTitle("Alert!");
                builder.setMessage("Provide correct information. This can not be edited.");
                builder.setPositiveButton("PROCEED", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(edtDesignationCentralDepartmentSignUpActivity.getText().length()==0 && edtDesignationCentralDepartmentSignUpActivity.getText().toString().isEmpty())
                        {
                            edtDesignationCentralDepartmentSignUpActivity.setError("please enter your Designation.");
                            edtDesignationCentralDepartmentSignUpActivity.requestFocus();
                        }
                        else
                        {
                            startActivity(new Intent(OtherCentralDepartmentSignUpActivity.this, GoToIndianRailwayOtherSignUpActivity.class));
                            finish();
                        }
//                        Toast.makeText(IndianRailwayCentralDepartmentSignUpActivity.this,"Succesfully....",Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("EDIT", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
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


                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(OtherCentralDepartmentSignUpActivity.this, android.R.layout.simple_spinner_item, temp); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCurrentStateCentralDepartmentSignUpActivity.setAdapter(spinnerArrayAdapter);

            }

            @Override
            public void onFailure(Call<List<StateName>> call, Throwable t) {

            }
        });


        spinnerCurrentStateCentralDepartmentSignUpActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(OtherCentralDepartmentSignUpActivity.this, android.R.layout.simple_spinner_item, tempDistrict); //selected item will look like a spinner set from XML
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerCurrentDistrictCentralDepartmentSignUpActivity.setAdapter(spinnerArrayAdapter);

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
