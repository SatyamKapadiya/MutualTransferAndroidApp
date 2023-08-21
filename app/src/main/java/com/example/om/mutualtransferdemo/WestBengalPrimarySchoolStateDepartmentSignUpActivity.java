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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.layout.simple_spinner_dropdown_item;
import static android.R.layout.simple_spinner_item;

public class WestBengalPrimarySchoolStateDepartmentSignUpActivity extends AppCompatActivity {

    Spinner spinnerCurrentDistrictWestBangal,spinnerCategoryWestBangal,spinnerDesignationWestBangal,spinnerQulificationWestBangal,
            spinnerTrainedWestBangal;
    Button btnNextPrimaryWestBangal;
    PrimaryWestBangal primaryWestBangal;
    List<WestBangalState> westBangalStates;
    List<StateName> stateNames;

    public final String MyPERF = "myper";
    public SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_west_bengal_primary_school_state_department_signup);
        sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);

        spinnerCurrentDistrictWestBangal= (Spinner) findViewById(R.id.spinnerCurrentDistrictWestBangal);
        spinnerCategoryWestBangal= (Spinner) findViewById(R.id.spinnerCategoryWestBangal);
        spinnerDesignationWestBangal= (Spinner) findViewById(R.id.spinnerDesignationWestBangal);
        spinnerQulificationWestBangal= (Spinner) findViewById(R.id.spinnerQulificationWestBangal);
        spinnerTrainedWestBangal= (Spinner) findViewById(R.id.spinnerTrainedWestBangal);
        btnNextPrimaryWestBangal= (Button) findViewById(R.id.btnNextPrimaryWestBangal);

        btnNextPrimaryWestBangal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("designation",primaryWestBangal.getDesignation().get(spinnerDesignationWestBangal.getSelectedItemPosition()).getId()+"");
                editor.putString("qulification",primaryWestBangal.getQualification().get(spinnerQulificationWestBangal.getSelectedItemPosition()).getId()+"");
                editor.putString("category",primaryWestBangal.getCategory().get(spinnerCategoryWestBangal.getSelectedItemPosition()).getId()+"");
                editor.putString("trained",primaryWestBangal.getTraned().get(spinnerTrainedWestBangal.getSelectedItemPosition()).getId()+"");
                editor.putString("district",westBangalStates.get(spinnerCurrentDistrictWestBangal.getSelectedItemPosition()).getId()+"");
                editor.apply();
                startActivity(new Intent(WestBengalPrimarySchoolStateDepartmentSignUpActivity.this,GoToWestBangalSignUpActivity.class));
                finish();
            }
        });

        Call<PrimaryWestBangal> callGrad=AppController.getInstance().getApiInterface().getPrimaryWestBangal();
        callGrad.enqueue(new Callback<PrimaryWestBangal>() {
            @Override
            public void onResponse(Call<PrimaryWestBangal> call, Response<PrimaryWestBangal> response) {
                ArrayList<String> tempSchoolType = new ArrayList<>();
                ArrayList<String> tempCurrentDistrict= new ArrayList<>();
                ArrayList<String> tempDesignation= new ArrayList<>();
                ArrayList<String> tempQulification = new ArrayList<>();
                ArrayList<String> tempCategory= new ArrayList<>();
                ArrayList<String> tempTrained= new ArrayList<>();

                primaryWestBangal= response.body();

                for (int i = 0; i < primaryWestBangal.getCategory().size(); i++) {
//                    Log.d("primaryWB", "onResponse: "+primaryWestBangal.getCategory().get(i).getCategory());
                    tempCategory.add(primaryWestBangal.getCategory().get(i).getCategoryName());
                }
                for (int i = 0; i < primaryWestBangal.getDesignation().size(); i++) {
                    tempDesignation.add(primaryWestBangal.getDesignation().get(i).getDesignation());
                }
                for (int i = 0; i < primaryWestBangal.getQualification().size(); i++) {
                    tempQulification.add(primaryWestBangal.getQualification().get(i).getQualification());
                }

                for (int i = 0; i <primaryWestBangal.getTraned().size(); i++) {
                    tempTrained.add(primaryWestBangal.getTraned().get(i).getTranedName());
                }


                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(WestBengalPrimarySchoolStateDepartmentSignUpActivity.this, simple_spinner_item, tempCategory); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCategoryWestBangal.setAdapter(spinnerArrayAdapter);

                ArrayAdapter<String> spinnerArrayAdapterDesignation=new ArrayAdapter<String>(WestBengalPrimarySchoolStateDepartmentSignUpActivity.this, android.R.layout.simple_spinner_item,tempDesignation);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerDesignationWestBangal.setAdapter(spinnerArrayAdapterDesignation);

                ArrayAdapter<String> spinnerArrayAdapterQulification=new ArrayAdapter<String>(WestBengalPrimarySchoolStateDepartmentSignUpActivity.this,simple_spinner_item,tempCategory);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerQulificationWestBangal.setAdapter(spinnerArrayAdapterQulification);

                ArrayAdapter<String> spinnerArrayAdapterSelectionpost = new ArrayAdapter<String>(WestBengalPrimarySchoolStateDepartmentSignUpActivity.this, simple_spinner_item, tempTrained);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//selected item will look like a spinner set from XML
                spinnerTrainedWestBangal.setAdapter(spinnerArrayAdapterSelectionpost);
            }

            @Override
            public void onFailure(Call<PrimaryWestBangal> call, Throwable t) {

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

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(WestBengalPrimarySchoolStateDepartmentSignUpActivity.this, android.R.layout.simple_spinner_item, tempDistrict); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCurrentDistrictWestBangal.setAdapter(spinnerArrayAdapter);
            }

            @Override
            public void onFailure(Call<List<WestBangalState>> call, Throwable t) {

            }
        });
    }
}
