package com.example.loginpagedemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import watchIt.Actor;
import watchIt.Director;
import watchIt.Movie;
import watchIt.Person;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddMovieController implements Initializable {

    @FXML
    private TextField Budget;

    @FXML
    private TextField Country;

    @FXML
    private DatePicker DDOB;

    @FXML
    private TextField DFname;

    @FXML
    private ChoiceBox<Person.enGender> DGender;

    @FXML
    private TextField DLname;

    @FXML
    private TextField DNation;

    @FXML
    private TextField Genre;

    @FXML
    private TextField Language;

    @FXML
    private DatePicker ReleaseDate;

    @FXML
    private TextField RunningTime;

    @FXML
    private TextField Title;

    @FXML
    private ImageView PosterView;



    private Stage stage;

    public AddMovieController(Stage stage) throws IOException {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public AddMovieController() throws IOException {
    }


    ArrayList<Actor> actors = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        if (DGender != null) {
            DGender.getItems().addAll(Person.enGender.Male, Person.enGender.Female);
        }

//        AddActorController addActorController = new AddActorController();
//        ActionEvent event = new ActionEvent();
//        try {
//            actors.add(addActorController.btn_SaveActor_Clicked(event));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }


    public void btn_AddActor_Clicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddActor.fxml"));
        Parent rootactor = loader.load();

        // Get the AddActorController
        AddActorController addActorController = loader.getController();

        // Create and display the stage
        Stage stageactor = new Stage();
        stageactor.setScene(new Scene(rootactor));
        stageactor.showAndWait(); // Wait for the user to complete the actor addition

        // After the stage is closed, retrieve the new actor
        Actor actor = addActorController.getActor(); // Implement a getter in AddActorController
        if (actor != null) {
            actors.add(actor); // Add the actor to the list
            MessageBox.showConfirmation("Success", "Actor added successfully!");
        } else {
            MessageBox.showError("Error", "No actor was added.");
        }
    }




    private File selectedFile;

    public void btn_Poster_Clicked(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();


        File defaultDir = new File("C://Users/Ahmed Alian/IdeaProjects/Watch_It/src/main/resources/img");

        if (defaultDir.exists() && defaultDir.isDirectory()) {
            fileChooser.setInitialDirectory(defaultDir);
        } else {
            System.out.println("Default directory does not exist: " + defaultDir.getAbsolutePath());
        }


        selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            PosterView.setImage(image);

        } else {
            System.out.println("No file selected.");
        }

    }


    public void btn_Save_Clicked(ActionEvent event) throws IOException {

        if (DFname.getText().isEmpty() || DLname.getText().isEmpty() || DDOB.getValue() == null || DGender.getValue() == null || DNation.getText().isEmpty() ||
                Title.getText().isEmpty() || Genre.getText().isEmpty() || RunningTime.getText().isEmpty() || Budget.getText().isEmpty() ||
                Country.getText().isEmpty() || Language.getText().isEmpty() || ReleaseDate.getValue() == null) {
            MessageBox.showError("Error", "Please fill in all movie and director details.");
            return;
        }

        String dfname = DFname.getText();
        String dlname = DLname.getText();
        LocalDate ddob = DDOB.getValue();
        Person.enGender dgender = DGender.getValue();
        String dnation = DNation.getText();


        String title = Title.getText();
        String genre = Genre.getText();
        int runningTime = Integer.parseInt(RunningTime.getText());
        float budget = Float.parseFloat(Budget.getText());
        String country = Country.getText();
        String language = Language.getText();
        LocalDate releaseDate = ReleaseDate.getValue();
        float revenue = 0;
        int views = 0;


        Director director = new Director(dfname, dlname, ddob, dgender, dnation);



        Movie movie = new Movie(title, genre, runningTime, budget, country, language, releaseDate, revenue, views, director, actors, selectedFile.toURI().toString());

        Movie.AddNewMovie(movie);

        if (Movie.IsMovieExist(title)) {
            MessageBox.showConfirmation("Success", "The movie added successfully!");
            AdminController.stageaddmovie.close();
        } else {
            MessageBox.showError("Failed", "Something went wrong.");
        }

    }
}


