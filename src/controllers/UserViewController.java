package controllers;

import data.Plan;
import database_managers.DBUserDataManager;
import enum_types.PageType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class UserViewController extends Controller{
    private Plan[] plans;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonOK;

    @FXML
    private Button buttonCancel;

    @FXML
    private ComboBox<Plan> plansComboBox;

    @FXML
    private TextField additionalHoursTextField;

    @FXML
    private Label planNameLabel;

    @FXML
    private Label planStartYearLabel;

    @FXML
    private Label workTypeLabel;

    @FXML
    private Label planEndYearLabel;

    @FXML
    private Label fullHoursLabel;

    @FXML
    private Label performedHoursLabel;

    @FXML
    private Button addPlanButton;

    @FXML
    void initialize() {
        buttonOK.setOnAction(event->onOK());
        buttonCancel.setOnAction(event->onCancel());
        addPlanButton.setOnAction(event->addPlan());
        plansComboBox.setOnAction(event->showSelectedPlan());
    }

    private void showSelectedPlan(){
        Plan selectedPlan = plansComboBox.getValue();
        planNameLabel.setText(selectedPlan.namePlan);
        planStartYearLabel.setText(selectedPlan.startYear.toString());
        planEndYearLabel.setText(selectedPlan.endYear.toString());
        fullHoursLabel.setText(selectedPlan.allHours.toString());
        performedHoursLabel.setText(selectedPlan.performedHours.toString());
        workTypeLabel.setText(selectedPlan.workType.getName());
    }

    private void onOK() {
        if(plansComboBox.getValue()!=null) {
            updatePlanData();
            refresh();
        }
    }

    private void updatePlanData(){
        Plan currentPlan = plansComboBox.getValue();
        int additionalHours = getAdditionalHours();
        if(additionalHours != 0) {
            currentPlan.addHours(additionalHours);
            DBUserDataManager userDataManager = system.getUserDataManager();
            userDataManager.addHours(currentPlan);
        }
    }

    private int getAdditionalHours(){
        String hoursAsString = additionalHoursTextField.getText();
        try {
            int hours = Integer.parseInt(hoursAsString);
            return hours;
        } catch (Exception e) {
            showErrorMessage("Попытка некорректна. Попробуйте еще");
        }
        return 0;
    }

    private void onCancel(){
        system.startDialog(PageType.LOGIN_PAGE);
    }

    private void addPlan(){
        system.startDialog(PageType.NEW_PLAN_PAGE);
    }


    public void setPlans(Plan[]plans){
        this.plans = Arrays.copyOf(plans,plans.length);
        plansComboBox.setItems(FXCollections.observableArrayList(plans));
        if(plans.length == 0)
            buttonOK.setVisible(false);
        else {
            plansComboBox.getSelectionModel().selectFirst();
            showSelectedPlan();
        }
    }

    @Override
    public void refresh() {
        additionalHoursTextField.setText("");
        showSelectedPlan();
    }

    @Override
    public void close() {
        system.getStage().close();
    }
}
