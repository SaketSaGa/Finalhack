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

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ssn.skillindia.R;
import com.ssn.skillindia.model.CsrDetails;
import com.ssn.skillindia.ui.LabelledSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by karthik on 30-03-2017.
 */
public class CsrActivity extends AppCompatActivity {
    int lastPrimary;

    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.type_spinner)
    LabelledSpinner typeSpinner;
    @BindView(R.id.sector_spinner)
    LabelledSpinner sectorSpinner;
    @BindView(R.id.amount)
    EditText amount;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.objective_content)
    EditText objectiveContent;
    @BindView(R.id.submit_btn)
    AppCompatButton submitBtn;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_csr);
        ButterKnife.bind(this);

        typeSpinner.setItemsArray(R.array.type_of_agreement);
        sectorSpinner.setItemsArray(R.array.sector);

        lastPrimary = 0;
        databaseReference = FirebaseDatabase.getInstance().getReference();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CsrActivity.this, "Thanks for contributing. We will get back to you soon", Toast.LENGTH_LONG).show();

                CsrDetails csr = new CsrDetails(name.getText().toString(),
                        typeSpinner.getSpinner().getSelectedItem().toString(),
                        sectorSpinner.getSpinner().getSelectedItem().toString(),
                        email.getText().toString(), amount.getText().toString(),
                        objectiveContent.getText().toString());
                SharedPreferences sp = getSharedPreferences("CsrDetails", MODE_PRIVATE);

                lastPrimary = sp.getInt("csrid", 0);
                lastPrimary++;

                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("csrid", lastPrimary);
                editor.apply();

                databaseReference.child("csrFB").child(String.valueOf(lastPrimary)).setValue(csr);

                startActivity(new Intent(CsrActivity.this, DashboardActivity.class));
            }
        });
    }
}
