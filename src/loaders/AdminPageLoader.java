package loaders;

import controllers.AdminDialogViewController;
import database_managers.DBAdminDataManager;
import functional.MainSystem;
import javafx.fxml.FXMLLoader;

import java.util.Arrays;

public class AdminPageLoader extends Loader{
    public AdminPageLoader(MainSystem system) {
        super(system);
    }

    @Override
    void setTitle() {
        title = "Окно администратора";
    }

    @Override
    void setPagePath() {
        pagePath = "../views/adminDialogView.fxml";
    }

    @Override
    void setControllerParameters(FXMLLoader loader) {
        AdminDialogViewController controller = loader.getController();
        DBAdminDataManager adminDataManager = system.getAdminDataManager();
        String[][]systemLecturers = adminDataManager.getSelectedLecturers();
        if(systemLecturers!=null) {
            String[][] selectedLecturers = Arrays.copyOf(systemLecturers, systemLecturers.length);
            controller.showSelectedLecturers(selectedLecturers);
            adminDataManager.setSelectedLecturers(null);
        }

    }
}
