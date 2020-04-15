package vn.cns.covid19.model.updateOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vn.cns.covid19.model.orders.OrderDetail;

public class OrderUpdateRequest {
    @SerializedName("purchaseDate")
    @Expose
    private String purchaseDate;
    @SerializedName("branchId")
    @Expose
    private Integer branchId;
    @SerializedName("soldById")
    @Expose
    private Integer soldById;
    @SerializedName("cashierId")
    @Expose
    private Integer cashierId;
    @SerializedName("discount")
    @Expose
    private Integer discount;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("totalPayment")
    @Expose
    private Integer totalPayment;
    @SerializedName("accountId")
    @Expose
    private Integer accountId;
    @SerializedName("makeInvoice")
    @Expose
    private Boolean makeInvoice;
    @SerializedName("saleChannelId")
    @Expose
    private Integer saleChannelId;
    @SerializedName("orderDetails")
    @Expose
    private List<OrderDetail> orderDetails = null;

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

    public Integer getSoldById() {
        return soldById;
    }

    public void setSoldById(Integer soldById) {
        this.soldById = soldById;
    }

    public Integer getCashierId() {
        return cashierId;
    }

    public void setCashierId(Integer cashierId) {
        this.cashierId = cashierId;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Integer totalPayment) {
        this.totalPayment = totalPayment;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Boolean getMakeInvoice() {
        return makeInvoice;
    }

    public void setMakeInvoice(Boolean makeInvoice) {
        this.makeInvoice = makeInvoice;
    }

    public Integer getSaleChannelId() {
        return saleChannelId;
    }

    public void setSaleChannelId(Integer saleChannelId) {
        this.saleChannelId = saleChannelId;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
