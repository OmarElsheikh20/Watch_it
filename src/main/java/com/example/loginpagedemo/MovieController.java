package com.example.loginpagedemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import watchIt.Actor;
import watchIt.Movie;
import watchIt.UserWatchRecord;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class MovieController implements Initializable, OnRatingClosedListener {
    @FXML
    private Label lblTitle;

    @FXML
    private HBox CardLayout;

    @FXML
    private ImageView imgPoster;

    @FXML
    private Label lblBudgut;

    @FXML
    private Label lblCountry;

    @FXML
    private Label lblDuration;

    @FXML
    private Label lblFormTitle;

    @FXML
    private Label lblGenre;

    @FXML
    private Label lblLanguage;

    @FXML
    private Label lblRating;

    @FXML
    private Label lblRealeseDate;
    @FXML
    private Hyperlink lblDirectorName;
    @FXML
    private Label lblViews;
    @FXML
    private Button btnWatch;

    private Movie movie;
    private RatingController ratingController;
    private Stage ratingStage;

    @FXML
    void btnWatchLater_Clicked(ActionEvent event) {
        if (Global.CurrentUser.AddToWatchLater(movie)) {
            MessageBox.showConfirmation("Success!", "Movie added successfully .");
        }
        else {
            MessageBox.showInfo("", "The movie is already exist in watch later");
        }
    }

    public void CloseRatingStage() {
        if (ratingStage != null) {
            ratingStage.close();
        }
    }
    public void ShowRatingScreen() throws IOException {

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Rating.fxml"));
        Parent root = fxmlLoader.load();

        // Get the controller and pass data to it
        ratingController = fxmlLoader.getController();
        ratingController.setMovie(movie);
        ratingController.setOnRatingClosedListener(this);

        // Set up the scene and stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        ratingStage = stage;
        stage.show();
    }
    @FXML
    void btnWatch_Clicked(ActionEvent event) throws IOException {

        ShowRatingScreen();
        //RefreshMovie();
    }
    @Override
    public void onRatingClosed() {
        RefreshMovie();
    }
    private void RefreshMovie() {

        lblViews.setText(Integer.toString(movie.getViews()));
        lblRating.setText(Float.toString(Math.round( movie.getRating() * 10) / 10f));
        updateSubscriptionState();
    }

    public void setData(Movie selectedMovie) throws IOException {

        this.movie = selectedMovie;

        // Set basic movie data
        lblFormTitle.setText(movie.getTitle());
        lblTitle.setText(movie.getTitle());
        lblRealeseDate.setText(Integer.toString(movie.getReleaseDate().getYear()));
        lblDuration.setText(movie.getRunningTime() + " minutes");
        lblGenre.setText(movie.getGenre());
        lblLanguage.setText(movie.getLanguage());
        lblBudgut.setText(Float.toString(movie.getBudget()));
        lblCountry.setText(movie.getCountry());
        lblViews.setText(Integer.toString(movie.getViews()));
        lblRating.setText(Float.toString(Math.round( movie.getRating() * 10) / 10f));

        // Set director
        if (movie.getDirector() != null) {
            if (movie.getDirector().getFullName() != null) {
                lblDirectorName.setText(movie.getDirector().getFullName());
            }
        }

        // Load poster image
        loadPosterImage(movie.getPosterSrc());

        // Display cast
        if (movie.getActors() != null) {
            DisplayCast(movie.getActors());
        }
    }
    private void loadPosterImage(String posterPath) {
        try {
            Image image = (posterPath != null)
                    ? new Image(posterPath)
                    : new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unknown.png")));
            imgPoster.setImage(image);
        } catch (Exception e) {
            System.err.println("Error loading poster image: " + e.getMessage());
            imgPoster.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unknown.png"))));
        }
    }
    private void DisplayCast(ArrayList<Actor> actors) throws IOException {

        for (Actor value : actors) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ActorCard.fxml"));
            AnchorPane cardBox = fxmlLoader.load(); // exception !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            ActorCardController cardController = fxmlLoader.getController();
            cardController.setData(value);
            CardLayout.getChildren().add(cardBox);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        updateSubscriptionState();
    }

    private void updateSubscriptionState() {
        if (!Global.CurrentUser.hasValidSups()) {
            btnWatch.setDisable(true);
            MessageBox.showWarning("Your subscription has ended", "You have to renew your subscription.");
        } else {
            btnWatch.setDisable(false);
        }
    }


    @FXML
    void Director_Clicked(ActionEvent event) throws IOException {


        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Cast.fxml"));
        Parent root = fxmlLoader.load();

        // Get the controller and pass data to it
        CastController controller = fxmlLoader.getController();
        controller.setData(movie.getDirector());

        // Set up the scene and stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
