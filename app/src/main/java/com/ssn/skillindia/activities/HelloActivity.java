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

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ssn.skillindia.R;
import com.ssn.skillindia.model.User;
import com.ssn.skillindia.ui.LabelledSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HelloActivity extends AppCompatActivity {

    @BindView(R.id.activity_hello_name)
    TextView nameTextView;
    @BindView(R.id.activity_hello_email)
    TextView emailTextView;
    @BindView(R.id.activity_hello_age)
    TextView ageTextView;
    @BindView(R.id.activity_hello_mobile)
    TextView mobileTextView;
    @BindView(R.id.activity_hello_spinner_gender)
    LabelledSpinner genderSpinner;
    @BindView(R.id.activity_hello_spinner_state)
    LabelledSpinner stateSpinner;
    @BindView(R.id.activity_hello_spinner_district)
    LabelledSpinner districtSpinner;
    @BindView(R.id.activity_hello_button_start)
    Button startButton;
    @BindView(R.id.activity_hello_spinner_type)
    LabelledSpinner typeSpinner;

    private DatabaseReference databaseReference;
    private FirebaseUser currentUser;

    public static Intent createIntent(Context context, IdpResponse idpResponse) {
        Intent in = IdpResponse.getIntent(idpResponse);
        in.setClass(context, HelloActivity.class);
        return in;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        ButterKnife.bind(this);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        // Prevent SoftKeyboard to pop up on start
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        typeSpinner.setItemsArray(R.array.hello_activity_type_list);
        genderSpinner.setItemsArray(R.array.hello_activity_gender_list);
        stateSpinner.setItemsArray(R.array.hello_activity_state_list);
        districtSpinner.setItemsArray(R.array.hello_activity_district_list);
        initStartButton();

        populateProfile();

        String type = getIntent().getStringExtra("type");
        if (type != null) {
            typeSpinner.getSpinner().setSelection(((ArrayAdapter<String>) typeSpinner.getSpinner()
                    .getAdapter()).getPosition(type));
        }
    }

    private void setSelection(final String label, final LabelledSpinner labelledSpinner) {
        if (label != null) {
            int position = ((ArrayAdapter) labelledSpinner.getSpinner().getAdapter()).getPosition(label);
            labelledSpinner.setSelection(position);
        }
    }

    private void initStartButton() {
        final Drawable pinkArrow = ResourcesCompat.getDrawable(getResources(),
                R.drawable.ic_navigate_next_accent, null);
        if (pinkArrow != null) {
            pinkArrow.setBounds(0, 0, 60, 60);
            startButton.setCompoundDrawables(null, null, pinkArrow, null);
        }
    }

    @OnClick(R.id.activity_hello_button_start)
    void onStartClicked() {
        if (validateAge(ageTextView.getText().toString())) {
            startMainView();
        } else {
            displayErrorWrongAge();
        }
    }

    private boolean validateAge(String age) {
        if (TextUtils.isEmpty(age)) {
            return false;
        } else if (!TextUtils.isDigitsOnly(age)) {
            return false;
        } else {
            int finalAge = Integer.parseInt(age);
            return finalAge > 17 && finalAge < 120;
        }
    }

    public void displayErrorWrongAge() {
        Toast.makeText(getApplicationContext(), getString(R.string.hello_activity_age_invalid), Toast.LENGTH_SHORT).show();
    }

    public void startMainView() {
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        User user = new User();
        user.setId(currentUser.getUid());
        user.setName(nameTextView.getText().toString());
        user.setEmail(emailTextView.getText().toString());
        user.setState(stateSpinner.getSpinner().getSelectedItem().toString());
        user.setDistrict(districtSpinner.getSpinner().getSelectedItem().toString());
        user.setAge(ageTextView.getText().toString());
        user.setGender(genderSpinner.getSpinner().getSelectedItem().toString());
        user.setMobile(mobileTextView.getText().toString());
        String type = typeSpinner.getSpinner().getSelectedItem().toString();
        if (type.equals(getString(R.string.learner))) {
            user.setLearner(true);
            user.setTrainer(sharedPreferences.getBoolean("trainer", false));
            user.setTrainingPartner(sharedPreferences.getBoolean("training_partner", false));
            editor.putString("learner_name", nameTextView.getText().toString());
            editor.putString("learner_email", emailTextView.getText().toString());
            editor.putBoolean("learner", true);
            editor.putBoolean("trainer", sharedPreferences.getBoolean("trainer", false));
            editor.putBoolean("training_partner", sharedPreferences.getBoolean("training_partner", false));
        }
        if (type.equals(getString(R.string.trainer))) {
            user.setLearner(sharedPreferences.getBoolean("learner", false));
            user.setTrainer(true);
            user.setTrainingPartner(sharedPreferences.getBoolean("training_partner", false));
            editor.putString("trainer_name", nameTextView.getText().toString());
            editor.putString("trainer_email", emailTextView.getText().toString());
            editor.putBoolean("learner", sharedPreferences.getBoolean("learner", false));
            editor.putBoolean("trainer", true);
            editor.putBoolean("training_partner", sharedPreferences.getBoolean("training_partner", false));
        }
        if (type.equals(getString(R.string.training_partner))) {
            user.setLearner(sharedPreferences.getBoolean("learner", false));
            user.setTrainer(sharedPreferences.getBoolean("trainer", false));
            user.setTrainingPartner(true);
            editor.putString("training_partner_name", nameTextView.getText().toString());
            editor.putString("training_partner_email", emailTextView.getText().toString());
            editor.putBoolean("learner", sharedPreferences.getBoolean("learner", false));
            editor.putBoolean("trainer", sharedPreferences.getBoolean("trainer", false));
            editor.putBoolean("training_partner", true);
        }
        editor.apply();

        databaseReference.child("users").child(currentUser.getUid()).setValue(user);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void populateProfile() {
        nameTextView.setText(currentUser.getDisplayName());
        emailTextView.setText(currentUser.getEmail());

        Query userQuery = databaseReference.child("users").child(currentUser.getUid());
        userQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    User user = dataSnapshot.getValue(User.class);
                    nameTextView.setText(user.getName());
                    emailTextView.setText(user.getEmail());
                    stateSpinner.getSpinner()
                            .setSelection(((ArrayAdapter<String>) stateSpinner.getSpinner()
                                    .getAdapter()).getPosition(user.getState()));
                    districtSpinner.getSpinner()
                            .setSelection(((ArrayAdapter<String>) districtSpinner.getSpinner()
                                    .getAdapter()).getPosition(user.getDistrict()));
                    ageTextView.setText(String.valueOf(user.getAge()));
                    genderSpinner.getSpinner()
                            .setSelection(((ArrayAdapter<String>) genderSpinner.getSpinner()
                                    .getAdapter()).getPosition(user.getGender()));
                    mobileTextView.setText(user.getMobile());
                } catch (Exception e) {
                    Log.e("populateProfile", e.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
