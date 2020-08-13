package loaders;

import controllers.NewPlanViewController;
import database_managers.DBGetOnlyDataManager;
import functional.MainSystem;
import javafx.fxml.FXMLLoader;

import static consts.Сonst.NEW_PLAN_VIEW_PATH;
import static consts.Сonst.NEW_PLAN_VIEW_TITLE;

public class NewPlanPageLoader extends Loader{
    public NewPlanPageLoader(MainSystem system) {
        super(system);
    }

    @Override
    void setTitle() {
        title = NEW_PLAN_VIEW_TITLE;
    }

    @Override
    void setPagePath() {
        pagePath = NEW_PLAN_VIEW_PATH;
    }

    @Override
    void setControllerParameters(FXMLLoader loader) {
        NewPlanViewController newPlanViewController = loader.getController();
        DBGetOnlyDataManager requestManager = system.getGetOnlyDataManager();
        String[] workTypes = requestManager.getWorks();
        newPlanViewController.setWorkTypes(workTypes);
    }
}
