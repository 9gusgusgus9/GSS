package view;

import entity.Person;
import entity.Society;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utilities.Pair;
import utilities.Utilities;

public class ConveneToEventView extends ViewImpl{
	
	@FXML
	private Label nameLabel;
	
	@FXML
	private ImageView logo;
	
	@FXML
	private Rectangle color1;
	
	@FXML
	private Rectangle color2;
	
	@FXML
	private TableView<Person> gridConvene;
	
	@FXML
	private TableColumn<Person, String> nome;
	
	@FXML
	private TableColumn<Person, String> cognome;
	
	@FXML
	private TableColumn<Person, String> codRuolo;
	
	@FXML
	private TableColumn<Person, String> codCategoria;
	
	@FXML
	private Label event;
	
	@Override
	public void init(){
		this.setSociety();
		gridConvene.setItems(Utilities.getConvocati((int)Utilities.getEvent().getPrimaryKey()));
		event.setText(Utilities.getEvent().getEvent()+ ": dal "+ Utilities.getEvent().getInizio().getDate()+" al "+Utilities.getEvent().getFine().getDate());
		nome.setCellValueFactory(new PropertyValueFactory<Person, String>("nome"));
		cognome.setCellValueFactory(new PropertyValueFactory<Person, String>("cognome"));
		codRuolo.setCellValueFactory(new PropertyValueFactory<Person, String>("codRuolo"));
		codCategoria.setCellValueFactory(new PropertyValueFactory<Person, String>("codCategoria"));
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
	
	