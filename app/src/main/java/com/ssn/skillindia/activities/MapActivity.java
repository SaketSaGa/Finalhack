/*
 * Skill India
 * Copyright (C) 2017  e-LEMON-ators
 *
 * This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.ssn.skillindia.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ssn.skillindia.R;
import com.ssn.skillindia.SkillIndiaApplication;
import com.ssn.skillindia.model.TrainingCenter;
import com.ssn.skillindia.utils.LogHelper;
import com.ssn.skillindia.utils.RealmHelper;

import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.ssn.skillindia.R.id.map;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener {
    public static final String[] PERMISSIONS = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
    private static final String TAG = LogHelper.makeLogTag(MapActivity.class);
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private final LatLngBounds INDIA_BOUNDS = new LatLngBounds(new LatLng(6.651116, 67.505225),
            new LatLng(38.914665, 98.088975));
    private boolean locationEnabled = false;
    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;
    private Location location;
    private LatLng latLng = null;
    private Map<LatLng, String> latLngStringMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        latLngStringMap = new HashMap<>();

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(map);
        mapFragment.getMapAsync(this);
        attemptEnableMyLocation();

        Double lat = getIntent().getDoubleExtra("lat", 0);
        Double lng = getIntent().getDoubleExtra("lng", 0);

        if (lat == 0 || lng == 0) {
            if (locationEnabled) {
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                Criteria criteria = new Criteria();
                location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
            }
        } else latLng = new LatLng(lat, lng);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.setMyLocationEnabled(locationEnabled);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setOnInfoWindowClickListener(this);

        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setMapToolbarEnabled(true);

        googleMap.setLatLngBoundsForCameraTarget(INDIA_BOUNDS);

        SkillIndiaApplication skillIndiaApplication = (SkillIndiaApplication) getApplication();
        RealmHelper realmHelper = skillIndiaApplication.getRealmHelper();
        Realm realm = realmHelper.getRealmInstance();
        RealmResults<TrainingCenter> realmResults = realm.where(TrainingCenter.class).findAll();

        for (int i = 0; i < realmResults.size(); i++) {
            try {
                LatLng latLng = new LatLng(Double.parseDouble(realmResults.get(i).getLatitude()),
                        Double.parseDouble(realmResults.get(i).getLongitude()));
                if (latLngStringMap.containsValue(realmResults.get(i).getCenterName())) continue;
                else {
                    latLngStringMap.put(latLng, realmResults.get(i).getCenterName());
                    Marker marker = googleMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title(realmResults.get(i).getCenterName()));
                    marker.setTag(realmResults.get(i));
                }
            } catch (Exception e) {
                LogHelper.e(TAG, e.toString());
            }
        }

        if (latLng != null) moveCameraToPosition(latLng);
        else if (location == null) moveCameraToPosition(null);
        else moveCameraToPosition(new LatLng(location.getLatitude(), location.getLongitude()));
    }

    private void moveCameraToPosition(LatLng position) {
        if (position == null)
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(INDIA_BOUNDS.getCenter(), 4));
        else {
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder()
                    .target(position).zoom(18).build()));
        }
    }

    public void attemptEnableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, PERMISSIONS[0]) ==
                PackageManager.PERMISSION_GRANTED) {
            if (googleMap != null) {
                googleMap.setMyLocationEnabled(true);
                locationEnabled = true;
                return;
            }
        }

        ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_LOCATION_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode,
                                           @NonNull final String[] permissions,
                                           @NonNull final int[] grantResults) {
        if (requestCode != REQUEST_LOCATION_PERMISSION) {
            return;
        }

        if (permissions.length == PERMISSIONS.length &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (googleMap != null) {
                googleMap.setMyLocationEnabled(true);
                locationEnabled = true;
            }
        } else {
            Toast.makeText(this, "Location Permission Denied", Toast.LENGTH_LONG).show();

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        TrainingCenterDetailBaseActivity.trainingCenter = (TrainingCenter) marker.getTag();
        startActivity(new Intent(this, TrainingCenterDetailActivity.class));
    }
}
