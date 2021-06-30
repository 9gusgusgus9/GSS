
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
import entity.Image;
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
import javafx.scene.Parent;

public class Prova2 extends Application{

	public static void main(String[] args) throws SQLException, IOException {
//		// Utilities.tableEmpty();
//		InputStream immagine = new FileInputStream("src/main/resources/img/pallavolo.jpg");
//		Image image = new Image(immagine, "pallavolo", ".jpg");
//		//la image.insert() non va più richiamata, la fanno automaticamente i costruttori, dopo potete cancellare
//		//image.insert();
//		Society rick = new Society("12345678923", "TOMMI", Sport.CALCIO);
//		rick.insert();
//		Category allievi = new Category("allievi", rick.getPrimaryKey(), image);
//		allievi.insert();
//		Event allenamento = new Event(new DateTime(4, 2, 3), new DateTime(4, 3, 4), rick.getPrimaryKey(), 4);
//		Event generico = new Event(new DateTime(6, 2, 3), new DateTime(6, 3, 4), rick.getPrimaryKey(),
//				"Cena di squadra");
//		Event partita = new Event(new DateTime(2, 2, 3), new DateTime(2, 3, 4), rick.getPrimaryKey(), "Bubano", 4);
//		partita.insert();
//		allenamento.insert();
//		generico.insert();
//		partita.setRisultato("0-1");
//		Payment pagamento = new Payment(1000, false, Finanze.QUOTA);
//		pagamento.insert();
//		Staff rambo = new Staff("ojubfjwneoufnw86", "rambo", "rambo", new DateTime(1701, 02, 29),
//				(int) pagamento.getPrimaryKey(), Sesso.MASCHIO.getKey(), (String) rick.getPrimaryKey(), image, "All",
//				(int) allievi.getPrimaryKey());
//		rambo.insert();
//		Player ronaldo = new Player("CR777712CR771214", "Cristiano", "Ronaldo", new DateTime(1701, 02, 29),
//				(int) pagamento.getPrimaryKey(), Sesso.ALTRO.getKey(), (String) rick.getPrimaryKey(), image, 0, "123",
//				"123", new DateTime(2021, 12, 31), "DIF", (int) allievi.getPrimaryKey(), "DS");
//		ronaldo.insert();
//		Manager gus = new Manager("ojubfjwneoufnw90", "gus", "gus", new DateTime(1701, 02, 29),
//				(int) pagamento.getPrimaryKey(), Sesso.FEMMINA.getKey(), (String) rick.getPrimaryKey(), image, "DS");
//		gus.insert();
		launch(args);
		 
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
//	    primaryStage.setTitle("Convertitore da gradi a radianti");
//        FXMLLoader loader = new FXMLLoader(new URL("\\src\\main\\resources\\viewStyle\\StartView.fxml"));
//        BorderPane root = (BorderPane) loader.load();
//        Scene scene = new Scene(root);
//        primaryStage.setScene(scene);
//        primaryStage.setResizable(false);
//        primaryStage.show();
//        
        FXMLLoader load = new FXMLLoader(ClassLoader.getSystemResource("viewStyle/StartView.fxml"));
        Parent parent = null;
        try {
            parent = load.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene newScene;
        newScene = new Scene(parent);
        primaryStage.setScene(newScene);

        primaryStage.show();
	}
}
