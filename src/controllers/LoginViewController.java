package controllers;

import animations.Shake;
import data.User;
import database_managers.DBUserDataManager;
import enum_types.PageType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController extends Controller{

    public LoginViewController() {

    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonOK;

    @FXML
    private Button buttonCancel;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void initialize() {
        buttonOK.setOnAction(actionEvent -> onOK());
        buttonCancel.setOnAction(actionEvent -> onCancel());
    }

    private void onOK() {
        String login = loginField.getText();
        String password = String.valueOf(passwordField.getText());
        if (login.equals("") || password.equals("")) {
            shakeNodes();
            showErrorMessage("Пожалуйста, введите логин и пароль");
        }
        else {
            User user = new User(login, password);
            DBUserDataManager userDataManager = system.getUserDataManager();
            if (!userDataManager.checkUser(user)) {
                showErrorMessage("некорректные логин или пароль; попробуйте еще раз");
            }else{
                if(user.isAdministrator)
                    system.startDialog(PageType.ADMIN_PAGE);
                else
                    system.startDialog(PageType.USER_PAGE);
            }
        }
    }

    private void onCancel() {
        system.closeDialog(this);
    }

    private void shakeNodes(){
        Shake passwordFieldAnimation = new Shake(passwordField);
        Shake loginFieldAnimation = new Shake(loginField);
        passwordFieldAnimation.playAnimation();
        loginFieldAnimation.playAnimation();

    }

    public void refresh() {
        loginField.setText("");
        passwordField.setText("");
    }

    public void close(){
        Stage stage = system.getStage();
        // do what you have to do
        stage.close();
    }
}
