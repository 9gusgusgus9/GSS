package view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import entity.Category;
import entity.Person;
import entity.Society;
import javafx.beans.property.BooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utilities.Pair;
import utilities.Utilities;

public class Convocations extends ViewImpl {

	@FXML
	private Label nameLabel;
	
	@FXML
	private ImageView logo;
	
	@FXML
	private Rectangle color1;
	
	@FXML
	private Rectangle color2;
	
	@FXML
	private TableView<Person> table;
	
	@FXML
	private TableColumn<Person, Boolean> invite;
	
	@FXML
	private TableColumn<Person, String> nome;
	
	@FXML
	private TableColumn<Person, String> cognome;
	
	@FXML
	private TableColumn<Person, String> cf;
	
	@FXML
	private TableColumn<Person, String> mansione;
	
	private ObservableList<Person> people;
	
	@Override
	public void init() {
		this.setSociety();
		this.setPeople();
	}
	
	private void setSociety(){
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
	
	private void setPeople() {
		people = Utilities.getAllPeople();
		table.setItems(people);
		table.setEditable(true);
		invite.setCellFactory(column -> new CheckBoxTableCell<>());
		invite.setEditable(true);
		invite.setCellValueFactory(cellData -> {
            Person cellValue = cellData.getValue();
            BooleanProperty property = cellValue.getInvite();

            // Add listener to handler change
            property.addListener((observable, oldValue, newValue) -> cellValue.setInvite(newValue));

            return property;
        });
		nome.setCellValueFactory(new PropertyValueFactory<Person, String>("nome"));
		cognome.setCellValueFactory(new PropertyValueFactory<Person, String>("cognome"));
		cf.setCellValueFactory(new PropertyValueFactory<Person, String>("cf"));
		mansione.setCellValueFactory(new PropertyValueFactory<Person, String>("mansionee"));
	}
	
	@FXML
	private void invite() {
		List<Object> invitati = new LinkedList<>();
		Iterator<Person> iter = this.people.iterator();
		while(iter.hasNext()) {
			Person person = iter.next();
			if(person.getInvite().get()) {
				invitati.add(person.getCf());
			}
		}
		try {
			Utilities.insertConvocati(invitati, Utilities.getEvent().getPrimaryKey());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getStage().close();
	}
}
