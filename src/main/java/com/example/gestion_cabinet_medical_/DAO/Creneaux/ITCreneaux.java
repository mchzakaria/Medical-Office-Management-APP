package com.example.gestion_cabinet_medical_.DAO.Creneaux;

import java.util.List;

public interface ITCreneaux {
    void addCreneaux(String Type , Creneaux P);
    void updateCreneaux(String Type ,Creneaux P);
    void deleteCreneaux(String Type ,int indice);
    Creneaux getCreneaux(String Type ,int idc);
    List<Creneaux> get_all_Creneaux(String Type);
}

