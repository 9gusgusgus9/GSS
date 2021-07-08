package view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import entity.Category;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
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
	
	@FXML
	ImageView image2;
	
	@FXML
	Label label2;
	
	@FXML
	ImageView image3;
	
	@FXML
	Label label3;
	
	@FXML
	ImageView image4;
	
	@FXML
	Label label4;
	
	@FXML
	ImageView image5;
	
	@FXML
	Label label5;
	
	@FXML
	ImageView image6;
	
	@FXML
	Label label6;
	
	@FXML
	ImageView image7;
	
	@FXML
	Label label7;
	
	@FXML
	ImageView image8;
	
	@FXML
	Label label8;
	
	@FXML
	ImageView image9;
	
	@FXML
	Label label9;
	
	@FXML
	ImageView image10;
	
	@FXML
	Label label10;
	
	@FXML
	ImageView image11;
	
	@FXML
	Label label11;
	
	@FXML
	ImageView image12;
	
	@FXML
	Label label12;
	
	@FXML
	ImageView image13;
	
	@FXML
	Label label13;
	
	@FXML
	ImageView image14;
	
	@FXML
	Label label14;
	
	@FXML
	ImageView image15;
	
	@FXML
	Label label15;
		
	@Override
	public void init() {
		List<Pair<Category, Image>> list = new LinkedList<>();
		try {
			list = Utilities.getCategories();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator iter = list.iterator();
		int c = 2;
		while(iter.hasNext()) {
			Pair<Category, Image> next = (Pair<Category, Image>) iter.next();
			getLabel(c).setText(next.getX().getNome());
			getImageView(c).setImage(next.getY());
			c++;
		}
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

}
