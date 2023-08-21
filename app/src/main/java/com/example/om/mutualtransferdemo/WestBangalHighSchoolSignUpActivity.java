package com.example.om.mutualtransferdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class WestBangalHighSchoolSignUpActivity extends AppCompatActivity {

    HighSchoolWestBangal highSchoolWestBangal;
    List<HighSchoolSubject> highSchoolSubjects;
    List<WestBangalState> westBangalStates;
    Spinner spinnerSchoolTypeHighSchoolWestBangal,spinnerCurrentDistrictHighSchoolWestBangal,spinnerCommissionHighSchoolWestBangal,spinnerSelectionCategoryHighSchoolWestBangal,
            spinnerCategoryHighSchoolWestBangal,spinnerSubjectHighSchoolWestBangal,spinnerTrainedHighSchoolWestBangal,spinnerSchoolTypeCommonHighSchoolWestBangal;

    Button btnNextHighSchoolWestBangal;

    public final String MyPERF = "myper";
    public SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_west_bangal_high_school_sign_up);
        sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);

//        spinnerSchoolTypeHighSchoolWestBangal= (Spinner) findViewById(R.id.spinnerSchoolTypeHighSchoolWestBangal);
        spinnerCurrentDistrictHighSchoolWestBangal= (Spinner) findViewById(R.id.spinnerCurrentDistrictHighSchoolWestBangal);
        spinnerCommissionHighSchoolWestBangal= (Spinner) findViewById(R.id.spinnerCommissionHighSchoolWestBangal);
        spinnerSelectionCategoryHighSchoolWestBangal= (Spinner) findViewById(R.id.spinnerSelectionCategoryHighSchoolWestBangal);
        spinnerCategoryHighSchoolWestBangal= (Spinner) findViewById(R.id. spinnerCategoryHighSchoolWestBangal);
        spinnerSubjectHighSchoolWestBangal= (Spinner) findViewById(R.id.spinnerSubjectHighSchoolWestBangal);
        spinnerTrainedHighSchoolWestBangal= (Spinner) findViewById(R.id.spinnerTrainedHighSchoolWestBangal);
        spinnerSchoolTypeCommonHighSchoolWestBangal= (Spinner) findViewById(R.id.spinnerSchoolTypeCommonHighSchoolWestBangal);

        btnNextHighSchoolWestBangal= (Button) findViewById(R.id.btnNextHighSchoolWestBangal);

        btnNextHighSchoolWestBangal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("wbSchoolType",highSchoolWestBangal.getWbSchoolType().get(spinnerSchoolTypeCommonHighSchoolWestBangal.getSelectedItemPosition()).getId()+"");
                editor.putString("comission",highSchoolWestBangal.getWbComission().get(spinnerCommissionHighSchoolWestBangal.getSelectedItemPosition()).getId()+"");
                editor.putString("category",highSchoolWestBangal.getWbCategory().get(spinnerCategoryHighSchoolWestBangal.getSelectedItemPosition()).getId()+"");
                editor.putString("trained",highSchoolWestBangal.getWbTraned().get(spinnerTrainedHighSchoolWestBangal.getSelectedItemPosition()).getId()+"");
                editor.putString("postCategory",highSchoolWestBangal.getWbPostCategory().get(spinnerSelectionCategoryHighSchoolWestBangal.getSelectedItemPosition()).getId()+"");
                editor.putString("district",westBangalStates.get(spinnerCurrentDistrictHighSchoolWestBangal.getSelectedItemPosition()).getId()+"");
                editor.putString("subject",highSchoolSubjects.get(spinnerSubjectHighSchoolWestBangal.getSelectedItemPosition()).getId()+"");
                editor.apply();
                startActivity(new Intent(WestBangalHighSchoolSignUpActivity.this,GoToWestBangalSignUpActivity.class));
                finish();
            }
        });

        Call<HighSchoolWestBangal> callGrad=AppController.getInstance().getApiInterface().getHighSchoolWestBangal();
        callGrad.enqueue(new Callback<HighSchoolWestBangal>() {
            @Override
            public void onResponse(Call<HighSchoolWestBangal> call, Response<HighSchoolWestBangal> response) {
                ArrayList<String> tempSchoolType = new ArrayList<>();
                ArrayList<String> tempCurrentDistrict= new ArrayList<>();
                ArrayList<String> tempCommission= new ArrayList<>();
                ArrayList<String> tempSelectCategory = new ArrayList<>();
                ArrayList<String> tempCategory= new ArrayList<>();
                ArrayList<String> tempTrained= new ArrayList<>();
                ArrayList<String> tempCommonSchoolType= new ArrayList<>();
                highSchoolWestBangal= response.body();

                for (int i = 0; i < highSchoolWestBangal.getWbSchoolType().size(); i++) {
                    tempCommonSchoolType.add(highSchoolWestBangal.getWbSchoolType().get(i).getSchoolType());
                }
                for (int i = 0; i < highSchoolWestBangal.getWbComission().size(); i++) {
                    tempCommission.add(highSchoolWestBangal.getWbComission().get(i).getCommission());
                }
                for (int i = 0; i < highSchoolWestBangal.getWbPostCategory().size(); i++) {
                    tempSelectCategory.add(highSchoolWestBangal.getWbPostCategory().get(i).getPostCategory());
                }

                for (int i = 0; i < highSchoolWestBangal.getWbCategory().size(); i++) {
                    tempCategory.add(highSchoolWestBangal.getWbCategory().get(i).getCategoryName());
                }
                for (int i = 0; i < highSchoolWestBangal.getWbTraned().size(); i++) {
                    tempTrained.add(highSchoolWestBangal.getWbTraned().get(i).getTraned());
                }

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(WestBangalHighSchoolSignUpActivity.this, simple_spinner_item, tempCommonSchoolType); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSchoolTypeCommonHighSchoolWestBangal.setAdapter(spinnerArrayAdapter);

                ArrayAdapter<String> spinnerArrayAdapterCommision=new ArrayAdapter<String>(WestBangalHighSchoolSignUpActivity.this, android.R.layout.simple_spinner_item,tempCommission);
                spinnerArrayAdapterCommision.setDropDownViewResource(simple_spinner_dropdown_item);
                spinnerCommissionHighSchoolWestBangal.setAdapter(spinnerArrayAdapterCommision);

                ArrayAdapter<String> spinnerArrayAdapterCategory=new ArrayAdapter<String>(WestBangalHighSchoolSignUpActivity.this,simple_spinner_item,tempCategory);
                spinnerArrayAdapterCategory.setDropDownViewResource(simple_spinner_dropdown_item);
                spinnerCategoryHighSchoolWestBangal.setAdapter(spinnerArrayAdapterCategory);

                ArrayAdapter<String> spinnerArrayAdapterSelectionpost = new ArrayAdapter<String>(WestBangalHighSchoolSignUpActivity.this, simple_spinner_item, tempSelectCategory); //selected item will look like a spinner set from XML
                spinnerArrayAdapterSelectionpost.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSelectionCategoryHighSchoolWestBangal.setAdapter(spinnerArrayAdapterSelectionpost);

                ArrayAdapter<String> spinnerArrayAdapterTrained=new ArrayAdapter<String>(WestBangalHighSchoolSignUpActivity.this, android.R.layout.simple_spinner_item,tempTrained);
                spinnerArrayAdapterTrained.setDropDownViewResource(simple_spinner_dropdown_item);
                spinnerTrainedHighSchoolWestBangal.setAdapter(spinnerArrayAdapterTrained);

            }

            @Override
            public void onFailure(Call<HighSchoolWestBangal> call, Throwable t) {

            }
        });

        Call<List<HighSchoolSubject>> callHighSchoolSubject = AppController.getInstance().getApiInterface().getHighSchoolSubject("3");
        callHighSchoolSubject.enqueue(new Callback<List<HighSchoolSubject>>() {
            @Override
            public void onResponse(Call<List<HighSchoolSubject>> call, Response<List<HighSchoolSubject>> response) {
                ArrayList<String> tempHighSchoolSubject = new ArrayList<String>();

                highSchoolSubjects = response.body();
                for (int i = 0; i < highSchoolSubjects.size(); i++) {
                    tempHighSchoolSubject.add(highSchoolSubjects.get(i).getSubject());
                }

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(WestBangalHighSchoolSignUpActivity.this, android.R.layout.simple_spinner_item, tempHighSchoolSubject); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSubjectHighSchoolWestBangal.setAdapter(spinnerArrayAdapter);

            }

            @Override
            public void onFailure(Call<List<HighSchoolSubject>> call, Throwable t) {

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

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(WestBangalHighSchoolSignUpActivity.this, android.R.layout.simple_spinner_item, tempDistrict); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCurrentDistrictHighSchoolWestBangal.setAdapter(spinnerArrayAdapter);
            }

            @Override
            public void onFailure(Call<List<WestBangalState>> call, Throwable t) {

            }
        });

    }
}
