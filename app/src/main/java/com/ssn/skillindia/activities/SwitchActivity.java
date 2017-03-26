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

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.ssn.skillindia.R;
import com.ssn.skillindia.fragments.CitizenFragment;
import com.ssn.skillindia.fragments.TrainerFragment;
import com.ssn.skillindia.fragments.TrainingCenterFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SwitchActivity extends AppCompatActivity {

    public static String[] types = {"Citizen", "Trainer", "Training Center"};

    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.frame_container)
    FrameLayout frameContainer;
    @BindView(R.id.drawer_container)
    FrameLayout drawerContainer;
    @BindView(R.id.main_content)
    RelativeLayout mainContent;

    private FirebaseAnalytics firebaseAnalytics;
    private Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        ButterKnife.bind(this);

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(new SwitchViewPagerAdapter(toolbar.getContext(), types));

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment;
                switch (position) {
                    case 0:
                        fragment = new CitizenFragment();
                        break;
                    case 1:
                        fragment = new TrainerFragment();
                        break;
                    case 2:
                        fragment = new TrainingCenterFragment();
                        break;

                    default:
                        fragment = new CitizenFragment();
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, fragment)
                        .commit();
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(position));
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, types[position]);
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "navigation");
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        drawer = new DrawerBuilder(this)
                .withRootView(drawerContainer)
                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(false)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_home).withIcon(FontAwesome.Icon.faw_home),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_free_play).withIcon(FontAwesome.Icon.faw_gamepad),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_custom).withIcon(FontAwesome.Icon.faw_eye),
                        new SectionDrawerItem().withName(R.string.drawer_item_section_header),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(FontAwesome.Icon.faw_cog),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_help).withIcon(FontAwesome.Icon.faw_question).withEnabled(false),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_open_source).withIcon(FontAwesome.Icon.faw_github),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_contact).withIcon(FontAwesome.Icon.faw_bullhorn)
                )
                .withSavedInstance(savedInstanceState)
                .build();
    }
}
