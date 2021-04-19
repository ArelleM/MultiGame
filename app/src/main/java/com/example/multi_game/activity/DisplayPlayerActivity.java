package com.example.multi_game.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.multi_game.R;
import com.example.multi_game.adapter.PlayerAdapter;
import com.example.multi_game.dao.AppDatabase;
import com.example.multi_game.databinding.ActivityCreatePlayerBinding;
import com.example.multi_game.databinding.ActivityDisplayPlayersBinding;
import com.example.multi_game.model.Player;

import java.util.ArrayList;

public class DisplayPlayerActivity extends AppCompatActivity {
    ActivityDisplayPlayersBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_display_players);
        ArrayList<Player> playerList = new ArrayList<>();
        for (int i =0;i<50;i++){
            playerList.add(new Player("name"+i,"prenom",12,"loc","pic"));
        }
        binding.displayPlayersRv.setLayoutManager(new LinearLayoutManager(this));
        //binding.displayPlayersRv.setAdapter(new PlayerAdapter(AppDatabase.getDatabase(this).appDao().getAllPlayers()));
        binding.displayPlayersRv.setAdapter(new PlayerAdapter(this,AppDatabase.getDatabase(this).appDao().getAllPlayers()));
    }
}
