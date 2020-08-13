package loaders;

import controllers.AdminDialogViewController;
import database_managers.DBAdminDataManager;
import functional.MainSystem;
import javafx.fxml.FXMLLoader;

import java.util.Arrays;

import static consts.Сonst.ADMIN_VIEW_PATH;
import static consts.Сonst.ADMIN_VIEW_TITLE;

public class AdminPageLoader extends Loader{
    public AdminPageLoader(MainSystem system) {
        super(system);
    }

    @Override
    void setTitle() {
        title = ADMIN_VIEW_TITLE;
    }

    @Override
    void setPagePath() {
        pagePath = ADMIN_VIEW_PATH;
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
