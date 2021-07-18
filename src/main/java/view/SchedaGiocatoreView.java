package view;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import entity.Finanze;
import entity.Immagine;
import entity.Payment;
import entity.Player;
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
import utilities.DateTime;
import utilities.Pair;
import utilities.Utilities;

public class SchedaGiocatoreView extends ViewImpl{

	@FXML
	private AnchorPane pane;
	
	@FXML
	private Label nameLabel;

	@FXML
	private ImageView logo;

	@FXML
	private Rectangle color1;

	@FXML
	private Rectangle color2;
	
	@FXML
	TextField nameText;
	
	@FXML
	TextField surnameText;
	
	@FXML
	TextField cfText;
	
	@FXML
	TextField dataText;
	
	@FXML
	TextField matricolaText;
	
	@FXML
	TextField pesoText;
	
	@FXML
	TextField altezzaText;
	
	@FXML
	TextField certificatoText;
	
	@FXML
	TextField message;

	@FXML
	TextField playerPath;
	
	@FXML
	Button closeButton;
	
	@FXML
	TextField sesso;
	
	@FXML
	TextField preferenza;
	
	@FXML
	TextField ruolo;
	
	private int category;
	
	private String CF;
	
	@Override
	public void init() {
		this.setSociety();
		this.category=Utilities.getCategoria();
		this.setGiocatore();
	}
	
	private void setSociety() {
		Pair<Image, Society> society = null;
		try {
			society = Utilities.getSociety();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.logo.setImage(society.getX());
		this.nameLabel.setText(society.getY().getNome());
		this.color1.setFill(Color.valueOf(society.getY().getColor1()));
		this.color2.setFill(Color.valueOf(society.getY().getColor2()));
	}

	private void setGiocatore() {
		Pair<Image, Player> player = null;
		try {
			player = Utilities.getPlayer(CF);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
