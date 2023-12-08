package com.example.gestion_cabinet_medical_.DAO.Creneaux;

public class Creneaux {
    private int id_bigint ;
    private int version ;
    private int hdebut ;
    private int mdebut ;
    private int hfin ;
    private int mfin ;
    private int id_Medecin_bigint ;
    public Creneaux(int id_bigint, int version, int hdebut, int mdebut, int hfin, int mfin, int id_Medecin_bigint) {
        this.id_bigint = id_bigint;
        this.version = version;
        this.hdebut = hdebut;
        this.mdebut = mdebut;
        this.hfin = hfin;
        this.mfin = mfin;
        this.id_Medecin_bigint = id_Medecin_bigint;
    }
    public Creneaux(int version, int hdebut, int mdebut, int hfin, int mfin, int id_Medecin_bigint) {
        this.version = version;
        this.hdebut = hdebut;
        this.mdebut = mdebut;
        this.hfin = hfin;
        this.mfin = mfin;
        this.id_Medecin_bigint = id_Medecin_bigint;
    }
    public int getId_bigint() {
        return id_bigint;
    }
    public void setId_bigint(int id_bigint) {
        this.id_bigint = id_bigint;
    }
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }
    public int getHdebut() {
        return hdebut;
    }
    public void setHdebut(int hdebut) {
        this.hdebut = hdebut;
    }
    public int getMdebut() {
        return mdebut;
    }
    public void setMdebut(int mdebut) {
        this.mdebut = mdebut;
    }
    public int getHfin() {
        return hfin;
    }
    public void setHfin(int hfin) {
        this.hfin = hfin;
    }
    public int getMfin() {
        return mfin;
    }
    public void setMfin(int mfin) {
        this.mfin = mfin;
    }
    public int getId_Medecin_bigint() {
        return id_Medecin_bigint;
    }
    public void setId_Medecin_bigint(int id_Medecin_bigint) {
        this.id_Medecin_bigint = id_Medecin_bigint;
    }
    @Override
    public String toString() {
        return "Creneaux{" +
                "id_bigint=" + id_bigint +
                ", version=" + version +
                ", hdebut=" + hdebut +
                ", mdebut=" + mdebut +
                ", hfin=" + hfin +
                ", mfin=" + mfin +
                ", id_Medecin_bigint=" + id_Medecin_bigint +
                '}';
    }
}

