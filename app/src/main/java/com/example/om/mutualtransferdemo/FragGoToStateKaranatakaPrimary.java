package com.example.om.mutualtransferdemo;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragGoToStateKaranatakaPrimary extends Fragment {


    public FragGoToStateKaranatakaPrimary() {
        // Required empty public constructor
    }

    Button btnNextGoToStateKaranatakaPrimary;
    Spinner spinnerGoToChoiceDistrictKarnatakaPrimary;
    List<DistrictKarnatakaPrimarySchool> districtKarnatakaPrimarySchools;

    public final String MyPERF = "myper";
    public SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_go_to_state_karanataka_primary, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        spinnerGoToChoiceDistrictKarnatakaPrimary = (Spinner)view.findViewById(R.id.spinnerGoToChoiceDistrictKarnatakaPrimary);
        btnNextGoToStateKaranatakaPrimary = (Button)view.findViewById(R.id.btnNextGoToStateKaranatakaPrimary);

        btnNextGoToStateKaranatakaPrimary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getActivity().getSharedPreferences(MyPERF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                final String district = spinnerGoToChoiceDistrictKarnatakaPrimary.getSelectedItem().toString();
                editor.putString("District", district);
                editor.apply();
//                Log.d("errorData", "onClick: "+sharedPreferences.getString("name","piyu")+" " +sharedPreferences.getString("email","email")
//                    + " " +sharedPreferences.getString("phoneNumber", "invalid")
//                +" "+sharedPreferences.getInt("department_state",43)
//                        + " "+sharedPreferences.getString("avatar","avatar")
//                        + " " +sharedPreferences.getString("designation","designation")
//                        +" "+districtKarnatakaPrimarySchools.get(spinnerGoToChoiceDistrictKarnatakaPrimary.getSelectedItemPosition()).getId()
//                        + " "+sharedPreferences.getInt("School_Office_Selection",1)
//                        + " "+sharedPreferences.getInt("subject",123)
//                        + " "+sharedPreferences.getInt("appointedScheme",250)
//                );
                Call<ResponseBody> call = AppController.getInstance().getApiInterface().postKaranataka(
                        sharedPreferences.getString("name", "piyu"),
                        sharedPreferences.getString("email", "email"),
                        sharedPreferences.getString("phoneNumber", "invalid"),
                        "1",
                        783,
                        sharedPreferences.getString("avatar", "avatar"),
                        sharedPreferences.getString("designation", "designation"),
                        districtKarnatakaPrimarySchools.get(spinnerGoToChoiceDistrictKarnatakaPrimary.getSelectedItemPosition()).getId(),
                        "Karnataka",
                        sharedPreferences.getInt("School_Office_Selection", 1),
                        sharedPreferences.getString("subject", "NA"),
                        sharedPreferences.getInt("appointedScheme",1),
                        districtKarnatakaPrimarySchools.get(spinnerGoToChoiceDistrictKarnatakaPrimary.getSelectedItemPosition()).getId(),
                        1);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<okhttp3.ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String responseString = response.body().toString();
                            JSONObject jsonObject = new JSONObject(responseString);
                            sharedPreferences =getActivity().getSharedPreferences(MyPERF, Context.MODE_PRIVATE);

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("user_id", jsonObject.getString("user_id"));
                            editor.apply();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getActivity(),"succesfully.."+sharedPreferences.getString("user_id","user_id"),Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(),UserOptionActivity.class));
                        getActivity().finish();

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

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

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, tempDistrict); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerGoToChoiceDistrictKarnatakaPrimary.setAdapter(spinnerArrayAdapter);

            }

            @Override
            public void onFailure(Call<List<DistrictKarnatakaPrimarySchool>> call, Throwable t) {

            }
        });
    }
}
