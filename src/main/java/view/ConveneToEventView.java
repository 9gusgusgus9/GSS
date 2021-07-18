package view;

import entity.Person;

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
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import utilities.Utilities;

public class ConveneToEventView extends ViewImpl{
	
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
	TableView<Person> gridConvene;
	
	@FXML
	Button nextWeek;
	
	@FXML
	Button previousWeek;
	
	@FXML
	TableColumn<Person, String> nome;
	
	@FXML
	TableColumn<Person, String> cognome;
	
	@FXML
	TableColumn<Person, String> codRuolo;
	
	@FXML
	TableColumn<Person, String> codCategoria;
	
	@Override
	public void init(){
		try {
			gridConvene.setItems(Utilities.getConvocati((int)Utilities.getEvent().getPrimaryKey()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		nome.setCellValueFactory(new PropertyValueFactory<Person, String>("nome"));
		cognome.setCellValueFactory(new PropertyValueFactory<Person, String>("cognome"));
		codRuolo.setCellValueFactory(new PropertyValueFactory<Person, String>("codRuolo"));
		codCategoria.setCellValueFactory(new PropertyValueFactory<Person, String>("codCategoria"));
	}

}
	
	