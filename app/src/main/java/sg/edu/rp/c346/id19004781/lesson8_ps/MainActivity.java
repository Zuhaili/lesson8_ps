package sg.edu.rp.c346.id19004781.lesson8_ps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btnNorth, btnEast, btnCentral;
    private GoogleMap map;
    Spinner spinnerLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerLocation = findViewById(R.id.spinner);
        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }

                LatLng poi_north = new LatLng(1.448412336028024, 103.80036496939746);
                Marker north = map.addMarker(new
                        MarkerOptions()
                        .position(poi_north)
                        .title("North - HQ:")
                        .snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am-5pm\n" +
                                "Tel:65433456\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                LatLng poi_central = new LatLng(1.3075081761727698, 103.8293322961433);
                Marker central = map.addMarker(new
                        MarkerOptions()
                        .position(poi_central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                                "Operating hours: 11am-8pm\n" +
                                "Tel:67788652\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                LatLng poi_east = new LatLng(1.349150445580269, 103.93583968265024);
                Marker east = map.addMarker(new
                        MarkerOptions()
                        .position(poi_east)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788 \n" +
                                "Operating hours: 9am-5pm\n" +
                                "Tel:66776677\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
//

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        String marker_current = marker.getTitle();
                        Toast.makeText(MainActivity.this,marker_current,Toast.LENGTH_SHORT).show();


                        return false;
                    }
                });
//                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_north,
//                        15));


                btnNorth = findViewById(R.id.btnNorth);
                btnEast = findViewById(R.id.btnEast);
                btnCentral = findViewById(R.id.btnCentral);

                spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if(i == 0){
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_north,
                                    15));
                        } else if(i == 1) {
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central,
                                    15));
                        } else if(i == 2) {
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east,
                                    15));
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                        btnNorth.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (map != null) {
//                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//                    map.animateCamera(CameraUpdateFactory.zoomIn());
//                    map.animateCamera(CameraUpdateFactory.zoomOut());
                                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_north,
                                            15));

                                }
                            }
                        });

                btnCentral.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (map != null) {
//                    map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
//                            map.animateCamera(CameraUpdateFactory.zoomIn());
//                    map.animateCamera(CameraUpdateFactory.zoomOut());
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central,
                                    15));
                        }
                    }
                });

                btnEast.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (map != null) {
//                    map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//                            map.animateCamera(CameraUpdateFactory.zoomIn());
//                    map.animateCamera(CameraUpdateFactory.zoomOut());
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east,
                                    15));
                        }
                    }
                });

            }
        });



    }
}