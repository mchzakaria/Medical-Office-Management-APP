package com.example.gestion_cabinet_medical_.DAO;
import java.io.Serializable;
import java.lang.* ;
public class Personne{
    private int id_bigint ;
    private int version ;
    private String titre ;
    private String Nom ;
    private String Prenom ;
    public Personne(int idBigint,int version,String titre,String Nom,String Prenom) {
        this.id_bigint = idBigint;
        this.version = version ;
        this.titre = titre ;
        this.Nom = Nom ;
        this.Prenom = Prenom ;
    }
    public Personne(int version,String titre,String Nom,String Prenom) {
        this.version = version ;
        this.titre = titre ;
        this.Nom = Nom ;
        this.Prenom = Prenom ;
    }
    @Override
    public String toString() {
        return " ID : " + id_bigint + " Version : " + version + " Titre : " + titre + " Nom : " + Nom + " Prenom : " + Prenom + " " ;
    }
    public int getId_bigint() { return id_bigint; }
    public int getVersion() { return version; }
    public String getTitre() { return titre; }
    public String getNom() { return Nom; }
    public String getPrenom() { return Prenom; }
    public void setId_bigint(int id_bigint) { this.id_bigint = id_bigint; }
    public void setVersion(int version) { this.version = version; }
    public void setTitre(String titre) { this.titre = titre; }
    public void setNom(String nom) { Nom = nom; }
    public void setPrenom(String prenom) { Prenom = prenom; }
}
