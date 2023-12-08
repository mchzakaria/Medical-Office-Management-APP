package com.example.gestion_cabinet_medical_.DAO.Medecin;

import com.example.gestion_cabinet_medical_.DAO.Personne;

public class Medecin extends Personne {
    public Medecin(int idBigint, int version , String Nom, String Prenom) {
        super(idBigint, version, "Medecin", Nom, Prenom);
    }
    public Medecin(int version , String Nom, String Prenom) {
        super(version, "Medecin", Nom, Prenom);
    }
}
