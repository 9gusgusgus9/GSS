package view;

import javafx.stage.Stage;

public abstract class ViewImpl implements View {

	private Stage stage;

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
