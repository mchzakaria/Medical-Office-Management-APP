package com.example.gestion_cabinet_medical_.DAO.Creneaux;
import com.example.gestion_cabinet_medical_.DAO.Client.Client;
import com.example.gestion_cabinet_medical_.DATABASE.Connect;
import com.example.gestion_cabinet_medical_.DATABASE.DbConnect;
import com.example.gestion_cabinet_medical_.DATABASE.FileConnect;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ImpCreneaux implements ITCreneaux {
    Connection con = DbConnect.GetInstance().getCon();
    FileConnect<Creneaux> Filecon = Connect.getFileConnectInstance();
    @Override
    public void addCreneaux(String Type , Creneaux P) {
        if(Type.equals("database")){
            String req = "INSERT INTO creneaux (id, version, Hdebut, Hfin, Mdebut, Mfin, id_medecin ) VALUES (?,?,?,?,?,?,?) ";
            try {
                PreparedStatement ps = con.prepareStatement(req);
                ps.setInt(1,P.getId_bigint());
                ps.setInt(2,P.getVersion());
                ps.setInt(3,P.getHdebut());
                ps.setInt(4,P.getMdebut());
                ps.setInt(5,P.getHfin());
                ps.setInt(6,P.getMfin());
                ps.setInt(7,P.getId_Medecin_bigint());
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
                Filecon.SaveToFile(P,"creneaux.serial");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void updateCreneaux(String Type ,Creneaux P) {
        String req = "UPDATE creneaux SET  version=?, Hdebut=? , Hfin=? , Mdebut=?, Mfin=?, id_medecin=? WHERE id = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1,P.getVersion());
            ps.setInt(2,P.getHdebut());
            ps.setInt(3,P.getHfin());
            ps.setInt(4,P.getMdebut());
            ps.setInt(5,P.getMfin());
            ps.setInt(6,P.getId_Medecin_bigint());
            ps.setInt(7,P.getId_bigint());
            if(ps.executeUpdate() == 1){
                System.out.println("Update Succesfully ");
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    @Override
    public void deleteCreneaux(String Type ,int indice) {
        String req = "DELETE * FROM creneaux WHERE id = ? ";
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
    public Creneaux getCreneaux(String Type ,int idc) {
        Creneaux med = null ;
        if(Type.equals("database")){
            String req = "SELECT * FROM creneaux WHERE id=?";
            try {
                PreparedStatement ps = con.prepareStatement(req);
                ps.setInt(1,idc);
                ResultSet res = ps.executeQuery();
                if(res.next()){
                    med = new Creneaux(res.getInt("id"),res.getInt("version") , res.getInt("Hdebut") ,res.getInt("Hfin")  , res.getInt("Mdebut") ,res.getInt("Mfin") , res.getInt("id_medecin") );
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if (Type.equals("file")) {
            try {
                med = Filecon.ReadFromFile("creneaux.serial");
            } catch ( ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        return med ;
    }
    @Override
    public List<Creneaux> get_all_Creneaux(String Type) {
        List<Creneaux> med = new ArrayList<Creneaux>() ;
        String req = "SELECT * FROM creneaux";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                med.add(new Creneaux(res.getInt("id"),res.getInt("version") , res.getInt("Hdebut") ,res.getInt("Hfin")  , res.getInt("Mdebut") ,res.getInt("Mfin") , res.getInt("id_medecin")));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return med ;
    }}

