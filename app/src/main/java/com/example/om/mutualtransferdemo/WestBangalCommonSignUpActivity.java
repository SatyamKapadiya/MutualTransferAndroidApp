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

public class WestBangalCommonSignUpActivity extends AppCompatActivity {

    String[] CommonSchoolType={"Primary School","High School"};
    Spinner spinnerSchoolTypeWestBangalCommon;
    Button btnNextCommonSchoolTypeWestBangal;

    public final String MyPERF = "myper";
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_west_bangal_common_sign_up);
        sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);

        spinnerSchoolTypeWestBangalCommon= (Spinner) findViewById(R.id.spinnerSchoolTypeWestBangalCommon);
        btnNextCommonSchoolTypeWestBangal= (Button) findViewById(R.id.btnNextCommonSchoolTypeWestBangal);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(WestBangalCommonSignUpActivity.this, android.R.layout.simple_spinner_item,CommonSchoolType); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSchoolTypeWestBangalCommon.setAdapter(spinnerArrayAdapter);

        btnNextCommonSchoolTypeWestBangal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinnerSchoolTypeWestBangalCommon.getSelectedItemPosition()==0)
                {
                    sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("SchoolType",spinnerSchoolTypeWestBangalCommon.getSelectedItemPosition()+"");
                    editor.apply();
                    startActivity(new Intent(WestBangalCommonSignUpActivity.this,WestBengalPrimarySchoolStateDepartmentSignUpActivity.class));
                    finish();
                }
                else
                {
                    sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("SchoolType",spinnerSchoolTypeWestBangalCommon.getSelectedItemPosition()+"");
                    editor.apply();
                    startActivity(new Intent(WestBangalCommonSignUpActivity.this,WestBangalHighSchoolSignUpActivity.class));
                    finish();
                }
            }
        });


    }
}
