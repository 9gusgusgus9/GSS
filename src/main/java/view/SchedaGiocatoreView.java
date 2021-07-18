package view;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import entity.Category;
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
	private Label categoryLabel;

	@FXML
	private ImageView playerImage;
	
	@FXML
	TextField nameText;
	
	@FXML
	TextField surnameText;
	
	@FXML
	TextField cfText;
	
	@FXML
	TextField certificatoText;
	
	@FXML
	TextField dataText;

	@FXML
	TextField sessoText;
	
	@FXML
	TextField matricolaText;
	
	@FXML
	TextField pesoText;
	
	@FXML
	TextField altezzaText;
	
	@FXML
	Button closeButton;
		
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
		this.setCategory();
		this.CF=Utilities.getCF();
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

	private void setCategory() {
		Pair<Image, Category> category = null;
		try {
			category = Utilities.getCategory(this.category);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.categoryLabel.setText(category.getY().getNome());
		}
	
	private void setGiocatore() {
		Pair<Image, Player> player = null;
		try {
			player = Utilities.getPlayer(CF);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		this.playerImage.setImage(player.getX());
		this.nameText.setText(player.getY().getPersona().getNome());
		this.surnameText.setText(player.getY().getPersona().getCognome());
		this.cfText.setText((String) player.getY().getPrimaryKey());
		this.certificatoText.setText(player.getY().getData_scadenza_certificato().getDate());
		this.dataText.setText((String) player.getY().getPersona().getData().getDate());
		this.sessoText.setText(player.getY().getPersona().getCodSesso());
		this.pesoText.setText(player.getY().getPeso());
		this.altezzaText.setText(player.getY().getAltezza());
		this.matricolaText.setText(Integer.toString(player.getY().getPersona().getMatricola()));
		this.preferenza.setText(player.getY().getCodPreferenza());
		this.ruolo.setText(player.getY().getCodRuolo());
	}
	
	public void close() {
		this.getStage().close();
	}
}
