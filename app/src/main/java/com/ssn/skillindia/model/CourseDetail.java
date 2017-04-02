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

package com.ssn.skillindia.model;

import android.view.View;

public class CourseDetail {

    private String centerName;
    private String partnerName;
    private String courseFee;
    private String jobRole;
    private String courseDuration;
    private String address;
    private String demandRating;
    private String distance;
    private String contactNo;

    private View.OnClickListener requestBtnClickListener;

    public CourseDetail() {
    }

    public CourseDetail(String centerName, String partnerName, String courseFee, String jobRole,
                        String courseDuration, String address, String demandRating, String distance,
                        String contactNo) {
        this.centerName = centerName;
        this.partnerName = partnerName;
        this.courseFee = courseFee;
        this.jobRole = jobRole;
        this.courseDuration = courseDuration;
        this.address = address;
        this.demandRating = demandRating;
        this.distance = distance;
        this.contactNo = contactNo;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(String courseFee) {
        this.courseFee = courseFee;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDemandRating() {
        return demandRating;
    }

    public void setDemandRating(String demandRating) {
        this.demandRating = demandRating;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public View.OnClickListener getRequestBtnClickListener() {
        return requestBtnClickListener;
    }

    public void setRequestBtnClickListener(View.OnClickListener requestBtnClickListener) {
        this.requestBtnClickListener = requestBtnClickListener;
    }

    @Override
    public String toString() {
        return "CourseDetail{" +
                "centerName='" + centerName + '\'' +
                ", partnerName='" + partnerName + '\'' +
                ", courseFee='" + courseFee + '\'' +
                ", jobRole='" + jobRole + '\'' +
                ", courseDuration='" + courseDuration + '\'' +
                ", address='" + address + '\'' +
                ", demandRating='" + demandRating + '\'' +
                ", distance='" + distance + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", requestBtnClickListener=" + requestBtnClickListener +
                '}';
    }
}
