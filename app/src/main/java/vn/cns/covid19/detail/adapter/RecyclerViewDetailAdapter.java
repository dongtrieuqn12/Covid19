package vn.cns.covid19.detail.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import vn.cns.covid19.R;
import vn.cns.covid19.databinding.ListViewOrderDetailItemBinding;
import vn.cns.covid19.model.orders.OrderData;
import vn.cns.covid19.model.orders.OrderDetail;

public class RecyclerViewDetailAdapter extends RecyclerView.Adapter<RecyclerViewDetailAdapter.MyViewHolder> {

    public interface onClickDetail {
        void onClick (OrderDetail orderDetail);
    }

    private OrderData orderData;
    private onClickDetail listener;

    public void setOrderData(OrderData orderData) {
        this.orderData = orderData;
        notifyDataSetChanged();
    }

    public void setListener (onClickDetail listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewDetailAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListViewOrderDetailItemBinding listViewOrderDetailItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_view_order_detail_item,parent,false);
        return new MyViewHolder(listViewOrderDetailItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewDetailAdapter.MyViewHolder holder, int position) {
        OrderDetail orderDetail = orderData.getOrderDetails().get(position);
        holder.listViewOrderDetailItemBinding.setItem(orderDetail);
    }

    @Override
    public int getItemCount() {
        return orderData.getOrderDetails().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ListViewOrderDetailItemBinding listViewOrderDetailItemBinding;
        public MyViewHolder(@NonNull ListViewOrderDetailItemBinding listViewOrderDetailItemBinding) {
            super(listViewOrderDetailItemBinding.getRoot());
            this.listViewOrderDetailItemBinding = listViewOrderDetailItemBinding;
            this.listViewOrderDetailItemBinding.actionDeliveryButton.setOnClickListener(view ->
                    listener.onClick(orderData.getOrderDetails().get(getAdapterPosition())));
        }
    }
}
