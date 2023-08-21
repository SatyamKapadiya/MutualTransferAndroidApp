
package com.example.om.mutualtransferdemo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StateName {

    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
