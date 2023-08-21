
package com.example.om.mutualtransferdemo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DesignationKaranatakaState {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("school_id")
    @Expose
    private String schoolId;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

}
