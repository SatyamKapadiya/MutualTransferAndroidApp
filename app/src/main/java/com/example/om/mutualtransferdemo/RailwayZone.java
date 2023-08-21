
package com.example.om.mutualtransferdemo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RailwayZone {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("railway_zone_name")
    @Expose
    private String railwayZoneName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRailwayZoneName() {
        return railwayZoneName;
    }

    public void setRailwayZoneName(String railwayZoneName) {
        this.railwayZoneName = railwayZoneName;
    }

}
