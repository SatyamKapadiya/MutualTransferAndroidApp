
package com.example.om.mutualtransferdemo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DistrictKarnatakaPrimarySchool {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("districts")
    @Expose
    private String districts;
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

    public String getDistricts() {
        return districts;
    }

    public void setDistricts(String districts) {
        this.districts = districts;
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
