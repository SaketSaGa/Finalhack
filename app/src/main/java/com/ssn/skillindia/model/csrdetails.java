package com.ssn.skillindia.model;

/**
 * Created by karthik on 30-03-2017.
 */

public class csrdetails {
    private String name;
    private String typeofagreement;
    private String sector;
    private String email;
    private String amount;
    private String objective;


    public csrdetails(String name, String typeofagreement, String sector, String email, String amount, String objective) {
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
