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

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.ssn.skillindia.R;
import com.ssn.skillindia.fragments.learner.CheckProgressFragment;
import com.ssn.skillindia.fragments.learner.LearnerDashboardFragment;
import com.ssn.skillindia.fragments.learner.ScheduleFragment;
import com.ssn.skillindia.fragments.learner.SearchTrainingCenterFragment;
import com.ssn.skillindia.fragments.learner.WebinarsFragment;
import com.ssn.skillindia.fragments.trainingPartner.TrainingPartnerDashboardFragment;
import com.ssn.skillindia.fragments.trainer.TrainerDashboardFragment;
import com.ssn.skillindia.model.LocalJSONSource;
import com.ssn.skillindia.utils.LogHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SwitchActivity extends AppCompatActivity {

    private static final String TAG = LogHelper.makeLogTag(SwitchActivity.class);
    public static String[] TYPES = {"Learner", "Trainer", "Training Partner"};
    public static FirebaseAnalytics FIREBASE_ANALYTICS;
    public static String CURRENT_FRAGMENT;
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
    @BindView(R.id.bottom_bar)
    BottomBar bottomBar;
    List<PrimaryDrawerItem> learnerItemList, trainerItemList, trainingPartnerItemList;
    PrimaryDrawerItem item1, item2, item3, item4, item5, item6, item7;
    PrimaryDrawerItem item11, item12, item13, item14, item15, item16;
    PrimaryDrawerItem item21, item22, item23, item24;
    private AccountHeader headerResult = null;
    private Drawer drawer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        ButterKnife.bind(this);

        FIREBASE_ANALYTICS = FirebaseAnalytics.getInstance(this);
        CURRENT_FRAGMENT = getString(R.string.dashboard);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final IProfile profile1 = new ProfileDrawerItem().withName(TYPES[0])
                .withEmail("learner@gmail.com").withIcon(R.drawable.ic_learner);
        final IProfile profile2 = new ProfileDrawerItem().withName(TYPES[1])
                .withEmail("trainer@github.com").withIcon(R.drawable.ic_trainer);
        final IProfile profile3 = new ProfileDrawerItem().withName(TYPES[2])
                .withEmail("training_partner@outlook.com").withIcon(R.drawable.ic_training_partner);

        item1 = new PrimaryDrawerItem().withName(R.string.drawer_item_search_courses).withIdentifier(1).withIcon(FontAwesome.Icon.faw_search);
        item2 = new PrimaryDrawerItem().withName(R.string.drawer_item_search_center).withIdentifier(2).withIcon(FontAwesome.Icon.faw_map);
        item3 = new PrimaryDrawerItem().withName(R.string.drawer_item_webinars).withIdentifier(3).withIcon(FontAwesome.Icon.faw_youtube);
        item4 = new PrimaryDrawerItem().withName(R.string.drawer_item_check_progress).withIdentifier(4).withIcon(FontAwesome.Icon.faw_tasks);
        item5 = new PrimaryDrawerItem().withName(R.string.drawer_item_world_competition).withIdentifier(5).withIcon(FontAwesome.Icon.faw_globe);
        item6 = new PrimaryDrawerItem().withName(R.string.drawer_item_report_issues).withIdentifier(6).withIcon(FontAwesome.Icon.faw_bug);
        item7 = new PrimaryDrawerItem().withName(R.string.drawer_item_contact_trainer).withIdentifier(7).withIcon(FontAwesome.Icon.faw_phone);

        item11 = new PrimaryDrawerItem().withName(R.string.drawer_item_search_center).withIdentifier(11).withIcon(FontAwesome.Icon.faw_map);
        item12 = new PrimaryDrawerItem().withName(R.string.drawer_item_upload_webinars).withIdentifier(12).withIcon(FontAwesome.Icon.faw_youtube);
        item13 = new PrimaryDrawerItem().withName(R.string.drawer_item_contact_training_center).withIdentifier(13).withIcon(FontAwesome.Icon.faw_phone);
        item14 = new PrimaryDrawerItem().withName(R.string.drawer_item_update_skill_set).withIdentifier(14).withIcon(FontAwesome.Icon.faw_phone);
        item15 = new PrimaryDrawerItem().withName(R.string.drawer_item_world_competition).withIdentifier(15).withIcon(FontAwesome.Icon.faw_globe);
        item16 = new PrimaryDrawerItem().withName(R.string.drawer_item_report_issues).withIdentifier(16).withIcon(FontAwesome.Icon.faw_bug);

        item21 = new PrimaryDrawerItem().withName(R.string.drawer_item_setup_training_center).withIdentifier(21).withIcon(FontAwesome.Icon.faw_search);
        item22 = new PrimaryDrawerItem().withName(R.string.drawer_item_manage_trainers).withIdentifier(22).withIcon(FontAwesome.Icon.faw_search);
        item23 = new PrimaryDrawerItem().withName(R.string.drawer_item_enroll_learner_pmkvy).withIdentifier(23).withIcon(FontAwesome.Icon.faw_search);
        item24 = new PrimaryDrawerItem().withName(R.string.drawer_item_become_nsdc_partner).withIdentifier(24).withIcon(FontAwesome.Icon.faw_search);

        PrimaryDrawerItem[] learnerItems = {item1, item2, item3, item4, item5, item6, item7};
        PrimaryDrawerItem[] trainerItems = {item11, item12, item13, item14, item15, item16};
        PrimaryDrawerItem[] trainingPartnerItems = {item21, item22, item23, item24};
        learnerItemList = new ArrayList<>();
        trainerItemList = new ArrayList<>();
        trainingPartnerItemList = new ArrayList<>();
        Collections.addAll(learnerItemList, learnerItems);
        Collections.addAll(trainerItemList, trainerItems);
        Collections.addAll(trainingPartnerItemList, trainingPartnerItems);

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(profile1, profile2, profile3)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        CURRENT_FRAGMENT = getString(R.string.dashboard);
                        switch (profile.getName().toString()) {
                            case "Learner":
                                updateDrawerItems(learnerItemList);
                                switchFragment(new LearnerDashboardFragment(), getString(R.string.learner));
                                break;
                            case "Trainer":
                                updateDrawerItems(trainerItemList);
                                switchFragment(new TrainerDashboardFragment(), getString(R.string.trainer));
                                break;
                            case "Training Partner":
                                updateDrawerItems(trainingPartnerItemList);
                                switchFragment(new TrainingPartnerDashboardFragment(), getString(R.string.training_partner));
                                break;
                        }
                        return true;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        drawer = new DrawerBuilder(this)
                .withRootView(drawerContainer)
                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withAccountHeader(headerResult)
                .addDrawerItems(item1, item2, item3, item4, item5, item6, item7)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch ((int) drawerItem.getIdentifier()) {
                            case 1:
                                switchFragment(new SearchTrainingCenterFragment(),
                                        getString(R.string.drawer_item_search_center));
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                switchFragment(new CheckProgressFragment(),
                                        getString(R.string.drawer_item_check_progress));
                                break;
                            case 5:
                                switchFragment(new WebinarsFragment(),
                                        getString(R.string.drawer_item_webinars));
                                break;
                            case 6:
                                break;
                            case 7:
                                break;
                            case 8:
                                break;
                            case 23:
                                // TODO
                                //switchFragment(new CsrActivity(), getString(R.string.drawer_item_contribute_csr));
                                break;
                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        drawer.deselect();

        new LocalJSONSource(this);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_learner:
                        switchFragment(new LearnerDashboardFragment(), getString(R.string.learner));
                        break;
                    case R.id.tab_trainer:
                        switchFragment(new TrainerDashboardFragment(), getString(R.string.trainer));
                        break;
                    case R.id.tab_training_partner:
                        switchFragment(new TrainingPartnerDashboardFragment(), getString(R.string.training_partner));
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_map:
                startActivity(new Intent(this, MapActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        switch (CURRENT_FRAGMENT) {
            case "Dashboard":
                getMenuInflater().inflate(R.menu.menu_dashboard, menu);
                break;
            case "Search Training Center":
                getMenuInflater().inflate(R.menu.menu_training_center, menu);
                break;
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        drawer.saveInstanceState(outState);
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    private void switchFragment(Fragment fragment, String name) {
        CURRENT_FRAGMENT = name;
        invalidateOptionsMenu();

        if (CURRENT_FRAGMENT.equals(getString(R.string.learner)) ||
                CURRENT_FRAGMENT.equals(getString(R.string.trainer)) ||
                CURRENT_FRAGMENT.equals(getString(R.string.training_partner)))
            bottomBar.setVisibility(View.VISIBLE);
        else bottomBar.setVisibility(View.GONE);

        Bundle bundle = new Bundle();
        toolbar.setTitle(name);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, fragment)
                .commit();
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "navigation");
        FIREBASE_ANALYTICS.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    private void updateDrawerItems(List<PrimaryDrawerItem> items) {
        drawer.removeAllItems();
        for (PrimaryDrawerItem item : items) drawer.addItem(item);
    }

    public void webinarsOnClick(View view) {
        switchFragment(new WebinarsFragment(), getString(R.string.drawer_item_webinars));
    }

    public void searchTrainingCenterOnClick(View view) {
        switchFragment(new SearchTrainingCenterFragment(), getString(R.string.drawer_item_search_center));
    }

    public void scheduleOnClick(View view) {
        switchFragment(new ScheduleFragment(), getString(R.string.drawer_item_schedule));
    }
}
