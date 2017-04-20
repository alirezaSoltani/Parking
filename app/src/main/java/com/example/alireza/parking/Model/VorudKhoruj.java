package com.example.alireza.parking.Model;

/**
 * Created by AliReza on 4/5/2017.
 */

public class VorudKhoruj {
    private int id;
    private String shomareKhodro;
    private String saatVorud;
    private String saatKhoruj;
    private String tarikh;
    private String mablagh;
    private String des;
    private String shift;
    private String isBaygani;
    private String status;



    public VorudKhoruj(String shomareKhodro, String saatVorud, String saatKhoruj, String tarikh , String mablagh, String des , String shift , String isBaygani , String status) {
        this.shomareKhodro = shomareKhodro;
        this.saatVorud = saatVorud;
        this.saatKhoruj = saatKhoruj;
        this.tarikh = tarikh;
        this.mablagh = mablagh;
        this.des = des;
        this.shift = shift;
        this.isBaygani = isBaygani;
        this.status = status;
    }



    public VorudKhoruj(int id, String shomareKhodro, String saatVorud, String saatKhoruj,String tarikh, String mablagh, String des , String shift , String isBaygani , String status) {
        this.id = id;
        this.shomareKhodro = shomareKhodro;
        this.saatVorud = saatVorud;
        this.saatKhoruj = saatKhoruj;
        this.tarikh = tarikh;
        this.mablagh = mablagh;
        this.des = des;
        this.shift = shift;
        this.isBaygani = isBaygani;
        this.status = status;
    }



    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }


    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShomareKhodro() {
        return shomareKhodro;
    }

    public void setShomareKhodro(String shomareKhodro) {
        this.shomareKhodro = shomareKhodro;
    }

    public String getSaatVorud() {
        return saatVorud;
    }

    public void setSaatVorud(String saatVorud) {
        this.saatVorud = saatVorud;
    }

    public String getSaatKhoruj() {
        return saatKhoruj;
    }

    public void setSaatKhoruj(String saatKhoruj) {
        this.saatKhoruj = saatKhoruj;
    }

    public String getMablagh() {
        return mablagh;
    }

    public void setMablagh(String mablagh) {
        this.mablagh = mablagh;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
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
