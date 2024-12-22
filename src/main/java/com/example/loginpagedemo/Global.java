package com.example.loginpagedemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import watchIt.Movie;
import watchIt.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Global {

    public static User CurrentUser = new User("a", "a", "Alian","Ahmed","a");
    public static User Admin = new User("Admin", "admin@a.com","Ahmed", "Alian","Admin");

}
