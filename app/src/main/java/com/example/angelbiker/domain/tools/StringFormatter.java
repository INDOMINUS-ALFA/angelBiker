package com.example.angelbiker.domain.tools;

public class StringFormatter {
    public static String distanceFormatter(double distanceKm){
        if(distanceKm < 0.05){
            return "<50m";
        }
        if(distanceKm < 1){
            int distanceM = (int) (distanceKm * 1000);
            return distanceM + "m";
        }else{
            return (int) (distanceKm) + "Km";
        }
    }
}
