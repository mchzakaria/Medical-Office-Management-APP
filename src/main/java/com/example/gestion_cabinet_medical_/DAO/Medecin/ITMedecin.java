package com.example.gestion_cabinet_medical_.DAO.Medecin;

import java.util.List;

public interface ITMedecin {
    void addMedecin(String Type,Medecin P);
    int GetIdMedecinbyName(String Nom , String Prenom);
    int GetCrenauxbyIdMedecin(int id);
    void updateMedecin(String Type ,Medecin P);
    void deleteMedecin(String Type ,int indice);
    Medecin getMedecin(String Type ,int idc);
    List<Medecin> getMedecins(String Type);
}
