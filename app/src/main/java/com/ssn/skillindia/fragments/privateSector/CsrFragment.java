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

package com.ssn.skillindia.fragments.privateSector;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ssn.skillindia.R;
import com.ssn.skillindia.model.CsrDetails;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by karthik on 30-03-2017.
 */
public class CsrFragment extends Fragment {
    Button CSRbutton;
    Spinner t_spinner;
    Spinner s_spinner;
    EditText name;
    EditText amount;
    EditText email;
    EditText objective;
    String sector, TOA;
    int lastPrimary;
    private DatabaseReference mDatabase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_csr, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        t_spinner = (Spinner) view.findViewById(R.id.type_spinner);
        ArrayAdapter<CharSequence> t_adapter = ArrayAdapter.createFromResource(getActivity(), R.array.TOA, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        t_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        t_spinner.setAdapter(t_adapter);

        s_spinner = (Spinner) view.findViewById(R.id.sector_spinner);
        ArrayAdapter<CharSequence> s_adapter = ArrayAdapter.createFromResource(getActivity(), R.array.sector, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        s_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        s_spinner.setAdapter(s_adapter);

        CSRbutton = (Button) view.findViewById(R.id.CSRSUBMIT);
        name = (EditText) view.findViewById(R.id.name);
        amount = (EditText) view.findViewById(R.id.amount);
        email = (EditText) view.findViewById(R.id.email);
        objective = (EditText) view.findViewById(R.id.objective_content);
        lastPrimary = 0;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        CSRbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "Thanks for contirbuting...we will get back to you soon", Toast.LENGTH_SHORT);
                toast.show();
                sector = (String) s_spinner.getSelectedItem().toString();
                TOA = (String) t_spinner.getSelectedItem().toString();
                CsrDetails csr = new CsrDetails(name.getText().toString(), TOA.toString(), sector.toString(), email.getText().toString(), amount.getText().toString(), objective.getText().toString());
                SharedPreferences sp = getActivity().getSharedPreferences("CsrDetails", MODE_PRIVATE);

                lastPrimary = sp.getInt("csrid", 0);
                lastPrimary++;

                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("csrid", lastPrimary);
                editor.apply();

                mDatabase.child("csrFB").child(String.valueOf(lastPrimary)).setValue(csr);
            }
        });
    }
}
