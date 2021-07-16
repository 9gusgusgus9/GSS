import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.stage.Stage;
import utilities.Utilities;
import view.ViewSwitcher;
import view.ViewType;

public class ProvaSingleCategory extends Application{

	public static void main(String[] args) throws SQLException, IOException {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewSwitcher.getInstance().switchView(primaryStage, ViewType.SINGLECATEGORY);
	}
}
