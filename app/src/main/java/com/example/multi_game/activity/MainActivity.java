package com.example.multi_game.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.multi_game.R;
import com.example.multi_game.databinding.ActivityMainBinding;
import com.example.multi_game.fragment.DragNDropFragment;
import com.example.multi_game.fragment.FastTapFragment;
import com.example.multi_game.fragment.IpacFragment;
import com.example.multi_game.fragment.SettingsFragment;
import com.example.multi_game.fragment.SwipeFragment;
import com.example.multi_game.utils.ActivityUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ActivityUtils.addFragmentToActivity(MainActivity.this, new DragNDropFragment(),R.id.main_fragment_container);

        binding.mainBottomNav.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        if (item.getItemId() == R.id.drag){
                            ActivityUtils.addFragmentToActivity(MainActivity.this, new DragNDropFragment(), R.id.main_fragment_container);
                        } else if (item.getItemId() == R.id.settings){
                            ActivityUtils.addFragmentToActivity(MainActivity.this, new SettingsFragment(), R.id.main_fragment_container);
                        }
                        else if (item.getItemId() == R.id.swipe){
                            ActivityUtils.addFragmentToActivity(MainActivity.this, new SwipeFragment(), R.id.main_fragment_container);
                        }else if (item.getItemId() == R.id.ipac){
                            ActivityUtils.addFragmentToActivity(MainActivity.this, new IpacFragment(), R.id.main_fragment_container);
                        }else if (item.getItemId() == R.id.fast){
                            ActivityUtils.addFragmentToActivity(MainActivity.this, new FastTapFragment(), R.id.main_fragment_container);
                        }
                        return true;
                    }
                }
                );
    }
}
