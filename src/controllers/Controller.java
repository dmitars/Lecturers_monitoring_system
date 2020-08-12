package controllers;

import functional.MainSystem;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public abstract class Controller {
    MainSystem system;

    @FXML
    Label errorLabel;

    public void setControlSystem(MainSystem mainSystem) {
        this.system = mainSystem;
    }

    void showErrorMessage(String message){
        errorLabel.setText(message);
    }
    public abstract void refresh();
    public abstract void close();
}
