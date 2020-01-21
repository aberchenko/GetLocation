package com.example.getlocationapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private LatLng location;
    final int DEFAULT_ZOOM = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        DatabaseReader.getLocation(new OnCompleteListener<LatLng>() {
            @Override
            public void onComplete(@NonNull Task<LatLng> task) {
                location = task.getResult();
                startMap();
            }
        });

    }

    private void startMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in location,
        // and move the map's camera to the same location.
        googleMap.addMarker(new MarkerOptions().position(location)
                .title("Location"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(location.latitude,
                        location.longitude), DEFAULT_ZOOM));
    }

}
