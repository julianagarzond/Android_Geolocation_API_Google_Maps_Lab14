package com.example.googlemaps;

import android.app.FragmentManager;

import com.google.android.gms.maps.SupportMapFragment;

public interface OnMapReadyCallback {
    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    SupportMapFragment mapFragment =
            (SupportMapFragment) getSupportFragmentManager().findFragmentById( R.id.map );

    static FragmentManager getSupportFragmentManager() {
    }
mapFragment.getMapAsync( this );
}
