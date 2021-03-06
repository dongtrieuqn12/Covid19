package vn.cns.covid19.base;

import androidx.annotation.CallSuper;

import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableObserver;

public abstract class NetworkViewModel {
    protected @Constants.RequestState int requestState;
    protected Throwable lastError;

    public abstract boolean isRequestingInformation();

    public NetworkViewModel() {
        this.requestState = Constants.REQUEST_NONE;
    }

    public @Constants.RequestState int getRequestState() {
        if (isRequestingInformation()) {
            return Constants.REQUEST_RUNNING;
        }

        return requestState;
    }

    public Throwable getLastError() {
        return lastError;
    }

    public class MaybeNetworkObserver<T> extends DisposableMaybeObserver<T> {

        @Override
        @CallSuper
        public void onSuccess(T t) {
            requestState = Constants.REQUEST_SUCCEEDED;
        }

        @Override
        @CallSuper
        public void onError(Throwable e) {
            lastError = e;
            requestState = Constants.REQUEST_FAILED;
        }

        @Override
        public void onComplete() {

        }
    }
}
