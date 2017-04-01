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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.ssn.skillindia.R;
import com.ssn.skillindia.activities.SwitchActivity;
import com.ssn.skillindia.fragments.DashboardFragment;
import com.ssn.skillindia.fragments.learner.SearchTrainingCenterFragment;
import com.ssn.skillindia.model.DashboardItem;

import java.util.List;

import static com.ssn.skillindia.activities.SwitchActivity.FIREBASE_ANALYTICS;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {

    private RecyclerView parentRecycler;
    private List<DashboardItem> data;
    private SwitchActivity switchActivity;

    public DashboardAdapter(SwitchActivity switchActivity, List<DashboardItem> data) {
        this.switchActivity = switchActivity;
        this.data = data;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        parentRecycler = recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_dashboard_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DashboardItem dashboardItem = data.get(position);
        holder.imageView.setImageDrawable(dashboardItem.getIcon());
        holder.textView.setText(dashboardItem.getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (dashboardItem.getName()) {
                    case "Search Training Center":
                        switchFragment(new SearchTrainingCenterFragment(),
                                switchActivity.getString(R.string.drawer_item_search_center));
                        break;

                    default:
                        switchFragment(new DashboardFragment(),
                                switchActivity.getString(R.string.dashboard));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private void switchFragment(Fragment fragment, String name) {
        SwitchActivity.CURRENT_FRAGMENT = name;
        switchActivity.invalidateOptionsMenu();

        Bundle bundle = new Bundle();
        switchActivity.getSupportActionBar().setTitle(name);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        switchActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, fragment)
                .commit();
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "navigation");
        FIREBASE_ANALYTICS.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private FrameLayout cardView;
        private ImageView imageView;
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (FrameLayout) itemView.findViewById(R.id.item_card);
            imageView = (ImageView) itemView.findViewById(R.id.item_icon);
            textView = (TextView) itemView.findViewById(R.id.item_name);

            itemView.findViewById(R.id.item_card).setOnClickListener(this);
        }

        public void showText() {
            int parentHeight = ((View) imageView.getParent()).getHeight();
            float scale = (parentHeight - textView.getHeight()) / (float) imageView.getHeight();
            imageView.setPivotX(imageView.getWidth() * 0.5f);
            imageView.setPivotY(0);
            imageView.animate().scaleX(scale)
                    .withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            textView.setVisibility(View.VISIBLE);
                        }
                    })
                    .scaleY(scale).setDuration(200)
                    .start();
        }

        public void hideText() {
            textView.setVisibility(View.INVISIBLE);
            imageView.animate().scaleX(1f).scaleY(1f)
                    .setDuration(200)
                    .start();
        }

        @Override
        public void onClick(View v) {
            parentRecycler.smoothScrollToPosition(getAdapterPosition());
        }
    }
}
