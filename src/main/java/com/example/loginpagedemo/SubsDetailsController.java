package com.example.loginpagedemo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import jdk.jshell.JShell;
import watchIt.Subscription;

import java.time.format.DateTimeFormatter;

public class SubsDetailsController {

    @FXML
    private Label lblAllowedWatches;

    @FXML
    private Label lblEndDate;

    @FXML
    private Label lblPlan;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblRemaining;

    @FXML
    private Label lblStartDate;

    public void setData(Subscription subs) {
        if (subs != null) {
            lblStartDate.setText(subs.getStartDate().toString());
            lblPrice.setText(subs.getPrice() + " EGP");
            lblPlan.setText(subs.getPlan().toString());

            lblEndDate.setText(subs.getStartDate().plusDays(30).toString());
            lblAllowedWatches.setText(Integer.toString(subs.getAllowedWatches()));
            lblRemaining.setText(Integer.toString(Global.CurrentUser.getLimitMovies()));
        }
        else {
            MessageBox.showWarning("Your subscription has ended",
                    "You have to renew your subscription.");
        }

    }
}
