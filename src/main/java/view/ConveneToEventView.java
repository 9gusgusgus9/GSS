package view;

import entity.Person;
import entity.Society;

import java.io.IOException;
import java.sql.SQLException;

import com.sun.javafx.scene.control.VirtualScrollBar;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.VirtualFlow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utilities.Pair;
import utilities.Utilities;

public class ConveneToEventView extends ViewImpl{
	
	@FXML
	Label nameLabel;
	
	@FXML
	ImageView logo;
	
	@FXML
	Rectangle color1;
	
	@FXML
	Rectangle color2;
	
	@FXML
	TableView<Person> gridConvene;
	
	@FXML
	TableColumn<Person, String> nome;
	
	@FXML
	TableColumn<Person, String> cognome;
	
	@FXML
	TableColumn<Person, String> codRuolo;
	
	@FXML
	TableColumn<Person, String> codCategoria;
	
	@FXML
	Label event;
	
	@Override
	public void init(){
		this.setSociety();
		try {
			gridConvene.setItems(Utilities.getConvocati((int)Utilities.getEvent().getPrimaryKey()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		event.setText(Utilities.getEvent().getEvent()+ ": dal "+ Utilities.getEvent().getInizio().getDate()+" al "+Utilities.getEvent().getFine().getDate());
		nome.setCellValueFactory(new PropertyValueFactory<Person, String>("nome"));
		cognome.setCellValueFactory(new PropertyValueFactory<Person, String>("cognome"));
		codRuolo.setCellValueFactory(new PropertyValueFactory<Person, String>("codRuolo"));
		codCategoria.setCellValueFactory(new PropertyValueFactory<Person, String>("codCategoria"));
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

}
	
	