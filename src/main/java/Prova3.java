
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;

import entity.Category;
import entity.Event;
import entity.Finanze;
import entity.Immagine;
import entity.Manager;
import entity.Payment;
import entity.Player;
import entity.Sesso;
import entity.Society;
import entity.Sport;
import entity.Staff;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import utilities.DateTime;
import utilities.Utilities;
import view.ViewSwitcher;
import view.ViewType;
import javafx.scene.Parent;

public class Prova3 extends Application{

	public static void main(String[] args) throws SQLException, IOException {

		launch(args);
		 
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewSwitcher.getInstance().switchView(primaryStage, ViewType.START);
	}
}
