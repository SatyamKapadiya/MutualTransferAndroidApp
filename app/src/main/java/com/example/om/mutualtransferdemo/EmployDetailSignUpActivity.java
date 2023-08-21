package com.example.om.mutualtransferdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployDetailSignUpActivity extends AppCompatActivity {

    TextView txtCentralEmpSignUpActivity,txtDepartmentEmpSignUpActvity,txtStateEmpSignUpActivity;
    Spinner spinnerCentralEmpSignUpActivity,spinnerDepartmentEmpSignUpActivity,spinnerStateEmpSignUpActivity;
    Button btnNextEmpSignUpActivity;
    List<StateName> states;
    List<CentralDepartment> centralDepartments;
    List<StateGovernDepartment> stateGovernDepartment;

    public static final String MyPERF = "myper";
    public SharedPreferences sharedPreferences;

    String[] CentralOrState={"Central Government","State Government"};
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employ_detail_sign_up);


        txtCentralEmpSignUpActivity= (TextView) findViewById(R.id.txtCentralEmpSignUpActivity);
        txtDepartmentEmpSignUpActvity= (TextView) findViewById(R.id.txtDepartmentEmpSignUpActvity);
        txtStateEmpSignUpActivity= (TextView) findViewById(R.id.txtStateEmpSignUpActivity);


        spinnerCentralEmpSignUpActivity= (Spinner) findViewById(R.id.spinnerCentralEmpSignUpActivity);
        spinnerDepartmentEmpSignUpActivity= (Spinner) findViewById(R.id.spinnerDepartmentEmpSignUpActivity);
        spinnerStateEmpSignUpActivity= (Spinner) findViewById(R.id.spinnerStateEmpSignUpActivity);
//        spinnerStateEmpSignUpActivity.setVisibility(TRIM_MEMORY_BACKGROUND);

        btnNextEmpSignUpActivity= (Button) findViewById(R.id.btnNextEmpSignUpActivity);


        btnNextEmpSignUpActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EmployDetailSignUpActivity.this);
                builder.setTitle("Alert!");
                builder.setMessage("Provide correct information. This can not be edited.");
                builder.setPositiveButton("PROCEED", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        String department=spinnerDepartmentEmpSignUpActivity.getSelectedItem().toString();
//                        startActivity(new Intent(EmployDetailSignUpActivity.this,DepartmentSignUpActivity.class));
                        if (spinnerCentralEmpSignUpActivity.getSelectedItemPosition()==0) {
                            editor.putString("department_id",centralDepartments.get(spinnerDepartmentEmpSignUpActivity.getSelectedItemPosition()).getId()+"");
                            if (centralDepartments.get(spinnerDepartmentEmpSignUpActivity.getSelectedItemPosition()).getId() == 43) {
                                Intent intent=new Intent(EmployDetailSignUpActivity.this,IndianRailwayCentralDepartmentSignUpActivity.class);
                                intent.putExtra("department_id",centralDepartments.get(spinnerDepartmentEmpSignUpActivity.getSelectedItemPosition()).getId()+"");
                                startActivity(intent);
                                finish();
                            } else {
                                Intent intent=new Intent(EmployDetailSignUpActivity.this,OtherCentralDepartmentSignUpActivity.class);
                                intent.putExtra("department_id",centralDepartments.get(spinnerDepartmentEmpSignUpActivity.getSelectedItemPosition()).getId()+"");
                                startActivity(intent);
                                finish();
                            }
                        }else {
                            String selectedPosition = spinnerCentralEmpSignUpActivity.getSelectedItem().toString();
                            String state = spinnerStateEmpSignUpActivity.getSelectedItem().toString();
                            String department = spinnerDepartmentEmpSignUpActivity.getSelectedItem().toString();
                            editor.putString("CentralOrState", selectedPosition);
                            editor.putString("state",states.get(spinnerStateEmpSignUpActivity.getSelectedItemPosition()).getState()+"");
//                            editor.putString("Department",centralDepartments.get(spinnerDepartmentEmpSignUpActivity.getSelectedItemPosition()).getId()+"");
                            editor.putString("Department_state",stateGovernDepartment.get(spinnerDepartmentEmpSignUpActivity.getSelectedItemPosition()).getId()+"");
                            editor.putString("department_id",stateGovernDepartment.get(spinnerDepartmentEmpSignUpActivity.getSelectedItemPosition()).getId()+"");
//                            editor.putString("Department_id",);
                            if (stateGovernDepartment.get(spinnerDepartmentEmpSignUpActivity.getSelectedItemPosition()).getId() == 783) {
                                Intent intent=new Intent(EmployDetailSignUpActivity.this, KaranatakaStateDepartmentPrimarySchoolSignUpActivity.class);
                                intent.putExtra("department_id",stateGovernDepartment.get(spinnerDepartmentEmpSignUpActivity.getSelectedItemPosition()).getId()+"");
                                startActivity(intent);
                                finish();

                            } else if (stateGovernDepartment.get(spinnerDepartmentEmpSignUpActivity.getSelectedItemPosition()).getId() == 1788) {
                                Intent intent = new Intent(EmployDetailSignUpActivity.this, WestBangalCommonSignUpActivity.class);
                                intent.putExtra("department_id",stateGovernDepartment.get(spinnerDepartmentEmpSignUpActivity.getSelectedItemPosition()).getId()+"");
                                startActivity(intent);
                                finish();
                            } else {
                                Intent intent = new Intent(EmployDetailSignUpActivity.this, OtherStateDepartmentSignUpActivity.class);
                                intent.putExtra("department_id",stateGovernDepartment.get(spinnerDepartmentEmpSignUpActivity.getSelectedItemPosition()).getId()+"");
                                intent.putExtra("state", state);
                                startActivity(intent);
                                finish();
                            }
                        }
                        editor.apply();
             }
                });
                builder.setNegativeButton("EDIT", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        spinnerStateEmpSignUpActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    Call<List<StateGovernDepartment>> call = AppController.getInstance().getApiInterface().getGDepartmentName(states.get(position).getId().toString()+"");
                    call.enqueue(new Callback<List<StateGovernDepartment>>() {
                        @Override
                        public void onResponse(Call<List<StateGovernDepartment>> call, Response<List<StateGovernDepartment>> response) {
                            ArrayList<String> tempState = new ArrayList<String>();

                            stateGovernDepartment = response.body();
                            for (int i = 0; i < stateGovernDepartment.size(); i++) {
                                tempState.add(stateGovernDepartment.get(i).getDepartmentName());
                            }

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(EmployDetailSignUpActivity.this, android.R.layout.simple_spinner_item, tempState); //selected item will look like a spinner set from XML
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerDepartmentEmpSignUpActivity.setAdapter(spinnerArrayAdapter);

                        }

                        @Override
                        public void onFailure(Call<List<StateGovernDepartment>> call, Throwable t) {

                        }
                    });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerCentralEmpSignUpActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id)
            {

                if(position == 0) {
                    spinnerStateEmpSignUpActivity.setVisibility(View.GONE);
                    txtStateEmpSignUpActivity.setVisibility(View.GONE);
                    Call<List<CentralDepartment>> call=AppController.getInstance().getApiInterface().getDepartmentName();
                    call.enqueue(new Callback<List<CentralDepartment>>() {
                        @Override
                        public void onResponse(Call<List<CentralDepartment>> call, Response<List<CentralDepartment>> response) {
                            ArrayList<String> tempCentral = new ArrayList<String>();
                            centralDepartments=response.body();
                            for (int i = 0; i < centralDepartments.size(); i++) {
                                tempCentral.add(centralDepartments.get(i).getDepartmentName());
                            }

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(EmployDetailSignUpActivity.this, android.R.layout.simple_spinner_item, tempCentral); //selected item will look like a spinner set from XML
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerDepartmentEmpSignUpActivity.setAdapter(spinnerArrayAdapter);

                        }
                        @Override
                        public void onFailure(Call<List<CentralDepartment>> call, Throwable t) {

                        }
                    });
                }
                else {
                    txtStateEmpSignUpActivity.setVisibility(View.VISIBLE);
                    spinnerStateEmpSignUpActivity.setVisibility(View.VISIBLE);
                    Call<List<StateName>> call = AppController.getInstance().getApiInterface().getStateName();
                    call.enqueue(new Callback<List<StateName>>() {
                        @Override
                        public void onResponse(Call<List<StateName>> call, Response<List<StateName>> response) {
                            ArrayList<String> temp = new ArrayList<String>();

                            states = response.body();
                            for (int i = 0; i < states.size(); i++) {
                                temp.add(states.get(i).getState());
                            }

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(EmployDetailSignUpActivity.this, android.R.layout.simple_spinner_item, temp); //selected item will look like a spinner set from XML
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerStateEmpSignUpActivity.setAdapter(spinnerArrayAdapter);
                        }

                        @Override
                        public void onFailure(Call<List<StateName>> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(EmployDetailSignUpActivity.this, android.R.layout.simple_spinner_item,CentralOrState); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCentralEmpSignUpActivity.setAdapter(spinnerArrayAdapter);
    }
}
