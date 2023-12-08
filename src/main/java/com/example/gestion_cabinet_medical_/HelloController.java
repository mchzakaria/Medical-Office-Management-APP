package com.example.gestion_cabinet_medical_;

import com.example.gestion_cabinet_medical_.DAO.Client.Client;
import com.example.gestion_cabinet_medical_.DAO.Client.ImpClient;
import com.example.gestion_cabinet_medical_.DAO.Creneaux.Creneaux;
import com.example.gestion_cabinet_medical_.DAO.Creneaux.ImpCreneaux;
import com.example.gestion_cabinet_medical_.DAO.Medecin.ImpMedecin;
import com.example.gestion_cabinet_medical_.DAO.Medecin.Medecin;
import com.example.gestion_cabinet_medical_.DAO.Rv.ImpRv;
import com.example.gestion_cabinet_medical_.DAO.Rv.Rv;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class HelloController implements Initializable {
//    @FXML
//    private Label welcomeText , medecinbutton , homebutton , clientbutton , rvbutton ;
    @FXML
    private AnchorPane Container ;
    @FXML
    private AnchorPane A1 ,A2,A3,A4 ;
    @FXML
    private ListView ClientTable ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DocComboBox();
        ClientTable();
        date.setChronology(LocalDate.now().getChronology());
        date.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate lastAllowedDate = LocalDate.now();
                setDisable(date.isBefore(lastAllowedDate));
            }
        });
        A2.setVisible(false);
        A3.setVisible(false);
        A4.setVisible(false);
    }
    @FXML
    protected void onShowHomePage(ActionEvent e) {
        Container.getChildren().clear();
        A1.setVisible(true);
        Container.getChildren().add(A1);
    }
    @FXML
    protected void onShowMedecin(ActionEvent e){
        Container.getChildren().clear();
        A2.setVisible(true);
        Container.getChildren().add(A2);
    }
    @FXML
    protected void onShowRv(ActionEvent e){
        Container.getChildren().clear();
        A3.setVisible(true);
        Container.getChildren().add(A3);
    }
    @FXML
    protected void onShowClient(ActionEvent e){
        Container.getChildren().clear();
        A4.setVisible(true);
        Container.getChildren().add(A4);
    }

    @FXML
    private TextField nom,prenom,nomdoc,prenomdoc,HDebut,HFin,MDebut,MFin ;
    @FXML
    private DatePicker date ;
    @FXML
    private ComboBox doctor ;
    @FXML
    Label messageLabel;

    @FXML
    private void AddRendezvous(){
        // Create Object of ImpClient
        ImpClient client = new ImpClient();
        // Object of a Client
        Client C1 = new Client(1,nom.getText(),prenom.getText());
        // Add Client To DB
        client.addClient("database",C1);
        // Create Instance of Rendez_vous
        ImpRv Rv = new ImpRv();
        // Get Jour
        LocalDate selectedDate = date.getValue();
        Date rvdate = java.sql.Date.valueOf(selectedDate);
        // Get Id Client By Last Name and First Name
        int id = client.GetIdbyName(nom.getText(),prenom.getText());
        // GET ID CRENAUX
        ImpMedecin I1 = new ImpMedecin();
        String selectedItem = (String) doctor.getSelectionModel().getSelectedItem();
        String[] items = selectedItem.split(" ");
        String Nom , Prenom ;
        Nom = items[0] ; Prenom = items[1] ;
        System.out.println(Nom + Prenom);
        int idmedecin = I1.GetIdMedecinbyName(Nom , Prenom);
        int IdCrenaux = I1.GetCrenauxbyIdMedecin(idmedecin);
        Rv R1 = new Rv(rvdate,id,IdCrenaux);
        Rv.addRv("database",R1);
    }
    @FXML
    private void DocComboBox(){
        doctor.getItems().clear();
        ImpMedecin med = new ImpMedecin();
        ArrayList<Creneaux> AllCreneaux = (ArrayList<Creneaux>) new ImpCreneaux().get_all_Creneaux("database");
        ArrayList<String> data = new ArrayList<>();
        for (Creneaux m : AllCreneaux) {
            Medecin currectMedecin = med.getMedecin("database",m.getId_Medecin_bigint());
            data.add(currectMedecin.getNom() + " " + currectMedecin.getPrenom() +" de " + m.getHdebut() + ":" + m.getMfin() + " a " + m.getHfin() + ":" + m.getMfin() );
        }
        doctor.getItems().addAll(data);
    }

    private void ClientTable(){
        ClientTable.getItems().clear();
        ArrayList<Client> AllClients = (ArrayList<Client>) new ImpClient().getClients("database");
        ArrayList<String> Clients = new ArrayList<>();
        for (Client m : AllClients) {
            Clients.add(m.getNom() + " " + m.getPrenom());
        }
        for (String dat : Clients) {
            Label label = new Label(dat);
            Button Deletebutton = new Button("Delete");
            Deletebutton.setStyle("-fx-background-color: #ff4d4d; -fx-text-fill: white; -fx-font-weight: bold;");
            Button Updatebutton = new Button("Update");
            Updatebutton.setStyle("-fx-background-color: #38c038; -fx-text-fill: white; -fx-font-weight: bold;");
            HBox hbox = new HBox(label, Deletebutton, Updatebutton);
            hbox.setSpacing(10);
            ClientTable.getItems().add(hbox);
        }
    }

    @FXML
    private void AddMedecin(){
        ImpMedecin medecin = new ImpMedecin();
        ImpCreneaux creneaux = new ImpCreneaux();
        Medecin M1 = new Medecin(1 ,nomdoc.getText(),prenomdoc.getText());
        medecin.addMedecin("database",M1);
        int recentmedecin = medecin.GetIdMedecinbyName(nomdoc.getText(),prenomdoc.getText());
        Creneaux C1 = new Creneaux(1,Integer.parseInt(HDebut.getText().trim()),Integer.parseInt(MDebut.getText().trim()),Integer.parseInt(HFin.getText().trim()) ,Integer.parseInt(MFin.getText().trim()),recentmedecin);
        creneaux.addCreneaux("database",C1);
        messageLabel.setVisible(true);
        messageLabel.setText(nomdoc.getText() + " " + prenomdoc.getText() + " Added Succefully");
        messageLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #2196f3;");

        nomdoc.clear(); prenomdoc.clear(); HDebut.clear(); MDebut.clear(); HFin.clear(); MFin.clear();
    }

}