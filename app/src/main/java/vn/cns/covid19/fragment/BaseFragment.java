package vn.cns.covid19.fragment;

import android.content.Context;
import android.view.Gravity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.Objects;

import vn.cns.covid19.R;
import vn.cns.covid19.Utils.FlowUtils;
import vn.cns.covid19.base.Lifecycle;

public abstract class BaseFragment extends Fragment implements Lifecycle.View {

    protected ImageView wave;
    protected abstract Lifecycle.ViewModel getViewModel();

    @Override
    public void onResume() {
        super.onResume();
        getViewModel().onViewResumed();
    }

    @Override
    public void onStart() {
        super.onStart();
        getViewModel().onViewAttached(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getViewModel().onViewDetached();
    }

    @Override
    public void showLoadingDialog() {
        FlowUtils.getInstance().showLoadingDialog(this.getContext());
    }

    @Override
    public void dismissLoadingDialog() {
        FlowUtils.getInstance().dismissLoadingDialog();
    }

    @Override
    public void showFailedDialog(String errorMessage) {
        Context context = this.getContext();
        if (errorMessage == null) {
            errorMessage = Objects.requireNonNull(context).getString(R.string.unexpected_error_message);
        }
        FlowUtils.getInstance().showAlert(context, Objects.requireNonNull(context).getString(R.string.error), errorMessage);
    }

    @Override
    public void showAnimation() {
        Animation sizingAnimation = AnimationUtils.loadAnimation(this.getContext(), R.anim.anim_nfc_tap);
        sizingAnimation.setDuration(1500);
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setDuration(1500);
        fadeOut.setRepeatCount(Animation.INFINITE);
        fadeOut.setRepeatMode(Animation.RESTART);
        AnimationSet animation = new AnimationSet(true);
        animation.addAnimation(sizingAnimation);
        animation.addAnimation(fadeOut);
        wave.startAnimation(animation);
    }

    @Override
    public void showAnimationCard() {

    }

    @Override
    public void CountTimeOut() {

    }

    @Override
    public void ShowToast(String message) {
        Toast toast = Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setGravity(Gravity.BOTTOM, 0, 40);
        toast.show();
    }
}
