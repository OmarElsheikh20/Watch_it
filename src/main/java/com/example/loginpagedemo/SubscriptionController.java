package com.example.loginpagedemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import watchIt.Subscription;

import java.io.IOException;
import java.time.LocalDate;

public class SubscriptionController {

    @FXML
    private Button btnBasic;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnPremium;

    @FXML
    private Button btnStandard;

    @FXML
    private Label lblBasicPrice;

    @FXML
    private Label lblPremiumPrice;

    @FXML
    private Label lblStandardPrice;

    @FXML
    private AnchorPane paneBasic;

    @FXML
    private AnchorPane panePremium;

    @FXML
    private AnchorPane paneStandard;

    @FXML
    void btnBasic_Clicked(ActionEvent event) {
        btnPremium.setStyle("-fx-background-color: white;");
        btnStandard.setStyle("-fx-background-color: white;");
        btnBasic.setStyle("-fx-background-color: #dca523;");
        paneBasic.setVisible(true);
        paneStandard.setVisible(false);
        panePremium.setVisible(false);
    }

    @FXML
    void btnNext_Clicked(ActionEvent event) throws IOException {

        if (paneBasic.isVisible()) {
            Subscription sups = new Subscription(Subscription.enPlan.Basic, LocalDate.now());
            Global.CurrentUser.createNewSups(sups);
            MessageBox.showInfo("Confirm", "You can watch up to 5 movies per month");

        }
        else if (paneStandard.isVisible()) {
            Subscription sups = new Subscription(Subscription.enPlan.Standard, LocalDate.now());
            Global.CurrentUser.createNewSups(sups);
            MessageBox.showInfo("Confirm", "You can watch up to 10 movies per month");

        }
        else if (panePremium.isVisible()) {
            Subscription sups = new Subscription(Subscription.enPlan.Premium, LocalDate.now());
            Global.CurrentUser.createNewSups(sups);
            MessageBox.showInfo("Confirm", "You can watch up to 30 movies per month");

        }

        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnPremium_Clicked(ActionEvent event) {
        btnBasic.setStyle("-fx-background-color: white;");
        btnStandard.setStyle("-fx-background-color: white;");
        btnPremium.setStyle("-fx-background-color: #dca523;");
        paneBasic.setVisible(false);
        paneStandard.setVisible(false);
        panePremium.setVisible(true);
    }

    @FXML
    void btnStandard_Clicked(ActionEvent event) {
        btnBasic.setStyle("-fx-background-color: white;");
        btnPremium.setStyle("-fx-background-color: white;");
        btnStandard.setStyle("-fx-background-color: #dca523;");
        paneBasic.setVisible(false);
        paneStandard.setVisible(true);
        panePremium.setVisible(false);
    }

}
