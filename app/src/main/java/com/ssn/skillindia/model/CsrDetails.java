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
 * Created by karthik on 30-03-2017.
 */

public class CsrDetails {
    private String name;
    private String typeofagreement;
    private String sector;
    private String email;
    private String amount;
    private String objective;


    public CsrDetails(String name, String typeofagreement, String sector, String email, String amount, String objective) {
        this.name = name;
        this.typeofagreement = typeofagreement;
        this.sector = sector;
        this.email = email;
        this.amount = amount;
        this.objective = objective;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getTypeofagreement() {
        return typeofagreement;
    }

    public void setTypeofagreement(String typeofagreement) {
        this.typeofagreement = typeofagreement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }
}
