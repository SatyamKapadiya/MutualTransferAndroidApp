
package com.example.om.mutualtransferdemo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DepartmentGradPay {

    @SerializedName("railwayDepartment")
    @Expose
    private List<RailwayDepartment> railwayDepartment = null;
    @SerializedName("gradPay")
    @Expose
    private List<GradPay> gradPay = null;
    @SerializedName("category")
    @Expose
    private List<Category> category = null;

    public List<RailwayDepartment> getRailwayDepartment() {
        return railwayDepartment;
    }

    public void setRailwayDepartment(List<RailwayDepartment> railwayDepartment) {
        this.railwayDepartment = railwayDepartment;
    }

    public List<GradPay> getGradPay() {
        return gradPay;
    }

    public void setGradPay(List<GradPay> gradPay) {
        this.gradPay = gradPay;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

}
