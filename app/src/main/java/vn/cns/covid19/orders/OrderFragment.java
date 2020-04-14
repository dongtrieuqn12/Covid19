package vn.cns.covid19.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;
import java.util.Objects;

import vn.cns.covid19.MainActivity;
import vn.cns.covid19.base.Lifecycle;
import vn.cns.covid19.databinding.FragmentOrdersBinding;
import vn.cns.covid19.fragment.BaseFragment;
import vn.cns.covid19.model.orders.OrderData;
import vn.cns.covid19.orders.adapter.RecyclerViewOrdersAdapter;

public class OrderFragment extends BaseFragment implements OrderContract.View {

    public static final String TAG = OrderFragment.class.getSimpleName();

    private OrderContract.ViewModel orderViewModel;
    private FragmentOrdersBinding fragmentOrdersBinding;
    private RecyclerViewOrdersAdapter mRecyclerViewOrdersAdapter;
    private MainActivity activity;

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
        fragmentOrdersBinding.recyclerOrders.setLayoutManager(new LinearLayoutManager(activity));
        fragmentOrdersBinding.recyclerOrders.setHasFixedSize(true);
        fragmentOrdersBinding.recyclerOrders.setAdapter(mRecyclerViewOrdersAdapter);

        orderViewModel.getOrdersData().observe(getViewLifecycleOwner(), orderData -> {
            if (orderData != null) {
                mRecyclerViewOrdersAdapter.setOrderDataList(orderData);
            } else {
                activity.onBackPressed();
            }
        });
        orderViewModel.getOrders(activity.customerCode);
        return fragmentOrdersBinding.getRoot();
    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return orderViewModel;
    }
}
