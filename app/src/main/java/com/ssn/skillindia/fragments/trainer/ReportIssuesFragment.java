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

package com.ssn.skillindia.fragments.trainer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ssn.skillindia.R;
import com.ssn.skillindia.model.IssueDetail;
import com.ssn.skillindia.ui.LabelledSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by kaush on 02-04-2017.
 */

public class ReportIssuesFragment extends Fragment {

    int lastPrimary;

    @BindView(R.id.centerName)
    EditText centerName;
    @BindView(R.id.issue_spinner)
    LabelledSpinner issueSpinner;
    @BindView(R.id.location)
    EditText location;
    @BindView(R.id.description)
    EditText description;
    @BindView(R.id.submit_issue)
    AppCompatButton submitBtn;

    private DatabaseReference databaseReference;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_report_issues, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        issueSpinner.setItemsArray(R.array.issues);

        lastPrimary = 0;
        databaseReference = FirebaseDatabase.getInstance().getReference();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Issue Reported", Toast.LENGTH_LONG).show();

                IssueDetail issue = new IssueDetail(centerName.getText().toString(),
                        location.getText().toString(),
                        issueSpinner.getSpinner().getSelectedItem().toString(),
                        description.getText().toString());

                SharedPreferences sp = getActivity().getSharedPreferences("issues", MODE_PRIVATE);

                lastPrimary = sp.getInt("id", 0);
                lastPrimary++;

                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("id", lastPrimary);
                editor.apply();

                databaseReference.child("record").child(String.valueOf(lastPrimary)).setValue(issue);
                //databaseReference.child("csrFB").child(String.valueOf(lastPrimary)).setValue(csr);
            }
        });
        return rootView;
    }

}
