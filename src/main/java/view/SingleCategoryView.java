package view;

import entity.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class SingleCategoryView extends ViewImpl{

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
		// TODO Auto-generated method stub
		
	}
	
	public void prova() {
		System.out.println("coglione");
	}

}
