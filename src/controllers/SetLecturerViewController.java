package controllers;

import database_managers.DBAdminDataManager;
import enum_types.PageType;
import functional.MainSystem;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SetLecturerViewController  extends Controller{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonOK;

    @FXML
    private Button buttonCancel;

    /*@FXML
    private Label errorLabel;*/

    @FXML
    private TextField dateTextField;

    @FXML
    private ComboBox<String> academicDegreeComboBox;

    @FXML
    private ComboBox<String> departmentComboBox;

    @FXML
    void initialize() {
        buttonOK.setOnAction(event->onOK());
        buttonCancel.setOnAction(event->onCancel());
    }

    private void onOK(){
        if(checkDateIsCorrect()){
            String date = dateTextField.getText();
            String department = departmentComboBox.getValue();
            String academicDegree = academicDegreeComboBox.getValue();
            DBAdminDataManager adminDataManager = system.getAdminDataManager();
            String[][]lecturers = adminDataManager.getCurrentLecturers(date,department,academicDegree);
            adminDataManager.setSelectedLecturers(lecturers);
            system.startDialog(PageType.ADMIN_PAGE);
        }
    }

    private boolean checkDateIsCorrect(){
        try {
            LocalDate.parse(dateTextField.getText());
            return true;
        } catch (Exception e) {
            showErrorMessage("введена некорректная дата");
            return false;
        }
    }

    private void onCancel(){
        system.startDialog(PageType.ADMIN_PAGE);
    }

    public void setAcademicDegrees(String[] academicDegrees){
        academicDegreeComboBox.setItems(FXCollections.observableArrayList(academicDegrees));
        academicDegreeComboBox.getSelectionModel().selectFirst();
    }

    public void setDepartments(String[] departments){
        departmentComboBox.setItems(FXCollections.observableArrayList(departments));
        departmentComboBox.getSelectionModel().selectFirst();
    }

    @Override
    public void setControlSystem(MainSystem mainSystem) {
        system = mainSystem;
    }

    @Override
    public void refresh() {

    }

    @Override
    public void close() {

    }
}
