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
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;
import com.ssn.skillindia.R;
import com.ssn.skillindia.model.CourseDetail;

import java.util.List;

public class FoldingCellAdapter extends ArrayAdapter<CourseDetail> {
    private Context context;

    public FoldingCellAdapter(Context context, List<CourseDetail> objects) {
        super(context, 0, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final CourseDetail courseDetail = getItem(position);

        FoldingCell cell = (FoldingCell) convertView;
        final ViewHolder viewHolder;
        if (cell == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell = (FoldingCell) vi.inflate(R.layout.activity_animalhusbandry_detail, parent, false);

            viewHolder.foldingCell = (FoldingCell) cell.findViewById(R.id.folding_cell);
            viewHolder.centerName = (TextView) cell.findViewById(R.id.training_center);
            viewHolder.partnerName = (TextView) cell.findViewById(R.id.training_partner);
            viewHolder.courseFee = (TextView) cell.findViewById(R.id.course_fee);
            viewHolder.jobRole = (TextView) cell.findViewById(R.id.job_role);
            viewHolder.courseDuration = (TextView) cell.findViewById(R.id.course_duration);
            viewHolder.address = (TextView) cell.findViewById(R.id.address);
            viewHolder.demandRating = (TextView) cell.findViewById(R.id.demand_rating);
            viewHolder.distance = (TextView) cell.findViewById(R.id.distance);
            viewHolder.contactBtn = (Button) cell.findViewById(R.id.contact_btn);
            cell.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) cell.getTag();
        }

        viewHolder.foldingCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.foldingCell.toggle(false);
            }
        });

        viewHolder.centerName.setText(courseDetail.getCenterName());
        viewHolder.partnerName.setText(courseDetail.getPartnerName());
        viewHolder.courseFee.setText(courseDetail.getCourseFee());
        viewHolder.jobRole.setText(courseDetail.getJobRole());
        viewHolder.courseDuration.setText(courseDetail.getCourseDuration());
        viewHolder.address.setText(courseDetail.getAddress());
        viewHolder.demandRating.setText(courseDetail.getDemandRating());
        viewHolder.distance.setText(courseDetail.getDistance());

        viewHolder.contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + courseDetail.getContactNo())));
            }
        });

        return cell;
    }

    private static class ViewHolder {
        FoldingCell foldingCell;

        TextView centerName;
        TextView partnerName;
        TextView courseFee;
        TextView jobRole;
        TextView courseDuration;
        TextView address;
        TextView demandRating;
        TextView distance;

        Button contactBtn;
    }
}
