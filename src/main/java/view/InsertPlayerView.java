package view;

import java.io.IOException;
import java.sql.SQLException;

import entity.Finanze;
import entity.Payment;
import entity.Person;
import entity.Player;
import entity.Sesso;
import entity.Society;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utilities.DateTime;
import utilities.Pair;
import utilities.Utilities;

public class InsertPlayerView extends ViewImpl{

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
	Button insertButton;
	
	private int category;
	
	@Override
	public void init() {
		this.setSociety();
		this.category=Utilities.getCategoria();
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
		if(nameText.getText().isEmpty() || surnameText.getText().isEmpty() || cfText.getText().isEmpty() || dataText.getText().isEmpty() || matricolaText.getText().isEmpty() || pesoText.getText().isEmpty() || altezzaText.getText().isEmpty() || certificatoText.getText().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public void insert() throws NumberFormatException, SQLException, IOException {
		if(Utilities.checkPersona(this.cfText.getText())) {
			if(this.check()) {
				Payment pagamento = new Payment(1000, false, Finanze.QUOTA);
				pagamento.insert();
				Player player = new Player(this.cfText.getText(), this.nameText.getText(), this.surnameText.getText(), new DateTime(this.dataText.getText()),
						(int) pagamento.getPrimaryKey(), Sesso.ALTRO.getKey(), Utilities.getSociety().getY().getPrimaryKey(), Integer.parseInt(this.matricolaText.getText()), this.pesoText.getText(),
						this.altezzaText.getText(), new DateTime(this.certificatoText.getText()), "ATT", this.category, "DS");
				player.insert();
				this.getStage().close();
			} else {
				message.setText("Inserire tutte le informazioni");
			}
		} else {
			message.setText("CF già in uso");
		}
		
	}
}
