package com.example.loginpagedemo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import watchIt.Movie;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class MovieCardController {

    @FXML
    private ImageView MovieImage;

    @FXML
    private Label lblMovieName;
    @FXML
    private VBox BOX;

    private Movie selectedMovie;

    public void setData(Movie movie) {

        selectedMovie = movie;
        String posterPath = movie.getPosterSrc();
        Image image = null;
        if (posterPath != null) {
            try {
                image = new Image(posterPath);
            } catch (Exception e) {
                image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unknown.png")));
            }
        } else {
            System.out.println("Poster path is null!");
            image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unknown.png")));
        }
        MovieImage.setImage(image);
        lblMovieName.setText(movie.getTitle());

        BOX.setStyle("-fx-background-color: #"+ "white" +";" +
                " -fx-background-radius: 15;" +
                "-fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0.1), 10, 0 , 0 ,10);");
    }

    public Movie getData() {
        return selectedMovie;
    }

    @FXML
    void Show_Movie_Details(MouseEvent event) throws IOException {

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Movie.fxml"));
        Parent root = fxmlLoader.load();

        // Get the controller and pass data to it
        MovieController controller = fxmlLoader.getController();
        controller.setData(selectedMovie);

        // Set up the scene and stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
