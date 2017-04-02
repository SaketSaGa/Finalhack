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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.ssn.skillindia.R;
import com.ssn.skillindia.adapters.FoldingCellAdapter;
import com.ssn.skillindia.model.CourseDetail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AnimalHusbandryActivity extends AppCompatActivity {

    private ArrayList<CourseDetail> courseDetailArrayList;
    private FoldingCellAdapter foldingCellAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animalhusbandry);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.course_animal_husbandry));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView listView = (ListView) findViewById(R.id.animal_list_view);

        courseDetailArrayList = new ArrayList<>();
        courseDetailArrayList.add(new CourseDetail("AISECT TRAINING CENTER",
                "AISECT", "25000", "Senior Dairy Manager", "8 weeks",
                "25/53, AMmpa Nagar,Karna,Delhi", "5", "3", "9876543210"));
        courseDetailArrayList.add(new CourseDetail("Achariya Technologies",
                "Achariya", "35000", "Senior Dairy Manager", "8 weeks",
                "226,TILAK KHAND,GIRI NAGAR,KALKAJ,delhi", "3", "6", "9876543210"));
        courseDetailArrayList.add(new CourseDetail("AISECT TRAINING CENTER",
                "AISECT", "20000", "Senior Dairy Manager", "8 weeks",
                "557/1, PANA UDHAIN, NARELA, NEW DELHI-110040", "2", "4", "9876543210"));
        courseDetailArrayList.add(new CourseDetail("AISECT TRAINING CENTER",
                "AISECT", "30000", "Senior Dairy Manager", "8 weeks",
                "25/53, AMmpa Nagar,Karna,Delhi", "4", "2", "9876543210"));
        courseDetailArrayList.add(new CourseDetail("AISECT TRAINING CENTER",
                "AISECT", "35000", "Senior Dairy Manager", "8 weeks",
                "25/53, AMmpa Nagar,Karna,Delhi", "5", "7", "9876543210"));
        courseDetailArrayList.add(new CourseDetail("AISECT TRAINING CENTER",
                "AISECT", "45000", "Senior Dairy Manager", "8 weeks",
                "25/53, AMmpa Nagar,Karna,Delhi", "1", "5", "9876543210"));

        foldingCellAdapter = new FoldingCellAdapter(this, courseDetailArrayList);
        listView.setAdapter(foldingCellAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_animal_husbandry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_sort_rating:
                Collections.sort(courseDetailArrayList, new Comparator<CourseDetail>() {
                    @Override
                    public int compare(CourseDetail lhs, CourseDetail rhs) {
                        if (Integer.valueOf(lhs.getRating()) > Integer.valueOf(rhs.getRating()))
                            return -1;
                        else return 1;
                    }
                });
                foldingCellAdapter.notifyDataSetChanged();
                break;

            case R.id.action_sort_distance:
                Collections.sort(courseDetailArrayList, new Comparator<CourseDetail>() {
                    @Override
                    public int compare(CourseDetail lhs, CourseDetail rhs) {
                        if (Integer.valueOf(lhs.getDistance()) > Integer.valueOf(rhs.getDistance()))
                            return -1;
                        else return 1;
                    }
                });
                foldingCellAdapter.notifyDataSetChanged();
                break;

            case R.id.action_sort_course_fee:
                Collections.sort(courseDetailArrayList, new Comparator<CourseDetail>() {
                    @Override
                    public int compare(CourseDetail lhs, CourseDetail rhs) {
                        if (Integer.valueOf(lhs.getCourseFee()) > Integer.valueOf(rhs.getCourseFee()))
                            return -1;
                        else return 1;
                    }
                });
                foldingCellAdapter.notifyDataSetChanged();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
