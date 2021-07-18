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

public class InsertPlayerView extends ViewImpl{

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
	Button insertButton;
	
	@FXML
	Button browse;
	
	@FXML
	ChoiceBox<String> sesso;
	
	@FXML
	ChoiceBox<String> preferenza;
	
	@FXML
	ChoiceBox<String> ruolo;
	
	private int category;
	
	@Override
	public void init() {
		this.setSociety();
		try {
			this.setChoiceBox();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.category=Utilities.getCategoria();
	}
	
	private void setChoiceBox() throws SQLException {
		sesso.setItems(FXCollections.observableArrayList("Maschio","Femmina","Altro"));
		preferenza.setItems(FXCollections.observableArrayList("Dx","Sx","DS"));
		ruolo.setItems(FXCollections.observableArrayList(Utilities.getSport()));
	}
	
	public void setSesso() {
		sesso.show();
	}
	
	public void setPreferenza() {
		preferenza.show();
	}
	
	public void setRuolo() {
		ruolo.show();
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
	
	private boolean check() {
		if(nameText.getText().isEmpty() || surnameText.getText().isEmpty() || cfText.getText().isEmpty() || dataText.getText().isEmpty() || matricolaText.getText().isEmpty() || pesoText.getText().isEmpty() || altezzaText.getText().isEmpty() || certificatoText.getText().isEmpty() || sesso.getValue().isEmpty() || preferenza.getValue().isEmpty() || ruolo.getValue().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public void oneFileChooser() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selezionare un logo per la societ�");
		Stage stage = (Stage) pane.getScene().getWindow();
		File file = fileChooser.showOpenDialog(stage);
		if (file != null) {
			playerPath.setText(file.getPath());
		}
	}
	
	public void insert() throws NumberFormatException, SQLException, IOException {
		if(Utilities.checkPersona(this.cfText.getText())) {
			if(this.check()) {
				Payment pagamento = new Payment(1000, false, Finanze.QUOTA);
				pagamento.insert();
				if(this.playerPath.getText().isEmpty()) {
					Player player = new Player(this.cfText.getText(), this.nameText.getText(), this.surnameText.getText(), new DateTime(this.dataText.getText()),
							(int) pagamento.getPrimaryKey(), sesso.getValue(), Utilities.getSociety().getY().getPrimaryKey(), Integer.parseInt(this.matricolaText.getText()), this.pesoText.getText(),
							this.altezzaText.getText(), new DateTime(this.certificatoText.getText()), ruolo.getValue(), this.category, preferenza.getValue());
					player.insert();
				} else {
					Immagine image = new Immagine(playerPath.getText());
					Player player = new Player(this.cfText.getText(), this.nameText.getText(), this.surnameText.getText(), new DateTime(this.dataText.getText()),
						(int) pagamento.getPrimaryKey(), sesso.getValue(), Utilities.getSociety().getY().getPrimaryKey(),image, Integer.parseInt(this.matricolaText.getText()), this.pesoText.getText(),
						this.altezzaText.getText(), new DateTime(this.certificatoText.getText()), ruolo.getValue(), this.category, preferenza.getValue());
					player.insert();
				}
				this.getStage().close();
			} else {
				message.setText("Inserire tutte le informazioni");
			}
		} else {
			message.setText("CF gi� in uso");
		}
		
	}
}
