
package com.example.om.mutualtransferdemo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WbTraned {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("traned")
    @Expose
    private String traned;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTraned() {
        return traned;
    }

    public void setTraned(String traned) {
        this.traned = traned;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
