module com.example.gestion_cabinet_medical_ {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.gestion_cabinet_medical_ to javafx.fxml;
    exports com.example.gestion_cabinet_medical_;
}