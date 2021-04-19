package com.example.multi_game.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.multi_game.R;
import com.example.multi_game.dao.AppDatabase;
import com.example.multi_game.databinding.ActivityCreatePlayerBinding;
import com.example.multi_game.manager.PlayerManager;
import com.example.multi_game.model.Player;
import com.example.multi_game.utils.ActivityUtils;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.squareup.picasso.Picasso;

public class CreatePlayerActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE = 7;
    private static final int REQUEST_LOCALISATION_PERMISSION = 2001;
    private ActivityCreatePlayerBinding binding;
    private String pictureUrl;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_player);
        binding.createPlayerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
                //startActivityForResult(Intent.createChooser(intent,"Choix de la photo"), REQUEST_IMAGE);
                startActivityForResult(intent, REQUEST_IMAGE);
            }
        });
        binding.createPlayerCompas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkLocationAuthorized()) {
                    getUserLocation();
                } else {
                    ActivityCompat.requestPermissions(CreatePlayerActivity.this, new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION
                    }, REQUEST_LOCALISATION_PERMISSION);
                }
            }
        });

        binding.createPlayerValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!binding.createPlayerNom.getText().toString().isEmpty()
                        && !binding.createPlayerPrenom.getText().toString().isEmpty()
                        && !binding.createPlayerLocalisation.getText().toString().isEmpty()
                        && !binding.createPlayerAge.getText().toString().isEmpty()
                        && pictureUrl != null)
                {
                    Player player = new Player(binding.createPlayerNom.getText().toString(),
                            binding.createPlayerPrenom.getText().toString(),
                            Integer.parseInt(binding.createPlayerAge.getText().toString()),
                            binding.createPlayerLocalisation.getText().toString(),
                            pictureUrl);

                    AppDatabase.getDatabase(getApplicationContext()).appDao().insert(player);
                    PlayerManager.getInstance().setPlayer(player);
                    ActivityUtils.launchActivity(CreatePlayerActivity.this, MainActivity.class, true, true);
                }else{
                    Toast.makeText(CreatePlayerActivity.this,"Informations manquantes",Toast.LENGTH_LONG).show();
                }
                //Player player = new Player("nom","prenom",Integer.parseInt(binding.createPlayerAge.getText().toString()),"loc","picture");
            }
        });

        binding.createPlayerPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.launchActivity(CreatePlayerActivity.this, DisplayPlayerActivity.class,false, true);
            }
        });


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCALISATION_PERMISSION && checkLocationAuthorized()) {
            getUserLocation();
        }
    }
    private void getUserLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.getFusedLocationProviderClient(this).getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            binding.createPlayerLocalisation.setText(getString(R.string.location_lat_lng,
                                    location.getLatitude(), location.getLongitude()));
                        }
                    }
                });
    }

    private boolean checkLocationAuthorized(){
        return ActivityCompat.checkSelfPermission(CreatePlayerActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(CreatePlayerActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK) {
            Picasso.get().load(data.getData()).centerCrop().fit().into(binding.createPlayerImage);
            pictureUrl = data.getDataString();
        }
    }
}