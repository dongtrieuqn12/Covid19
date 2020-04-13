package vn.cns.covid19.model.config;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CMSConfig {
    @SerializedName("transactionToken")
    @Expose
    private String transactionToken;
    @SerializedName("attendanceToken")
    @Expose
    private String attendanceToken;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("hostAMS")
    @Expose
    private String hostAMS;
    @SerializedName("merchantId")
    @Expose
    private String merchantId;
    @SerializedName("host")
    @Expose
    private String host;
    @SerializedName("terminalId")
    @Expose
    private String terminalId;
    @SerializedName("merchantName")
    @Expose
    private String merchantName;

    public String getTransactionToken() {
        return transactionToken;
    }

    public void setTransactionToken(String transactionToken) {
        this.transactionToken = transactionToken;
    }

    public String getAttendanceToken() {
        return attendanceToken;
    }

    public void setAttendanceToken(String attendanceToken) {
        this.attendanceToken = attendanceToken;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHostAMS() {
        return hostAMS;
    }

    public void setHostAMS(String hostAMS) {
        this.hostAMS = hostAMS;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

}
