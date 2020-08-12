package controllers;

import data.Plan;
import data.WorkType;
import database_managers.DBUserDataManager;
import enum_types.PageType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NewPlanViewController extends Controller{

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
    private TextField startYearTextField;

    @FXML
    private TextField namePlanTextField;

    @FXML
    private TextField endYearTextField;

    @FXML
    private TextField hoursTextField;

    @FXML
    private ComboBox<String> workTypesComboBox;

    @FXML
    void initialize() {
        buttonOK.setOnAction(event->onOK());
        buttonCancel.setOnAction(event->onCancel());
    }

    private void onOK(){
        try {
            Plan createdPlan = getCreatedPlan();
            addPlan(createdPlan);
            onCancel();
        } catch (Exception e) {
            showErrorMessage("Попытка некорректна. Попробуйте еще");
        }
    }

    private Plan getCreatedPlan() throws Exception {
        DBUserDataManager userDataManager = system.getUserDataManager();
        Integer professorID = userDataManager.getProfessorID();
        String workName = workTypesComboBox.getValue();
        int workID = workTypesComboBox.getSelectionModel().getSelectedIndex()+1;
        WorkType workType = new WorkType(workID,workName);
        String namePlan = namePlanTextField.getText();
        int startYear = Integer.parseInt(startYearTextField.getText());
        int endYear = Integer.parseInt(endYearTextField.getText());
        int hours = Integer.parseInt(hoursTextField.getText());
        Plan createdPlan = new Plan(0, workType,
                namePlan, startYear, endYear, hours, 0, professorID);
        return createdPlan;
    }

    private void addPlan(Plan plan){
        DBUserDataManager userDataManager = system.getUserDataManager();
        userDataManager.addPlan(plan);
    }

    private void onCancel(){
        system.startDialog(PageType.USER_PAGE);
    }

    public void setWorkTypes(String[] workTypes){
        workTypesComboBox.setItems(FXCollections.observableArrayList(workTypes));
        workTypesComboBox.getSelectionModel().selectFirst();
    }

    @Override
    public void refresh() {

    }

    @Override
    public void close() {
        system.getStage().close();
    }
}
