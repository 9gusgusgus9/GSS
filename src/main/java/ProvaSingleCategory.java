import java.io.IOException;
import java.sql.SQLException;

import entity.Player;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utilities.Pair;
import utilities.Utilities;
import view.ViewSwitcher;
import view.ViewType;

public class ProvaSingleCategory extends Application{

	public static void main(String[] args) {

		launch(args);
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		Utilities.setCF("3456789012345678");
		Utilities.setCategoria(1);
		ViewSwitcher.getInstance().switchView(stage, ViewType.SCHEDAGIOCATORE);
	}
}

