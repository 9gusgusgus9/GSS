package view;

import controller.Controller;
import javafx.stage.Stage;

public abstract class ViewImpl implements View {

	private Stage stage;
	private Controller controller;
	
	@Override
	public void attachController(Controller controller) {
		this.controller=controller;
	}

	@Override
	public Controller getController() {
		return this.controller;
	}

	@Override
	public void setStage(Stage stage) {
		this.stage=stage;
	}

	@Override
	public Stage getStage() {
		return this.stage;
	}

	@Override
	public abstract void init();
	
}
