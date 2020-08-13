package functional;

import controllers.Controller;
import controllers.LoginViewController;
import database_managers.DBAdminDataManager;
import database_managers.DBGetOnlyDataManager;
import database_managers.DBUserDataManager;
import enum_types.PageType;
import factories.LoaderFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import loaders.Loader;

import static consts.Сonst.CONNECTION_FAILED_MESSAGE;
import static consts.Сonst.DATABASE_PATH;

public class MainSystem extends Application {
    private Stage stage;

    private Requester requester;
    private DBGetOnlyDataManager getOnlyDataManager;
    private DBUserDataManager userDataManager;
    private DBAdminDataManager adminDataManager;

    public DBUserDataManager getUserDataManager() {
        return userDataManager;
    }

    public DBAdminDataManager getAdminDataManager() {
        return adminDataManager;
    }

    public DBGetOnlyDataManager getGetOnlyDataManager() {
        return getOnlyDataManager;
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage stage) {
        requester = new Requester(DATABASE_PATH);
        if (!requester.performConnection()) {
            ErrorDisplay.showError(new Exception(CONNECTION_FAILED_MESSAGE));
        } else {
            initializeDataManagers();
            this.stage = stage;
            startDialog(PageType.LOGIN_PAGE);
        }
    }

    private void initializeDataManagers(){
        getOnlyDataManager = new DBGetOnlyDataManager(requester);
        userDataManager = new DBUserDataManager(requester);
        adminDataManager = new DBAdminDataManager(requester);
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void startDialog(PageType pageType) {
        LoaderFactory factory = new LoaderFactory(this);
        Loader loader = factory.getLoader(pageType);
        loader.load();
    }

    public void closeDialog(Controller controller) {
        if (controller instanceof LoginViewController) {
            requester.dispose();
        }
        controller.close();
    }

}
