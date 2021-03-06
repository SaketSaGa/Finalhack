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
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ssn.skillindia.R;
import com.ssn.skillindia.adapters.TimeLineAdapter;
import com.ssn.skillindia.model.CourseStatus;
import com.ssn.skillindia.model.TimeLineModel;

import java.util.ArrayList;
import java.util.List;

public class CheckCourseProgressFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private TimeLineAdapter mTimeLineAdapter;
    private List<TimeLineModel> mDataList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_check_course_progress, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(getLinearLayoutManager());
        mRecyclerView.setHasFixedSize(true);

        setDataListItems();
        mTimeLineAdapter = new TimeLineAdapter(mDataList);
        mRecyclerView.setAdapter(mTimeLineAdapter);

        return rootView;
    }

    private LinearLayoutManager getLinearLayoutManager() {
        return new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    }

    private void setDataListItems() {
        mDataList.add(new TimeLineModel("Basic of Information Technology", "", CourseStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Basic Principles of Programming", "2017-02-12 08:00", CourseStatus.ACTIVE));
        mDataList.add(new TimeLineModel("C Programming Language", "2017-02-11 21:00", CourseStatus.INACTIVE));
        mDataList.add(new TimeLineModel("Object Orientated Programming", "2017-02-11 18:00", CourseStatus.INACTIVE));
        mDataList.add(new TimeLineModel("Writing HTML Code", "2017-02-11 09:30", CourseStatus.INACTIVE));
        mDataList.add(new TimeLineModel("Data Structures using C++", "2017-02-11 08:00", CourseStatus.INACTIVE));
        mDataList.add(new TimeLineModel("Visual Studio .NET", "2017-02-10 15:00", CourseStatus.INACTIVE));
        mDataList.add(new TimeLineModel("Java J2EE", "2017-02-10 14:30", CourseStatus.INACTIVE));
        mDataList.add(new TimeLineModel("Order placed successfully", "2017-02-10 14:00", CourseStatus.INACTIVE));
    }
}
