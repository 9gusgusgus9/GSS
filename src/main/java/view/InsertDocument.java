package view;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import entity.Immagine;
import entity.Society;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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

public class InsertDocument extends ViewImpl{

	@FXML
	private AnchorPane pane;
	
	@FXML
	Label nameLabel;
	
	@FXML
	Button newEvento;
	
	@FXML
	ImageView logo;
	
	@FXML
	Rectangle color1;
	
	@FXML
	Rectangle color2;
	
	@FXML
	ChoiceBox<String> tipo;
	
	@FXML
	TextField path;
	
	@FXML
	Button browse;
	
	@FXML
	Button inserisci;
	
	@Override
	public void init() {
		this.setSociety();
		List<String> tipi = new LinkedList<>();
		Utilities.getDocumentsType().forEach(p -> {
			tipi.add(p.getY());
		});
		this.tipo.setItems(FXCollections.observableArrayList(tipi));
	}
	
	public void oneFileChooser() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selezionare un logo per la società");
		Stage stage = (Stage) pane.getScene().getWindow();
		File file = fileChooser.showOpenDialog(stage);
		if (file != null) {
			path.setText(file.getPath());
		}
	}
	
	public void insertDocument() {
		if(this.path.getText().isEmpty() || this.tipo.getValue().isEmpty()) {
			System.out.println("OK");
		} else {
			Immagine document = new Immagine(path.getText());
			int idTipo = 0;
			Iterator<Pair<Integer, String>> iter = Utilities.getDocumentsType().iterator();
			while(iter.hasNext()) {
				Pair<Integer, String> pair = iter.next();
				if(pair.getY().equals(tipo.getValue())) {
					idTipo = pair.getX();
				}
			}
			Utilities.insertDocument(Utilities.getCF(), document, idTipo);
			getStage().close();
		}
	}
	
	private void setSociety() {
		Pair<Image, Society> society = null;
		society = Utilities.getSociety();
		this.logo.setImage(society.getX());
		this.nameLabel.setText(society.getY().getNome());
		this.color1.setFill(Color.valueOf(society.getY().getColor1()));
		this.color2.setFill(Color.valueOf(society.getY().getColor2()));
	}

}
