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
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KaranatakaStateDepartmentPrimarySchoolSignUpActivity extends AppCompatActivity {

    Spinner spinnerSchoolOfficeSelectionKarnataka, spinnerDesignationKarnataka, spinnerAppointedKarnataka, spinnerDistrictKaranataka, spinnerSubjectKaranatakaPrimary;
    Button btnNextKarnatakaPrimarySignUpActivity;
    TextView txtDesignationKarnataka, txtSubjectKaranatakaPrimary;

    List<SchoolOfficeSelection> schoolOfficeSelections;
    List<DistrictKarnatakaPrimarySchool> districtKarnatakaPrimarySchools;
    List<DesignationKaranatakaState> designationKaranatakaStates;
    List<SubjectKaranatakaState> subjectKaranatakaStates;

    String[] KaranatakaState = {"State", "SSA"};

    public final String MyPERF = "myper";
    public SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karanataka_state_department_primary_school_sign_up);
        sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);

        spinnerSchoolOfficeSelectionKarnataka = (Spinner) findViewById(R.id.spinnerSchoolOfficeSelectionKarnataka);
        spinnerDesignationKarnataka = (Spinner) findViewById(R.id.spinnerDesignationKarnataka);
        spinnerSubjectKaranatakaPrimary = (Spinner) findViewById(R.id.spinnerSubjectKaranatakaPrimary);
        spinnerAppointedKarnataka = (Spinner) findViewById(R.id.spinnerAppointedKarnataka);
        spinnerDistrictKaranataka = (Spinner) findViewById(R.id.spinnerDistrictKranataka);

        txtDesignationKarnataka = (TextView) findViewById(R.id.txtDesignationKarnataka);
        txtSubjectKaranatakaPrimary = (TextView) findViewById(R.id.txtSubjectKaranatakaPrimary);

        btnNextKarnatakaPrimarySignUpActivity = (Button) findViewById(R.id.btnNextKarnatakaPrimarySignUpActivity);

        btnNextKarnatakaPrimarySignUpActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
//                final String schoolOffice=spinnerSchoolOfficeSelectionKarnataka.getSelectedItemPosition().;
                final String designation = spinnerDesignationKarnataka.getSelectedItem().toString();
                final String appointed = spinnerAppointedKarnataka.getSelectedItem().toString();
                final String district = spinnerDistrictKaranataka.getSelectedItem().toString();
                editor.putInt("School_Office_Selection", schoolOfficeSelections.get(spinnerSchoolOfficeSelectionKarnataka.getSelectedItemPosition()).getId());
                editor.putString("designation", designation);
                editor.putInt("appointedScheme", spinnerAppointedKarnataka.getSelectedItemPosition()+1);
                editor.putInt("district", districtKarnatakaPrimarySchools.get(spinnerDistrictKaranataka.getSelectedItemPosition()).getId());
                if (spinnerSubjectKaranatakaPrimary.getVisibility() == View.VISIBLE) {
                    sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);
                    editor.putString("subject", subjectKaranatakaStates.get(spinnerSubjectKaranatakaPrimary.getSelectedItemPosition()).getSubject());
                    editor.apply();
                } else {
                    editor.putString("subject", "NA");
                }
//                editor.putString("subject",subject);
                editor.apply();

                AlertDialog.Builder builder = new AlertDialog.Builder(KaranatakaStateDepartmentPrimarySchoolSignUpActivity.this);
                builder.setTitle("Alert!");
                builder.setMessage("Provide correct information. This can not be edited.");
                builder.setPositiveButton("PROCEED", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        startActivity(new Intent(KaranatakaStateDepartmentPrimarySchoolSignUpActivity.this, GoToStateKaranatakaPrimary.class));
                        finish();
//                        Toast.makeText(IndianRailwayCentralDepartmentSignUpActivity.this,"Succesfully....",Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("EDIT", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        ArrayAdapter<String> spinnerArrayAdapterState = new ArrayAdapter<String>(KaranatakaStateDepartmentPrimarySchoolSignUpActivity.this, android.R.layout.simple_spinner_item, KaranatakaState); //selected item will look like a spinner set from XML
        spinnerArrayAdapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAppointedKarnataka.setAdapter(spinnerArrayAdapterState);

        Call<List<SchoolOfficeSelection>> callSchoolOfficeSelection = AppController.getInstance().getApiInterface().getSchoolOfficeSelection();
        callSchoolOfficeSelection.enqueue(new Callback<List<SchoolOfficeSelection>>() {
            @Override
            public void onResponse(Call<List<SchoolOfficeSelection>> call, Response<List<SchoolOfficeSelection>> response) {
                ArrayList<String> tempSchoolOfficeSelection = new ArrayList<String>();

                schoolOfficeSelections = response.body();
                for (int i = 0; i < schoolOfficeSelections.size(); i++) {
                    tempSchoolOfficeSelection.add(schoolOfficeSelections.get(i).getName());
                }


                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(KaranatakaStateDepartmentPrimarySchoolSignUpActivity.this, android.R.layout.simple_spinner_item, tempSchoolOfficeSelection); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSchoolOfficeSelectionKarnataka.setAdapter(spinnerArrayAdapter);

            }

            @Override
            public void onFailure(Call<List<SchoolOfficeSelection>> call, Throwable t) {

            }
        });

        spinnerSchoolOfficeSelectionKarnataka.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String school = schoolOfficeSelections.get(position).getName();
                Call<List<DesignationKaranatakaState>> callSchool = AppController.getInstance().getApiInterface().getDesignationKaranataka(schoolOfficeSelections.get(position).getId() + "");
                callSchool.enqueue(new Callback<List<DesignationKaranatakaState>>() {
                    @Override
                    public void onResponse(Call<List<DesignationKaranatakaState>> call, Response<List<DesignationKaranatakaState>> response) {
                        ArrayList<String> tempSchoolOffice = new ArrayList<String>();
                        designationKaranatakaStates = response.body();
                        for (int i = 0; i < designationKaranatakaStates.size(); i++) {
                            tempSchoolOffice.add(designationKaranatakaStates.get(i).getDesignation());
                        }

                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(KaranatakaStateDepartmentPrimarySchoolSignUpActivity.this, android.R.layout.simple_spinner_item, tempSchoolOffice); //selected item will look like a spinner set from XML
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerDesignationKarnataka.setAdapter(spinnerArrayAdapter);

                    }

                    @Override
                    public void onFailure(Call<List<DesignationKaranatakaState>> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Call<List<DistrictKarnatakaPrimarySchool>> callDistrictKaranataka = AppController.getInstance().getApiInterface().getDistrictKaranataka();
        callDistrictKaranataka.enqueue(new Callback<List<DistrictKarnatakaPrimarySchool>>() {
            @Override
            public void onResponse(Call<List<DistrictKarnatakaPrimarySchool>> call, Response<List<DistrictKarnatakaPrimarySchool>> response) {
                ArrayList<String> tempDistrict = new ArrayList<String>();

                districtKarnatakaPrimarySchools = response.body();
                for (int i = 0; i < districtKarnatakaPrimarySchools.size(); i++) {
                    tempDistrict.add(districtKarnatakaPrimarySchools.get(i).getDistricts());
                }


                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(KaranatakaStateDepartmentPrimarySchoolSignUpActivity.this, android.R.layout.simple_spinner_item, tempDistrict); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerDistrictKaranataka.setAdapter(spinnerArrayAdapter);

            }

            @Override
            public void onFailure(Call<List<DistrictKarnatakaPrimarySchool>> call, Throwable t) {

            }
        });

        Call<List<DesignationKaranatakaState>> callDesignationKarnataka = AppController.getInstance().getApiInterface().getDesignationKaranataka("2");
        callDesignationKarnataka.enqueue(new Callback<List<DesignationKaranatakaState>>() {
            @Override
            public void onResponse(Call<List<DesignationKaranatakaState>> call, Response<List<DesignationKaranatakaState>> response) {
                ArrayList<String> tempDesignationKaranataka = new ArrayList<String>();

                designationKaranatakaStates = response.body();
                for (int i = 0; i < designationKaranatakaStates.size(); i++) {
                    tempDesignationKaranataka.add(designationKaranatakaStates.get(i).getDesignation());
                }


                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(KaranatakaStateDepartmentPrimarySchoolSignUpActivity.this, android.R.layout.simple_spinner_item, tempDesignationKaranataka); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerDesignationKarnataka.setAdapter(spinnerArrayAdapter);

            }

            @Override
            public void onFailure(Call<List<DesignationKaranatakaState>> call, Throwable t) {

            }
        });

        spinnerDesignationKarnataka.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, final View view, int position, long id) {
//                String designation=designationKaranatakaStates.get(position).getDesignation();
                Call<List<SubjectKaranatakaState>> callDesignation = AppController.getInstance().apiInterface.getSubjectKaranataka(designationKaranatakaStates.get(position).getId() + "");
                callDesignation.enqueue(new Callback<List<SubjectKaranatakaState>>() {
                    @Override
                    public void onResponse(Call<List<SubjectKaranatakaState>> call, Response<List<SubjectKaranatakaState>> response) {
                        ArrayList<String> tempSubject = new ArrayList<String>();
                        subjectKaranatakaStates = response.body();
                        for (int i = 0; i < subjectKaranatakaStates.size(); i++) {

                            tempSubject.add(subjectKaranatakaStates.get(i).getSubject());
                        }

                        if (subjectKaranatakaStates.isEmpty()) {
                            txtSubjectKaranatakaPrimary.setVisibility(View.GONE);
                            spinnerSubjectKaranatakaPrimary.setVisibility(View.GONE);

                        } else {
                            txtSubjectKaranatakaPrimary.setVisibility(View.VISIBLE);
                            spinnerSubjectKaranatakaPrimary.setVisibility(View.VISIBLE);
                        }
                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(KaranatakaStateDepartmentPrimarySchoolSignUpActivity.this, android.R.layout.simple_spinner_item, tempSubject); //selected item will look like a spinner set from XML
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerSubjectKaranatakaPrimary.setAdapter(spinnerArrayAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<SubjectKaranatakaState>> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}



