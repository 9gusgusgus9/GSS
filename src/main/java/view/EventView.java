package view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import entity.Category;
import entity.Society;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utilities.Pair;
import utilities.Utilities;

public class EventView extends ViewImpl {

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
	private Label dal, inizio, al, fine;
	
	@FXML
	private Label lab1, lab2, lab3, lab4;
	
	@FXML
	private Button set;
	
	
	@Override
	public void init() {
		this.setSociety();
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
	
	private void setEvent() {
		
	}
}
