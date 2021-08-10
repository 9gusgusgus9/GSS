package view;

import java.io.File;

import entity.Category;
import entity.Immagine;
import entity.Society;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utilities.Pair;
import utilities.Utilities;

public class InsertCategoryView extends ViewImpl {

	@FXML
	AnchorPane pane;
	
	@FXML
	Label nameLabel;
	
	@FXML
	ImageView logo;
	
	@FXML
	Rectangle color1;
	
	@FXML
	Rectangle color2;
	
	@FXML
	Label nomeLab;
	
	@FXML
	TextField nome;
	
	@FXML
	Label fotoSqLab;
	
	@FXML
	TextField fotoSqPath;
	
	@FXML
	Button browseButton;
	
	@FXML
	Button insertButton;
	
	private boolean image = false;
	
	@Override
	public void init() {
		this.setSociety();
		this.fotoSqPath.setEditable(false);
		this.fotoSqPath.setText("");
	}
	
	private void setSociety(){
		Pair<Image, Society> society = null;
		society = Utilities.getSociety();
		this.logo.setImage(society.getX());
		this.nameLabel.setText(society.getY().getNome());
		this.color1.setFill(Color.valueOf(society.getY().getColor1()));
		this.color2.setFill(Color.valueOf(society.getY().getColor2()));
	}
	
	@FXML
	public void oneFileChooser() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selezionare la foto di squadra della categoria");
		Stage stage = (Stage) pane.getScene().getWindow();
		File file = fileChooser.showOpenDialog(stage);
		if (file != null) {
			fotoSqPath.setText(file.getPath());
			this.image = true;
		}
	}
	
	@FXML
	public void inserisci() {
		if(this.nome.getText() != "") {
			String pIVA = "";
			pIVA = Utilities.getSociety().getY().getPrimaryKey();
			Category category = null;
			if(this.image) {
				Immagine image = new Immagine(this.fotoSqPath.getText());
				category = new Category(this.nome.getText(), pIVA, image);
			} else {
				category = new Category(this.nome.getText(), pIVA);
			}
			category.insert();
			getStage().close();
		} else {
			this.nomeLab.setTextFill(Color.RED);
		}
	}
}
