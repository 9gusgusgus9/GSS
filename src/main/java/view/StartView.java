package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utilities.Utilities;

public class StartView implements View {

	@FXML
	private ImageView calcio;

	@FXML
	private ImageView pallavolo;

	@FXML
	private ImageView basket;

	@FXML
	private TextField nome;

	@FXML
	private AnchorPane pane;

	@FXML
	private TextField logoPath;

	public void init() {
		calcio.setOpacity(0.5);
		basket.setOpacity(0.5);
		pallavolo.setOpacity(0.5);
	}

	public void oneFileChooser() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selezionare un logo per la società");
		Stage stage = (Stage) pane.getScene().getWindow();
		File file = fileChooser.showOpenDialog(stage);
		if (file != null) {
			logoPath.setText(file.getPath());
		}
	}

	public void setCalcio() {
		calcio.setOpacity(1);
		basket.setOpacity(0.5);
		pallavolo.setOpacity(0.5);
	}

	public void setPallavolo() {
		calcio.setOpacity(0.5);
		basket.setOpacity(0.5);
		pallavolo.setOpacity(1);
	}

	public void setBasket() {
		calcio.setOpacity(0.5);
		basket.setOpacity(1);
		pallavolo.setOpacity(0.5);
	}

	@Override
	public void attachController(Controller controller) {
		
	}

	@Override
	public Controller getController() {
		return null;
	}

	@Override
	public void setStage(Stage stage) {
		
	}

	@Override
	public Stage getStage() {
		return null;
	}

}
