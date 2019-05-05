package com.figueroaluis.dormdash.ui.fragmentprofilefood;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.figueroaluis.dormdash.R;

public class FragmentProfileFoodFragment extends Fragment {

    private FragmentProfileFoodViewModel mViewModel;

    public static FragmentProfileFoodFragment newInstance() {
        return new FragmentProfileFoodFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_food_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FragmentProfileFoodViewModel.class);
        // TODO: Use the ViewModel
    }

}
