module com.example.loginpagedemo {
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;
    requires java.desktop;
    requires jdk.jshell;
    requires java.rmi;
    opens com.example.loginpagedemo to javafx.fxml;
    exports com.example.loginpagedemo;
    exports watchIt;
}
