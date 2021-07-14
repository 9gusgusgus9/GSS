package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import entity.Immagine;
import entity.Society;
import entity.Sport;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StartView extends ViewImpl {

	@FXML
	private AnchorPane pane;

	@FXML
	private Label nomeLab;

	@FXML
	private Label pivaLab;

	@FXML
	private Label logoLab;

	@FXML
	private Label sportLab;

	@FXML
	private TextField nome;

	@FXML
	private TextField pIVA;

	@FXML
	private TextField logoPath;

	@FXML
	private ImageView calcio;

	@FXML
	private ImageView pallavolo;

	@FXML
	private ImageView basket;
	
	@FXML
	private ColorPicker color1;

	@FXML
	private ColorPicker color2;

	private String societyName = null;
	private String partitaIVA = null;
	private String logo = null;
	private String colorA = null;
	private String colorB = null;
	private Sport sport = Sport.NONSELEZIONATO;
	private boolean flag = true;
	
	public void init() {
		this.setDefault();
		this.calcio.setOpacity(0.5);
		this.basket.setOpacity(0.5);
		this.pallavolo.setOpacity(0.5);
		this.logoPath.setEditable(false);
	}

	private void setDefault() {
		Immagine image = new Immagine("src/main/resources/img/default/stemma.jpg");
		image.setPrimaryKey(1);
		image.insert();
		image = new Immagine("src/main/resources/img/default/categoria.jpg");
		image.setPrimaryKey(2);
		image.insert();
		image = new Immagine("src/main/resources/img/default/user.png");
		image.setPrimaryKey(3);
		image.insert();
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
		this.sport = Sport.CALCIO;
		calcio.setOpacity(1);
		basket.setOpacity(0.5);
		pallavolo.setOpacity(0.5);
	}

	public void setPallavolo() {
		this.sport = Sport.PALLAVOLO;
		calcio.setOpacity(0.5);
		basket.setOpacity(0.5);
		pallavolo.setOpacity(1);
	}

	public void setBasket() {
		this.sport = Sport.BASKET;
		calcio.setOpacity(0.5);
		basket.setOpacity(1);
		pallavolo.setOpacity(0.5);
	}

	public void switchToTest() throws FileNotFoundException {
		if(this.nome.getText().equals("")) {
			this.nomeLab.setTextFill(Color.RED);
			this.flag = false;
		} else {
			this.nomeLab.setTextFill(Color.BLACK);
		}
		if(this.pIVA.getText().equals("")) {
			this.pivaLab.setTextFill(Color.RED);
			this.flag = false;
		} else {
			this.pivaLab.setTextFill(Color.BLACK);
		}
		if(this.sport.equals(Sport.NONSELEZIONATO)) {
			this.sportLab.setTextFill(Color.RED);
			this.flag = false;
		} else {
			this.sportLab.setTextFill(Color.BLACK);
		}
		if(this.flag) {
			this.societyName = this.nome.getText();
			this.partitaIVA = this.pIVA.getText();
			this.logo = this.logoPath.getText();
			if(this.logoPath.getText().equals("")) {
				if(this.color1.getValue().equals(Color.WHITE) && this.color2.getValue().equals(Color.WHITE)) {
					Society society = new Society(this.partitaIVA, this.societyName, this.sport, "#9c9c9c", "#e4f267");
					society.insert();
				} else {
					Society society = new Society(this.partitaIVA, this.societyName, this.sport, this.color1.getValue().toString(), this.color2.getValue().toString());
					society.insert();
				}
			} else {
				Immagine image = new Immagine(this.logo);
				if(this.color1.equals(Color.WHITE) && this.color2.equals(Color.WHITE)) {
					Society society = new Society(this.partitaIVA, this.societyName, this.sport, image, "#9c9c9c", "#e4f267");
					society.insert();
				} else {
					Society society = new Society(this.partitaIVA, this.societyName, this.sport, image, this.color1.getValue().toString(), this.color2.getValue().toString());
					society.insert();
				}
				
			}
			ViewSwitcher.getInstance().switchView(this.getStage(), ViewType.CATEGORY);
		} else {
			this.flag = true;
		}
	}
}
