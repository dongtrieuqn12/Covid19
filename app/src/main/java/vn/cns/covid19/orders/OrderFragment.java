package vn.cns.covid19.orders;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;

import java.util.List;
import java.util.Objects;

import vn.cns.covid19.MainActivity;
import vn.cns.covid19.Utils.Const;
import vn.cns.covid19.base.Lifecycle;
import vn.cns.covid19.databinding.FragmentOrdersBinding;
import vn.cns.covid19.fragment.BaseFragment;
import vn.cns.covid19.model.customer.CustomerResponse;
import vn.cns.covid19.model.orders.OrderData;
import vn.cns.covid19.model.orders.OrdersResponse;
import vn.cns.covid19.orders.adapter.RecyclerViewOrdersAdapter;

public class OrderFragment extends BaseFragment implements OrderContract.View, RecyclerViewOrdersAdapter.ClickListener {

    public interface OnUserSelectOrderData {
        void onSelectOrderData (OrderData orderData);
    }

    public static final String TAG = OrderFragment.class.getSimpleName();

    private OrderContract.ViewModel orderViewModel;
    private FragmentOrdersBinding fragmentOrdersBinding;
    private RecyclerViewOrdersAdapter mRecyclerViewOrdersAdapter;
    private MainActivity activity;
    private OnUserSelectOrderData listener;

    public void setListener (OnUserSelectOrderData listener) {
        this.listener = listener;
    }

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OrderRepository orderRepository = OrderRepository.getInstance(Objects.requireNonNull(getActivity()).getApplicationContext());
        activity = (MainActivity) getActivity();
        orderViewModel = new OrderViewModel(orderRepository);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //initialize UI
        fragmentOrdersBinding = FragmentOrdersBinding.inflate(inflater,container,false);

        mRecyclerViewOrdersAdapter = new RecyclerViewOrdersAdapter();
        mRecyclerViewOrdersAdapter.setListener(this);
        fragmentOrdersBinding.recyclerOrders.setLayoutManager(new LinearLayoutManager(activity));
        fragmentOrdersBinding.recyclerOrders.setHasFixedSize(true);
        fragmentOrdersBinding.recyclerOrders.setAdapter(mRecyclerViewOrdersAdapter);

        showLoadingDialog();
        orderViewModel.getOrders(activity.customerCode);
//        orderViewModel.getOrders("040D475A4D4D80");
        return fragmentOrdersBinding.getRoot();
    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return orderViewModel;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void updateOrders(OrdersResponse ordersResponse) {
        dismissLoadingDialog();
        if (ordersResponse.getTotal() == 0) {
            Log.d(Const.TAG,new Gson().toJson(ordersResponse));
            showFailedDialog("Thẻ chưa có đơn hàng");
            activity.onBackPressed();
        } else {
            mRecyclerViewOrdersAdapter.setOrderDataList(ordersResponse.getData());
        }
    }

    @Override
    public void updateCustomer(CustomerResponse customerResponse) {
        customerResponse.getData().get(0).setOrganization(activity.customerId.replace("null",""));
        fragmentOrdersBinding.setCustomer(customerResponse.getData().get(0));
    }

    @Override
    public void onFailed() {
        dismissLoadingDialog();
        activity.onBackPressed();
    }

    @Override
    public void onClick(OrderData orderData) {
        listener.onSelectOrderData(orderData);
    }
}
