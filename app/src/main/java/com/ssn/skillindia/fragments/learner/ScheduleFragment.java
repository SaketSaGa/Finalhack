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

package com.ssn.skillindia.fragments.learner;

import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.ssn.skillindia.R;
import com.ssn.skillindia.SkillIndiaApplication;
import com.ssn.skillindia.model.Event;
import com.ssn.skillindia.utils.DateTimeUtils;
import com.ssn.skillindia.utils.LogHelper;
import com.ssn.skillindia.utils.RealmHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.facebook.FacebookSdk.getApplicationContext;
import static com.ssn.skillindia.utils.DateTimeUtils.dateToCalendar;

public class ScheduleFragment extends Fragment implements WeekView.EventClickListener,
        MonthLoader.MonthChangeListener, WeekView.EventLongPressListener {
    private static final String TAG = LogHelper.makeLogTag(ScheduleFragment.class);

    private WeekView weekView;
    private RealmResults<Event> eventRealmResults;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SkillIndiaApplication skillIndiaApplication = (SkillIndiaApplication) getApplicationContext();
        RealmHelper realmHelper = skillIndiaApplication.getRealmHelper();
        Realm realm = realmHelper.getRealmInstance();
        eventRealmResults = realm.where(Event.class).equalTo("type", "ELC").findAll();

        weekView = (WeekView) view.findViewById(R.id.weekView);
        weekView.setOnEventClickListener(this);
        weekView.setMonthChangeListener(this);
        weekView.setEventLongPressListener(this);
        weekView.setNumberOfVisibleDays(3);

        setupDateTimeInterpreter();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            Calendar calendar = DateTimeUtils.dateToCalendar(simpleDateFormat.parse("08-03-2017 09:00:00"));
            weekView.goToDate(calendar);
            weekView.goToHour(7);
        } catch (Exception e) {
            Log.e("DATE", e.toString());
        }
    }

    private void setupDateTimeInterpreter() {
        weekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" dd/MM", Locale.getDefault());
                return weekday.toUpperCase() + format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour) {
                return "" + hour + ":00";
            }
        });
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        List<WeekViewEvent> events = new ArrayList<>();

        for (Event event : eventRealmResults) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date date = simpleDateFormat.parse(event.getStartTime());
                Calendar startTime = dateToCalendar(date);
                date = simpleDateFormat.parse(event.getEndTime());
                Calendar endTime = dateToCalendar(date);
                WeekViewEvent weekViewEvent = new WeekViewEvent(event.getId(), event.getName(),
                        startTime, endTime);
                weekViewEvent.setColor(Color.parseColor(getColorForType("ELC")));
                weekViewEvent.setLocation("\n" + event.getLocation());
                events.add(weekViewEvent);
            } catch (Exception e) {
                LogHelper.e(TAG, e.toString());
            }
        }

        List<WeekViewEvent> matchedEvents = new ArrayList<>();
        for (WeekViewEvent event : events) {
            if (eventMatches(event, newYear, newMonth)) {
                matchedEvents.add(event);
            }
        }
        return matchedEvents;
    }

    @Override
    public void onEventClick(WeekViewEvent weekViewEvent, RectF rectF) {
        // TODO
        //EventDetailBaseActivity.event = eventRealmResults.where().equalTo("id", weekViewEvent.getId()).findFirst();
        //startActivity(new Intent(DayViewActivity.this, EventDetailActivity.class));
    }

    @Override
    public void onEventLongPress(WeekViewEvent weekViewEvent, RectF rectF) {

    }

    private boolean eventMatches(WeekViewEvent event, int year, int month) {
        return (event.getStartTime().get(Calendar.YEAR) == year &&
                event.getStartTime().get(Calendar.MONTH) == month - 1) ||
                (event.getEndTime().get(Calendar.YEAR) == year &&
                        event.getEndTime().get(Calendar.MONTH) == month - 1);
    }

    private String getColorForType(String type) {
        switch (type) {
            case "null":
                return "#16a085";
            case "ELC":
                return "#16a085";
            case "Saaral":
                return "#16a085";
            case "Music":
                return "#16a085";
            case "Variety":
                return "#16a085";
            case "Dance":
                return "#16a085";
            case "Quiz":
                return "#16a085";

            default:
                return "#16a085";
        }
    }
}
