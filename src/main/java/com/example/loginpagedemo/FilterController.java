package com.example.loginpagedemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;


public class FilterController {

    public enum Duration {
        LESS_THAN_HOUR,
        LESS_THAN_TWO_HOURS,
        MORE_THAN_TWO_HOURS
    }

    public enum Rating {
        ABOVE_3,
        ABOVE_4
    }

    public enum Language {
        ARABIC,
        ENGLISH,
        OTHERS
    }



    @FXML
    private ToggleGroup grDuration;

    @FXML
    private ToggleGroup grLanguage;

    @FXML
    private ToggleGroup grRating;

    @FXML
    private RadioButton rbAbove3;

    @FXML
    private RadioButton rbAbove4;

    @FXML
    private RadioButton rbArabic;

    @FXML
    private RadioButton rbEnglish;

    @FXML
    private RadioButton rbLessThanHour;

    @FXML
    private RadioButton rbMoreThanTwoHours;

    @FXML
    private RadioButton rbOtherLanguages;

    @FXML
    private RadioButton rbLessThanTowHours;
    private boolean applyClicked = false;

    // Enums to represent the selected options
    private Duration selectedDuration;
    private Rating selectedRating;
    private Language selectedLanguage;

    @FXML
    void Apply_Clicked(ActionEvent event) {
        applyClicked = true;
        Stage stage = (Stage) rbAbove3.getScene().getWindow();
        stage.close();
    }
    public Filters selectedFilters() {

        if (!applyClicked) {
            return null;
        }

        Duration selectedDuration = null;
        Rating selectedRating = null;
        Language selectedLanguage = null;

        // Determine selected duration
        if (grDuration.getSelectedToggle() == rbLessThanHour) {
            selectedDuration = Duration.LESS_THAN_HOUR;
        } else if (grDuration.getSelectedToggle() == rbLessThanTowHours) {
            selectedDuration = Duration.LESS_THAN_TWO_HOURS;
        } else if (grDuration.getSelectedToggle() == rbMoreThanTwoHours) {
            selectedDuration = Duration.MORE_THAN_TWO_HOURS;
        }

        // Determine selected rating
        if (grRating.getSelectedToggle() == rbAbove3) {
            selectedRating = Rating.ABOVE_3;
        } else if (grRating.getSelectedToggle() == rbAbove4) {
            selectedRating = Rating.ABOVE_4;
        }

        // Determine selected language
        if (grLanguage.getSelectedToggle() == rbArabic) {
            selectedLanguage = Language.ARABIC;
        } else if (grLanguage.getSelectedToggle() == rbEnglish) {
            selectedLanguage = Language.ENGLISH;
        } else if (grLanguage.getSelectedToggle() == rbOtherLanguages) {
            selectedLanguage = Language.OTHERS;
        }

        return new Filters(selectedDuration, selectedRating, selectedLanguage);
    }
}
