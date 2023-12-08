package com.example.gestion_cabinet_medical_.DAO.Rv;

import java.sql.Date;

public class Rv {
    private int id_bigint ;
    private Date Jour ;
    private int id_Client_bigint ;
    private int id_creneau_bigint ;
    public Rv(int id_bigint, Date Jour, int id_Client_bigint, int id_creneau_bigint) {
        this.id_bigint = id_bigint;
        this.Jour = Jour;
        this.id_Client_bigint = id_Client_bigint;
        this.id_creneau_bigint = id_creneau_bigint;
    }
    public Rv(Date Jour, int id_Client_bigint, int id_creneau_bigint) {
        this.Jour = Jour;
        this.id_Client_bigint = id_Client_bigint;
        this.id_creneau_bigint = id_creneau_bigint;
    }
    @Override
    public String toString() {
        return "Rv{" +
                "id_bigint=" + id_bigint +
                ", Jour=" + Jour +
                ", id_Client_bigint=" + id_Client_bigint +
                ", id_creneau_bigint=" + id_creneau_bigint +
                '}';
    }
    public int getId_bigint() {
        return id_bigint;
    }
    public void setId_bigint(int id_bigint) {this.id_bigint = id_bigint;}
    public  Date getJour() {return Jour;}
    public void setJour(Date jour) {Jour = jour;}
    public int getId_Client_bigint() {return id_Client_bigint;}
    public void setId_Client_bigint(int id_Client_bigint) {this.id_Client_bigint = id_Client_bigint;}
    public int getId_Creneau_bigint() {return id_creneau_bigint;}
    public void setId_Creneau_bigint(int id_creneau_bigint) {this.id_creneau_bigint = id_creneau_bigint;}
}