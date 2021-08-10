package view;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import entity.Finanze;
import entity.Immagine;
import entity.Payment;
import entity.Society;
import entity.Staff;
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

public class InsertStaffView extends ViewImpl{

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
	TextField message;

	@FXML
	TextField staffPath;
	
	@FXML
	Button insertButton;
	
	@FXML
	Button browse;
	
	@FXML
	ChoiceBox<String> sesso;
	
	@FXML
	ChoiceBox<String> ruolo;
	
	private int category;
	
	@Override
	public void init() {
		this.setSociety();
		this.setChoiceBox();
		this.category=Utilities.getCategoria();
	}
	
	private void setChoiceBox() {
		sesso.setItems(FXCollections.observableArrayList("Maschio","Femmina","Altro"));
		ruolo.setItems(FXCollections.observableArrayList("2All","Acc","All","Mass","PA","Altro"));
	}
	
	public void setSesso() {
		sesso.show();
	}
		
	public void setRuolo() {
		ruolo.show();
	}
	
	private void setSociety() {
		Pair<Image, Society> society = null;
		society = Utilities.getSociety();
		this.logo.setImage(society.getX());
		this.nameLabel.setText(society.getY().getNome());
		this.color1.setFill(Color.valueOf(society.getY().getColor1()));
		this.color2.setFill(Color.valueOf(society.getY().getColor2()));
	}
	
	private boolean check() {
		if(nameText.getText().isEmpty() || surnameText.getText().isEmpty() || cfText.getText().isEmpty() || dataText.getText().isEmpty() || matricolaText.getText().isEmpty() || sesso.getValue().isEmpty() || ruolo.getValue().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public void oneFileChooser() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selezionare un logo per la società");
		Stage stage = (Stage) pane.getScene().getWindow();
		File file = fileChooser.showOpenDialog(stage);
		if (file != null) {
			staffPath.setText(file.getPath());
		}
	}
	
	public void insert() throws NumberFormatException, SQLException, IOException {
		if(Utilities.isFreeCF(this.cfText.getText())) {
			if(this.check()) {
				Payment pagamento = new Payment(1000, false, Finanze.QUOTA);
				pagamento.insert();
				if(this.staffPath.getText().isEmpty()) {
					Staff staff = new Staff(this.cfText.getText(), this.nameText.getText(), this.surnameText.getText(), new DateTime(this.dataText.getText()),
						(int) pagamento.getPrimaryKey(), sesso.getValue(), Utilities.getSociety().getY().getPrimaryKey(), Integer.parseInt(this.matricolaText.getText()),
						ruolo.getValue(), this.category);
					staff.insert();
				} else {
					Immagine image = new Immagine(staffPath.getText());
					Staff staff = new Staff(this.cfText.getText(), this.nameText.getText(), this.surnameText.getText(), new DateTime(this.dataText.getText()),
						(int) pagamento.getPrimaryKey(), sesso.getValue(), Utilities.getSociety().getY().getPrimaryKey(),image, Integer.parseInt(this.matricolaText.getText()),
						ruolo.getValue(), this.category);
					staff.insert();
				}
				this.getStage().close();
			} else {
				message.setText("Inserire tutte le informazioni");
			}
		} else {
			message.setText("CF già in uso");
		}
		
	}
}
