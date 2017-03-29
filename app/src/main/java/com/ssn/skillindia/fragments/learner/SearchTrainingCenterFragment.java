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
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ssn.skillindia.R;
import com.ssn.skillindia.SkillIndiaApplication;
import com.ssn.skillindia.model.TrainingCenter;
import com.ssn.skillindia.ui.LabelledSpinner;
import com.ssn.skillindia.utils.LogHelper;
import com.ssn.skillindia.utils.RealmHelper;
import com.ssn.skillindia.utils.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class SearchTrainingCenterFragment extends Fragment {

    private static final String TAG = LogHelper.makeLogTag(SearchTrainingCenterFragment.class);

    @BindView(R.id.state_spinner)
    LabelledSpinner stateSpinner;
    @BindView(R.id.district_spinner)
    LabelledSpinner districtSpinner;
    @BindView(R.id.sector_spinner)
    LabelledSpinner sectorSpinner;

    private Realm realm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_training_center, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SkillIndiaApplication skillIndiaApplication = (SkillIndiaApplication) getActivity().getApplication();
        RealmHelper realmHelper = skillIndiaApplication.getRealmHelper();
        realm = realmHelper.getRealmInstance();

        setSpinner(stateSpinner, "state");
        setSpinner(districtSpinner, "district");
        setSpinner(sectorSpinner, "sector");
    }

    private void setSpinner(LabelledSpinner spinner, String type) {
        ArrayList<String> stringArrayList = new ArrayList<>();

        Class<?> c = TrainingCenter.class;
        Method method;
        try {
            method = c.getDeclaredMethod("get" + StringUtils.firstCharToUpperCase(type));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return;
        }

        RealmResults<TrainingCenter> realmResults = realm.where(TrainingCenter.class).distinct(type);
        for (int i = 0; i < realmResults.size(); i++) {
            try {
                stringArrayList.add(String.valueOf(method.invoke(realmResults.get(i))));
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        spinner.setItemsArray(stringArrayList);
    }
}
