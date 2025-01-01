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
    private List<Movie> searchedMovies;

    @FXML
    private HBox CardLayout,CardLayout1;
    @FXML
    private HBox btnHome, btnSearch, btnHistory, btnRecommended, btnWatchLater, btnSubsDetails;

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
        buttons = List.of(btnHome, btnSearch, btnHistory, btnWatchLater, btnRecommended,btnSubsDetails);

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

    private Filters filters;
    @FXML
    void btnSearch_Clicked(MouseEvent event) throws IOException {
        Display_Search();
    }
    @FXML
    void tfSearch_KeyReleasd(KeyEvent event) throws IOException {
        Display_Search();
    }
    @FXML
    void Search_Clicked(MouseEvent event) throws IOException {
        pan_Home.setVisible(false);
        pan_Search.setVisible(true);

        try {
            searchedMovies = new ArrayList<>(Movie.getAllMovies());
            Display_Search();
        } catch (IOException e) {
            System.err.println("Failed operation, Can't load movies or filter" + e.getMessage());

        }

    }
    private void Display_Search() throws IOException {
        String word = FuckingSearch.getText().trim();

        if (word.isEmpty())
            searchedMovies = Movie.getAllMovies();
        else
            searchedMovies = Movie.Filter(word).stream().toList();

        srchCardLayout.getChildren().clear();
        int column = 0;
        int row = 0;

// Set row and column constraints to remove extra space dynamically
        srchCardLayout.getRowConstraints().clear();
        srchCardLayout.getColumnConstraints().clear();

// Define the height of each row based on the movie card height
        int movieCardHeight = 280;

// Assuming a width of 5 columns
        int totalColumns = 5;
        double spacing = 10.0; // Margin between cards

// Loop through the movies and add them to the grid pane
        for (Movie value : searchedMovies) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
            VBox cardBox = fxmlLoader.load();

            MovieCardController cardController = fxmlLoader.getController();
            cardController.setData(value);

            srchCardLayout.add(cardBox, column, row);

            column++;

            if (column == totalColumns) {
                column = 0;
                row++;
            }

            GridPane.setMargin(cardBox, new Insets(spacing));
        }

// Set constraints dynamically for proper layout
        for (int i = 0; i < totalColumns; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100.0 / totalColumns);
            srchCardLayout.getColumnConstraints().add(columnConstraints);
        }

        for (int i = 0; i <= row; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setMinHeight(movieCardHeight + spacing);
            rowConstraints.setVgrow(Priority.NEVER); // Avoid stretching vertically
            srchCardLayout.getRowConstraints().add(rowConstraints);
        }


    }
    @FXML
    void filter_Clicked(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Filter.fxml"));
        Parent root = loader.load();

        FilterController filterController = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Filter");
        stage.showAndWait();

        // Get selected filters
        filters = filterController.selectedFilters();

        if (filters != null) {
            searchedMovies = Movie.FilterWithCriteria(filters);
            Update();
        }
    }
    private void Update() throws IOException {
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

    @FXML
    void History_Clicked(MouseEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserMovies.fxml"));
            Parent root = fxmlLoader.load();

            // Get the controller and pass data to it
            UserMoviesController controller = fxmlLoader.getController();
            controller.Display_History();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            MessageBox.showError("Error", "Cannot Load \"UserMovies.fxml\" "
                    + e.getMessage());
        }

    }

    @FXML
    void Recommended_Clicked(MouseEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserMovies.fxml"));
            Parent root = fxmlLoader.load();

            // Get the controller and pass data to it
            UserMoviesController controller = fxmlLoader.getController();
            controller.Display_Recommended();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            MessageBox.showError("Error", "Cannot Load \"UserMovies.fxml\" "
                    + e.getMessage());
        }
    }

    @FXML
    void WatchLater_Clicked(MouseEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserMovies.fxml"));
            Parent root = fxmlLoader.load();

            // Get the controller and pass data to it
            UserMoviesController controller = fxmlLoader.getController();
            controller.Display_WatchLater();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            MessageBox.showError("Error", "Cannot Load \"UserMovies.fxml\" "
                    + e.getMessage());
        }
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
    void Logout_Clicked(MouseEvent event) {
        try {
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();

            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root));
            loginStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
