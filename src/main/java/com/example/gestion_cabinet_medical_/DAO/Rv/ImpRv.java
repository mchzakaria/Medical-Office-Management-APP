package com.example.gestion_cabinet_medical_.DAO.Rv;
import com.example.gestion_cabinet_medical_.DATABASE.Connect;
import com.example.gestion_cabinet_medical_.DATABASE.DbConnect;
import com.example.gestion_cabinet_medical_.DATABASE.FileConnect;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ImpRv implements ITRv {
    Connection con = DbConnect.GetInstance().getCon();
    FileConnect<Rv> Filecon = Connect.getFileConnectInstance();
    @Override
    public void addRv(String Type ,Rv P) {
        if(Type.equals("database")){
            String req = "INSERT INTO rv (id, jour, id_client, id_creneau) VALUES (?,?,?,?) ";
            try {
                PreparedStatement ps = con.prepareStatement(req);
                ps.setInt(1,P.getId_bigint());
                ps.setDate(2,P.getJour());
                ps.setInt(3,P.getId_Client_bigint());
                ps.setInt(4,P.getId_Creneau_bigint());
                if(ps.executeUpdate() == 1){
                    System.out.println(" RV Added Succesfully ");
                }
            }
            catch(SQLException e){
                System.out.println(e);
            }
        }
        else if(Type.equals("file")){
            try {
                Filecon.SaveToFile(P,"Rv.serial");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void updateRv(String Type ,Rv P) {
        if(Type.equals("database")){
            String req = "UPDATE rv SET jour = ? , id_client = ? , id_creneau = ? WHERE id = ? ";
            try {
                PreparedStatement ps = con.prepareStatement(req);
                ps.setDate(1,P.getJour());
                ps.setInt(2,P.getId_Client_bigint());
                ps.setInt(3,P.getId_Creneau_bigint());
                if(ps.executeUpdate() == 1){
                    System.out.println("Update Succesfully ");
                }
            }
            catch(SQLException e){
                System.out.println(e);
            }
        }
        else if(Type.equals("file")){

        }
    }
    @Override
    public void deleteRv(String Type ,int indice) {
        String req = "DELETE * FROM rv WHERE id = ? ";
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
    public Rv getRv(String Type ,int idc) {
        Rv med = null ;
        if(Type.equals("database")){
            String req = "SELECT * FROM rv WHERE id=?";
            try {
                PreparedStatement ps = con.prepareStatement(req);
                ps.setInt(1,idc);
                ResultSet res = ps.executeQuery();
                if(res.next()){
                    med = new Rv(idc,res.getDate("jour"), res.getInt("id_client"),res.getInt("id_creneau"));
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if (Type.equals("file")) {
            try {
                med = Filecon.ReadFromFile("Rv.serial");
            } catch ( ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        return med ;
    }
    @Override
    public List<Rv> getRvs(String Type) {
        List<Rv> med = new ArrayList<Rv>() ;
        String req = "SELECT * FROM rv";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                med.add(new Rv(res.getInt("id"),res.getDate("jour"), res.getInt("id_client"),res.getInt("id_creneau")));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return med ;
    }}
