package controllers;

import database_managers.DBAdminDataManager;
import enum_types.PageType;
import functional.MainSystem;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminDialogViewController extends Controller{

    private DBAdminDataManager adminDataManager;

    private final String[] tableSumsColumnNames = {"Фамилия", "Имя", "Отчество", "Отработанные часы", "Все часы"};
    private final String[] tableLecturersColumnNames = {"Фамилия", "Имя", "Отчество", "Начало работы"};
    private final String[] tableHardWorkingLecturersColumnNames = {"Фамилия", "Имя", "Отчество",
            "Университет", "Факультет", "Кафедра"};

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonCancel;

    @FXML
    private TableView<String[]> table;

    @FXML
    private Button generateSumsButton;

    @FXML
    private Button findHardWorkingButton;

    @FXML
    private Button findCurrentLecturersButton;

    @FXML
    void initialize() {
        buttonCancel.setOnAction(event->onCancel());
        generateSumsButton.setOnAction(event->generateSums());
        findHardWorkingButton.setOnAction(event->findHardWorkingLecturers());
        findCurrentLecturersButton.setOnAction(event->findCurrentLecturers());
    }

    private void onCancel(){
        system.startDialog(PageType.LOGIN_PAGE);
    }

    private void generateSums(){
        String[][] sums = adminDataManager.getFinalSum();
        generateTableForArray(sums,tableSumsColumnNames);
    }

    private void generateTableForArray(String[][]data, String[] columnNames){
        refresh();
        if(data.length == 0){
            showErrorMessage("По вашему запросу ничего не найдено");
            return;
        }
        for(int i = 0;i<columnNames.length;i++){
            String columnName = columnNames[i];
            var column = createTableColumnWithIndex(i,columnName);
            table.getColumns().add(column);
        }
        ObservableList<String[]> observableSums = FXCollections.observableArrayList(data);
        table.setItems(observableSums);
        table.setVisible(true);
    }

    private TableColumn<String[],String> createTableColumnWithIndex(int index, String columnName){
        TableColumn<String[],String>column = new TableColumn<>(columnName);
        column.setCellValueFactory(data -> new SimpleStringProperty((data.getValue()[index])));
        return column;
    }

    private void findHardWorkingLecturers(){
        String[][] lecturers = adminDataManager.getHardWorkingLecturers();
        generateTableForArray(lecturers,tableHardWorkingLecturersColumnNames);
    }

    private void findCurrentLecturers(){
        system.startDialog(PageType.SET_LECTURER_PAGE);
    }

    @Override
    public void setControlSystem(MainSystem mainSystem) {
        super.setControlSystem(mainSystem);
        adminDataManager = mainSystem.getAdminDataManager();
    }

    public void showSelectedLecturers(String[][] selectedLecturers) {
        generateTableForArray(selectedLecturers,tableLecturersColumnNames);
    }


    @Override
    public void refresh() {
        errorLabel.setText("");
        table.getColumns().clear();
    }

    @Override
    public void close() {

    }
}
