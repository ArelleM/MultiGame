package com.example.multi_game.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.multi_game.R;
import com.example.multi_game.databinding.FragmentSwipeBinding;
import com.example.multi_game.utils.ActivityUtils;

public class SwipeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentSwipeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_swipe, container, false);
        binding.swipeGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.addFragmentToFragment(SwipeFragment.this,new SwipeGameFragment(),R.id.fastGameContainer);
            }
        });
        return binding.getRoot();
    }
}
