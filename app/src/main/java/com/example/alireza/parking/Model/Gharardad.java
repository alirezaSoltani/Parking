package com.example.alireza.parking.Model;

/**
 * Created by AliReza on 4/5/2017.
 */

public class Gharardad  {
    private int id;
    private String name;
    private String tel;
    private String car_Type;
    private String car_pelak;
    private String mablagh;
    private String des;
    private String isBaygani;
    private String status;



    public Gharardad(String name, String tel, String car_Type, String car_pelak, String mablagh, String des , String isBaygani , String status) {
        this.name = name;
        this.tel = tel;
        this.car_Type = car_Type;
        this.car_pelak = car_pelak;
        this.mablagh = mablagh;
        this.des = des;
        this.isBaygani = isBaygani;
        this.status = status;
    }
    public Gharardad(int id, String name, String tel, String car_Type, String car_pelak, String mablagh, String des , String isBaygani , String status) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.car_Type = car_Type;
        this.car_pelak = car_pelak;
        this.mablagh = mablagh;
        this.des = des;
        this.isBaygani = isBaygani;
        this.status = status;
    }
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCar_Type() {
        return car_Type;
    }

    public void setCar_Type(String car_Type) {
        this.car_Type = car_Type;
    }

    public String getCar_pelak() {
        return car_pelak;
    }

    public void setCar_pelak(String car_pelak) {
        this.car_pelak = car_pelak;
    }

    public String getMablagh() {
        return mablagh;
    }

    public void setMablagh(String mablagh) {
        this.mablagh = mablagh;
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
