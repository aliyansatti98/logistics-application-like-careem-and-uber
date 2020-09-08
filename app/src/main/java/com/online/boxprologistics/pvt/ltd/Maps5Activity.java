package com.online.boxprologistics.pvt.ltd;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.tabs.TabLayout;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

public class Maps5Activity extends AppCompatActivity implements OnMapReadyCallback {


    public static final String TAG=null;
    public static final String Tag=null;
    EditText editText2,editText1;
    private Button btn1,btn2;
    PlacesClient placesClient;
    public String PlaceName;
    LatLng placeLatLng;
    GoogleMap mMap;
    private GPSTracker gpsTracker;

    double latitude,longitude,latii,lngii;
    SupportMapFragment mapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps5);
        btn1=findViewById(R.id.cconfirmpickup);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Maps5Activity.this,maps6Activity.class);
                intent.putExtra("PlaceName",PlaceName);
                intent.putExtra("PickUpLat",latitude);
                intent.putExtra("PickUpLon",longitude);

                startActivity(intent);
            }
        });
        // editText1=findViewById(R.id.editText1);
        //editText2 =findViewById(R.id.editText2);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(Maps5Activity.this);
        gpsTracker = new GPSTracker(Maps5Activity.this);

        // Eger konum bilgisi alinabiliyorsa ekranda goruntulenir
        if (gpsTracker.canGetLocation())
        {
            mapFragment.getMapAsync(Maps5Activity.this);


            // Toast.makeText(getApplicationContext(), "Location : \nLatitude " + latitude + "\nLongitude " + longitude, Toast.LENGTH_LONG).show();
        }
        else
        {

            gpsTracker.showSettingsAlert();
        }
        // Add a marker in Sydney and move the camera
        placeLatLng = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());


        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), "AIzaSyCvzCt3r83Q8YSMdBJUcz26Vos_jxDCFpY");
        }

// Create a new Places client instance.

        placesClient = Places.createClient(Maps5Activity.this);

        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteFragment.setCountry("PK");


        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.LAT_LNG, Place.Field.NAME));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
                PlaceName = place.getName();
                placeLatLng = place.getLatLng();
                latitude=placeLatLng.latitude;
                longitude=placeLatLng.longitude;



                mapFragment.getMapAsync(Maps5Activity.this);

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(Tag, "An error occurred: " + status);
            }
        });

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



        mMap.addMarker(new MarkerOptions().position(placeLatLng).title(PlaceName));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(placeLatLng));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(placeLatLng, 20);
        mMap.animateCamera(cameraUpdate);
    }
}



