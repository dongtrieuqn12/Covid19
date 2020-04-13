package vn.cns.covid19.model.config;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfigResponse {
    @SerializedName("OrgCode")
    @Expose
    private String orgCode;
    @SerializedName("canteenConfig")
    @Expose
    private KiotConfig canteenConfig;
    @SerializedName("locationId")
    @Expose
    private Object locationId;
    @SerializedName("samId")
    @Expose
    private String samId;
    @SerializedName("mcpConfig")
    @Expose
    private CMSConfig mcpConfig;
    @SerializedName("ConfigloadingDate")
    @Expose
    private String configloadingDate;

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Object getLocationId() {
        return locationId;
    }

    public void setLocationId(Object locationId) {
        this.locationId = locationId;
    }

    public String getSamId() {
        return samId;
    }

    public void setSamId(String samId) {
        this.samId = samId;
    }

    public String getConfigloadingDate() {
        return configloadingDate;
    }

    public void setConfigloadingDate(String configloadingDate) {
        this.configloadingDate = configloadingDate;
    }

    public KiotConfig getKiotConfig() {
        return canteenConfig;
    }

    public void setKiotConfig(KiotConfig canteenConfig) {
        this.canteenConfig = canteenConfig;
    }

    public CMSConfig getCMSConfig() {
        return mcpConfig;
    }

    public void setCMSConfig(CMSConfig mcpConfig) {
        this.mcpConfig = mcpConfig;
    }
}
