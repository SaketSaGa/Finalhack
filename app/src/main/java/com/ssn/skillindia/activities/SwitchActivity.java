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
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
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
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.ssn.skillindia.R;
import com.ssn.skillindia.fragments.learner.AgricultureFragment;
import com.ssn.skillindia.fragments.learner.CheckProgressFragment;
import com.ssn.skillindia.fragments.learner.LearnerDashboardFragment;
import com.ssn.skillindia.fragments.learner.ScheduleFragment;
import com.ssn.skillindia.fragments.learner.SearchTrainingCenterFragment;
import com.ssn.skillindia.fragments.learner.SearchTrainingCourseFragment;
import com.ssn.skillindia.fragments.learner.WebinarsTabFragment;
import com.ssn.skillindia.fragments.trainer.ReportIssuesFragment;
import com.ssn.skillindia.fragments.trainer.TrainerDashboardFragment;
import com.ssn.skillindia.fragments.trainingPartner.EnrollPmkvyFragment;
import com.ssn.skillindia.fragments.trainingPartner.TrainingPartnerDashboardFragment;
import com.ssn.skillindia.model.LocalJSONSource;
import com.ssn.skillindia.utils.LogHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.mobiwise.materialintro.animation.MaterialIntroListener;
import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.view.MaterialIntroView;

public class SwitchActivity extends AppCompatActivity implements MaterialIntroListener {

    private static final String TAG = LogHelper.makeLogTag(SwitchActivity.class);
    private static final String MENU_SEARCH_ID_TAG = "menuSearchIdTag";
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
    PrimaryDrawerItem item1, item2, item3, item4, item5, item6, item7, item8;
    PrimaryDrawerItem item11, item12, item13, item14, item15, item16;
    PrimaryDrawerItem item21, item22, item23, item24;
    private AccountHeader headerResult = null;
    private Drawer drawer = null;
    private String tab;
    private SharedPreferences.Editor tabEditor;
    private SharedPreferences.Editor userEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        ButterKnife.bind(this);

        FIREBASE_ANALYTICS = FirebaseAnalytics.getInstance(this);
        CURRENT_FRAGMENT = getString(R.string.dashboard);

        SharedPreferences tabSharedPreferences = getSharedPreferences("tab", MODE_PRIVATE);
        final SharedPreferences userSharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        tabEditor = tabSharedPreferences.edit();
        tab = tabSharedPreferences.getString("tab", "learner");
        setBottomBarDefaultTab();

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        boolean p1, p2, p3;
        p1 = userSharedPreferences.getBoolean("learner", false);
        p2 = userSharedPreferences.getBoolean("trainer", false);
        p3 = userSharedPreferences.getBoolean("training_partner", false);
        IProfile profile1 = null, profile2 = null, profile3 = null;

        final String learnerName, trainerName, trainingPartnerName;
        learnerName = userSharedPreferences.getString("learner_name", "");
        trainerName = userSharedPreferences.getString("trainer_name", "");
        trainingPartnerName = userSharedPreferences.getString("training_partner_name", "");
        if (p1)
            profile1 = new ProfileDrawerItem().withName(userSharedPreferences.getString("learner_name", ""))
                    .withEmail(userSharedPreferences.getString("learner_email", "")).withIcon(R.drawable.ic_learner);
        if (p2)
            profile2 = new ProfileDrawerItem().withName(userSharedPreferences.getString("trainer_name", ""))
                    .withEmail(userSharedPreferences.getString("trainer_email", "")).withIcon(R.drawable.ic_trainer);
        if (p3)
            profile3 = new ProfileDrawerItem().withName(userSharedPreferences.getString("training_partner_name", ""))
                    .withEmail(userSharedPreferences.getString("training_partner_email", "")).withIcon(R.drawable.ic_training_partner);

        item1 = new PrimaryDrawerItem().withName(R.string.drawer_item_search_courses).withIdentifier(1).withIcon(FontAwesome.Icon.faw_search);
        item2 = new PrimaryDrawerItem().withName(R.string.drawer_item_search_center).withIdentifier(2).withIcon(FontAwesome.Icon.faw_map);
        item3 = new PrimaryDrawerItem().withName(R.string.drawer_item_webinars).withIdentifier(3).withIcon(FontAwesome.Icon.faw_youtube);
        item4 = new PrimaryDrawerItem().withName(R.string.drawer_item_check_progress).withIdentifier(4).withIcon(FontAwesome.Icon.faw_tasks);
        item5 = new PrimaryDrawerItem().withName(R.string.drawer_item_world_competition).withIdentifier(5).withIcon(FontAwesome.Icon.faw_globe);
        item6 = new PrimaryDrawerItem().withName(R.string.drawer_item_schedule).withIdentifier(6).withIcon(FontAwesome.Icon.faw_calendar);
        item7 = new PrimaryDrawerItem().withName(R.string.drawer_item_report_issues).withIdentifier(7).withIcon(FontAwesome.Icon.faw_bug);
        item8 = new PrimaryDrawerItem().withName(R.string.drawer_item_contact_trainer).withIdentifier(8).withIcon(FontAwesome.Icon.faw_phone);

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

        final PrimaryDrawerItem[] learnerItems = {item1, item2, item3, item4, item5, item6, item7, item8};
        final PrimaryDrawerItem[] trainerItems = {item11, item12, item13, item14, item15, item16};
        final PrimaryDrawerItem[] trainingPartnerItems = {item21, item22, item23, item24};
        learnerItemList = new ArrayList<>();
        trainerItemList = new ArrayList<>();
        trainingPartnerItemList = new ArrayList<>();
        Collections.addAll(learnerItemList, learnerItems);
        Collections.addAll(trainerItemList, trainerItems);
        Collections.addAll(trainingPartnerItemList, trainingPartnerItems);

        AccountHeader.OnAccountHeaderListener onAccountHeaderListener = new AccountHeader.OnAccountHeaderListener() {
            @Override
            public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                CURRENT_FRAGMENT = getString(R.string.dashboard);
                if (profile.getName().toString().equals(learnerName)) {
                    updateDrawerItems(learnerItemList, getString(R.string.learner));
                    switchFragment(new LearnerDashboardFragment(), getString(R.string.learner));
                }
                if (profile.getName().toString().equals(trainerName)) {
                    updateDrawerItems(trainerItemList, getString(R.string.trainer));
                    switchFragment(new TrainerDashboardFragment(), getString(R.string.trainer));
                }
                if (profile.getName().toString().equals(trainingPartnerName)) {
                    updateDrawerItems(trainingPartnerItemList, getString(R.string.training_partner));
                    switchFragment(new TrainingPartnerDashboardFragment(), getString(R.string.training_partner));
                }
                return true;
            }
        };

        AccountHeaderBuilder accountHeaderBuilder = new AccountHeaderBuilder().withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .withOnAccountHeaderListener(onAccountHeaderListener)
                .withSavedInstance(savedInstanceState);
        if (profile1 != null) accountHeaderBuilder.addProfiles(profile1);
        if (profile2 != null) accountHeaderBuilder.addProfiles(profile2);
        if (profile3 != null) accountHeaderBuilder.addProfiles(profile3);

        headerResult = accountHeaderBuilder.build();

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
                        switch ((int) drawerItem.getIdentifier()) {
                            case 1:
                                switchFragment(new SearchTrainingCourseFragment(),
                                        getString(R.string.drawer_item_search_courses));
                                break;
                            case 2:
                                switchFragment(new SearchTrainingCenterFragment(),
                                        getString(R.string.drawer_item_search_center));
                                break;
                            case 3:
                                switchFragment(new WebinarsTabFragment(),
                                        getString(R.string.drawer_item_webinars));
                                break;
                            case 4:
                                switchFragment(new CheckProgressFragment(),
                                        getString(R.string.drawer_item_check_progress));
                                break;
                            case 5:
                                break;
                            case 6:
                                switchFragment(new ScheduleFragment(),
                                        getString(R.string.drawer_item_check_progress));
                                break;
                            case 7:
                            case 16:
                                switchFragment(new ReportIssuesFragment(),
                                        getString(R.string.drawer_item_report_issues));
                                break;
                            case 8:
                                break;
                            case 23:
                                break;
                            case 97:
                                userEditor = userSharedPreferences.edit();
                                userEditor.putBoolean("learner", false);
                                userEditor.apply();
                                recreate();
                                break;
                            case 98:
                                userEditor = userSharedPreferences.edit();
                                userEditor.putBoolean("trainer", false);
                                userEditor.apply();
                                recreate();
                                break;
                            case 99:
                                userEditor = userSharedPreferences.edit();
                                userEditor.putBoolean("training_partner", false);
                                userEditor.apply();
                                recreate();
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
                        tab = "learner";
                        tabEditor.putString("tab", "learner");
                        tabEditor.apply();
                        updateDrawerItems(learnerItemList, getString(R.string.learner));
                        switchFragment(new LearnerDashboardFragment(), getString(R.string.learner));
                        break;
                    case R.id.tab_trainer:
                        tab = "trainer";
                        tabEditor.putString("tab", "trainer");
                        tabEditor.apply();
                        updateDrawerItems(trainerItemList, getString(R.string.trainer));
                        switchFragment(new TrainerDashboardFragment(), getString(R.string.trainer));
                        break;
                    case R.id.tab_training_partner:
                        tab = "training_partner";
                        tabEditor.putString("tab", "training_partner");
                        tabEditor.apply();
                        updateDrawerItems(trainingPartnerItemList, getString(R.string.training_partner));
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
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        switch (item.getItemId()) {
            case R.id.action_map:
                startActivity(new Intent(this, MapActivity.class));
                break;

            case R.id.action_help:
                View helpView = toolbar.findViewById(R.id.action_help);
                showIntro(helpView, MENU_SEARCH_ID_TAG, getString(R.string.help_intro), FocusGravity.CENTER);
                break;

            case R.id.action_englis:
                conf.locale = new Locale("en");
                res.updateConfiguration(conf, dm);
                recreate();
                break;
            case R.id.action_hindi:
                conf.locale = new Locale("hi");
                res.updateConfiguration(conf, dm);
                recreate();
                break;
            case R.id.action_tamil:
                conf.locale = new Locale("ta");
                res.updateConfiguration(conf, dm);
                recreate();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        switch (CURRENT_FRAGMENT) {
            case "Dashboard":
            case "Learner":
            case "Trainer":
            case "Training Partner":
                getMenuInflater().inflate(R.menu.menu_dashboard, menu);
                break;
            case "Search Training Center":
                getMenuInflater().inflate(R.menu.menu_training_center, menu);
                break;
            default:
                getMenuInflater().inflate(R.menu.menu_dashboard, menu);
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
        if (drawer != null && drawer.isDrawerOpen())
            drawer.closeDrawer();
        else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            CURRENT_FRAGMENT = getString(R.string.dashboard);
            setBottomBarDefaultTab();
            bottomBar.setVisibility(View.VISIBLE);
        } else super.onBackPressed();
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
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.frame_container, fragment)
                .addToBackStack(name)
                .commit();
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "navigation");
        FIREBASE_ANALYTICS.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    private void updateDrawerItems(List<PrimaryDrawerItem> items, String type) {
        drawer.removeAllItems();
        for (PrimaryDrawerItem item : items) drawer.addItem(item);
        drawer.addItem(new DividerDrawerItem());
        switch (type) {
            case "Learner":
                drawer.addItem(new SecondaryDrawerItem().withName(getString(R.string.drawer_item_log_out))
                        .withIdentifier(97).withIcon(FontAwesome.Icon.faw_sign_out));
                break;
            case "Trainer":
                drawer.addItem(new SecondaryDrawerItem().withName(getString(R.string.drawer_item_log_out))
                        .withIdentifier(98).withIcon(FontAwesome.Icon.faw_sign_out));
                break;
            case "Training Partner":
                drawer.addItem(new SecondaryDrawerItem().withName(getString(R.string.drawer_item_log_out))
                        .withIdentifier(99).withIcon(FontAwesome.Icon.faw_sign_out));
                break;
        }
    }

    public void webinarsOnClick(View view) {
        switchFragment(new WebinarsTabFragment(), getString(R.string.drawer_item_webinars));
    }

    public void searchTrainingCenterOnClick(View view) {
        switchFragment(new SearchTrainingCenterFragment(), getString(R.string.drawer_item_search_center));
    }

    public void searchTrainingCourseOnClick(View view) {
        switchFragment(new SearchTrainingCourseFragment(), getString(R.string.drawer_item_search_courses));
    }

    public void checkProgressOnClick(View view) {
        switchFragment(new CheckProgressFragment(), getString(R.string.drawer_item_check_progress));
    }

    public void scheduleOnClick(View view) {
        switchFragment(new ScheduleFragment(), getString(R.string.drawer_item_schedule));
    }

    public void enrollPmkvyOnClick(View view) {
        switchFragment(new EnrollPmkvyFragment(), getString(R.string.drawer_item_enroll_learner_pmkvy));
    }

    public void searchSectorAgriculture(View view) {
        switchFragment(new AgricultureFragment(), getString(R.string.sector_agriculture_actionbar));
    }

    public void husbandryOnClick(View view) {
        startActivity(new Intent(this, AnimalHusbandryActivity.class));
    }

    public void reportIssuesOnClick(View view) {
        switchFragment(new ReportIssuesFragment(), getString(R.string.drawer_item_report_issues));
    }

    private void setBottomBarDefaultTab() {
        switch (tab) {
            case "learner":
                switchFragment(new LearnerDashboardFragment(), getString(R.string.learner));
                bottomBar.setDefaultTab(R.id.tab_learner);
                break;
            case "trainer":
                switchFragment(new TrainerDashboardFragment(), getString(R.string.trainer));
                bottomBar.setDefaultTab(R.id.tab_trainer);
                break;
            case "training_partner":
                switchFragment(new TrainingPartnerDashboardFragment(), getString(R.string.training_partner));
                bottomBar.setDefaultTab(R.id.tab_training_partner);
                break;
        }
    }

    public void setupOnClick(View view) {
        startActivity(new Intent(this, HelloActivity.class).putExtra("type", CURRENT_FRAGMENT));
    }

    public void showIntro(View view, String id, String text, FocusGravity focusGravity) {
        new MaterialIntroView.Builder(this)
                .enableDotAnimation(true)
                .setFocusGravity(focusGravity)
                .setFocusType(Focus.MINIMUM)
                .setDelayMillis(100)
                .enableFadeAnimation(true)
                .performClick(true)
                .setInfoText(text)
                .setTarget(view)
                .setListener(this)
                .setUsageId(id)
                .show();
    }

    @Override
    public void onUserClicked(String s) {
    }
}