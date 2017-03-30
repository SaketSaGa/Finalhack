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
import android.content.Intent;
import android.content.pm.PackageManager;
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

import io.realm.Realm;
import io.realm.RealmResults;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener {
    public static final String[] PERMISSIONS = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
    private static final String TAG = LogHelper.makeLogTag(MapActivity.class);
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private boolean locationEnabled = false;

    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;

    private String location;
    private LatLngBounds SSN_COLLEGE = new LatLngBounds(new LatLng(12.748690, 80.188149),
            new LatLng(12.755693, 80.205421));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        location = getIntent().getStringExtra("location");

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        attemptEnableMyLocation();
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

        // TODO
        //googleMap.setLatLngBoundsForCameraTarget(SSN_COLLEGE);

        SkillIndiaApplication skillIndiaApplication = (SkillIndiaApplication) getApplication();
        RealmHelper realmHelper = skillIndiaApplication.getRealmHelper();
        Realm realm = realmHelper.getRealmInstance();
        RealmResults<TrainingCenter> realmResults = realm.where(TrainingCenter.class).findAll();

        for (int i = 0; i < realmResults.size(); i++) {
            try {
                LatLng latLng = new LatLng(Double.parseDouble(realmResults.get(i).getLatitude()),
                        Double.parseDouble(realmResults.get(i).getLongitude()));
                Marker marker = googleMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(realmResults.get(i).getCenterName()));
                marker.setTag(realmResults.get(i));
            } catch (Exception e) {
                LogHelper.e(TAG, e.toString());
            }
        }

        // TODO
        //moveCameraToPosition(new LatLng());
    }

    private void moveCameraToPosition(LatLng position) {
        if (position == null)
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SSN_COLLEGE.getCenter(), 17));
        else {
            Marker marker = googleMap.addMarker(new MarkerOptions()
                    .position(position)
                    .title(location));
            marker.showInfoWindow();
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