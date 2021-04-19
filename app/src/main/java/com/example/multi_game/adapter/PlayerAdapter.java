package com.example.multi_game.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.multi_game.R;
import com.example.multi_game.activity.CreatePlayerActivity;
import com.example.multi_game.activity.DisplayPlayerActivity;
import com.example.multi_game.activity.MainActivity;
import com.example.multi_game.manager.PlayerManager;
import com.example.multi_game.model.Player;
import com.example.multi_game.utils.ActivityUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerHolder> {
    private  final  DisplayPlayerActivity activity;
    private final List<Player> players;
    public PlayerAdapter(DisplayPlayerActivity activity, List<Player> players) {
        this.activity = activity;
        this.players = players;
    }
    @NonNull
    @Override
    public PlayerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_display_player_row, parent, false);
        return new PlayerHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PlayerHolder holder, int position) {
        Player player = players.get(position);
        holder.firstName.setText(player.getFirstName());
        holder.lastName.setText(player.getName());
        Picasso.get().load(player.getPicture()).centerCrop().fit().into(holder.imageView);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerManager.getInstance().setPlayer(player);
                ActivityUtils.launchActivity(activity, MainActivity.class, false, true);
                activity.finishAffinity();
            }
        });
    }
    @Override
    public int getItemCount() {
        return players.size();
    }
    class PlayerHolder extends RecyclerView.ViewHolder {

        public LinearLayout container;
        public ImageView imageView;
        public TextView firstName;
        public TextView lastName;
        PlayerHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.player_row_container);
            imageView = itemView.findViewById(R.id.player_row_image);
            lastName = itemView.findViewById(R.id.player_row_name);
            firstName = itemView.findViewById(R.id.player_row_firstname);
        }
    }
}