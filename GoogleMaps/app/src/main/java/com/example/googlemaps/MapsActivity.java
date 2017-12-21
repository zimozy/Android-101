package com.example.googlemaps;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // get the buttons
        Button ibgButton    = findViewById(R.id.btnIbg);
        Button nycButton    = findViewById(R.id.btnNyc);
        Button londonButton = findViewById(R.id.btnLondon);


        //set the click events to show the given place

        ibgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo("ibg");
            }
        });

        nycButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo("nyc");
            }
        });

        londonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo("london");
            }
        });

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

        // set up initial location and view
        LatLng initialLatLnt = new LatLng(0, 0);

        CameraPosition cameraPosition = new CameraPosition.Builder() //create a camera position
                .target(initialLatLnt)
                .zoom(1)
                .build();

        mMap.addMarker( //create a marker/"tooltip"
                new MarkerOptions()
                        .position(initialLatLnt)
                        .title("Welcome. Tap a location button above.")
        ).showInfoWindow(); //displays the "tooltip"

        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void goTo(String place) {

        //set up for scope outside the switch
        String title     = null;
        double latitude  = 0;
        double longitude = 0;
        int zoom         = 15; //default for our application
        int bearing      = 0;  //north
        int tilt         = 0;  //straight above
        int mapType      = GoogleMap.MAP_TYPE_NORMAL;

        switch (place) { //set the place coordinates, name, and map info
            case "ibg":
                latitude  = 35.228934;
                longitude = -80.845483;
                title     = "IBG Offices | Charlotte, NC";
                zoom      = 17;
                bearing   = 155;
                tilt      = 70;
                break;

            case "nyc":
                latitude  = 40.7127;
                longitude = -74.0059;
                title     = "New York City, New York";
                zoom      = 13;
                bearing   = 25;
                tilt      = 90;
                mapType   = GoogleMap.MAP_TYPE_SATELLITE;
                break;

            case "london":
                latitude  = 51.513817;
                longitude = -0.099320;
                title     = "London, England";
                zoom      = 10;
                break;
        }

        LatLng latLng = new LatLng(latitude, longitude); //create latitude + longitude
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(title); //create marker label

        CameraPosition cameraPosition = new CameraPosition.Builder() // create camera position
                .target(latLng)      // Sets the center of the map to Mountain View
                .zoom(zoom)                   // Sets the zoom
                .bearing(bearing)                // Sets the orientation of the camera to east
                .tilt(tilt)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder

        //display the results
        mMap.addMarker(markerOptions).showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.setMapType(mapType);
    }
}
