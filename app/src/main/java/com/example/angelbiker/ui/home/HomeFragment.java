package com.example.angelbiker.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.angelbiker.R;


public class HomeFragment extends Fragment {
private String urlInstagram, urlFacebook, urlTwitter, urlYoutube;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        TextView texthome2 = root.findViewById(R.id.text_home2);
        TextView texthome3 = root.findViewById(R.id.text_home3);
        urlInstagram = "https://www.instagram.com/daniel_herreros_alpha/";
        urlFacebook = "https://www.facebook.com/daniel.herreros.9047";
        urlTwitter = "https://twitter.com/AlfaIndominus";
        urlYoutube = "https://www.youtube.com/channel/UCS14Zb-ZdKb4bpGjPJncoAg";
        ImageButton instagram = root.findViewById(R.id.Instagram);
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(urlInstagram);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        ImageButton facebook = root.findViewById(R.id.Facebook);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(urlFacebook);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        ImageButton twitter = root.findViewById(R.id.Twitter);
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(urlTwitter);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        ImageButton youtube = root.findViewById(R.id.Youtube);
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(urlYoutube);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
                texthome2.setText("If you have some Issue/Question contact me in my Social Media or Mail");
                texthome3.setText("Remember to drive safe and enjoy Ride");
            }


        });
        return root;



    }
}