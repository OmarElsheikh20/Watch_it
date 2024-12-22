package com.example.loginpagedemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import watchIt.Movie;
import watchIt.UserWatchRecord;

import java.io.IOException;
import java.time.LocalDate;

public class RatingController {


    public Integer RATE;
    @FXML
    private Rating ctrRating;

    private Movie selectedMovie;

    private OnRatingClosedListener onRatingClosedListener; // Callback listener
    public void setOnRatingClosedListener(OnRatingClosedListener listener) {
        this.onRatingClosedListener = listener;
    }

    @FXML
    void btnRate_Clicked(ActionEvent event) {

        if (selectedMovie == null) {
            MessageBox.showError("Error", "No movie selected for rating," +
                    " selected movie object is null");
            return;
        }

        RATE = (int)ctrRating.getRating();
        if(MessageBox.showConfirmation("Confirm", "Your Rate is " +  RATE +
                ", you want to continue?")) {

            selectedMovie.CalcRating(RATE);
            completeWatching(RATE);
            closeRatingScreen();
        }

    }
    @FXML
    void btnWatchWithoutRating_Clicked(ActionEvent event) {

        if (selectedMovie == null) {
            MessageBox.showError("Error", "No movie selected for rating, selected movie object is null");
            return;
        }

        if(MessageBox.showConfirmation("Confirm", "You are gonna to watch the movie without rating, you want to continue?")) {
            completeWatching(null);
            closeRatingScreen();
        }

    }

    private void completeWatching(Integer rate) {

        selectedMovie.IncrementViewsByOne();
        Global.CurrentUser.DecrementLimitByOne();

        // Add the movie to the user's watch history
        UserWatchRecord record = new UserWatchRecord(Global.CurrentUser.getID(), selectedMovie, LocalDate.now(), rate);
        Global.CurrentUser.WatchMovie(record);

        MessageBox.showInfo("Watching Done", "The movie has been added to your history. " +
                "Remaining movies you can watch in your current subscription: " + Global.CurrentUser.getLimitMovies());
    }
    private void closeRatingScreen() {

        if (onRatingClosedListener != null) {
            onRatingClosedListener.onRatingClosed();
        }
        Stage stage = (Stage) ctrRating.getScene().getWindow();
        stage.close();
        
    }
    public void setMovie(Movie movie) {
        selectedMovie = movie;
    }
}