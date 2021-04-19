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
import com.example.multi_game.activity.CreatePlayerActivity;
import com.example.multi_game.activity.DisplayPlayerActivity;
import com.example.multi_game.databinding.FragmentFastTapBinding;
import com.example.multi_game.databinding.FragmentFastTapFinishBinding;
import com.example.multi_game.databinding.FragmentSwipeFinishBinding;
import com.example.multi_game.utils.ActivityUtils;

public class SwipeFinishFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentSwipeFinishBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_swipe_finish, container, false);
        Bundle bundle = this.getArguments();
        binding.score.setText(String.valueOf(bundle.getInt("score")));
        binding.swipeQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finishAffinity();
            }
        });
        binding.swipeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.addFragmentToFragment(SwipeFinishFragment.this,new SwipeFragment(),R.id.swipeGameContainer);
            }
        });
        return binding.getRoot();
    }

}
