package com.example.loginpagedemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import watchIt.Movie;
import javafx.scene.input.MouseEvent;
import  javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    private ArrayList<Movie> recentMovies;
    private ArrayList<Movie> MostViewsMovies;
    private ArrayList<Movie> searchedMovies;

    @FXML
    private HBox CardLayout,CardLayout1;
    @FXML
    private HBox btnHome, btnSearch, btnCategories, btnHistory, btnRecommended, btnWatchLater, btnSubsDetails;

    @FXML
    private GridPane srchCardLayout;

    @FXML
    private Pane pan_Home;
    @FXML
    private Pane pan_Search;
    @FXML
    private TextField FuckingSearch;
    @FXML
    private Label lblUserName;

    private List <HBox> buttons;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        recentMovies = Movie.getRecentMovies();
        MostViewsMovies = Movie.getMostViewedMovie(24);

        lblUserName.setText(Global.CurrentUser.getFullName());
        buttons = List.of(btnHome, btnSearch, btnCategories, btnHistory, btnWatchLater, btnRecommended,btnSubsDetails);

//        pan_Home.setVisible(true);
//        pan_Search.setVisible(false);
//        try {
//            Display_RecentMovies();
//            Display_TrendingMovies();
//        }
//        catch (IOException ex) {
//            System.err.println("Failed operation, Can't get recent movies or trending movies");
//        }
        Home_Clicked(null);

    }
    public void Display_RecentMovies() throws IOException {

        CardLayout.getChildren().clear();
        for (Movie value : recentMovies) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
            VBox cardBox = fxmlLoader.load();
            MovieCardController cardController = fxmlLoader.getController();
            cardController.setData(value);
            CardLayout.getChildren().add(cardBox);
        }
    }
    public void Display_TrendingMovies() throws IOException {


        CardLayout1.getChildren().clear();

        /*int column = 8;
        int row = 1;

        for(Movie movie :  MostViewsMovies){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource( "Card.fxml"));
            VBox cardBox = fxmlLoader.load();
            MovieCardController cardController = fxmlLoader.getController();
            cardController.setData(movie);

            if (column == 6) {
                column =0;
                row++;
            }

            MovieContainer.add(cardBox, column++,row);
            GridPane.setMargin(cardBox, new Insets(10));

        }*/
        for (Movie value : MostViewsMovies) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
            VBox cardBox = fxmlLoader.load(); // exception !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            MovieCardController cardController = fxmlLoader.getController();
            cardController.setData(value);
            CardLayout1.getChildren().add(cardBox);
        }
    }

    @FXML
    void Categories_Clicked(MouseEvent event) {

    }

    @FXML
    void History_Clicked(MouseEvent event) {

    }

    @FXML
    void Home_Clicked(MouseEvent event) {
        pan_Home.setVisible(true);
        pan_Search.setVisible(false);
        btnHome.getStyleClass().addAll("selected","mouse-moved");
        buttons.stream()
                .filter(b -> b != btnHome)
                .forEach(b -> {
                    b.getStyleClass().remove("selected");
                    if (!b.getStyleClass().contains("mouse-moved")) {
                        b.getStyleClass().add("mouse-moved");
                    }
                });
        try {
            Display_RecentMovies();
            Display_TrendingMovies();
        }
        catch (IOException ex) {
            System.err.println("Failed operation, Can't get recent movies or trending movies" + ex.getMessage());
        }
    }
    @FXML
    void Search_Clicked(MouseEvent event) throws IOException {
        pan_Home.setVisible(false);
        pan_Search.setVisible(true);

        btnSearch.getStyleClass().addAll("selected", "mouse-moved");
        buttons.stream()
                .filter(b -> b != btnSearch)
                .forEach(b -> {
                    b.getStyleClass().remove("selected");
                    if (!b.getStyleClass().contains("mouse-moved")) {
                        b.getStyleClass().add("mouse-moved");
                    }
                });
        try {
            searchedMovies = new ArrayList<>(Movie.getAllMovies());
            Display_Search();
        } catch (IOException e) {
            System.err.println("Failed operation, Can't load movies or filter" + e.getMessage());

        }

    }
    @FXML
    void Recommended_Clicked(MouseEvent event) {

    }
    @FXML
    void WatchLater_Clicked(MouseEvent event) {

    }
    @FXML
    void ShowSubsDetails_Clicked(MouseEvent event) {
        if (Global.CurrentUser.hasValidSups()) {
            try {
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("subsDetails.fxml"));
                Parent root = fxmlLoader.load();

                // Get the controller and pass data to it
                SubsDetailsController controller = fxmlLoader.getController();
                controller.setData(Global.CurrentUser.getSubscription());

                // Set up the scene and stage
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                MessageBox.showError("Error", "Cannot Load subsDetails.fxml "
                + e.getMessage());
            }
        } else {
            MessageBox.showWarning("Your subscription has ended",
                    "You have to renew your subscription.");
        }
    }

    @FXML
    void btnSearch_Clicked(MouseEvent event) throws IOException {
       Display_Search();
    }
    @FXML
    void tfSearch_KeyReleasd(KeyEvent event) throws IOException {
        Display_Search();
    }
    private void Display_Search() throws IOException {
        String word = FuckingSearch.getText().trim();
        System.out.println("text changed to: " + word);

        if (word.isEmpty())
            searchedMovies = Movie.getAllMovies();
        else
            searchedMovies = Movie.Filter(word);

        srchCardLayout.getChildren().clear();
        int column = 0;
        int row = 0;
        for (Movie value : searchedMovies) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
            VBox cardBox = fxmlLoader.load();

            MovieCardController cardController = fxmlLoader.getController();
            cardController.setData(value);

            srchCardLayout.add(cardBox, column, row);

            column++;

            if (column == 5) {
                column = 0;
                row++;
            }

            GridPane.setMargin(cardBox, new Insets(10));

        }
    }

}
