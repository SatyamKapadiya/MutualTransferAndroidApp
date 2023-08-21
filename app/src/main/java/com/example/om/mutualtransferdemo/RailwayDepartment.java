
package com.example.om.mutualtransferdemo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RailwayDepartment {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("department_name")
    @Expose
    private String departmentName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

}
