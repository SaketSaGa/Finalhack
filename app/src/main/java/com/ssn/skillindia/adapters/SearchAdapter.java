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

package com.ssn.skillindia.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ssn.skillindia.R;
import com.ssn.skillindia.activities.TrainingCenterDetailActivity;
import com.ssn.skillindia.activities.TrainingCenterDetailBaseActivity;
import com.ssn.skillindia.model.TrainingCenter;

import co.moonmonkeylabs.realmsearchview.RealmSearchAdapter;
import co.moonmonkeylabs.realmsearchview.RealmSearchViewHolder;
import io.realm.Realm;

public class SearchAdapter extends RealmSearchAdapter<TrainingCenter, SearchAdapter.ViewHolder> {

    private Context context;

    public SearchAdapter(Context context, Realm realm, String filterColumnName) {
        super(context, realm, filterColumnName);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup parent, int i) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View EventView = inflater.inflate(R.layout.item_training_center, parent, false);

        return new ViewHolder(EventView);
    }

    @Override
    public void onBindRealmViewHolder(ViewHolder viewHolder, int position) {
        final TrainingCenter trainingCenter = realmResults.get(position);

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrainingCenterDetailBaseActivity.trainingCenter = trainingCenter;
                context.startActivity(new Intent(context, TrainingCenterDetailActivity.class));
            }
        });

        viewHolder.centerNameTV.setText(trainingCenter.getCenterName());
        viewHolder.partnerNameTV.setText(trainingCenter.getPartnerName());
    }

    @Override
    public int getItemCount() {
        return realmResults.size();
    }

    public static class ViewHolder extends RealmSearchViewHolder {
        private LinearLayout linearLayout;
        private TextView centerNameTV;
        private TextView partnerNameTV;

        public ViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.center_ll);

            centerNameTV = (TextView) itemView.findViewById(R.id.center_name_tv);
            partnerNameTV = (TextView) itemView.findViewById(R.id.partner_name_tv);
        }
    }
}
