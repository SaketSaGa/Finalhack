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
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.ssn.skillindia.R;
import com.ssn.skillindia.adapters.FoldingCellAdapter;
import com.ssn.skillindia.model.CourseDetail;

import java.util.ArrayList;

public class AnimalHusbandryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animalhusbandry);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.course_animal_husbandry));
        setSupportActionBar(toolbar);

        ListView listView = (ListView) findViewById(R.id.animal_list_view);

        final ArrayList<CourseDetail> courseDetailArrayList = new ArrayList<>();
        courseDetailArrayList.add(new CourseDetail("AISECT TRAINING CENTER",
                "Training Partner: AISECT", "Course Fee: 25000",
                "Job Role: Senior Dairy Manager", "Course Duration: 8 weeks",
                "Address: 25/53, AMmpa Nagar,Karna,Delhi", "5", "3km",
                "9876543210"));
        courseDetailArrayList.add(new CourseDetail("AISECT TRAINING CENTER",
                "Training Partner: AISECT", "Course Fee: 25000",
                "Job Role: Senior Dairy Manager", "Course Duration: 8 weeks",
                "Address: 25/53, AMmpa Nagar,Karna,Delhi", "5", "3km",
                "9876543210"));
        courseDetailArrayList.add(new CourseDetail("AISECT TRAINING CENTER",
                "Training Partner: AISECT", "Course Fee: 25000",
                "Job Role: Senior Dairy Manager", "Course Duration: 8 weeks",
                "Address: 25/53, AMmpa Nagar,Karna,Delhi", "5", "3km",
                "9876543210"));
        courseDetailArrayList.add(new CourseDetail("AISECT TRAINING CENTER",
                "Training Partner: AISECT", "Course Fee: 25000",
                "Job Role: Senior Dairy Manager", "Course Duration: 8 weeks",
                "Address: 25/53, AMmpa Nagar,Karna,Delhi", "5", "3km",
                "9876543210"));
        courseDetailArrayList.add(new CourseDetail("AISECT TRAINING CENTER",
                "Training Partner: AISECT", "Course Fee: 25000",
                "Job Role: Senior Dairy Manager", "Course Duration: 8 weeks",
                "Address: 25/53, AMmpa Nagar,Karna,Delhi", "5", "3km",
                "9876543210"));
        courseDetailArrayList.add(new CourseDetail("AISECT TRAINING CENTER",
                "Training Partner: AISECT", "Course Fee: 25000",
                "Job Role: Senior Dairy Manager", "Course Duration: 8 weeks",
                "Address: 25/53, AMmpa Nagar,Karna,Delhi", "5", "3km",
                "9876543210"));


        final FoldingCellAdapter adapter = new FoldingCellAdapter(this, courseDetailArrayList);
        listView.setAdapter(adapter);
    }
}
