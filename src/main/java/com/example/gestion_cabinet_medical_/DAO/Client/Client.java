package com.example.gestion_cabinet_medical_.DAO.Client;


import com.example.gestion_cabinet_medical_.DAO.Personne;

public class Client extends Personne {
    public Client(int idBigint, int version, String Nom, String Prenom) {
        super(idBigint, version, "Client", Nom, Prenom);
    }
    public Client(int version, String Nom, String Prenom) {
        super(version, "Client", Nom, Prenom);
    }
}
