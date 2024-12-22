package com.example.loginpagedemo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import watchIt.Movie;
import watchIt.Person;
import watchIt.Subscription;
import watchIt.User;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class AdminController implements Initializable {


    @FXML
    private Label Mrm;

    @FXML
    private Label Msp;

    @FXML
    private TableColumn<User, String> Ufirstname;

    @FXML
    private TableColumn<User, Integer> Uid;

    @FXML
    private TableColumn<User, String> Ulastname;

    @FXML
    private TableColumn<User, String> Upassword;

    @FXML
    private TableView<User> UsersView;

    @FXML
    private TableColumn<User, Subscription.enPlan> Usub;

    @FXML
    private TableColumn<User, String> Uusername;

    @FXML
    private TableColumn<User, String> Uemail;


    @FXML
    private TableView<Movie> MoviesView;

    @FXML
    private TableColumn<Movie, Integer> Id;

    @FXML
    private TableColumn<Movie, String> Title;

    @FXML
    private TableColumn<Movie, String> Genre;

    @FXML
    private TableColumn<Movie, Float> RunningTime;

    @FXML
    private TableColumn<Movie, Float> Budget;

    @FXML
    private TableColumn<Movie, String> Country;

    @FXML
    private TableColumn<Movie, String> Language;

    @FXML
    private TableColumn<Movie, LocalDate> ReleaseDate;

    @FXML
    private TableColumn<Movie, Float> Revenue;

    @FXML
    private TableColumn<Movie, Integer> Views;

    @FXML
    private ContextMenu contextMenu;



    public static Stage stageaddmovie = new Stage();

    public AdminController() throws IOException {
    }


    public void btn_Movies_Clicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Movies");
        stage.show();

    }

    public void btn_Users_Clicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Admin_Users.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Users");
        stage.show();

    }

    public void btn_Subs_Clicked(MouseEvent event) throws IOException {

        if(Msp!=null) {
            MostSubscribedPlan();
        }
        Parent root = FXMLLoader.load(getClass().getResource("Subs.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Subscriptions");
        stage.show();

    }


    public void btn_AddMovie_Clicked(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddMovie.fxml"));
        Parent root = loader.load();
        AddMovieController controller = loader.getController();
        controller.setStage(stageaddmovie);

        Scene scene = new Scene(root);
        stageaddmovie.setScene(scene);
        stageaddmovie.setTitle("Add Movie");
        stageaddmovie.show();


    }


    ArrayList<Movie> movies = Movie.CopyMovies();
    ArrayList<User> users = User.getAllUsers();
    ArrayList<String> MrmMonths = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Movie> list = FXCollections.observableArrayList(movies);


        MostSubscribedPlan();
        try {
            MrmMonths =  MostRevenueMonth();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (Mrm !=null){
            Mrm.setText("Most Revenue Month: " + MrmMonths.toString());
        }

        if (Id != null) {
            Id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        } else {
            System.err.println("Id TableColumn is null!");
        }

        if (Title != null) {
            Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        } else {
            System.err.println("Title TableColumn is null!");
        }

        if (Genre != null) {
            Genre.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        } else {
            System.err.println("Genre TableColumn is null!");
        }

        if (RunningTime != null) {
            RunningTime.setCellValueFactory(new PropertyValueFactory<>("RunningTime"));
        } else {
            System.err.println("RunningTime TableColumn is null!");
        }

        if (Budget != null) {
            Budget.setCellValueFactory(new PropertyValueFactory<>("Budget"));
        } else {
            System.err.println("Budget TableColumn is null!");
        }

        if (Country != null) {
            Country.setCellValueFactory(new PropertyValueFactory<>("Country"));
        } else {
            System.err.println("Country TableColumn is null!");
        }

        if (Language != null) {
            Language.setCellValueFactory(new PropertyValueFactory<>("Language"));
        } else {
            System.err.println("Language TableColumn is null!");
        }

        if (ReleaseDate != null) {
            ReleaseDate.setCellValueFactory(new PropertyValueFactory<>("ReleaseDate"));
        } else {
            System.err.println("ReleaseDate TableColumn is null!");
        }

        if (Revenue != null) {
            Revenue.setCellValueFactory(new PropertyValueFactory<>("Revenue"));
        } else {
            System.err.println("Revenue TableColumn is null!");
        }

        if (Views != null) {
            Views.setCellValueFactory(new PropertyValueFactory<>("Views"));
        } else {
            System.err.println("Views TableColumn is null!");
        }


        if (MoviesView != null) {
            MoviesView.setItems(list);
        } else {
            System.err.println("MoviesView TableView is null!");
        }
        if (MoviesView != null) {
            MoviesView.setVisible(true);
        } else {
            System.err.println("MoviesView TableView is null!");
        }


        ObservableList<User> list2 = FXCollections.observableArrayList(users);

        if (Uid != null) {
            Uid.setCellValueFactory(new PropertyValueFactory<>("ID"));
        } else {
            System.err.println("Id TableColumn is null!");
        }

        if (Uusername != null) {
            Uusername.setCellValueFactory(new PropertyValueFactory<>("Username"));
        } else {
            System.err.println("Username TableColumn is null!");
        }

        if (Upassword != null) {
            Upassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
        } else {
            System.err.println("Password TableColumn is null!");
        }

        if (Ufirstname != null) {
            Ufirstname.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        } else {
            System.err.println("Firstname TableColumn is null!");
        }

        if (Ulastname != null) {
            Ulastname.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        } else {
            System.err.println("LastName TableColumn is null!");
        }

        if (Usub != null) {
            Usub.setCellValueFactory(new PropertyValueFactory<>("GuiSubscription"));
        } else {
            System.err.println("SubscriptionType TableColumn is null!");
        }

        if (Uemail != null) {
            Uemail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        } else {
            System.err.println("Email TableColumn is null!");
        }


        if (UsersView != null) {
            UsersView.setItems(list2);
        } else {
            System.err.println("UsersView TableView is null!");
        }
        if (UsersView != null) {
            UsersView.setVisible(true);
        } else {
            System.err.println("UsersView TableView is null!");
        }

    }

    public void Deletemovie(ActionEvent event) {

        Movie movie = MoviesView.getSelectionModel().getSelectedItem();
        if (movie != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Delete Confirmation");
            confirmationAlert.setHeaderText("Are you sure you want to delete this movie?");
            confirmationAlert.setContentText("Movie: " + movie.getTitle());

            ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);

            if (result == ButtonType.OK) {

                MoviesView.getItems().remove(movie);

                movies.remove(movie);

                Movie.DeleteMovie(movie);

                MessageBox.showConfirmation("Success", "Movie deleted successfully.");
            } else {
                MessageBox.showError("Cancelled", "Movie delete Cancelled.");
            }
        } else {
            MessageBox.showError("Error", "No movie selected for deletion.");
        }


    }


    public void DeleteUser(ActionEvent event) throws IOException {

        User user = UsersView.getSelectionModel().getSelectedItem();
        if (user != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Delete Confirmation");
            confirmationAlert.setHeaderText("Are you sure you want to delete this User?");
            confirmationAlert.setContentText("User: " + user.getUsername());

            ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);

            if (result == ButtonType.OK) {

                UsersView.getItems().remove(user);

                User.Delete(user.getUsername());

                MessageBox.showConfirmation("Success", "User deleted successfully.");
            } else {
                MessageBox.showError("Cancelled", "User delete Cancelled.");
            }
        } else {
            MessageBox.showError("Error", "No User selected for deletion.");
        }
    }

    public void MostSubscribedPlan() {

        int basic=0,standard=0,premium=0;
        for (User user : users)
        {
            switch (user.getCurrentSubscription().getPlan()) {
                case Basic:
                    basic++;
                    break;
                case Standard:
                    standard++;
                    break;
                case Premium:
                    premium++;
                    break;
                default:
                    break;
            }
        }

        if (Msp != null) {
            if (basic > premium && basic > standard) {
                Msp.setText("Most Subscribed Plan: Basic");
            } else if (standard > basic && standard > premium) {
                Msp.setText("Most Subscribed Plan: Standard");
            } else if (premium > basic && premium > standard) {
                Msp.setText("Most Subscribed Plan: Premium");
            } else if (basic == standard && basic > premium) {
                Msp.setText("Most Subscribed Plan: Basic and Standard");
            } else if (basic == premium && basic > standard) {
                Msp.setText("Most Subscribed Plan: Basic and Premium");
            } else if (standard == premium && standard > basic) {
                Msp.setText("Most Subscribed Plan: Standard and Premium");
            } else if (basic == 0 && standard == 0 && premium == 0) {
                Msp.setText("No Users have subscribed to any plan");
            } else {
                Msp.setText("Most Subscribed Plan: Tie between all plans");
            }

        }


    }

    private Subscription.enPlan PlanofMonth  (ArrayList<Subscription> SubsHistory,int Month){

        for(Subscription s:SubsHistory){
            if(s.getStartDate().getMonth().getValue()==Month){
                return s.getPlan();
            }
        }
        return Subscription.enPlan.Non;
    }


    public ArrayList<String> MostRevenueMonth() throws IOException {

        ArrayList<User> users = User.LoadUsersFromFile();

        int [] EachMonthRevenue= new int[13];

        EachMonthRevenue[0]=0;

        for(int i=1;i<=12;i++) {


            for (User user : users) {

                EachMonthRevenue[i]+= Subscription.getPrices(PlanofMonth(user.getSubscriptionHistory(),i));

            }

        }

        int max = 22;
        for (int i = 1; i < EachMonthRevenue.length; i++) {
            if (EachMonthRevenue[i] > max) {
                max = EachMonthRevenue[i];
            }
        }

        ArrayList<Integer> indices = new ArrayList<>();// indices is the month(number) which has max Revenue .
        if(max==22)
        {
            indices.add(0);
        }
        for (int i = 0; i < EachMonthRevenue.length; i++) {
            if (EachMonthRevenue[i] == max) {
                indices.add(i);
            }
        }

        ArrayList<String> months = new ArrayList<>();

        for (int i = 0; i < indices.size(); i++) {
            months.add(getMonthName(indices.get(i)));
        }

        return months;

    }
    public static String getMonthName(int month) {
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "No Users";
        }
    }
}
