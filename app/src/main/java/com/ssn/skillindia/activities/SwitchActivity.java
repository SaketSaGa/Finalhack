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
import com.ssn.skillindia.R;
import com.ssn.skillindia.fragments.DashboardFragment;
import com.ssn.skillindia.fragments.LearnerFragment;
import com.ssn.skillindia.fragments.PrivateSectorFragment;
import com.ssn.skillindia.fragments.TrainerFragment;
import com.ssn.skillindia.fragments.learner.CheckProgressFragment;
import com.ssn.skillindia.fragments.learner.SearchTrainingCenterFragment;
import com.ssn.skillindia.fragments.learner.WebinarsFragment;
import com.ssn.skillindia.fragments.privateSector.CsrFragment;
import com.ssn.skillindia.model.LocalJSONSource;
import com.ssn.skillindia.utils.LogHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SwitchActivity extends AppCompatActivity {

    private static final String TAG = LogHelper.makeLogTag(SwitchActivity.class);
    public static String[] types = {"Learner", "Trainer", "Private Sector"};

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

    List<PrimaryDrawerItem> learnerItemList, trainerItemList, privateSectorItemList;
    PrimaryDrawerItem item1, item2, item3, item4, item5, item6, item7, item8;
    PrimaryDrawerItem item11, item12, item13, item14, item15, item16;
    PrimaryDrawerItem item21, item22, item23, item24, item25;

    private FirebaseAnalytics firebaseAnalytics;
    private AccountHeader headerResult = null;
    private Drawer drawer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        ButterKnife.bind(this);

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final IProfile profile1 = new ProfileDrawerItem().withName(types[0])
                .withEmail("learner@gmail.com").withIcon(R.drawable.ic_learner);
        final IProfile profile2 = new ProfileDrawerItem().withName(types[1])
                .withEmail("trainer@github.com").withIcon(R.drawable.ic_trainer);
        final IProfile profile3 = new ProfileDrawerItem().withName(types[2])
                .withEmail("private_sector@outlook.com").withIcon(R.drawable.ic_private_sector);

        item1 = new PrimaryDrawerItem().withName(R.string.drawer_item_search_center).withIdentifier(1).withIcon(FontAwesome.Icon.faw_map);
        item2 = new PrimaryDrawerItem().withName(R.string.drawer_item_search_courses).withIdentifier(2).withIcon(FontAwesome.Icon.faw_search);
        item3 = new PrimaryDrawerItem().withName(R.string.drawer_item_world_competition).withIdentifier(3).withIcon(FontAwesome.Icon.faw_globe);
        item4 = new PrimaryDrawerItem().withName(R.string.drawer_item_check_progress).withIdentifier(4).withIcon(FontAwesome.Icon.faw_tasks);
        item5 = new PrimaryDrawerItem().withName(R.string.drawer_item_webinars).withIdentifier(5).withIcon(FontAwesome.Icon.faw_youtube);
        item6 = new PrimaryDrawerItem().withName(R.string.drawer_item_register_pmkvy).withIdentifier(6).withIcon(FontAwesome.Icon.faw_sign_in);
        item7 = new PrimaryDrawerItem().withName(R.string.drawer_item_report_issues).withIdentifier(7).withIcon(FontAwesome.Icon.faw_bug);
        item8 = new PrimaryDrawerItem().withName(R.string.drawer_item_contact).withIdentifier(8).withIcon(FontAwesome.Icon.faw_phone);

        item11 = new PrimaryDrawerItem().withName(R.string.drawer_item_search_center).withIdentifier(11).withIcon(FontAwesome.Icon.faw_map);
        item12 = new PrimaryDrawerItem().withName(R.string.drawer_item_world_competition).withIdentifier(12).withIcon(FontAwesome.Icon.faw_globe);
        item13 = new PrimaryDrawerItem().withName(R.string.drawer_item_register_nsdc).withIdentifier(13).withIcon(FontAwesome.Icon.faw_tasks);
        item14 = new PrimaryDrawerItem().withName(R.string.drawer_item_upload_webinars).withIdentifier(14).withIcon(FontAwesome.Icon.faw_youtube);
        item15 = new PrimaryDrawerItem().withName(R.string.drawer_item_report_issues).withIdentifier(15).withIcon(FontAwesome.Icon.faw_bug);
        item16 = new PrimaryDrawerItem().withName(R.string.drawer_item_contact).withIdentifier(16).withIcon(FontAwesome.Icon.faw_phone);

        item21 = new PrimaryDrawerItem().withName(R.string.drawer_item_register_nsdc).withIdentifier(21).withIcon(FontAwesome.Icon.faw_map);
        item22 = new PrimaryDrawerItem().withName(R.string.drawer_item_tenders).withIdentifier(22).withIcon(FontAwesome.Icon.faw_search);
        item23 = new PrimaryDrawerItem().withName(R.string.drawer_item_contribute_csr).withIdentifier(23).withIcon(FontAwesome.Icon.faw_globe);
        item24 = new PrimaryDrawerItem().withName(R.string.drawer_item_report_issues).withIdentifier(24).withIcon(FontAwesome.Icon.faw_bug);
        item25 = new PrimaryDrawerItem().withName(R.string.drawer_item_contact).withIdentifier(25).withIcon(FontAwesome.Icon.faw_phone);

        PrimaryDrawerItem[] learnerItems = {item1, item2, item3, item4, item5, item6, item7, item8};
        PrimaryDrawerItem[] trainerItems = {item11, item12, item13, item14, item15, item16};
        PrimaryDrawerItem[] privateSectorItems = {item21, item22, item23, item24, item25};
        learnerItemList = new ArrayList<>();
        trainerItemList = new ArrayList<>();
        privateSectorItemList = new ArrayList<>();
        Collections.addAll(learnerItemList, learnerItems);
        Collections.addAll(trainerItemList, trainerItems);
        Collections.addAll(privateSectorItemList, privateSectorItems);

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(profile1, profile2, profile3)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        Fragment fragment;
                        Bundle bundle = new Bundle();
                        switch (profile.getName().toString()) {
                            case "Learner":
                                updateDrawerItems(learnerItemList);

                                fragment = new LearnerFragment();
                                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, types[0]);
                                break;
                            case "Trainer":
                                updateDrawerItems(trainerItemList);

                                fragment = new TrainerFragment();
                                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, types[1]);
                                break;
                            case "Private Sector":
                                updateDrawerItems(privateSectorItemList);

                                fragment = new PrivateSectorFragment();
                                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, types[2]);
                                break;

                            default:
                                fragment = new LearnerFragment();
                        }
                        switchFragment(fragment, bundle);
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
                .addDrawerItems(item1, item2, item3, item4, item5, item6, item7, item8)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Bundle bundle = new Bundle();
                        switch ((int) drawerItem.getIdentifier()) {
                            case 1:
                                toolbar.setTitle(getString(R.string.drawer_item_search_center));
                                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME,
                                        getString(R.string.drawer_item_search_center));
                                switchFragment(new SearchTrainingCenterFragment(), bundle);
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                toolbar.setTitle(getString(R.string.drawer_item_check_progress));
                                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME,
                                        getString(R.string.drawer_item_check_progress));
                                switchFragment(new CheckProgressFragment(), bundle);
                                break;
                            case 5:
                                toolbar.setTitle(getString(R.string.drawer_item_webinars));
                                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME,
                                        getString(R.string.drawer_item_webinars));
                                switchFragment(new WebinarsFragment(), bundle);
                                break;
                            case 6:
                                break;
                            case 7:
                                break;
                            case 8:
                                break;
                            case 23:
                                toolbar.setTitle(getString(R.string.drawer_item_contribute_csr));
                                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME,
                                        getString(R.string.drawer_item_contribute_csr));
                                switchFragment(new CsrFragment(), bundle);
                                break;
                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        drawer.deselect();

        switchFragment(new DashboardFragment(), new Bundle());
        new LocalJSONSource(this);
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

    private void switchFragment(Fragment fragment, Bundle bundle) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, fragment)
                .commit();
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "navigation");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    private void updateDrawerItems(List<PrimaryDrawerItem> items) {
        drawer.removeAllItems();
        for (PrimaryDrawerItem item : items) drawer.addItem(item);
    }
}
