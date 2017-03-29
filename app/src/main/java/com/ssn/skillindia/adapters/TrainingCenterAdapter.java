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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ssn.skillindia.R;
import com.ssn.skillindia.activities.TrainingCenterDetailActivity;
import com.ssn.skillindia.activities.TrainingCenterDetailBaseActivity;
import com.ssn.skillindia.model.TrainingCenter;

import java.util.List;

public class TrainingCenterAdapter extends RecyclerView.Adapter<TrainingCenterAdapter.ViewHolder> {
    private List<TrainingCenter> trainingCenterList;
    private Context context;

    public TrainingCenterAdapter(Context context, List<TrainingCenter> trainingCenterList) {
        this.trainingCenterList = trainingCenterList;
        this.context = context;
    }

    private Context getContext() {
        return context;
    }

    @Override
    public TrainingCenterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View EventView = inflater.inflate(R.layout.item_training_center, parent, false);

        return new ViewHolder(EventView);
    }

    @Override
    public void onBindViewHolder(final TrainingCenterAdapter.ViewHolder viewHolder, int position) {
        final TrainingCenter trainingCenter = trainingCenterList.get(position);

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
        return trainingCenterList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
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
