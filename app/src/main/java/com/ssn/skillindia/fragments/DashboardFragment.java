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

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ssn.skillindia.R;
import com.ssn.skillindia.activities.SwitchActivity;
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
                        getActivity().getResources().getDrawable(R.drawable.ic_map_faw)),
                new DashboardItem(getString(R.string.drawer_item_search_courses),
                        getActivity().getResources().getDrawable(R.drawable.ic_search)),
                new DashboardItem(getString(R.string.drawer_item_world_competition),
                        getActivity().getResources().getDrawable(R.drawable.ic_globe)),
                new DashboardItem(getString(R.string.drawer_item_check_progress),
                        getActivity().getResources().getDrawable(R.drawable.ic_tasks_list)),
                new DashboardItem(getString(R.string.drawer_item_webinars),
                        getActivity().getResources().getDrawable(R.drawable.ic_youtube)),
                new DashboardItem(getString(R.string.drawer_item_register_pmkvy),
                        getActivity().getResources().getDrawable(R.drawable.ic_sign_in)),
                new DashboardItem(getString(R.string.drawer_item_report_issues),
                        getActivity().getResources().getDrawable(R.drawable.ic_bug)),
                new DashboardItem(getString(R.string.drawer_item_contact),
                        getActivity().getResources().getDrawable(R.drawable.ic_telephone)));

        List<DashboardItem> trainerItems = Arrays.asList(
                new DashboardItem(getString(R.string.drawer_item_search_center),
                        getActivity().getResources().getDrawable(R.drawable.ic_map)),
                new DashboardItem(getString(R.string.drawer_item_world_competition),
                        getActivity().getResources().getDrawable(R.drawable.ic_globe)),
                new DashboardItem(getString(R.string.drawer_item_register_nsdc),
                        getActivity().getResources().getDrawable(R.drawable.ic_sign_in)),
                new DashboardItem(getString(R.string.drawer_item_upload_webinars),
                        getActivity().getResources().getDrawable(R.drawable.ic_upload)),
                new DashboardItem(getString(R.string.drawer_item_report_issues),
                        getActivity().getResources().getDrawable(R.drawable.ic_bug)),
                new DashboardItem(getString(R.string.drawer_item_contact),
                        getActivity().getResources().getDrawable(R.drawable.ic_telephone)));

        List<DashboardItem> privateSectorItems = Arrays.asList(
                new DashboardItem(getString(R.string.drawer_item_register_nsdc),
                        getActivity().getResources().getDrawable(R.drawable.ic_map_faw)),
                new DashboardItem(getString(R.string.drawer_item_tenders),
                        getActivity().getResources().getDrawable(R.drawable.ic_search)),
                new DashboardItem(getString(R.string.drawer_item_contribute_csr),
                        getActivity().getResources().getDrawable(R.drawable.ic_money)),
                new DashboardItem(getString(R.string.drawer_item_report_issues),
                        getActivity().getResources().getDrawable(R.drawable.ic_bug)),
                new DashboardItem(getString(R.string.drawer_item_contact),
                        getActivity().getResources().getDrawable(R.drawable.ic_telephone)));

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
        discreteScrollView.setAdapter(new DashboardAdapter((SwitchActivity) getActivity(), dashboardItems));
        discreteScrollView.setOnItemChangedListener(this);
        discreteScrollView.setScrollStateChangeListener(this);
        discreteScrollView.scrollToPosition(2);
        discreteScrollView.setItemTransitionTimeMillis(150);
        discreteScrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());
    }
}
