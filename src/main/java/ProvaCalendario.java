import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewSwitcher;
import view.ViewType;

public class ProvaCalendario extends Application{

	public static void main(String[] args) {

		launch(args);
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		ViewSwitcher.getInstance().switchView(stage, ViewType.CALENDAR);
	}
}
