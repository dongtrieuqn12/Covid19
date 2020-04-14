package vn.cns.covid19.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import vn.cns.covid19.base.Lifecycle;
import vn.cns.covid19.databinding.FragmentHomeBinding;
import vn.cns.covid19.fragment.BaseFragment;

public class HomeFragment extends BaseFragment implements HomeContract.View {

    public static final String TAG = HomeFragment.class.getSimpleName();

    private FragmentHomeBinding fragmentHomeBinding;
    private HomeContract.ViewModel homeViewModel;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HomeRepository homeRepository = HomeRepository.getInstance(getContext());
        homeViewModel = new HomeViewModel(homeRepository);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater,container,false);
        wave = fragmentHomeBinding.image;
        return fragmentHomeBinding.getRoot();
    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return homeViewModel;
    }
}
