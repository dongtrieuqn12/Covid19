package vn.cns.covid19.model.config;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KiotConfig {
    @SerializedName("Retailer")
    @Expose
    private String retailer;
    @SerializedName("priceBookId")
    @Expose
    private String priceBookId;
    @SerializedName("branchId")
    @Expose
    private String branchId;
    @SerializedName("client_secret")
    @Expose
    private String clientSecret;
    @SerializedName("client_id")
    @Expose
    private String clientId;

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public String getPriceBookId() {
        return priceBookId;
    }

    public void setPriceBookId(String priceBookId) {
        this.priceBookId = priceBookId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
