package vn.cns.covid19.model.customer;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import vn.cns.covid19.BR;
import vn.cns.covid19.Utils.Const;
import vn.cns.covid19.base.ShortenId;

public class CustomerData extends BaseObservable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("gender")
    @Expose
    private Boolean gender;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("retailerId")
    @Expose
    private Integer retailerId;
    @SerializedName("branchId")
    @Expose
    private Integer branchId;
    @SerializedName("locationName")
    @Expose
    private String locationName;
    @SerializedName("wardName")
    @Expose
    private String wardName;
    @SerializedName("modifiedDate")
    @Expose
    private String modifiedDate;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("organization")
    @Expose
    private String organization;
    @SerializedName("debt")
    @Expose
    private Integer debt;

    @SuppressLint("SetTextI18n")
    @BindingAdapter({"address","locationName"})
    public static void setAddress (TextView textView, String address, String locationName) {
        textView.setText(address + " " + locationName);
    }

    @SuppressLint("SetTextI18n")
    @BindingAdapter("customerId")
    public static void setImage (ImageView img, String customerId) {
        if (customerId == null) {
            return;
        }
        Log.d(Const.TAG,"customerId: " + customerId);
        String imageURL = Const.AMS_HOST + "public/photo/" + ShortenId.encode(Long.valueOf(customerId));
        Log.d(Const.TAG,imageURL);
        Glide.with(img.getContext())
                .load(imageURL)
                .into(img);
    }

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

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(vn.cns.covid19.BR.name);
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    @Bindable
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        notifyPropertyChanged(vn.cns.covid19.BR.address);
    }

    public Integer getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(Integer retailerId) {
        this.retailerId = retailerId;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    @Bindable
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
        notifyPropertyChanged(vn.cns.covid19.BR.locationName);
    }

    @Bindable
    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
        notifyPropertyChanged(vn.cns.covid19.BR.wardName);
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Bindable
    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
        notifyPropertyChanged(vn.cns.covid19.BR.organization);
    }

    public Integer getDebt() {
        return debt;
    }

    public void setDebt(Integer debt) {
        this.debt = debt;
    }
}
