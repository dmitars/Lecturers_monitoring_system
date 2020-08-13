package loaders;

import controllers.SetLecturerViewController;
import database_managers.DBGetOnlyDataManager;
import functional.MainSystem;
import javafx.fxml.FXMLLoader;

import static consts.Сonst.SET_LECTURER_VIEW_PATH;
import static consts.Сonst.SET_LECTURER_VIEW_TITLE;

public class SetLecturerPageLoader extends Loader{
    public SetLecturerPageLoader(MainSystem system) {
        super(system);
    }

    @Override
    void setTitle() {
        title = SET_LECTURER_VIEW_TITLE;
    }

    @Override
    void setPagePath() {
        pagePath = SET_LECTURER_VIEW_PATH;
    }

    @Override
    void setControllerParameters(FXMLLoader loader) {
        SetLecturerViewController controller = loader.getController();
        setControllersAcademicDegrees(controller);
        setControllersDepartments(controller);
    }

    private void setControllersDepartments(SetLecturerViewController controller){
        DBGetOnlyDataManager requestManager = system.getGetOnlyDataManager();
        String[]departments = requestManager.getDepartments();
        controller.setDepartments(departments);
    }

    private void setControllersAcademicDegrees(SetLecturerViewController controller){
        DBGetOnlyDataManager requestManager = system.getGetOnlyDataManager();
        String[]academicDegrees = requestManager.getAcademicDegrees();
        controller.setAcademicDegrees(academicDegrees);
    }
}
