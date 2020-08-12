package loaders;

import controllers.UserViewController;
import data.Plan;
import database_managers.DBUserDataManager;
import functional.MainSystem;
import javafx.fxml.FXMLLoader;

public class UserPageLoader extends Loader{

    public UserPageLoader(MainSystem system) {
        super(system);

    }

    @Override
    void setControllerParameters(FXMLLoader loader) {
        UserViewController userViewController = loader.getController();
        DBUserDataManager userDataManager = system.getUserDataManager();
        Plan[]plans = userDataManager.getPlans();
        userViewController.setPlans(plans);
    }

    @Override
    void setTitle() {
        title = "Окно пользователя";
    }

    @Override
    void setPagePath() {
        pagePath = "../views/userView.fxml";
    }
}
