package view;

import java.io.IOException;
import java.sql.SQLException;

import entity.Category;
import entity.Person;
import entity.Society;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableView<Person> tableView;

    @FXML
    private TableColumn<Person, String> columnCFPlayer;
    
    @FXML
    private TableColumn<Person, String> columnNamePlayer;

    @FXML
    private TableColumn<Person, String> columnRuoloPlayer;
	
    @FXML
    private TableColumn<Person, String> columnCFStaff;
    
    @FXML
    private TableColumn<Person, String> columnNameStaff;

    @FXML
    private TableColumn<Person, String> columnRuoloStaff;
	
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
	}
	
	public void switchToInsertPlayer() {
		ViewSwitcher.getInstance().switchView(new Stage(), ViewType.INSERTPLAYERVIEW);
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
			category = Utilities.getCategory(1);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		this.categoria.setText(category.getY().getNome());
	}
	
}
