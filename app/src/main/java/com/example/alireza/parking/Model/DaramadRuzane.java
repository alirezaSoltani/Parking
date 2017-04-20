package com.example.alireza.parking.Model;

import java.util.logging.StreamHandler;

/**
 * Created by AliReza on 4/5/2017.
 */

public class DaramadRuzane {



    private int id;
    private String day;
    private String date;
    private String Mablagh;
    private String des;
    private String shift;
    private String isBaygani;
    private String status;


    public DaramadRuzane(int id, String day, String date, String mablagh, String des, String shift , String isBaygani , String status) {
        this.id = id;
        this.day = day;
        this.date = date;
        Mablagh = mablagh;
        this.des = des;
        this.shift = shift;
        this.isBaygani = isBaygani;
        this.status = status;
    }
    public DaramadRuzane(String day, String date, String mablagh, String des, String shift , String isBaygani , String status) {
        this.day = day;
        this.date = date;
        Mablagh = mablagh;
        this.des = des;
        this.shift = shift;
        this.isBaygani = isBaygani;
        this.status = status;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMablagh() {
        return Mablagh;
    }

    public void setMablagh(String mablagh) {
        Mablagh = mablagh;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getIsBaygani() {
        return isBaygani;
    }

    public void setIsBaygani(String isBaygani) {
        this.isBaygani = isBaygani;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
