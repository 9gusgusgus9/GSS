package view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import entity.Category;
import entity.Society;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import utilities.Pair;
import utilities.Utilities;

public class CategoryView extends ViewImpl {

	@FXML
	Label nameLabel;
	
	@FXML
	ImageView logo;
	
	@FXML
	Rectangle color1;
	
	@FXML
	Rectangle color2;
	
	@FXML
	ImageView image1;
	
	@FXML
	Label label1;
	
	int flag1 = 0;
	
	@FXML
	ImageView image2;
	
	@FXML
	Label label2;

	int flag2 = 0;
	
	@FXML
	ImageView image3;
	
	@FXML
	Label label3;

	int flag3 = 0;
	
	@FXML
	ImageView image4;
	
	@FXML
	Label label4;
	
	int flag4 = 0;
	
	@FXML
	ImageView image5;
	
	@FXML
	Label label5;
	
	int flag5 = 0;
	
	@FXML
	ImageView image6;
	
	@FXML
	Label label6;
	
	int flag6 = 0;
	
	@FXML
	ImageView image7;
	
	@FXML
	Label label7;
	
	int flag7 = 0;
	
	@FXML
	ImageView image8;
	
	@FXML
	Label label8;
	
	int flag8 = 0;
	
	@FXML
	ImageView image9;
	
	@FXML
	Label label9;
	
	int flag9 = 0;
	
	@FXML
	ImageView image10;
	
	@FXML
	Label label10;
	
	int flag10 = 0;
	
	@FXML
	ImageView image11;
	
	@FXML
	Label label11;
	
	int flag11 = 0;
	
	@FXML
	ImageView image12;
	
	@FXML
	Label label12;
	
	int flag12 = 0;
	
	@FXML
	ImageView image13;
	
	@FXML
	Label label13;
	
	int flag13 = 0;
	
	@FXML
	ImageView image14;
	
	@FXML
	Label label14;
	
	int flag14 = 0;
	
	@FXML
	ImageView image15;
	
	@FXML
	Label label15;
	
	int flag15 = 0;
		
	@Override
	public void init() {
		this.setSociety();
		this.loadCategories();
	}
	
	private void loadCategories() {
		List<Pair<Category, Image>> list = new LinkedList<>();
		try {
			list = Utilities.getCategories();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<Pair<Category, Image>> iter = list.iterator();
		int c = 2;
		while(iter.hasNext()) {
			Pair<Category, Image> next = (Pair<Category, Image>) iter.next();
			this.getLabel(c).setText(next.getX().getNome());
			this.getImageView(c).setImage(next.getY());
			this.setFlag(c, (int) next.getX().getPrimaryKey()); 
			this.getImageView(c).setOnMouseClicked(new EventHandler<Event>() {
				
				@Override
				public void handle(Event arg0) {
					ViewSwitcher.getInstance().switchView(new Stage(), ViewType.TEST);
				}
			});
			c++;
		}
		
		
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
	
	private Label getLabel(int x) {
		switch(x) {
		case 1:
			return this.label1;
		case 2:
			return this.label2;
		case 3:
			return this.label3;
		case 4:
			return this.label4;
		case 5:
			return this.label5;
		case 6:
			return this.label6;
		case 7:
			return this.label7;
		case 8:
			return this.label8;
		case 9:
			return this.label9;
		case 10:
			return this.label10;
		case 11:
			return this.label11;
		case 12:
			return this.label12;
		case 13:
			return this.label13;
		case 14:
			return this.label14;
		case 15:
			return this.label15;
		default :
			return null;
		}
	}
	
	private ImageView getImageView(int x) {
		switch(x) {
		case 1:
			return this.image1;
		case 2:
			return this.image2;
		case 3:
			return this.image3;
		case 4:
			return this.image4;
		case 5:
			return this.image5;
		case 6:
			return this.image6;
		case 7:
			return this.image7;
		case 8:
			return this.image8;
		case 9:
			return this.image9;
		case 10:
			return this.image10;
		case 11:
			return this.image11;
		case 12:
			return this.image12;
		case 13:
			return this.image13;
		case 14:
			return this.image14;
		case 15:
			return this.image15;
		default :
			return null;
		}
	}
	
	private int getFlag(int x) {
		switch(x) {
		case 1:
			return this.flag1;
		case 2:
			return this.flag2;
		case 3:
			return this.flag3;
		case 4:
			return this.flag4;
		case 5:
			return this.flag5;
		case 6:
			return this.flag6;
		case 7:
			return this.flag7;
		case 8:
			return this.flag8;
		case 9:
			return this.flag9;
		case 10:
			return this.flag10;
		case 11:
			return this.flag11;
		case 12:
			return this.flag12;
		case 13:
			return this.flag13;
		case 14:
			return this.flag14;
		case 15:
			return this.flag15;
		default :
			return 0;
		}
	}
	
	private void setFlag(int x, int id) {
		switch(x) {
		case 1:
			this.flag1 = id;
		case 2:
			this.flag2 = id;
		case 3:
			this.flag3 = id;
		case 4:
			this.flag4 = id;
		case 5:
			this.flag5 = id;
		case 6:
			this.flag6 = id;
		case 7:
			this.flag7 = id;
		case 8:
			this.flag8 = id;
		case 9:
			this.flag9 = id;
		case 10:
			this.flag10 = id;
		case 11:
			this.flag11 = id;
		case 12:
			this.flag12 = id;
		case 13:
			this.flag13 = id;
		case 14:
			this.flag14 = id;
		case 15:
			this.flag15 = id;
		default :
			
		}
	}

}
