package vn.cns.covid19.orders.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.cns.covid19.R;
import vn.cns.covid19.databinding.ListViewOrdersItemBinding;
import vn.cns.covid19.model.orders.OrderData;

public class RecyclerViewOrdersAdapter extends RecyclerView.Adapter<RecyclerViewOrdersAdapter.MyViewHolder> {

    public interface ClickListener {
        void onClick (OrderData orderData);
    }

    private List<OrderData> orderDataList = new ArrayList<>();
    private ClickListener listener;

    public void setListener (ClickListener listener) {
        this.listener = listener;
    }

    public void setOrderDataList(List<OrderData> orderDataList) {
        this.orderDataList = orderDataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewOrdersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListViewOrdersItemBinding listViewOrdersItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_view_orders_item,parent,false);
        return new MyViewHolder(listViewOrdersItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewOrdersAdapter.MyViewHolder holder, int position) {
        OrderData orderData = orderDataList.get(position);
        holder.listViewOrdersItemBinding.setOrderData(orderData);
    }

    @Override
    public int getItemCount() {
        return orderDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ListViewOrdersItemBinding listViewOrdersItemBinding;

        public MyViewHolder(@NonNull ListViewOrdersItemBinding listViewOrdersItemBinding) {
            super(listViewOrdersItemBinding.getRoot());
            this.listViewOrdersItemBinding = listViewOrdersItemBinding;
            listViewOrdersItemBinding.getRoot().setOnClickListener(view -> listener.onClick(orderDataList.get(getAdapterPosition())));
        }
    }
}
