package com.bijakpay.member.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agustinaindah on 30/08/2017.
 */

public class ItemMaterial implements Serializable {

    @SerializedName("material_id")
    @Expose
    private Integer materialId;
    @SerializedName("material_title")
    @Expose
    private String materialTitle;
    @SerializedName("material_filename")
    @Expose
    private String materialFilename;
    @SerializedName("material_privilege")
    @Expose
    private String materialPrivilege;
    @SerializedName("material_desc")
    @Expose
    private String materialDesc;

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getMaterialTitle() {
        return materialTitle;
    }

    public void setMaterialTitle(String materialTitle) {
        this.materialTitle = materialTitle;
    }

    public String getMaterialFilename() {
        return materialFilename;
    }

    public void setMaterialFilename(String materialFilename) {
        this.materialFilename = materialFilename;
    }

    public String getMaterialPrivilege() {
        return materialPrivilege;
    }

    public void setMaterialPrivilege(String materialPrivilege) {
        this.materialPrivilege = materialPrivilege;
    }

    public String getMaterialDesc() {
        return materialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }
}
