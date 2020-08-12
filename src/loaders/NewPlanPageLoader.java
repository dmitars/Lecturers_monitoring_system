package loaders;

import controllers.NewPlanViewController;
import database_managers.DBGetOnlyDataManager;
import functional.MainSystem;
import javafx.fxml.FXMLLoader;

public class NewPlanPageLoader extends Loader{
    public NewPlanPageLoader(MainSystem system) {
        super(system);
    }

    @Override
    void setTitle() {
        title = "Окно добавления плана";
    }

    @Override
    void setPagePath() {
        pagePath = "../views/newPlanView.fxml";
    }

    @Override
    void setControllerParameters(FXMLLoader loader) {
        NewPlanViewController newPlanViewController = loader.getController();
        DBGetOnlyDataManager requestManager = system.getGetOnlyDataManager();
        String[] workTypes = requestManager.getWorks();
        newPlanViewController.setWorkTypes(workTypes);
    }
}
