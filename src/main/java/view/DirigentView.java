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

public class DirigentView extends ViewImpl{

	@FXML
	private Label nameLabel;

	@FXML
	private ImageView logo;

	@FXML
	private Rectangle color1;

	@FXML
	private Rectangle color2;
	
    @FXML
    private TableView<Person> dirigente;

    @FXML
    private TableColumn<Person, String> dirigenteCF;

    @FXML
    private TableColumn<Person, String> dirigenteCognome;
    
    @FXML
    private TableColumn<Person, String> dirigenteNome;

    @FXML
    private TableColumn<Person, String> dirigenteRuolo;
    
    @FXML
    private Button addDirigent;
    
	@Override
	public void init() {
		this.setSociety();
		this.loadManager();
	}
	
	public void switchToInsertPlayer() {
		ViewSwitcher.getInstance().switchView(new Stage(), ViewType.INSERTDIRIGENT);
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
	
	private void loadManager() {
		dirigente.setItems(FXCollections.observableArrayList(Utilities.getAllPeople().stream().filter(p -> {
			if(Utilities.getMansionByCF(p.getCf()).equals("Dirigente")) {
				return true;
			}else {
				return false;
			}
		}).collect(Collectors.toList())));
		dirigenteCF.setCellValueFactory(new PropertyValueFactory<Person, String>("cf"));
		dirigenteCognome.setCellValueFactory(new PropertyValueFactory<Person, String>("cognome"));
		dirigenteNome.setCellValueFactory(new PropertyValueFactory<Person, String>("nome"));
		dirigenteRuolo.setCellValueFactory(new PropertyValueFactory<Person, String>("codRuolo"));
	}
}

