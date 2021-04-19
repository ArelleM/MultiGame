package com.example.multi_game.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.multi_game.R;
import com.example.multi_game.databinding.FragmentFastTapGameBinding;
import com.example.multi_game.utils.ActivityUtils;

public class FastTapGameFragment extends Fragment {
    private static final int START_TIME = 20;
    public int counter = START_TIME;
    private int currentScore;
    private boolean isLongTap;
    CountDownTimer countDownTimer;
    FragmentFastTapGameBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fast_tap_game, container, false);
        binding.score.setText(getString(R.string.score,0));
        binding.time.setText(getString(R.string.time_left,20));
        countDownTimer = new CountDownTimer(20000, 1000) {
            public void onTick(long millisUntilFinished) {
                counter--;
                binding.time.setText(getString(R.string.time_left, counter));
            }

            public void onFinish() {
                binding.time.setText("Terminé");
                Bundle bundle = new Bundle();
                bundle.putInt("score", currentScore);
                ActivityUtils.addFragmentToFragment(FastTapGameFragment.this, new FastTapFinishFragment(), R.id.fastGameContainer, bundle);
            }
        }.start();

        binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (counter > 0){
                    if(isLongTap){
                        currentScore++;
                        changeTap();
                    }
                    binding.score.setText(getString(R.string.score, currentScore));
                }
                return true;
            }
        });


        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter > 0){
                    if(!isLongTap){
                        currentScore++;
                        changeTap();
                    }
                    binding.score.setText(getString(R.string.score, currentScore));
                }
            }
        });
        if(Math.random() > 0.5){
            binding.tapOrder.setText("TAP");
            isLongTap=false;
        }else{
            binding.tapOrder.setText("LONG TAP");
            isLongTap=true;
        }

        return binding.getRoot();
    }
    public void changeTap(){
        if(Math.random() > 0.5){
            binding.tapOrder.setText("TAP");
            isLongTap=false;
        }else{
            binding.tapOrder.setText("LONG TAP");
            isLongTap=true;
        }
    }

    @Override
    public void onDestroyView() {
        countDownTimer.cancel();
        super.onDestroyView();
    }
}
