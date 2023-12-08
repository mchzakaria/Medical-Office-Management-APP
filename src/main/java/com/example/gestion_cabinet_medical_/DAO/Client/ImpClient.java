package com.example.gestion_cabinet_medical_.DAO.Client;

import com.example.gestion_cabinet_medical_.DATABASE.Connect;
import com.example.gestion_cabinet_medical_.DATABASE.DbConnect;
import com.example.gestion_cabinet_medical_.DATABASE.FileConnect;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImpClient implements ITClient {
    Connection con = DbConnect.GetInstance().getCon();
    FileConnect<Client> Filecon = Connect.getFileConnectInstance();
    @Override
    public void addClient(String Type ,Client P) {
        if(Type.equals("database")){
            String req = "INSERT INTO personne (version,titre,nom,prenom) VALUES (?,?,?,?) ";
            try {
                PreparedStatement ps = con.prepareStatement(req);
                ps.setInt(1,P.getVersion());
                ps.setString(2,P.getTitre());
                ps.setString(3,P.getNom());
                ps.setString(4,P.getPrenom());
                if(ps.executeUpdate() == 1){
                    System.out.println("Added Succesfully ");}}
            catch(SQLException e){
                System.out.println(e);
            }
        }
        else if(Type.equals("file")){
            try {
                Filecon.SaveToFile(P,"client.serial");
            } catch (IOException e) {
                throw new RuntimeException(e);}}}
    public int GetIdbyName(String Nom , String Prenom){
        int id = 0 ;
        String req = "SELECT * FROM personne WHERE nom=? AND prenom=? AND titre=? ";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1,Nom);
            ps.setString(2,Prenom);
            ps.setString(3,"Client");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getInt("id");
                System.out.println("ID Client : " + id);}}
        catch(SQLException e){
            System.out.println(e);}
        return id ;}
    @Override
    public void updateClient(String Type ,Client P) throws IOException, ClassNotFoundException {
        if(Type.equals("database")){
            String req = "UPDATE personne SET version = ? , titre = ? , nom = ? , prenom = ? WHERE id = ? ";
            try {
                PreparedStatement ps = con.prepareStatement(req);
                ps.setInt(1,P.getVersion());
                ps.setString(2,P.getTitre());
                ps.setString(3,P.getNom());
                ps.setString(4,P.getPrenom());
                ps.setInt(5,P.getId_bigint());
                if(ps.executeUpdate() == 1){
                    System.out.println("Update Succesfully ");}}
            catch(SQLException e){
                System.out.println(e);}}
        else if (Type.equals("file")) {
            // Read object from file
            Client client = Filecon.ReadFromFile("client.serial");
            // Update object
            if (client instanceof Client) {
                Client updatedClient = client;
                updatedClient.setVersion(P.getVersion());}
            Filecon.SaveToFile(client, "client.serial");}}
    @Override
    public void deleteClient(String Type ,int indice) throws IOException, ClassNotFoundException {
        if(Type.equals("database")){
            String req = "DELETE * FROM personne WHERE id = ? ";
            try {
                PreparedStatement ps = con.prepareStatement(req);
                ps.setInt(1, indice);
                if(ps.executeUpdate() == 1){
                    System.out.println("Delete Succesfully ");
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        else if (Type.equals("file")){
            Client client = Filecon.ReadFromFile("client.serial");
            if (client.getId_bigint() != indice) {
                Filecon.SaveToFile(client, "client.serial");}}}
    @Override
    public Client getClient(String Type ,int idc) {
        Client med = null;
        if (Type.equals("database")) {
            String req = "SELECT * FROM personne WHERE id=? and titre=?";
            try {
                PreparedStatement ps = con.prepareStatement(req);
                ps.setInt(1, idc);
                ps.setString(2, "Client");
                ResultSet res = ps.executeQuery();
                if (res.next()) {
                    med = new Client(idc, res.getInt("version"), res.getString("nom"), res.getString("prenom"));
                }
            }catch(SQLException e){
                    throw new RuntimeException(e);
                }
            }
            else if (Type.equals("file")) {
                try {
                    med = Filecon.ReadFromFile("client.serial");
                } catch (ClassNotFoundException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return med;
        }
        @Override
        public List<Client> getClients (String Type){
            List<Client> med = new ArrayList<Client>();
            if (Type.equals("database")) {
                String req = "SELECT * FROM personne WHERE titre=?";
                try {
                    PreparedStatement ps = con.prepareStatement(req);
                    ps.setString(1, "Client");
                    ResultSet res = ps.executeQuery();
                    while (res.next()) {
                        med.add(new Client(res.getInt("id"), res.getInt("version"), res.getString("nom"), res.getString("prenom")));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if (Type.equals("file")) {
                try {
                    List<Client> fileClients = (List<Client>) Filecon.ReadFromFile("client.serial");
                    med.addAll(fileClients);
                } catch (ClassNotFoundException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return med;
        }
    }