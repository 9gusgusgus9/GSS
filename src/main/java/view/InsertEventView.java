package view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import entity.Category;
import entity.Event;
import entity.Society;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utilities.Pair;
import utilities.Utilities;

public class InsertEventView extends ViewImpl {

	@FXML
	private Label nameLabel;
	
	@FXML
	private ImageView logo;
	
	@FXML
	private Rectangle color1;
	
	@FXML
	private Rectangle color2;
	
	@FXML
	private Label name;
	
	@FXML
	private Label dal, al;
	
	@FXML
	private DatePicker inizio, fine;
	
	@FXML
	private Label nomeAvvLab, categoriaLab, descrizioneLab;
	
	@FXML
	private RadioButton rPartita, rAllenamento, rGenerico;
	
	@FXML
	private TextField nomeAvv, descrizione;
	
	@FXML
	private ChoiceBox<String> categoria;
	
	@FXML
	private HBox partita, allenamento, generico, crea;
	
	@FXML
	private Button inserisci;
	
	private boolean flag = true;
	
	@Override
	public void init() {
		this.setSociety();
		this.allenamento.setVisible(false);
		this.partita.setVisible(false);
		this.generico.setVisible(false);
		this.crea.setVisible(false);
		this.setCategories();
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
		System.out.println(society.getY().getColor1());
		System.out.println(society.getY().getColor2());
		this.color1.setFill(Color.valueOf(society.getY().getColor1()));
		this.color2.setFill(Color.valueOf(society.getY().getColor2()));
	}
	
	private void setCategories() {
		List<String> list = new LinkedList();
		try {
			Utilities.getCategories().stream().forEach((p) -> list.add(p.getX().getNome()));
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		categoria.setItems(FXCollections.observableArrayList(list));
	}
	
	@FXML
	private void setPartita() {
		this.generico.setVisible(false);
		this.allenamento.setVisible(true);
		this.partita.setVisible(true);
		this.crea.setVisible(true);
		this.name.setText("NUOVA PARTITA");
	}
	
	@FXML
	private void setAllenamento() {
		this.generico.setVisible(false);
		this.allenamento.setVisible(true);
		this.partita.setVisible(false);
		this.crea.setVisible(true);
		this.name.setText("NUOVO ALLENAMENTO");
	}
	
	@FXML
	private void setGenerico() {
		this.generico.setVisible(true);
		this.allenamento.setVisible(false);
		this.partita.setVisible(false);
		this.crea.setVisible(true);
		this.name.setText("NUOVO GENERICO");
	}
	
	@FXML
	private void inserisci() {
		if(inizio.getValue() == null) {
			dal.setTextFill(Color.RED);
			flag = false;
		} else {
			dal.setTextFill(Color.BLACK);
		}
		if(fine.getValue() == null) {
			al.setTextFill(Color.RED);
			flag = false;
		} else {
			al.setTextFill(Color.BLACK);
		}
		if(rPartita.isSelected()) {
			if(nomeAvv.getText().equals("")) {
				nomeAvvLab.setTextFill(Color.RED);
				flag = false;
			}else {
				nomeAvvLab.setTextFill(Color.BLACK);
			}
			if(categoria.getValue() == null) {
				categoriaLab.setTextFill(Color.RED);
				flag = false;
			}else {
				categoriaLab.setTextFill(Color.BLACK);
			}
		} else if(rAllenamento.isSelected()){
			if(categoria.getValue() == null) {
				categoriaLab.setTextFill(Color.RED);
				flag = false;
			}else {
				categoriaLab.setTextFill(Color.BLACK);
			}
		} else {
			if(descrizione.getText().equals("")) {
				descrizioneLab.setTextFill(Color.RED);
				flag = false;
			}else {
				descrizioneLab.setTextFill(Color.BLACK);
			}
		}
		if(this.flag) {
			
		}
		this.flag = true;
	}
}