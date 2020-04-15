package vn.cns.covid19.model.orders;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.cns.covid19.BR;

public class OrderDetail extends BaseObservable {
    @SerializedName("productId")
    @Expose
    private Integer productId;
    @SerializedName("productCode")
    @Expose
    private String productCode;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("discount")
    @Expose
    private Double discount;
    @SerializedName("discountRatio")
    @Expose
    private Integer discountRatio;
    @SerializedName("note")
    @Expose
    private String note;

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @BindingAdapter("setPrice")
    public static void eventName (TextView textView, Double price) {
        String data = String.valueOf(price);
        textView.setText(data);
    }

    @BindingAdapter("setButton")
    public static void setView (Button view, String note) {
        NoteOrder noteOrder = new Gson().fromJson(note,NoteOrder.class);
        if (noteOrder == null) {
            view.setVisibility(View.VISIBLE);
            return;
        }
        if (noteOrder.isDelivered()) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    @BindingAdapter("setCode")
    public static void setCode (TextView view, String code) {
        view.setText(code.substring(code.length() - 3));
    }

    @BindingAdapter("setLayout")
    public static void setLayout (LinearLayout view, String note) {
        NoteOrder noteOrder = new Gson().fromJson(note,NoteOrder.class);
        if (noteOrder == null) {
            view.setVisibility(View.GONE);
            return;
        }
        if (noteOrder.isDelivered()) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Bindable
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
        notifyPropertyChanged(vn.cns.covid19.BR.productCode);
    }

    @Bindable
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
        notifyPropertyChanged(vn.cns.covid19.BR.productName);
    }

    @Bindable
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        notifyPropertyChanged(vn.cns.covid19.BR.quantity);
    }

    @Bindable
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
        notifyPropertyChanged(vn.cns.covid19.BR.price);
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getDiscountRatio() {
        return discountRatio;
    }

    public void setDiscountRatio(Integer discountRatio) {
        this.discountRatio = discountRatio;
    }

    @Bindable
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
        notifyPropertyChanged(vn.cns.covid19.BR.note);
    }
}
