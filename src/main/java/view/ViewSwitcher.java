package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewSwitcher {
	
	private boolean firstSwitch = true;
	
	private ViewSwitcher() {

    }
	
	   /* Singleton Pattern */
    private static class LazyHolder {
        private static final ViewSwitcher SINGLETON = new ViewSwitcher();
    }

    // Create SINGLETON on the first call.
    public static ViewSwitcher getInstance() {
        return LazyHolder.SINGLETON;
    }

    /**
     * Switch the view displayed on the Stage.
     * 
     * @param stage
     * @param viewType the type of the View to switch
     * @param model    the Instance of the GameModel
     * @throws IOException
     */
    public void switchView(final Stage stage, final ViewType viewType) {
        View view = this.loadStyle(stage, viewType);
        view.setStage(stage);
        view.init();
        stage.setTitle("Team Management");
        stage.show();
    }
    
    /**
     * Load Style from FXML file and creates the View linked to it.
     * 
     * @param stage    to be changed
     * @param viewType of new View
     * @return The View
     */
    private View loadStyle(final Stage stage, final ViewType viewType) {
        FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(viewType.getPath()));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene newScene;
        if (this.firstSwitch || !stage.isShowing()) {
            newScene = new Scene(root);
            this.firstSwitch = false;
        } else {
            /* Do not resize the stage */
            newScene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
        }
        stage.setScene(newScene);
        View view = loader.getController();
        stage.getScene().getStylesheets().clear();
        view.setStage(stage);
        stage.setScene(newScene);
        return view;
    }
}
