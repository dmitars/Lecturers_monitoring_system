package loaders;

import functional.MainSystem;
import javafx.fxml.FXMLLoader;

public class LoginPageLoader extends Loader{
    public LoginPageLoader(MainSystem system) {
        super(system);
    }

    @Override
    void setTitle() {
        title = "Окно авторизации";
    }

    @Override
    void setPagePath() {
        pagePath = "../views/loginView.fxml";
    }

    @Override
    void setControllerParameters(FXMLLoader loader) {

    }
}
