package loaders;

import com.sun.media.jfxmedia.logging.Logger;
import controllers.Controller;
import functional.MainSystem;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class Loader {
    private final int WINDOW_WIDTH = 650;
    private final int WINDOW_HEIGHT = 400;

    String pagePath = null;
    String title = null;
    MainSystem system;

    public Loader(MainSystem system){
        this.system = system;
        setPagePath();
        setTitle();
    }

    abstract void setTitle();

    abstract void setPagePath();

    public void load(){
        FXMLLoader loader = getLoader();
        loadPage(loader);
    }

    abstract void setControllerParameters(FXMLLoader loader);

    private FXMLLoader getLoader(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(pagePath));
        return loader;
    }

    private void loadPage(FXMLLoader loader){
        try{
            Parent root = getRoot(loader);
            setControllerParameters(loader);
            setPageControlSystem(loader);
            configureStage(root,title);
            showStage();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private Parent getRoot(FXMLLoader loader) throws IOException {
        return loader.load();
    }

    private void setPageControlSystem(FXMLLoader loader){
        Controller controller = loader.getController();
        controller.setControlSystem(system);
    }

    private void configureStage(Parent root, String title){
        Stage stage = system.getStage();
        stage.setTitle(title);
        stage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
    }

    private void showStage(){
        Stage stage = system.getStage();
        stage.show();
    }
}
