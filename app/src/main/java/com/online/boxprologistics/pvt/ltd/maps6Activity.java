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
import com.google.android.gms.maps.*;

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

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import static java.lang.Math.acos;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class maps6Activity extends AppCompatActivity implements OnMapReadyCallback {


    public static final String TAG=null;
    public static final String Tag=null;
    EditText editText2,editText1;
    private Button btn1,btn2;
    PlacesClient placesClient;
    public String PlaceName;
    LatLng placeLatLng;
    GoogleMap mMap;
    double dLati,dLong,latii2,lngii2;
    private GPSTracker gpsTracker;

    double latitude,longitude;
    SupportMapFragment mapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps6);
        btn2= findViewById(R.id.cconfirmdropoff);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String pickup=intent.getStringExtra("PlaceName");
                double pickLat=intent.getDoubleExtra("PickUpLat",0);
                double pickLong=intent.getDoubleExtra("PickUpLon",0);
                Intent intent1 = new Intent(maps6Activity.this,cramessengeractivity.class);
                intent1.putExtra("dropoff",PlaceName);
                intent1.putExtra("pickup",pickup);
                intent1.putExtra("DropOffLatLng",placeLatLng);
                Toast.makeText(getApplicationContext(), "Location : \npickup " +pickup  + "\n dropoff " + PlaceName, Toast.LENGTH_LONG).show();

                //Calculating the distance in meters

                double lon1=pickLong;
                double  lon2 =lngii2;
                double   lat1 =pickLat;
                double  lat2 =latii2;

                //  Toast.makeText(map2.this, "latitude " + lat2 + "longitude " + lon2, Toast.LENGTH_SHORT).show();

/*
        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.acos(Math.sin(Math.PI*lat1/180.0)*Math.sin(Math.PI*lat2/180.0)+Math.cos(Math.PI*lat1/180.0)*Math.cos(Math.PI*lat2/180.0)*Math.cos(Math.PI*lon1/180.0-Math.PI*lon2/180.0))*6378;
*/



/*
        double Rad = 6372.8; //Earth's Radius In kilometers
        // TODO Auto-generated method stub
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double haverdistanceKM = Rad * c;

*/

                double PI_RAD = Math.PI / 180.0;
                double phi1 = lat1 * PI_RAD;
                double phi2 = lat2 * PI_RAD;
                double lam1 = lon1 * PI_RAD;
                double lam2 = lon2 * PI_RAD;

                double rat = 6371.01 * acos(sin(phi1) * sin(phi2) + cos(phi1) * cos(phi2) * cos(lam2 - lam1));



                Intent in = new Intent(maps6Activity.this,bikecourier.class);
                in.putExtra("distance",rat);
                in.putExtra("dropoff",PlaceName);
                in.putExtra("pickup",pickup);
                startActivity(in);

                Toast.makeText(maps6Activity.this,"Distance "+ Math.round(rat) + "Km",Toast.LENGTH_LONG).show();


                //Displaying the distance


                //  startActivity(intent1);
            }
        });
        // editText1=findViewById(R.id.editText1);
        //editText2 =findViewById(R.id.editText2);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        gpsTracker = new GPSTracker(maps6Activity.this);

        // Eger konum bilgisi alinabiliyorsa ekranda goruntulenir
        if (gpsTracker.canGetLocation())
        {
            mapFragment.getMapAsync(maps6Activity.this);


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

        placesClient = Places.createClient(maps6Activity.this);

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
                latii2=placeLatLng.latitude;
                lngii2=placeLatLng.longitude;

                mapFragment.getMapAsync(maps6Activity.this);

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
    public double deg2rad(double deg) {
        return deg * (Math.PI/180);
    }
}



