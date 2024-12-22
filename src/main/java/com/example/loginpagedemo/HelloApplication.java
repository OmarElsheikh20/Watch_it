package com.example.loginpagedemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import watchIt.*;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed at load \"Login.fxml\" file");
        }
    }

    public static void main(String[] args) {
        /*Movie.clearMoviesFile();

        Director di = new Director("Ahmed", "Alian", LocalDate.of(2014,12,1), Person.enGender.Male,"Syrian");
        ArrayList<Actor> actors = new ArrayList<>();
        ArrayList<Movie> movies = new ArrayList<>();
        actors.add(new Actor("Ahmed", "Alian",  LocalDate.of(2014,12,1), Person.enGender.Male,"Syrian"));
        actors.add(new Actor("Amr", "Ahmed",  LocalDate.of(2014,12,1), Person.enGender.Male,"Syrian"));
        actors.add(new Actor("Omar", "Mohammed",  LocalDate.of(2014,12,1), Person.enGender.Male,"Syrian"));

         File selectedFile = new File("C:/Users/Ahmed Alian/IdeaProjects/Watch_It/src/main/resources/img/interstellar.jpg");
         Movie movie1 = new Movie("Interstellar", "Action",125,12000,"US","English", LocalDate.of(2014,12,1), selectedFile.toURI().toString(),di);
         movie1.setActors(actors);
         movies.add(movie1);

         selectedFile = new File("C:/Users/Ahmed Alian/IdeaProjects/Watch_It/src/main/resources/img/joker.jpg");
        movie1 = new Movie("Joker", "Action",126,11245,"US","English", LocalDate.of(2020,12,1), selectedFile.toURI().toString(),di,actors);
        movies.add(movie1);

        selectedFile = new File("C:/Users/Ahmed Alian/IdeaProjects/Watch_It/src/main/resources/img/inception.jpg");
        movie1 = new Movie("Inception", "Action",90,2410,"US","English", LocalDate.of(2020,12,1), selectedFile.toURI().toString(),di,actors);
        movies.add(movie1);

        selectedFile = new File("C:/Users/Ahmed Alian/IdeaProjects/Watch_It/src/main/resources/img/good_will_hunting.jpg");
        movie1 = new Movie("Good Will Hunting", "Drama",202,1256,"US","English", LocalDate.of(2020,12,1), selectedFile.toURI().toString(),di,actors);
        movies.add(movie1);

        selectedFile = new File("C:/Users/Ahmed Alian/IdeaProjects/Watch_It/src/main/resources/img/the_godfather.jpg");
        movie1 = new Movie("The God Father", "Drama",202,1256,"US","English", LocalDate.of(2020,12,1), selectedFile.toURI().toString(),di,actors);
        movies.add(movie1);

        selectedFile = new File("C:/Users/Ahmed Alian/IdeaProjects/Watch_It/src/main/resources/img/BL.jpeg");
        movie1 = new Movie("Bad Leader", "Drama",202,1256,"US","English", LocalDate.of(2020,12,1), selectedFile.toURI().toString(),di,actors);
        movies.add(movie1);

        selectedFile = new File("C:/Users/Ahmed Alian/IdeaProjects/Watch_It/src/main/resources/img/OIP.jpeg");
        movie1 = new Movie("The Darkest Day Of My Life", "Drama",202,1256,"US","English", LocalDate.of(2020,12,1), selectedFile.toURI().toString(),di,actors);
        movies.add(movie1);

        selectedFile = new File("C:/Users/Ahmed Alian/IdeaProjects/Watch_It/src/main/resources/img/R.jpeg");
        movie1 = new Movie("Avatar", "Drama",202,1256,"US","English", LocalDate.of(2020,12,1), selectedFile.toURI().toString(),di,actors);
        movies.add(movie1);

        selectedFile = new File("C:/Users/Ahmed Alian/IdeaProjects/Watch_It/src/main/resources/img/TLOU.jpeg");
        movie1 = new Movie("The Last Of Us", "Drama",202,1256,"US","English", LocalDate.of(2020,12,1), selectedFile.toURI().toString(),di,actors);
        movies.add(movie1);

        Movie.AddNewMovies(movies);

        ArrayList<Movie> loadedMovies = Movie.getAllMovies();
        System.out.println("Loaded Movies:");
        for (Movie movie : loadedMovies) {
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Director: " + movie.getDirector().getFullName());
            for (Actor actor : movie.getActors()) {
                System.out.println("    - " + actor.getFullName());
            }
        }*/
        launch();
    }
}
