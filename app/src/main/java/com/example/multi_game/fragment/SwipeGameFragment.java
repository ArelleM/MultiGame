package com.example.multi_game.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.multi_game.R;
import com.example.multi_game.databinding.FragmentFastTapGameBinding;
import com.example.multi_game.model.OnSwipeTouchListener;
import com.example.multi_game.utils.ActivityUtils;

public class SwipeGameFragment extends Fragment {
    private static final int START_TIME = 20000;
    private static final int SWIPE_RIGHT = 0;
    private static final int SWIPE_LEFT = 1;
    private static final int SWIPE_DOWN = 2;
    private static final int SWIPE_UP = 3;
    public int counter = START_TIME;
    private int swipeDirections;
    private int currentScore = -1;
    private CountDownTimer countDownTimer;
    private FragmentFastTapGameBinding binding;
    private float initialX;
    private float initialY;
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
                ActivityUtils.addFragmentToFragment(SwipeGameFragment.this, new SwipeFinishFragment(), R.id.fastGameContainer, bundle);
            }
        }.start();
        setAction();

        binding.fastGameContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getActionMasked();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = event.getX();
                        initialY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        float finalX = event.getX();
                        float finalY = event.getY();
                        if (initialX < (finalX - 50) && swipeDirections == SWIPE_RIGHT) {
                            System.out.println("Left to Right swipe performed");
                            setAction();
                            // Log.d(TAG, "Left to Right swipe performed");
                        }
                        if (initialX > (finalX + 50 && swipeDirections == SWIPE_LEFT) {
                            System.out.println("Right to Left swipe performed");
                            setAction();
                            // Log.d(TAG, "Right to Left swipe performed");
                        }
                        if (initialY < (finalY - 50) && swipeDirections == SWIPE_DOWN) {
                            String a = "";
                            System.out.println("Up to Down swipe performed");
                            setAction();
                            // Log.d(TAG, "Up to Down swipe performed");
                        }
                        if (initialY > (finalY + 200) && swipeDirections == SWIPE_UP) {
                            String a = "";
                            System.out.println("Down to Up swipe performed");
                            setAction();
                            // Log.d(TAG, "Down to Up swipe performed");
                        }
                        break;
                }
                return true;
            }
        });
        return binding.getRoot();
    }
    private void setAction() {
        currentScore++;
        binding.score.setText(getString(R.string.score, currentScore));
        if (Math.random() < 0.25) {
            swipeDirections = 0;
            binding.tapOrder.setText(getString(isLongTap ? R.string.long_tap : R.string.tap));
        } else if (Math.random() < 0.5) {
            swipeDirections = 1;
            binding.tapOrder.setText(getString(isLongTap ? R.string.long_tap : R.string.tap));
        } else if (Math.random() < 0.75) {
            swipeDirections = 2;
            binding.tapOrder.setText(getString(isLongTap ? R.string.long_tap : R.string.tap));
        } else {
            swipeDirections = 3;
            binding.tapOrder.setText(getString(isLongTap ? R.string.long_tap : R.string.tap));
        }

    }
    @Override
    public void onDestroyView() {
        countDownTimer.cancel();
        super.onDestroyView();
    }
}
