package com.example.samplenavigation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.samplenavigation.util.Utility;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Map");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        latitude = getIntent().getStringExtra("latitude");
        longitude = getIntent().getStringExtra("longitude");
        Log.v("TTT", "longitude = " + latitude + "   " + longitude);

        setView();
    }

    private void setView() {
        requestPermission(Manifest.permission.ACCESS_FINE_LOCATION, Utility.LOCATION_REQUEST_CODE);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapsFragment);
        mapFragment.getMapAsync(GoogleMapActivity.this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showMap(latitude, longitude);
            }
        }, 100);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        GoogleMapActivity.this.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        this.finish();
    }

    private void showMap(String latitude, String longitude) {
        LatLng TutorialsPoint = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(TutorialsPoint));//.title("Hi"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(TutorialsPoint));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        mMap.animateCamera(zoom);
//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//
//            @Override
//            public void onMapClick(final LatLng point) {
//                mMap.clear();
//                final MarkerOptions marker = new MarkerOptions()
//                        .position(new LatLng(point.latitude, point.longitude))
//                        .title("");
//                Log.v("TTT", "point.latitude; = " + point.latitude + "  " + point.longitude);
//
////                    float[] results = new float[1];
////                    Location.distanceBetween(selectedLatitude, selectedLatitude, point.latitude, point.longitude, results);
////                    Log.v("TTT", "results distance " + CalculationByDistance(selectedLatitude, selectedLatitude, point.latitude, point.longitude));
//                Location sourceLocation = new Location("");
//                sourceLocation.setLongitude(listNearestLocation.get(spnrNearestLocation.getSelectedItemPosition()).getLongitude());//selectedLongitude);
//                sourceLocation.setLatitude(listNearestLocation.get(spnrNearestLocation.getSelectedItemPosition()).getLatitude());//selectedLatitude);
//
//            }
//        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
//        LatLng TutorialsPoint = new LatLng(8.561, 76.8893);
//        mMap.addMarker(new MarkerOptions().position(TutorialsPoint).title("Hi"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(TutorialsPoint));
    }

    protected void requestPermission(String permissionType, int requestCode) {
        int permission = ContextCompat.checkSelfPermission(this, permissionType);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{permissionType}, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Utility.LOCATION_REQUEST_CODE: {

                if (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Unable to show location - permission required", Toast.LENGTH_LONG).show();
                }
                else {
                    showMap(latitude, longitude);
                }
                return;
            }
        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
