package android.BeeFood.master.View.AccountSetup;

import static java.lang.Thread.*;
import android.BeeFood.master.R;
import android.BeeFood.master.databinding.ActivityMapsBinding;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.facebook.login.LoginManager;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    LocationManager locationManager;
    double latitude = 0, longitude = 0;
    EditText edt_AccountSetup_Map_Address;
    Button btn_accountsetup_map_continue;
    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        //goi ham update
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
        edt_AccountSetup_Map_Address = findViewById(R.id.edt_AccountSetup_Map_Address);
        btn_accountsetup_map_continue = findViewById(R.id.btn_accountsetup_map_continue);
        btn_accountsetup_map_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MapsActivity.this, Screen_Pin_Code.class));
            }
        });
        LinearLayout linearLayout=findViewById(R.id.linearLayout2);
        ImageView dropdownprofile=findViewById(R.id.dropdownprofile);
        dropdownprofile.setOnClickListener(view -> {
            linearLayout.setVisibility(View.GONE);
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Toast.makeText(MapsActivity.this, "Đang lấy vị trí", Toast.LENGTH_SHORT).show();
                    if (longitude == 0 && latitude == 0) {
                        try {
                            mMap = googleMap;
                            LatLng User = new LatLng(latitude, longitude);
                            mMap.addMarker(new MarkerOptions().position(User).title("Vị trí của bạn"));
                            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(User, 18);
                            mMap.animateCamera(cameraUpdate, 3000, null);
                            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                            Toast.makeText(MapsActivity.this, "lấy vị trí thành công", Toast.LENGTH_SHORT).show();
                            sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        });

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            edt_AccountSetup_Map_Address.setText(addresses.get(0).getAddressLine(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        onMapReady(mMap);
    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }
}