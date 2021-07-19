package view;

import java.beans.EventHandler;
import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;

import entity.Category;
import entity.Evento;
import entity.Person;
import entity.Society;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
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
    private VBox vBoxTable;
    
    @FXML
    private TableView<Person> player;

    @FXML
    private TableColumn<Person, String> nomePlayer;
    
    @FXML
    private TableColumn<Person, String> cognomePlayer;

    @FXML
    private TableColumn<Person, String> codRuoloPlayer;
	
    @FXML
    private TableView<Person> staff;
    
    @FXML
    private TableColumn<Person, String> nomeStaff;
    
    @FXML
    private TableColumn<Person, String> cognomeStaff;

    @FXML
    private TableColumn<Person, String> codRuoloStaff;
	
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
		this.populateTable();
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
	
	private void populateTable() {
		ObservableList<Person> people = Utilities.getAllPeople();
		player.setItems(FXCollections.observableArrayList(people.stream().filter(p-> {
					if(Utilities.getMansionByCF(p.getCf()).equals("Giocatore")) {
						int codCategoria = Utilities.getCategoria();
						if(Utilities.getCategoryCfFromCod(codCategoria).contains(p.getCf())) {
							return true;
						} else {
							return false;	
						}
					} else {
						return false;
					}
		}).collect(Collectors.toList())));
		nomePlayer.setCellValueFactory(new PropertyValueFactory<Person, String>("nome"));
		cognomePlayer.setCellValueFactory(new PropertyValueFactory<Person, String>("cognome"));
		codRuoloPlayer.setCellValueFactory(new PropertyValueFactory<Person, String>("codRuolo"));
		player.setRowFactory( tv -> {
		    TableRow<Person> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            Utilities.setCF(row.getItem().getCf());
		            ViewSwitcher.getInstance().switchView(new Stage(), ViewType.SCHEDAGIOCATORE);
		        }
		    });
		    return row ;
		});
		
		staff.setItems(FXCollections.observableArrayList(people.stream().filter(p-> {
			if(Utilities.getMansionByCF(p.getCf()).equals("Staff")) {
				int codCategoria = Utilities.getCategoria();
				if(Utilities.getCategoryCfFromCod(codCategoria).contains(p.getCf())) {
					return true;
				} else {
					return false;	
				}
			} else {
				return false;
			}
		}).collect(Collectors.toList())));
		nomeStaff.setCellValueFactory(new PropertyValueFactory<Person, String>("nome"));
		cognomeStaff.setCellValueFactory(new PropertyValueFactory<Person, String>("cognome"));
		codRuoloStaff.setCellValueFactory(new PropertyValueFactory<Person, String>("codRuolo"));
		staff.setRowFactory( tv -> {
		    TableRow<Person> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            Utilities.setCF(row.getItem().getCf());
		            ViewSwitcher.getInstance().switchView(new Stage(), ViewType.SCHEDAGIOCATORE);
		        }
		    });
		    return row ;
		});
	}
}
