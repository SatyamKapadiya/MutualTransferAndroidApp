package com.example.om.mutualtransferdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
 * Created by om on 3/10/2017.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    Context context;
    private String Fragment[] = {"MyProfile", "EditChoice"};
    List<CentralDepartment> centralDepartments;
    List<StateGovernDepartment> stateGovernDepartment;
    SharedPreferences sharedPreferences;
    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context=context;
        sharedPreferences = context.getSharedPreferences("myper",Context.MODE_PRIVATE);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MyProfile();
            case 1:
                if(sharedPreferences.getString("department_id","43").equalsIgnoreCase("43")) {
                    return new FragGoToIndianRailwayCentralsignUp();
                }
                else if(sharedPreferences.getString("department_id","783").equalsIgnoreCase("783"))
                {
                    return new FragGoToStateKaranatakaPrimary();
                }
                else if(sharedPreferences.getString("department_id","1788").equalsIgnoreCase("1788"))
                {
                    return new FragGoToWestBangalSignUp();
                }
                else
                {
                    return new FragGoToIndianRailwayOtherSignUp();
                }
//                    if (centralDepartments.get(position).getId()==43) {
//                        return new FragGoToIndianRailwayCentralsignUp();
//                    }
//                    else
//                    {
//                        return new FragGoToIndianRailwayOtherSignUp();
//                    }
//                }
//                else
//                {
//                    if(stateGovernDepartment.get(position).getId()==783)
//                    {
//                        return new FragGoToStateKaranatakaPrimary();
//                    }
//                    else if(stateGovernDepartment.get(position).getId()==1788)
//                    {
//                        return new FragGoToWestBangalSignUp();
//                    }
//                    else
//                    {
//                        return new FragGoToOtherState();
//                    }

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return Fragment.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Fragment[position];
    }

}

