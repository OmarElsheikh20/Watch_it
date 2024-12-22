package com.example.loginpagedemo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import watchIt.Actor;

public class ActorCardController {
    @FXML
    private Label lblActorName;
    public void setData(Actor value) {
        lblActorName.setText(value.getFullName());
    }
}
