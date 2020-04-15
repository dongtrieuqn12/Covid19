package vn.cns.covid19.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;

import vn.cns.covid19.R;
import vn.cns.covid19.Utils.Const;
import vn.cns.covid19.base.Lifecycle;
import vn.cns.covid19.databinding.FragmentDetailBinding;
import vn.cns.covid19.detail.adapter.RecyclerViewDetailAdapter;
import vn.cns.covid19.fragment.BaseFragment;
import vn.cns.covid19.model.orders.OrderData;
import vn.cns.covid19.model.orders.OrderDetail;

public class DetailFragment extends BaseFragment implements DetailContract.View, RecyclerViewDetailAdapter.onClickDetail, View.OnClickListener {

    public static final String TAG = DetailFragment.class.getSimpleName();

    private DetailContract.ViewModel viewModel;
    private FragmentDetailBinding fragmentDetailBinding;
    private OrderData orderData;
    private RecyclerViewDetailAdapter mAdapter;

    public DetailFragment(OrderData orderData) {
        this.orderData = orderData;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DetaiRepository repository = DetaiRepository.getInstance(getContext());
        viewModel = new DetailViewModel(repository);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentDetailBinding = FragmentDetailBinding.inflate(inflater,container,false);
        fragmentDetailBinding.actionDeliveryButton.setOnClickListener(this);
        fragmentDetailBinding.recyclerViewDelivery.setLayoutManager(new LinearLayoutManager(getActivity()));
        fragmentDetailBinding.recyclerViewDelivery.setHasFixedSize(true);
        mAdapter = new RecyclerViewDetailAdapter();
        fragmentDetailBinding.recyclerViewDelivery.setAdapter(mAdapter);
        mAdapter.setListener(this);
        mAdapter.setOrderData(orderData);
        return fragmentDetailBinding.getRoot();
    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void updateOrder(OrderData orderData) {
        dismissLoadingDialog();
        Log.d(Const.TAG,"response update: " + new Gson().toJson(orderData));
        this.orderData = orderData;
        mAdapter.setOrderData(this.orderData);
        if (orderData.getStatus() == 3) {
            fragmentDetailBinding.actionDeliveryButton.setBackground(getResources().getDrawable(R.drawable.button_grey_no_radius));
            fragmentDetailBinding.actionDeliveryButton.setClickable(false);
            fragmentDetailBinding.actionDeliveryButton.setText(getResources().getString(R.string.button_delivered));
        }
    }

    @Override
    public void onFailed() {
        dismissLoadingDialog();
    }

    @Override
    public void onClick(OrderDetail orderDetail) {
        showLoadingDialog();
        viewModel.requestUpdate(orderData,orderDetail);
    }

    @Override
    public void onClick(View view) {
        viewModel.requestUpdate(orderData,null);
    }
}
