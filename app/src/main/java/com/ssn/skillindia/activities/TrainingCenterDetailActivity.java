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
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ssn.skillindia.R;
import com.ssn.skillindia.ui.detail.Contact;
import com.ssn.skillindia.utils.LogHelper;

import me.drakeet.multitype.Items;

public class TrainingCenterDetailActivity extends TrainingCenterDetailBaseActivity {
    private static final String TAG = LogHelper.makeLogTag(TrainingCenterDetailActivity.class);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onCreateHeader(ImageView icon) {
    }

    @Override
    protected void onItemsCreated(@NonNull Items items) {
        if (!trainingCenter.getContactName().equals("")) {
            items.add(new Contact(R.drawable.ic_call, trainingCenter.getContactName(),
                    trainingCenter.getContactNumber()));
        }
    }

    @Nullable
    @Override
    protected CharSequence onCreateTitle() {
        collapsingToolbar.setTitle(trainingCenter.getCenterName());
        toolbar1.setTitle(trainingCenter.getPartnerName());
        toolbar2.setTitle(trainingCenter.getPartnerName());
        return super.onCreateTitle();
    }

    @Override
    protected void onActionClick(View action) {
        super.onActionClick(action);
        CardView cardView = (CardView) action;
        RelativeLayout relativeLayout = (RelativeLayout) cardView.getChildAt(0);
        ImageView imageView = (ImageView) relativeLayout.getChildAt(0);
        switch ((int) imageView.getTag()) {
            case R.drawable.ic_call:
                startActivity(new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + trainingCenter.getContactNumber())));
                break;

            case R.drawable.ic_map:
                startActivity(new Intent(TrainingCenterDetailActivity.this, MapActivity.class));
                break;
        }
    }
}
