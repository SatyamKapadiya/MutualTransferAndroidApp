
package com.example.om.mutualtransferdemo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HighSchoolWestBangal {

    @SerializedName("wbCategory")
    @Expose
    private List<WbCategory> wbCategory = null;
    @SerializedName("wbPostCategory")
    @Expose
    private List<WbPostCategory> wbPostCategory = null;
    @SerializedName("wbComission")
    @Expose
    private List<WbComission> wbComission = null;
    @SerializedName("wbSchoolType")
    @Expose
    private List<WbSchoolType> wbSchoolType = null;
    @SerializedName("wbTraned")
    @Expose
    private List<WbTraned> wbTraned = null;

    public List<WbCategory> getWbCategory() {
        return wbCategory;
    }

    public void setWbCategory(List<WbCategory> wbCategory) {
        this.wbCategory = wbCategory;
    }

    public List<WbPostCategory> getWbPostCategory() {
        return wbPostCategory;
    }

    public void setWbPostCategory(List<WbPostCategory> wbPostCategory) {
        this.wbPostCategory = wbPostCategory;
    }

    public List<WbComission> getWbComission() {
        return wbComission;
    }

    public void setWbComission(List<WbComission> wbComission) {
        this.wbComission = wbComission;
    }

    public List<WbSchoolType> getWbSchoolType() {
        return wbSchoolType;
    }

    public void setWbSchoolType(List<WbSchoolType> wbSchoolType) {
        this.wbSchoolType = wbSchoolType;
    }

    public List<WbTraned> getWbTraned() {
        return wbTraned;
    }

    public void setWbTraned(List<WbTraned> wbTraned) {
        this.wbTraned = wbTraned;
    }

}
