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

package com.ssn.skillindia.fragments.learner;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ssn.skillindia.R;
import com.ssn.skillindia.adapters.WebinarsTabAdapter;

public class WebinarsTabFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_webinars_tab, container, false);
        WebinarsTabAdapter webinarsTabAdapter = new WebinarsTabAdapter(getActivity().getSupportFragmentManager());

        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.webinars_view_pager);
        viewPager.setAdapter(webinarsTabAdapter);
        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.webinars_tabs);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setOffscreenPageLimit(0);

        return rootView;
    }
}
