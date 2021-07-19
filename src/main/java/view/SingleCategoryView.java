package view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;

import entity.Category;
import entity.Person;
import entity.Society;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import utilities.Pair;
import utilities.Utilities;

public class SingleCategoryView extends ViewImpl{

	@FXML
	private Label nameLabel;

	@FXML
	private ImageView logo;

	@FXML
	private Rectangle color1;

	@FXML
	private Rectangle color2;
    
    @FXML
    private TableView<Person> giocatore;

    @FXML
    private TableColumn<Person, String> giocatoreCF;
    
    @FXML
    private TableColumn<Person, String> giocatoreNome;

    @FXML
    private TableColumn<Person, String> giocatoreRuolo;
    
    @FXML
    private TableView<Person> staff;

    @FXML
    private TableColumn<Person, String> staffCF;
    
    @FXML
    private TableColumn<Person, String> staffNome;

    @FXML
    private TableColumn<Person, String> staffRuolo;
		
    @FXML
    private Label categoria;
    
    @FXML
    private Button addPlayer;
    
    @FXML
    private Button addStaff;
    
	@Override
	public void init() {
		this.setSociety();
		this.setCategory();
		this.loadPlayerAndStaff();
	}
	
	public void switchToInsertPlayer() {
		ViewSwitcher.getInstance().switchView(new Stage(), ViewType.INSERTPLAYERVIEW);
	}

	public void switchToInsertStaff() {
		ViewSwitcher.getInstance().switchView(new Stage(), ViewType.INSERTSTAFF);
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
			if(Utilities.getCategoria()==0) {
				this.categoria.setText("Dirigenti");
			} else {
				category = Utilities.getCategory(Utilities.getCategoria());
				this.categoria.setText(category.getY().getNome());
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadPlayerAndStaff() {
		ObservableList<Person> list = Utilities.getAllPeople();
		giocatore.setItems(FXCollections.observableArrayList(list.stream().filter(p -> {
			if(Utilities.getMansionByCF(p.getCf()).equals("Giocatore")) {
				return true;
			}else {
				return false;
			}
		}).collect(Collectors.toList())));
		staff.setItems(FXCollections.observableArrayList(list.stream().filter(p -> {
			if(Utilities.getMansionByCF(p.getCf()).equals("Staff")) {
				return true;
			}else {
				return false;
			}
		}).collect(Collectors.toList())));
		giocatoreCF.setCellValueFactory(new PropertyValueFactory<Person, String>("cf"));
		giocatoreNome.setCellValueFactory(new PropertyValueFactory<Person, String>("nome"));
		giocatoreRuolo.setCellValueFactory(new PropertyValueFactory<Person, String>("codRuolo"));

		staffCF.setCellValueFactory(new PropertyValueFactory<Person, String>("cf"));
		staffNome.setCellValueFactory(new PropertyValueFactory<Person, String>("nome"));
		staffRuolo.setCellValueFactory(new PropertyValueFactory<Person, String>("codRuolo"));
	}
}
