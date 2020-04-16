package vn.cns.covid19.model.orders;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vn.cns.covid19.BR;

public class OrderData extends BaseObservable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("purchaseDate")
    @Expose
    private String purchaseDate;
    @SerializedName("branchId")
    @Expose
    private Integer branchId;
    @SerializedName("branchName")
    @Expose
    private String branchName;
    @SerializedName("soldById")
    @Expose
    private Integer soldById;
    @SerializedName("soldByName")
    @Expose
    private String soldByName;
    @SerializedName("customerId")
    @Expose
    private Integer customerId;
    @SerializedName("customerCode")
    @Expose
    private String customerCode;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("total")
    @Expose
    private Double total;
    @SerializedName("totalPayment")
    @Expose
    private Double totalPayment;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("statusValue")
    @Expose
    private String statusValue;
    @SerializedName("retailerId")
    @Expose
    private Integer retailerId;
    @SerializedName("usingCod")
    @Expose
    private Boolean usingCod;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("orderDetails")
    @Expose
    private List<OrderDetail> orderDetails = null;
    @SerializedName("modifiedDate")
    @Expose
    private String modifiedDate;
    @SerializedName("SaleChannelId")
    @Expose
    private Integer saleChannelId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Bindable
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        notifyPropertyChanged(vn.cns.covid19.BR.code);
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Integer getSoldById() {
        return soldById;
    }

    public void setSoldById(Integer soldById) {
        this.soldById = soldById;
    }

    public String getSoldByName() {
        return soldByName;
    }

    public void setSoldByName(String soldByName) {
        this.soldByName = soldByName;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    @Bindable
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
        notifyPropertyChanged(vn.cns.covid19.BR.customerName);
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }

    @Bindable
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
        notifyPropertyChanged(vn.cns.covid19.BR.status);
    }

    @Bindable
    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
        notifyPropertyChanged(vn.cns.covid19.BR.statusValue);
    }

    public Integer getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(Integer retailerId) {
        this.retailerId = retailerId;
    }

    public Boolean getUsingCod() {
        return usingCod;
    }

    public void setUsingCod(Boolean usingCod) {
        this.usingCod = usingCod;
    }

    @Bindable
    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
        notifyPropertyChanged(vn.cns.covid19.BR.createdDate);
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getSaleChannelId() {
        return saleChannelId;
    }

    public void setSaleChannelId(Integer saleChannelId) {
        this.saleChannelId = saleChannelId;
    }

}
