package view;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import entity.Finanze;
import entity.Immagine;
import entity.Manager;
import entity.Payment;
import entity.Society;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
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

public class InsertDirigentView extends ViewImpl{

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
	private TextField nameText;
	
	@FXML
	private TextField surnameText;
	
	@FXML
	private TextField cfText;
	
	@FXML
	private DatePicker dataText;
	
	@FXML
	private TextField matricolaText;

	@FXML
	private TextField message;

	@FXML
	private TextField dirigentPath;
	
	@FXML
	private Button insertButton;
	
	@FXML
	private Button browse;
	
	@FXML
	private ChoiceBox<String> sesso;
	
	@FXML
	private ChoiceBox<String> ruolo;
	
	@Override
	public void init() {
		this.setSociety();
		this.setChoiceBox();
	}
	
	private void setChoiceBox() {
		sesso.setItems(FXCollections.observableArrayList("Maschio","Femmina","Altro"));
		ruolo.setItems(FXCollections.observableArrayList("AD","Cust","DS","MS","PA","Pres","RF","RG","VP","Altro"));
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
		if(nameText.getText().isEmpty() || surnameText.getText().isEmpty() || cfText.getText().isEmpty() || dataText.getValue() == null || matricolaText.getText().isEmpty() || sesso.getValue().isEmpty() || ruolo.getValue().isEmpty()) {
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
			dirigentPath.setText(file.getPath());
		}
	}
	
	public void insert() throws NumberFormatException, SQLException, IOException {
		if(Utilities.isFreeCF(this.cfText.getText())) {
			if(this.cfText.getText().length() == 16) {
				if(this.check()) {
					Payment pagamento = new Payment(1000, false, Finanze.QUOTA);
					pagamento.insert();
					if(this.dirigentPath.getText().isEmpty()) {
						Manager manager = new Manager(this.cfText.getText(), this.nameText.getText(), this.surnameText.getText(), new DateTime(this.dataText.getValue().getYear(), this.dataText.getValue().getMonthValue(),this.dataText.getValue().getDayOfMonth()),
								(int) pagamento.getPrimaryKey(), sesso.getValue(), Utilities.getSociety().getY().getPrimaryKey(), Integer.parseInt(this.matricolaText.getText()),
								ruolo.getValue());
						manager.insert();
					} else {
						Immagine image = new Immagine(dirigentPath.getText());
						Manager manager = new Manager(this.cfText.getText(), this.nameText.getText(), this.surnameText.getText(), new DateTime(this.dataText.getValue().getYear(), this.dataText.getValue().getMonthValue(),this.dataText.getValue().getDayOfMonth()),
								(int) pagamento.getPrimaryKey(), sesso.getValue(), Utilities.getSociety().getY().getPrimaryKey(),image, Integer.parseInt(this.matricolaText.getText()),
								ruolo.getValue());
						manager.insert();
					}
					this.getStage().close();
				} else {
					message.setText("Inserire tutte le informazioni");
				}
			} else {
				message.setText("CF deve essere di 16 cifre");
			}
		} else {
			message.setText("CF già in uso");
		}
		
	}
}
