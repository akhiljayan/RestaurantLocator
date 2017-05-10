package com.example.akhiljayan.nearbyshops;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String id = "";
    String latitude = "";
    String longitude = "";
    String distance = "";


    String name = "";
    String lati = "";
    String longi = "";
    String contactnumber = "";
    String contactemail = "";
    String category = "";
    String entryaddress = "";
    String postalcode = "";
    String website = "";
    String openinghours = "";
    String dist = "";
    String facebookpage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();

        name = i.getStringExtra("name");
        lati = i.getStringExtra("lati");
        longi = i.getStringExtra("longi");
        contactnumber = i.getStringExtra("contactnumber");
        contactemail = i.getStringExtra("contactemail");
        category = i.getStringExtra("category");
        entryaddress = i.getStringExtra("entryaddress");
        postalcode = i.getStringExtra("postalcode");
        website = i.getStringExtra("website");
        openinghours = i.getStringExtra("openinghours");
        dist = i.getStringExtra("dist");
        facebookpage = i.getStringExtra("facebookpage");




        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        double latitude = Double.parseDouble(lati);
        double longitude = Double.parseDouble(longi);

        LatLng sydney = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
}
