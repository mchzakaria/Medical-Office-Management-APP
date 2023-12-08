package com.example.gestion_cabinet_medical_.DAO.Client;

import java.io.IOException;
import java.util.* ;
public interface ITClient {
    void addClient(String Type ,Client P);
    int GetIdbyName(String Nom , String Prenom);
    void updateClient(String Type ,Client P) throws IOException, ClassNotFoundException;
    void deleteClient(String Type ,int indice) throws IOException, ClassNotFoundException;
    Client getClient(String Type ,int idc);
    List<Client> getClients(String Type );
}
