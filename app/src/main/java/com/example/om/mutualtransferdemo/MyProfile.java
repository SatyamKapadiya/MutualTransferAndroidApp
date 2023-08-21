package com.example.om.mutualtransferdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by om on 3/10/2017.
 */
public class MyProfile extends Fragment {

    ImageView imgProfileActivity;
    TextView txtNameProfileActivity,txtDesignationName,txtDepartmentNameProfile,txtMobileNoProfile;

    public static final String MyPERF = "myper";
    public SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragement_myprofile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        SharedPreferences prefs=getActivity().getSharedPreferences(MyPERF, Context.MODE_PRIVATE);

        imgProfileActivity= (ImageView) view.findViewById(R.id.imgProfileActivity);
        txtNameProfileActivity= (TextView) view.findViewById(R.id.txtNameProfileActivity);
        txtDesignationName= (TextView) view.findViewById(R.id.txtDesignationName);
        txtDepartmentNameProfile= (TextView) view.findViewById(R.id.txtDepartmentNameProfile);
        txtMobileNoProfile= (TextView) view.findViewById(R.id.txtMobileNoProfile);
        sharedPreferences = getActivity().getSharedPreferences(MyPERF, Context.MODE_PRIVATE);

        String picUrl = prefs.getString("picture", "picture");
        String name = prefs.getString("name", "name");
        String phoneNumber=prefs.getString("phoneNumber","phoneNumber");
        String department= prefs.getString("department_id","department_id");
        String designation=prefs.getString("designation","designation");
        Log.d("picUrl", "onViewCreated: " + picUrl + " " + name + "" + phoneNumber + "" + department + "" + designation);
        Picasso.with(getActivity()).load(picUrl).transform(new CircleTransform()).error(R.mipmap.ic_launcher).into(imgProfileActivity);
        txtNameProfileActivity.setText(name);
        txtDepartmentNameProfile.setText(department);
        txtDesignationName.setText(designation);
        txtMobileNoProfile.setText(phoneNumber);

    }




}
