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

    @Override
    public void onMapReady( GoogleMap googleMap )
    {
        this.googleMap = googleMap;
    }

    public void showMyLocation()
    {
        if ( googleMap != null )
        {
            String[] permissions = { android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION };
            if ( hasPermissions( this, permissions ) )
            {
                googleMap.setMyLocationEnabled( true );

                FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                // Got last known location. In some rare situations this can be null.
                                if (location != null) {
                                    addMarkerAndZoom( location, "My Location", 15 );
                                }
                            }
                        });
            }
            else
            {
                ActivityCompat.requestPermissions( activity, permissions, ACCESS_LOCATION_PERMISSION_CODE );
            }
        }
    }

    public static boolean hasPermissions( Context context, String[] permissions )
    {
        for ( String permission : permissions )
        {
            if ( ContextCompat.checkSelfPermission( context, permission ) == PackageManager.PERMISSION_DENIED )
            {
                return false;
            }
        }
        return true;
    }

    public void addMarkerAndZoom( Location location, String title, int zoom  )
    {
        LatLng myLocation = new LatLng( location.getLatitude(), location.getLongitude() );
        googleMap.addMarker( new MarkerOptions().position( myLocation ).title( title ) );
        googleMap.moveCamera( CameraUpdateFactory.newLatLngZoom( myLocation, zoom ) );
    }

}
