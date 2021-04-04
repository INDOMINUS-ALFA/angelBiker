package com.example.angelbiker.ui.location.listLocation.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.angelbiker.R;
import com.example.angelbiker.domain.DB.modelos.location.LocationModel;
import com.example.angelbiker.ui.location.location.LocationActivity;

import java.io.File;

public class LocationViewHolder extends RecyclerView.ViewHolder {
    private Context context;
    private final TextView textViewName, textViewDistance;
    private final ImageView imageView;

    private LocationViewHolder(View itemView) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.textViewName);
        textViewDistance = itemView.findViewById(R.id.textViewDistance);
        imageView = itemView.findViewById(R.id.imageView);

        itemView.findViewById(R.id.cardView).setOnClickListener(v -> {
            Intent locationActivity = new Intent(context, LocationActivity.class);
            locationActivity.putExtra(LocationModel.LOCATION_NAME, textViewName.getText().toString());
            context.startActivity(locationActivity);
        });
    }

    public void bind(String name, String distance, String photoPath) {
        textViewName.setText(name);
        textViewDistance.setText(distance);
        imageView.setImageURI(Uri.fromFile(new File(photoPath)));
    }

    static LocationViewHolder create(ViewGroup parent, Context context) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_locations_item, parent, false);
        LocationViewHolder locationViewHolder = new LocationViewHolder(view);
        locationViewHolder.context = context;
        return locationViewHolder;
    }

}
