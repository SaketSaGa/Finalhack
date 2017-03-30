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
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;
import com.ssn.skillindia.R;
import com.ssn.skillindia.model.CourseStatus;
import com.ssn.skillindia.model.TimeLineModel;
import com.ssn.skillindia.utils.DateTimeUtils;
import com.ssn.skillindia.utils.LogHelper;
import com.ssn.skillindia.utils.VectorDrawableUtils;

import java.util.List;

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder> {
    private static final String TAG = LogHelper.makeLogTag(TimeLineAdapter.class);
    private List<TimeLineModel> mFeedList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public TimeLineAdapter(List<TimeLineModel> feedList) {
        mFeedList = feedList;
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        mLayoutInflater = LayoutInflater.from(mContext);
        View view = mLayoutInflater.inflate(R.layout.item_timeline, parent, false);

        return new TimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {
        final int pos = position;
        final TimeLineModel timeLineModel = mFeedList.get(position);

        if (timeLineModel.getStatus() == CourseStatus.INACTIVE) {
            holder.mTimelineView.setMarker(VectorDrawableUtils.getDrawable(mContext, R.drawable.ic_marker_inactive, android.R.color.darker_gray));
            holder.checkImageView.setVisibility(View.GONE);
        } else if (timeLineModel.getStatus() == CourseStatus.ACTIVE) {
            holder.mTimelineView.setMarker(VectorDrawableUtils.getDrawable(mContext, R.drawable.ic_marker_active, R.color.colorPrimary));
            holder.checkImageView.setVisibility(View.VISIBLE);
        } else {
            holder.mTimelineView.setMarker(ContextCompat.getDrawable(mContext, R.drawable.ic_marker), ContextCompat.getColor(mContext, R.color.colorPrimary));
            holder.checkImageView.setVisibility(View.GONE);
        }

        if (!timeLineModel.getDate().isEmpty()) {
            holder.mDate.setVisibility(View.VISIBLE);
            holder.mDate.setText(DateTimeUtils.parseDateTime(timeLineModel.getDate(), "yyyy-MM-dd HH:mm", "hh:mm a, dd-MMM-yyyy"));
        } else
            holder.mDate.setVisibility(View.GONE);

        holder.mMessage.setText(timeLineModel.getMessage());

        holder.checkImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogHelper.e("CLICK", v.toString());
                try {
                    timeLineModel.setStatus(CourseStatus.COMPLETED);
                    mFeedList.get(pos + 1).setStatus(CourseStatus.ACTIVE);
                    notifyItemChanged(pos);
                    notifyItemChanged(pos + 1);
                } catch (Exception e) {
                    LogHelper.e(TAG, e.toString());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (mFeedList != null ? mFeedList.size() : 0);
    }

    public class TimeLineViewHolder extends RecyclerView.ViewHolder {

        TextView mDate;
        TextView mMessage;
        TimelineView mTimelineView;
        ImageView checkImageView;

        public TimeLineViewHolder(View itemView, int viewType) {
            super(itemView);

            mDate = (TextView) itemView.findViewById(R.id.text_timeline_date);
            mMessage = (TextView) itemView.findViewById(R.id.text_timeline_title);
            mTimelineView = (TimelineView) itemView.findViewById(R.id.time_marker);
            checkImageView = (ImageView) itemView.findViewById(R.id.check_image_view);

            mTimelineView.initLine(viewType);
        }
    }

}
