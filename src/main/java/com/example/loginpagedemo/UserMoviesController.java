package com.example.loginpagedemo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import watchIt.Movie;

import java.io.IOException;

public class UserMoviesController {

    @FXML
    private HBox CardLayout;

    @FXML
    private Label lblMovies;

    public void Display_History() throws IOException {

        lblMovies.setText("History");
        CardLayout.getChildren().clear();
        for (Movie value : Global.CurrentUser.getHistory()) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
            VBox cardBox = fxmlLoader.load();
            MovieCardController cardController = fxmlLoader.getController();
            cardController.setData(value);
            CardLayout.getChildren().add(cardBox);
        }
    }
    public void Display_WatchLater() throws IOException {

        lblMovies.setText("Watch Later");
        CardLayout.getChildren().clear();
        for (Movie value : Global.CurrentUser.getWatchLater()) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
            VBox cardBox = fxmlLoader.load();
            MovieCardController cardController = fxmlLoader.getController();
            cardController.setData(value);
            CardLayout.getChildren().add(cardBox);
        }
    }
    public void Display_Recommended() throws IOException {

        lblMovies.setText("Recommended");
        CardLayout.getChildren().clear();
        for (Movie value : Global.CurrentUser.getRecommended()) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
            VBox cardBox = fxmlLoader.load();
            MovieCardController cardController = fxmlLoader.getController();
            cardController.setData(value);
            CardLayout.getChildren().add(cardBox);
        }
    }
}
