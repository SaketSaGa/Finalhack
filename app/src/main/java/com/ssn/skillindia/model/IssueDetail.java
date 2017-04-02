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

/**
 * Created by kaush on 02-04-2017.
 */
public class IssueDetail {
    private String centerName;
    private String location;
    private String typeOfIssue;
    private String Description;


    public IssueDetail(String name, String location, String typeofissue, String desc) {
        this.centerName = name;
        this.location = location;
        this.typeOfIssue = typeofissue;
        this.Description = desc;
    }

    public String getCenterName() {
        return centerName;
    }

    public String getLocation() {
        return location;
    }

    public String getTypeOfIssue() {
        return typeOfIssue;
    }

    public String getDescription() {
        return Description;
    }

    @Override
    public String toString() {
        return "IssueDetail{" +
                "centerName='" + centerName + '\'' +
                ", location='" + location + '\'' +
                ", typeOfIssue='" + typeOfIssue + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }
}
