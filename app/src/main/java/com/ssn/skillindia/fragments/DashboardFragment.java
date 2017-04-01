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

package com.ssn.skillindia.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.ssn.skillindia.R;
import com.ssn.skillindia.adapters.DashboardAdapter;
import com.ssn.skillindia.model.DashboardItem;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.Arrays;
import java.util.List;

public class DashboardFragment extends Fragment implements
        DiscreteScrollView.ScrollStateChangeListener<DashboardAdapter.ViewHolder>,
        DiscreteScrollView.OnItemChangedListener<DashboardAdapter.ViewHolder> {

    private DiscreteScrollView learnerPicker, trainerPicker, privateSectorPicker;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int iconColor = getActivity().getResources().getColor(R.color.grayIconTint);

        List<DashboardItem> learnerItems = Arrays.asList(
                new DashboardItem(getString(R.string.drawer_item_search_center),
                        new IconicsDrawable(getActivity()).icon(FontAwesome.Icon.faw_map)
                                .color(iconColor).sizeDp(48)),
                new DashboardItem(getString(R.string.drawer_item_search_courses),
                        new IconicsDrawable(getActivity()).icon(FontAwesome.Icon.faw_search)
                                .color(iconColor).sizeDp(48)),
                new DashboardItem(getString(R.string.drawer_item_world_competition),
                        new IconicsDrawable(getActivity()).icon(FontAwesome.Icon.faw_globe)
                                .color(iconColor).sizeDp(48)),
                new DashboardItem(getString(R.string.drawer_item_check_progress),
                        new IconicsDrawable(getActivity()).icon(FontAwesome.Icon.faw_tasks)
                                .color(iconColor).sizeDp(48)),
                new DashboardItem(getString(R.string.drawer_item_webinars),
                        new IconicsDrawable(getActivity()).icon(FontAwesome.Icon.faw_youtube)
                                .color(Color.parseColor("#FF0000")).sizeDp(48)),
                new DashboardItem(getString(R.string.drawer_item_register_pmkvy),
                        new IconicsDrawable(getActivity()).icon(FontAwesome.Icon.faw_sign_in)
                                .color(iconColor).sizeDp(48)),
                new DashboardItem(getString(R.string.drawer_item_report_issues),
                        new IconicsDrawable(getActivity()).icon(FontAwesome.Icon.faw_bug)
                                .color(iconColor).sizeDp(48)),
                new DashboardItem(getString(R.string.drawer_item_contact),
                        new IconicsDrawable(getActivity()).icon(FontAwesome.Icon.faw_phone)
                                .color(iconColor).sizeDp(48)));

        List<DashboardItem> trainerItems = Arrays.asList(
                new DashboardItem(getString(R.string.drawer_item_search_center),
                        new IconicsDrawable(getActivity()).icon(FontAwesome.Icon.faw_map)
                                .color(iconColor).sizeDp(48)),
                new DashboardItem(getString(R.string.drawer_item_world_competition),
                        new IconicsDrawable(getActivity()).icon(FontAwesome.Icon.faw_globe)
                                .color(iconColor).sizeDp(48)),
                new DashboardItem(getString(R.string.drawer_item_register_nsdc),
                        new IconicsDrawable(getActivity()).icon(FontAwesome.Icon.faw_sign_in)
                                .color(iconColor).sizeDp(48)),
                new DashboardItem(getString(R.string.drawer_item_upload_webinars),
                        new IconicsDrawable(getActivity()).icon(FontAwesome.Icon.faw_upload)
                                .color(iconColor).sizeDp(48)),
                new DashboardItem(getString(R.string.drawer_item_report_issues),
                        new IconicsDrawable(getActivity()).icon(FontAwesome.Icon.faw_bug)
                                .color(iconColor).sizeDp(48)),
                new DashboardItem(getString(R.string.drawer_item_contact),
                        new IconicsDrawable(getActivity()).icon(FontAwesome.Icon.faw_phone)
                                .color(iconColor).sizeDp(48)));

        List<DashboardItem> privateSectorItems = Arrays.asList(
                new DashboardItem(getString(R.string.drawer_item_register_nsdc),
                        new IconicsDrawable(getActivity()).icon(FontAwesome.Icon.faw_map)
                                .color(iconColor).sizeDp(48)),
                new DashboardItem(getString(R.string.drawer_item_tenders),
                        new IconicsDrawable(getActivity()).icon(FontAwesome.Icon.faw_search)
                                .color(iconColor).sizeDp(48)),
                new DashboardItem(getString(R.string.drawer_item_contribute_csr),
                        new IconicsDrawable(getActivity()).icon(FontAwesome.Icon.faw_money)
                                .color(iconColor).sizeDp(48)),
                new DashboardItem(getString(R.string.drawer_item_report_issues),
                        new IconicsDrawable(getActivity()).icon(FontAwesome.Icon.faw_bug)
                                .color(iconColor).sizeDp(48)),
                new DashboardItem(getString(R.string.drawer_item_contact),
                        new IconicsDrawable(getActivity()).icon(FontAwesome.Icon.faw_phone)
                                .color(iconColor).sizeDp(48)));

        learnerPicker = (DiscreteScrollView) view.findViewById(R.id.learner_picker);
        trainerPicker = (DiscreteScrollView) view.findViewById(R.id.trainer_picker);
        privateSectorPicker = (DiscreteScrollView) view.findViewById(R.id.private_sector_picker);

        setupDiscreteScrollView(learnerPicker, learnerItems);
        setupDiscreteScrollView(trainerPicker, trainerItems);
        setupDiscreteScrollView(privateSectorPicker, privateSectorItems);
    }

    @Override
    public void onScroll(float scrollPosition, @NonNull DashboardAdapter.ViewHolder currentHolder,
                         @NonNull DashboardAdapter.ViewHolder newCurrent) {
    }

    @Override
    public void onCurrentItemChanged(@NonNull DashboardAdapter.ViewHolder holder, int position) {
        holder.showText();
    }

    @Override
    public void onScrollStart(@NonNull DashboardAdapter.ViewHolder holder, int position) {
        holder.hideText();
    }

    @Override
    public void onScrollEnd(@NonNull DashboardAdapter.ViewHolder holder, int position) {
    }

    private void setupDiscreteScrollView(DiscreteScrollView discreteScrollView,
                                         List<DashboardItem> dashboardItems) {
        discreteScrollView.setAdapter(new DashboardAdapter(dashboardItems));
        discreteScrollView.setOnItemChangedListener(this);
        discreteScrollView.setScrollStateChangeListener(this);
        discreteScrollView.scrollToPosition(2);
        discreteScrollView.setItemTransitionTimeMillis(150);
        discreteScrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());
    }
}
