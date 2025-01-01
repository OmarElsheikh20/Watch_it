package com.example.loginpagedemo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import watchIt.Cast;
import watchIt.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class CastController {

    @FXML
    private HBox CardLayout;

    @FXML
    private Label lblDateOfBirth;

    @FXML
    private Label lblFirst;

    @FXML
    private Label lblGender;

    @FXML
    private Label lblLast;

    @FXML
    private Label lblName;

    @FXML
    private Label lblNationality;

    public void setData(Cast cast) {

        if (cast != null) {
            lblFirst.setText(cast.get_FirstName());
            lblLast.setText(cast.get_LastName());

            lblName.setText(cast.getFullName());
            lblNationality.setText(cast.get_Nationality());
            lblDateOfBirth.setText(cast.get_DateOfBirth().toString());
            lblGender.setText(cast.get_Gender().toString());

            try {
                setMovies(cast);
            } catch (IOException e) {
                MessageBox.showError("Failure", "Cannot load actor movies");
            }
        }
    }
    private void setMovies(Cast cast) throws IOException {
        HashSet<Movie> moviesCast = cast.getListOfMovies();
        CardLayout.getChildren().clear();
        for (Movie value : moviesCast) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
            VBox cardBox = fxmlLoader.load();
            MovieCardController cardController = fxmlLoader.getController();
            cardController.setData(value);
            CardLayout.getChildren().add(cardBox);
        }
    }
}
