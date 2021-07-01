import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProvaView extends javafx.application.Application {

	@Override
	public void start(Stage stage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root,500,500);
		stage.setTitle("JavaFX Demo");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) { launch(args);}
}
