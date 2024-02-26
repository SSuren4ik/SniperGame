module com.example.snipergame {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                        
    opens com.example.snipergame to javafx.fxml;
    exports com.example.snipergame;
}