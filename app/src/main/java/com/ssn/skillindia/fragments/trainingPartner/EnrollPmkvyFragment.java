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

package com.ssn.skillindia.fragments.trainingPartner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.zagum.switchicon.SwitchIconView;
import com.ssn.skillindia.R;
import com.ssn.skillindia.activities.EnrollPmkvyDetailActivity;


public class EnrollPmkvyFragment extends Fragment {

    Button Nextpage_button;
    private SwitchIconView switchIcon1;
    private SwitchIconView switchIcon2;
    private SwitchIconView switchIcon3;
    private SwitchIconView switchIcon4;
    private SwitchIconView switchIcon5;
    private View button1;
    private View button2;
    private View button3;
    private View button4;
    private View button5;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_enroll_pmkvy, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        switchIcon1 = (SwitchIconView) view.findViewById(R.id.switchIconView1);
        switchIcon2 = (SwitchIconView) view.findViewById(R.id.switchIconView2);
        switchIcon3 = (SwitchIconView) view.findViewById(R.id.switchIconView3);
        switchIcon4 = (SwitchIconView) view.findViewById(R.id.switchIconView4);
        switchIcon5 = (SwitchIconView) view.findViewById(R.id.switchIconView5);

        Nextpage_button = (Button) view.findViewById(R.id.nextButton);

        button1 = view.findViewById(R.id.button1);
        button2 = view.findViewById(R.id.button2);
        button3 = view.findViewById(R.id.button3);
        button4 = view.findViewById(R.id.button4);
        button5 = view.findViewById(R.id.button5);


        Nextpage_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EnrollPmkvyDetailActivity.class));
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchIcon1.switchState();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchIcon2.switchState();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchIcon3.switchState();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchIcon4.switchState();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchIcon5.switchState();
            }
        });

    }
}
