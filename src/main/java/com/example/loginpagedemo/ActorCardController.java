package com.example.loginpagedemo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import watchIt.Actor;
import watchIt.Cast;

import java.io.IOException;

public class ActorCardController {
    @FXML
    private Label lblActorName;
    private Cast selectedCast;
    public void setData(Actor value) {
        selectedCast = value;
        lblActorName.setText(value.getFullName());
    }
    @FXML
    void Cast_Clicked(MouseEvent event) throws IOException {

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Cast.fxml"));
        Parent root = fxmlLoader.load();

        // Get the controller and pass data to it
        CastController controller = fxmlLoader.getController();
        controller.setData(selectedCast);

        // Set up the scene and stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
