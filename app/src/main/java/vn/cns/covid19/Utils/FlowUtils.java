package vn.cns.covid19.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import androidx.appcompat.app.AlertDialog;
import vn.cns.covid19.R;

/**
 * Created by Ho Dong Trieu on 12/12/2018
 */
public class FlowUtils {
    private static FlowUtils sInstance;
    private ProgressDialog mProgressDialog;

    public static FlowUtils getInstance() {
        if (sInstance == null) {
            sInstance = new FlowUtils();
        }
        return sInstance;
    }


    public void showLoadingDialog(Context context) {
        if (!isLoadingDialogShowing()) {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setMessage(context.getString(R.string.loading_string));
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }
    }

    public void dismissLoadingDialog() {
        if (isLoadingDialogShowing()) {
            try {
                mProgressDialog.dismiss();
                mProgressDialog = null;
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
        }
    }

    private boolean isLoadingDialogShowing() {
        return mProgressDialog != null && mProgressDialog.isShowing();
    }

    public void showAlert(Context context, String title, String message) {
        AlertDialog.Builder bld = new AlertDialog.Builder(context);
        bld.setTitle(title);
        bld.setMessage(message);
        bld.setPositiveButton(context.getString(R.string.ok), (dialogInterface, i) -> {
            dialogInterface.dismiss();
        });
        AlertDialog alertDialog = bld.create();
        alertDialog.show();
    }
}
