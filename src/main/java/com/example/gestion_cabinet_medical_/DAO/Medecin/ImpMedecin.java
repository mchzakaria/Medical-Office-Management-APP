package com.example.gestion_cabinet_medical_.DAO.Medecin;
import com.example.gestion_cabinet_medical_.DAO.Client.Client;
import com.example.gestion_cabinet_medical_.DAO.Medecin.ITMedecin;
import com.example.gestion_cabinet_medical_.DATABASE.Connect;
import com.example.gestion_cabinet_medical_.DATABASE.DbConnect;
import com.example.gestion_cabinet_medical_.DATABASE.FileConnect;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class ImpMedecin implements ITMedecin {
    Connection con = DbConnect.GetInstance().getCon();
    FileConnect<Medecin> Filecon = Connect.getFileConnectInstance();
    @Override
    public void addMedecin(String Type,Medecin P) {
        if(Type.equals("database")){
            String req = "INSERT INTO personne (version,titre,nom,prenom) VALUES (?,?,?,?) ";
            try {
                PreparedStatement ps = con.prepareStatement(req);
                ps.setInt(1,P.getVersion());
                ps.setString(2,P.getTitre());
                ps.setString(3,P.getNom());
                ps.setString(4,P.getPrenom());
                if(ps.executeUpdate() == 1){
                    System.out.println("Added Succesfully ");
                }
            }
            catch(SQLException e){
                System.out.println(e);
            }
        }
        else if(Type.equals("file")){
            try {
                Filecon.SaveToFile(P,"medecin.serial");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public int GetCrenauxbyIdMedecin(int id){
        int idc = 0 ;
        String req = "SELECT * FROM creneaux WHERE id_medecin=?";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                idc = rs.getInt("id");
                System.out.println("ID Crenaux : " + idc);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return idc ;
    }
    public int GetIdMedecinbyName(String Nom , String Prenom){
        int idmedecin = 0 ;
        String req = "SELECT * FROM personne WHERE nom=? AND prenom=? AND titre=? ";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1,Nom);
            ps.setString(2,Prenom);
            ps.setString(3,"Medecin");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                idmedecin = rs.getInt("id");
                System.out.println("ID Crenaux : " + idmedecin);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return idmedecin ;
    }
    @Override
    public void updateMedecin(String Type ,Medecin P) {
        String req = "UPDATE personne SET version = ? , titre = ? , nom = ? , prenom = ? WHERE id = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1,P.getVersion());
            ps.setString(2,P.getTitre());
            ps.setString(3,P.getNom());
            ps.setString(4,P.getPrenom());
            ps.setInt(5,P.getId_bigint());
            if(ps.executeUpdate() == 1){
                System.out.println("Update Succesfully ");
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    @Override
    public void deleteMedecin(String Type ,int indice) {
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
    @Override
    public Medecin getMedecin(String Type ,int idc) {
        Medecin med = null ;
        if(Type.equals("database")){
            String req = "SELECT * FROM personne WHERE id=? and titre=?";
            try {
                PreparedStatement ps = con.prepareStatement(req);
                ps.setInt(1,idc);
                ps.setString(2,"Medecin");
                ResultSet res = ps.executeQuery();
                if(res.next()){
                    med = new Medecin(idc,res.getInt("version"),res.getString("nom"),res.getString("prenom"));
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if (Type.equals("file")) {
            try {
                med = Filecon.ReadFromFile("medecin.serial");
            } catch ( ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        return med ;
    }
    @Override
    public List<Medecin> getMedecins(String Type) {
        List<Medecin> med = new ArrayList<Medecin>() ;
        String req = "SELECT * FROM personne WHERE titre=?";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1,"Medecin");
            ResultSet res = ps.executeQuery();
            while (res.next()){
                med.add(new Medecin(res.getInt("id"),res.getInt("version"),res.getString("nom"),res.getString("prenom")));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return med ;
    }
}

