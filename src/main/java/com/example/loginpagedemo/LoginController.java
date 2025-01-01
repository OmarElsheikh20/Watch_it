package com.example.loginpagedemo;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import watchIt.User;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    @FXML
    private CheckBox cbShowPassword;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private TextField tfUsername;

    @FXML
    private TextField tfPasswword;



    public void btnSignIn_Clicked(ActionEvent e) {

        String Username = tfUsername.getText();
        String Password = pfPassword.getText();

        if (tfUsername.getText().isEmpty() || pfPassword.getText().isEmpty()) {
            MessageBox.showError("Error","Enter your username and password");
            return;
        }

        if (Username.equalsIgnoreCase(Global.Admin.getUsername()) && Password.equalsIgnoreCase(Global.Admin.getPassword())) {
            Display(e, "Admin.fxml");
            return;
        }

        User user;
        try {
            user = User.Find(Username, Password);
        }
        catch (Exception ex) {
            System.out.println("Failed in Find method" + ex.getMessage());
            return;
        }

        if (user != null) {
            MessageBox.showInfo( "Success","Login Successful!");
        }
        else {
            MessageBox.showError( "Failed","Wrong username or password !");
            return;
        }

        Global.CurrentUser = user;
        if (user.hasValidSups()) {
            Display(e, "Main.fxml");
        }
        else {
            Display(e,"Subscription.fxml");
        }

    }
    public void CbShowPassword_Clicked(ActionEvent e)
    {
        if (cbShowPassword.isSelected()) {
            tfPasswword.setText(pfPassword.getText());
            pfPassword.setVisible(false);
            tfPasswword.setVisible(true);
        }
        else {
            pfPassword.setText(tfPasswword.getText());
            tfPasswword.setVisible(false);
            pfPassword.setVisible(true);
        }
    }

    @FXML
    void hlSignIn_Clicked(ActionEvent event) {
            Display(event, "SignUp.fxml");
    }

    private void Display(ActionEvent event,String FILE) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(FILE)));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            MessageBox.showError("Failure", "Cannot load "+FILE+" "+ e.getMessage());
        }
    }
}
