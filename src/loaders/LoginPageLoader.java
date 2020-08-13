package loaders;

import functional.MainSystem;
import javafx.fxml.FXMLLoader;

import static consts.Сonst.LOGIN_VIEW_PATH;
import static consts.Сonst.LOGIN_VIEW_TITLE;

public class LoginPageLoader extends Loader{
    public LoginPageLoader(MainSystem system) {
        super(system);
    }

    @Override
    void setTitle() {
        title = LOGIN_VIEW_TITLE;
    }

    @Override
    void setPagePath() {
        pagePath = LOGIN_VIEW_PATH;
    }

    @Override
    void setControllerParameters(FXMLLoader loader) {

    }
}
