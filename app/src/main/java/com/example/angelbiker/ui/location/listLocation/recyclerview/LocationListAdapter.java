package com.example.angelbiker.ui.location.listLocation.recyclerview;

import android.app.Activity;
import android.location.Location;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.angelbiker.domain.DB.modelos.location.LocationMinimal;
import com.example.angelbiker.domain.tools.StringFormatter;


public class LocationListAdapter extends ListAdapter<LocationMinimal, LocationViewHolder> {

    private Activity activity;
    private Location currentLocation;

    public LocationListAdapter(@NonNull DiffUtil.ItemCallback<LocationMinimal> diffCallback, Activity activity, Location currentLocation) {
        super(diffCallback);
        this.activity = activity;
        this.currentLocation = currentLocation;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return LocationViewHolder.create(parent, activity);
    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, int position) {
        LocationMinimal current = getItem(position);
        if (currentLocation != null) {
            holder.bind(current.getName(), StringFormatter.distanceFormatter(current.getDistanceTo(currentLocation)), current.getPhotoPath());
        } else {
            holder.bind(current.getName(), "Unknown distance", current.getPhotoPath());
        }
    }

    public static class LocationDiff extends DiffUtil.ItemCallback<LocationMinimal> {

        @Override
        public boolean areItemsTheSame(@NonNull LocationMinimal oldItem, @NonNull LocationMinimal newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull LocationMinimal oldItem, @NonNull LocationMinimal newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }
}
