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
import android.view.MenuItem;

import com.ssn.skillindia.R;
import com.ssn.skillindia.SkillIndiaApplication;
import com.ssn.skillindia.adapters.SearchAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.moonmonkeylabs.realmsearchview.RealmSearchView;
import io.realm.Realm;

public class RealmSearchActivity extends AppCompatActivity {

    @BindView(R.id.search_view)
    RealmSearchView searchView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SkillIndiaApplication skillIndiaApplication = (SkillIndiaApplication) getApplicationContext();
        Realm realm = skillIndiaApplication.getRealmHelper().getRealmInstance();
        SearchAdapter searchAdapter = new SearchAdapter(this, realm, "centerName");
        searchView.setAdapter(searchAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
