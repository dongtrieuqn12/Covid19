package vn.cns.covid19.model.orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrdersResponse {
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("pageSize")
    @Expose
    private Integer pageSize;
    @SerializedName("data")
    @Expose
    private List<OrderData> data = null;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<OrderData> getData() {
        return data;
    }

    public void setData(List<OrderData> data) {
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
