
package com.example.om.mutualtransferdemo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("primaryResult")
    @Expose
    private List<PrimaryResult> primaryResult = null;

    public List<PrimaryResult> getPrimaryResult() {
        return primaryResult;
    }

    public void setPrimaryResult(List<PrimaryResult> primaryResult) {
        this.primaryResult = primaryResult;
    }

}
