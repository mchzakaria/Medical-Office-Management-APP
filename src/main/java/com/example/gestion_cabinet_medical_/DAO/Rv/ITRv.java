package com.example.gestion_cabinet_medical_.DAO.Rv;
import java.util.List;

public interface ITRv {
    void addRv(String Type ,Rv P);
    void updateRv(String Type ,Rv P);
    void deleteRv(String Type ,int indice);
    Rv getRv(String Type ,int idc);
    List<Rv> getRvs(String Type);
}
