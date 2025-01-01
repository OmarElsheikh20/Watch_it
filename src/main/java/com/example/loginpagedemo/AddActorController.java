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
import javafx.stage.Stage;
import watchIt.Actor;
import watchIt.Person;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddActorController implements Initializable {



    @FXML
    private DatePicker ADOB;

    @FXML
    private TextField AFname;

    @FXML
    private ChoiceBox<Person.enGender> AGender;

    @FXML
    private TextField ALname;

    @FXML
    private TextField ANation;

    @FXML
    private Button SaveActor;

    private Actor actor;




    public AddActorController() {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (AGender != null) {
            AGender.getItems().addAll(Person.enGender.Male, Person.enGender.Female);
        }
    }



    public  void DisplayAddActor() throws IOException {

        try {
            Parent rootactor = FXMLLoader.load(getClass().getResource("AddActor.fxml"));
            Scene sceneactor = new Scene(rootactor);
            Stage stageactor = new Stage();
            stageactor.setScene(sceneactor);
            stageactor.show();
        } catch (IOException e) {
            e.printStackTrace();
            MessageBox.showError("Error", "Unable to load AddActor.fxml. Please check the file path.");
        }
    }


    public void btn_SaveActor_Clicked(ActionEvent event) throws IOException {

        if (AFname.getText().isEmpty() || ALname.getText().isEmpty() || ADOB.getValue() == null || AGender.getValue() == null || ANation.getText().isEmpty()) {
            MessageBox.showError("Error", "Please fill in all the actor details.");
        }

        String afname = AFname.getText();
        String alname = ALname.getText();
        LocalDate adob = ADOB.getValue();
        Person.enGender agender = AGender.getValue();
        String anation = ANation.getText();

       this.actor = new Actor(afname, alname, adob, agender, anation);
//        ArrayList<Actor> actors = new ArrayList<>();
//        actors.add(actor);

        AFname.clear();
        ALname.clear();
        ADOB.setValue(null);
        AGender.setValue(null);
        ANation.clear();

        ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
    }

    public Actor getActor() {
        return actor;
    }

}
