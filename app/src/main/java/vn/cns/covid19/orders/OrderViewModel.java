package vn.cns.covid19.orders;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

import io.reactivex.functions.Consumer;
import vn.cns.covid19.base.Lifecycle;
import vn.cns.covid19.base.NetworkViewModel;
import vn.cns.covid19.model.customer.CustomerResponse;
import vn.cns.covid19.model.orders.OrdersResponse;

public class OrderViewModel extends NetworkViewModel implements OrderContract.ViewModel {

    private OrderContract.View viewCallback;
    private OrderRepository orderRepository;

    public OrderViewModel (OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void onViewResumed() {

    }

    @Override
    public void onViewAttached(Lifecycle.View viewCallback) {
        this.viewCallback = (OrderContract.View) viewCallback;
    }

    @Override
    public void onViewDetached() {
        viewCallback = null;
    }

    @Override
    public boolean isRequestingInformation() {
        return false;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getOrders(String customerCode) {
        orderRepository.getData(customerCode).subscribe(new Consumer<Map<String, Object>>() {
            @Override
            public void accept(Map<String, Object> stringObjectMap) throws Exception {
                updateView(stringObjectMap);
            }
        });
    }

    private void updateView(Map<String,Object> map) {
        OrdersResponse ordersResponse = (OrdersResponse) map.get("orders");
        CustomerResponse customerResponse = (CustomerResponse) map.get("customer");
        viewCallback.updateOrders(ordersResponse);
        if (customerResponse != null && customerResponse.getTotal() > 0) {
            viewCallback.updateCustomer(customerResponse);
        }
    }

    @Override
    public void downLoadImage(String url) {
        new DownloadImageTask().execute(url);
    }

    @SuppressLint("StaticFieldLeak")
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", Objects.requireNonNull(e.getMessage()));
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            viewCallback.updateImage(result);
        }
    }
}
