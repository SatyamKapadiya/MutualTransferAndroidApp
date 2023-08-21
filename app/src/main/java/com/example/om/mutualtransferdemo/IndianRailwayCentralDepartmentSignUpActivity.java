package com.example.om.mutualtransferdemo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.digits.sdk.android.Digits;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.layout.simple_dropdown_item_1line;
import static android.R.layout.simple_spinner_dropdown_item;
import static android.R.layout.simple_spinner_item;

public class IndianRailwayCentralDepartmentSignUpActivity extends AppCompatActivity {

    TextView txtCategoryIndianRailwayCentral,txtDepartmentIndianRailwayCentral,txtGradePayIndianRailwayCentral,txtBasicPayIndianRailwayCentral,
             txtAppointedDateIndianRailwayCentral,txtDesignationIndianRailwayCentral,txtRailwayZoneIndianRailwayCentral,txtRailwayDivisionIndianRailwayCentral;
    Spinner spinnerCategoryIndianRailwayCentral,spinnerDepartmentIndianRailwayCentral,spinnerGradePayIndianRailwayCentral,spinnerDesignationIndianRailwayCentral,
            spinnerRailwayZoneIndianRailwayCentral,spinnerRailwayDivisionIndianRailwayCentral;
    public static EditText edtBasicPayIndianRailwayCentral,edtAppointedDateIndianRailwayCentral;
    Button btnNextIndianRailwayCentralSignUpActivity;

    DepartmentGradPay departmentGradPays;

    List<RailwayZone> railwayZones;
    List<RailwayDivision> railwayDivision;
    List<RailwayDesignation> railwayDesignations;

    public static final String MyPERF = "myper";
    public SharedPreferences sharedPreferences;

    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indian_railway_central_department_signup);

        spinnerCategoryIndianRailwayCentral= (Spinner) findViewById(R.id.spinnerCategoryIndianRailwayCentral);
        spinnerDepartmentIndianRailwayCentral= (Spinner) findViewById(R.id.spinnerDepartmentIndianRailwayCentral);
        spinnerGradePayIndianRailwayCentral= (Spinner) findViewById(R.id.spinnerGradePayIndianRailwayCentral);
        spinnerDesignationIndianRailwayCentral= (Spinner) findViewById(R.id.spinnerDesignationIndianRailwayCentral);
        spinnerRailwayZoneIndianRailwayCentral= (Spinner) findViewById(R.id.spinnerRailwayZoneIndianRailwayCentral);
        spinnerRailwayDivisionIndianRailwayCentral= (Spinner) findViewById(R.id.spinnerRailwayDivisionIndianRailwayCentral);

        edtBasicPayIndianRailwayCentral= (EditText) findViewById(R.id.edtBasicPayIndianRailwayCentral);
        edtAppointedDateIndianRailwayCentral= (EditText) findViewById(R.id.edtAppointedDateIndianRailwayCentral);

        btnNextIndianRailwayCentralSignUpActivity= (Button) findViewById(R.id.btnNextIndianRailwayCentralSignUpActivity);

        edtAppointedDateIndianRailwayCentral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getFragmentManager(), "hm");
            }
        });

        btnNextIndianRailwayCentralSignUpActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(edtBasicPayIndianRailwayCentral.getText().toString().length()==0 && edtBasicPayIndianRailwayCentral.getText().toString().isEmpty())
//                {
//                    edtBasicPayIndianRailwayCentral.setError("please enter your BasicPay.");
//                    edtBasicPayIndianRailwayCentral.requestFocus();
//                }
//
//                if(edtAppointedDateIndianRailwayCentral.getText().length()==0 && edtAppointedDateIndianRailwayCentral.getText().toString().isEmpty())
//                {
//                    edtAppointedDateIndianRailwayCentral.setError("Please enter Your Date..");
//                    edtAppointedDateIndianRailwayCentral.requestFocus();
//                }

                AlertDialog.Builder builder = new AlertDialog.Builder(IndianRailwayCentralDepartmentSignUpActivity.this);
                builder.setTitle("Alert!");
                builder.setMessage("Provide correct information. This can not be edited.");
                builder.setPositiveButton("PROCEED", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(edtBasicPayIndianRailwayCentral.getText().toString().length()==0 && edtBasicPayIndianRailwayCentral.getText().toString().isEmpty())
                        {
                            edtBasicPayIndianRailwayCentral.setError("please enter your BasicPay.");
                            edtBasicPayIndianRailwayCentral.requestFocus();
                        }

                       else if(edtAppointedDateIndianRailwayCentral.getText().length()==0 && edtAppointedDateIndianRailwayCentral.getText().toString().isEmpty())
                        {
                            edtAppointedDateIndianRailwayCentral.setError("Please enter Your Date..");
                            edtAppointedDateIndianRailwayCentral.requestFocus();
                        }

                        else {
                            sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            SharedPreferences prefs = getSharedPreferences(MyPERF, MODE_PRIVATE);
//                            String basicPay = prefs.getString("basicPay", "basicPay");
//                            String appoinmentDate = prefs.getString("", "appoinmentDate");
                            editor.putInt("category", departmentGradPays.getCategory().get(spinnerCategoryIndianRailwayCentral.getSelectedItemPosition()).getId());
                            editor.putString("railwayDepartment", departmentGradPays.getRailwayDepartment().get(spinnerDepartmentIndianRailwayCentral.getSelectedItemPosition()).getId()+"");
                            editor.putInt("gradPay", departmentGradPays.getGradPay().get(spinnerGradePayIndianRailwayCentral.getSelectedItemPosition()).getId());
                            editor.putString("designation", spinnerDesignationIndianRailwayCentral.getSelectedItem().toString());
//                            editor.putString("railwayZone", spinnerRailwayZoneIndianRailwayCentral.getSelectedItem().toString());
//                            editor.putString("railwayDivision", spinnerRailwayDivisionIndianRailwayCentral.getSelectedItem().toString());
                            editor.putString("appoinmentDate",edtAppointedDateIndianRailwayCentral.getText().toString());
                            editor.putString("basicPay",edtBasicPayIndianRailwayCentral.getText().toString());
                            editor.putString("railwayZone",railwayZones.get(spinnerRailwayZoneIndianRailwayCentral.getSelectedItemPosition()).getId()+"");
                            editor.putString("railwayDivision",railwayDivision.get(spinnerRailwayDivisionIndianRailwayCentral.getSelectedItemPosition()).getId()+"");
                            editor.apply();
                            startActivity(new Intent(IndianRailwayCentralDepartmentSignUpActivity.this, GoToIndianRailwayCentralSignUpActivity.class));
                            finish();
//                        Toast.makeText(IndianRailwayCentralDepartmentSignUpActivity.this,"Succesfully....",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                builder.setNegativeButton("EDIT", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        Call<List<RailwayDesignation>> callDesignation = AppController.getInstance().getApiInterface().getRailwayDesignation("3");
        callDesignation.enqueue(new Callback<List<RailwayDesignation>>() {
            @Override
            public void onResponse(Call<List<RailwayDesignation>> call, Response<List<RailwayDesignation>> response) {
                ArrayList<String> tempRailwayDesignation = new ArrayList<String>();

                railwayDesignations = response.body();
                for (int i = 0; i < railwayDesignations.size(); i++) {
                    tempRailwayDesignation.add(railwayDesignations.get(i).getDesignation());
                }

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(IndianRailwayCentralDepartmentSignUpActivity.this, android.R.layout.simple_spinner_item, tempRailwayDesignation); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerDesignationIndianRailwayCentral.setAdapter(spinnerArrayAdapter);

            }

            @Override
            public void onFailure(Call<List<RailwayDesignation>> call, Throwable t) {

            }
        });

            spinnerRailwayZoneIndianRailwayCentral.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { Call<List<RailwayDivision>> callRailwayDivision = AppController.getInstance().getApiInterface().getRailwayDivision(railwayZones.get(position).getId().toString()+"");
                    callRailwayDivision.enqueue(new Callback<List<RailwayDivision>>() {
                        @Override
                        public void onResponse(Call<List<RailwayDivision>> call, Response<List<RailwayDivision>> response) {
                            ArrayList<String> tempRailwayDivision = new ArrayList<String>();
                            railwayDivision = response.body();
                            for (int i = 0; i < railwayDivision.size(); i++) {
                                tempRailwayDivision.add(railwayDivision.get(i).getDivision());
                            }

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(IndianRailwayCentralDepartmentSignUpActivity.this, android.R.layout.simple_spinner_item, tempRailwayDivision); //selected item will look like a spinner set from XML
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerRailwayDivisionIndianRailwayCentral.setAdapter(spinnerArrayAdapter);
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

            Call<List<RailwayZone>> call = AppController.getInstance().getApiInterface().getRailwayZone();
            call.enqueue(new Callback<List<RailwayZone>>() {
            @Override
            public void onResponse(Call<List<RailwayZone>> call, Response<List<RailwayZone>> response) {
                ArrayList<String> tempRailwayZone = new ArrayList<String>();

                railwayZones = response.body();
                for (int i = 0; i < railwayZones.size(); i++) {
                    tempRailwayZone.add(railwayZones.get(i).getRailwayZoneName());
                }


                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(IndianRailwayCentralDepartmentSignUpActivity.this, android.R.layout.simple_spinner_item, tempRailwayZone); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerRailwayZoneIndianRailwayCentral.setAdapter(spinnerArrayAdapter);

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

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(IndianRailwayCentralDepartmentSignUpActivity.this, android.R.layout.simple_spinner_item, tempRailwayDivision); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerRailwayDivisionIndianRailwayCentral.setAdapter(spinnerArrayAdapter);

            }

            @Override
            public void onFailure(Call<List<RailwayDivision>> call, Throwable t) {

            }
        });

        Call<DepartmentGradPay> callGrad=AppController.getInstance().getApiInterface().getDepartmentGradPay();
        callGrad.enqueue(new Callback<DepartmentGradPay>() {
            @Override
            public void onResponse(Call<DepartmentGradPay> call, Response<DepartmentGradPay> response) {
                ArrayList<String> tempCategory = new ArrayList<>();
                ArrayList<String> tempGradPay= new ArrayList<>();
                ArrayList<String> tempRailwayDepartment= new ArrayList<>();
                departmentGradPays= response.body();

                for (int i = 0; i < departmentGradPays.getCategory().size(); i++) {
                    tempCategory.add(departmentGradPays.getCategory().get(i).getCategory());
                }
                for (int i = 0; i < departmentGradPays.getGradPay().size(); i++) {
                    tempGradPay.add(departmentGradPays.getGradPay().get(i).getGradePay());
                }
                for (int i = 0; i < departmentGradPays.getRailwayDepartment().size(); i++) {
                    tempRailwayDepartment.add(departmentGradPays.getRailwayDepartment().get(i).getDepartmentName());
                }

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(IndianRailwayCentralDepartmentSignUpActivity.this, simple_spinner_item, tempCategory); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCategoryIndianRailwayCentral.setAdapter(spinnerArrayAdapter);

                ArrayAdapter<String> spinnerArrayAdapterGradPay=new ArrayAdapter<String>(IndianRailwayCentralDepartmentSignUpActivity.this, android.R.layout.simple_spinner_item,tempGradPay);
                spinnerArrayAdapterGradPay.setDropDownViewResource(simple_spinner_dropdown_item);
                spinnerGradePayIndianRailwayCentral.setAdapter(spinnerArrayAdapterGradPay);

                ArrayAdapter<String> spinnerArrayAdapterRailwayDepartment=new ArrayAdapter<String>(IndianRailwayCentralDepartmentSignUpActivity.this,simple_spinner_item,tempRailwayDepartment);
                spinnerArrayAdapterRailwayDepartment.setDropDownViewResource(simple_spinner_dropdown_item);
                spinnerDepartmentIndianRailwayCentral.setAdapter(spinnerArrayAdapterRailwayDepartment);

            }

            @Override
            public void onFailure(Call<DepartmentGradPay> call, Throwable t) {

            }
        });

    }
    public class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar chooseDate = Calendar.getInstance();

            int year = chooseDate.get(Calendar.YEAR);
            int month = chooseDate.get(Calendar.MONTH);
            int day = chooseDate.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);

        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            String year1 = String.valueOf(year);
            String month1 = String.valueOf(month + 1);
            String day1 = String.valueOf(day);
            edtAppointedDateIndianRailwayCentral.setText(year1 + "-" + month1 + "-" + day1);
        }
    }

}

//public class DatePickerFragment extends DialogFragment
//        implements DatePickerDialog.OnDateSetListener {
//
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//
//        final Calendar chooseDate = Calendar.getInstance();
//
//        int year = chooseDate.get(Calendar.YEAR);
//        int month = chooseDate.get(Calendar.MONTH);
//        int day = chooseDate.get(Calendar.DAY_OF_MONTH);
//        return new DatePickerDialog(getActivity(), this, year, month, day);
//
//    }
//
//    public void onDateSet(DatePicker view, int year, int month, int day) {
//        String year1 = String.valueOf(year);
//        String month1 = String.valueOf(month + 1);
//        String day1 = String.valueOf(day);
//        .setText(year1 + "-" + month1 + "-" + day1);
//    }
//}


