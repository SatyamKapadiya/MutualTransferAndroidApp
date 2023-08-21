
package com.example.om.mutualtransferdemo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrimaryWestBangal {

    @SerializedName("category")
    @Expose
    private List<CategoryPrimary> category = null;
    @SerializedName("designation")
    @Expose
    private List<DesignationPrimary> designation = null;
    @SerializedName("qualification")
    @Expose
    private List<QualificationPrimary> qualification = null;
    @SerializedName("traned")
    @Expose
    private List<TranedPrimary> traned = null;

    public List<CategoryPrimary> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryPrimary> category) {
        this.category = category;
    }

    public List<DesignationPrimary> getDesignation() {
        return designation;
    }

    public void setDesignation(List<DesignationPrimary> designation) {
        this.designation = designation;
    }

    public List<QualificationPrimary> getQualification() {
        return qualification;
    }

    public void setQualification(List<QualificationPrimary> qualification) {
        this.qualification = qualification;
    }

    public List<TranedPrimary> getTraned() {
        return traned;
    }

    public void setTraned(List<TranedPrimary> traned) {
        this.traned = traned;
    }

}
