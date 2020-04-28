package com.example.sqliteclass1;

import java.util.Date;

public class Visitor {

    private String name;
    private long identification;
    private int apartmentId;
    private String visitorType;
    private Date checkinDate;

    public Visitor(String name, long identification, int apartmentId, String visitorType, Date checkinDate) {
        super();

        this.name = name;
        this.identification = identification;
        this.apartmentId = apartmentId;
        this.visitorType = visitorType;
        this.checkinDate = checkinDate;
    }

    // Getters
    public String getName() {
        return this.name;
    }

    public long getIdentification() {
        return this.identification;
    }

    public int getApartmentId() {
        return this.apartmentId;
    }

    public String getVisitorType() {
        return this.visitorType;
    }

    public Date getCheckinDate() {
        return this.checkinDate;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setIdentification(long identification) {
        this.identification = identification;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public void setVisitorType(String visitorType) {
        this.visitorType = visitorType;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

}
