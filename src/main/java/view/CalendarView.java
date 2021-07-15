package view;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.previousOrSame;

import java.sql.SQLException;

import static java.time.temporal.TemporalAdjusters.nextOrSame;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import utilities.DateTime;
import utilities.Pair;
import utilities.Utilities;

public class CalendarView extends ViewImpl{
	
	private LocalDate first;
	private LocalDate last;
	
	@FXML
	Label nameLabel;
	
	@FXML
	ImageView logo;
	
	@FXML
	Rectangle color1;
	
	@FXML
	Rectangle color2;
	
	@FXML
	TableView<String> gridWeek;
	
	@FXML
	Label actualWeek;
	
	@FXML
	Button nextWeek;
	
	@FXML
	Button previousWeek;
	
	@FXML
	TableColumn<DateTime, String> lunedi;
	
	@Override
	public void init(){
		this.first = LocalDate.now().with(previousOrSame(DayOfWeek.MONDAY)); 
		this.last = LocalDate.now().with(nextOrSame(DayOfWeek.SUNDAY));
		actualWeek.setText(first.getDayOfMonth() + "-" + last.getDayOfMonth() + ", " + this.first.getMonth());
	    try {
	    	List<Pair<DateTime, String>> stp = new LinkedList<>();
	    	for(Pair<DateTime, String> p : Utilities.getEvents(new DateTime(first.getYear(), first.getMonthValue(), first.getDayOfMonth())
			        		, new DateTime(last.getYear(), last.getMonthValue(), last.getDayOfMonth()))) {
	    		stp.add(p.getX(), p);
	    	}
			final ObservableList<String> data =
			        FXCollections.observableArrayList(stp);
			
	        lunedi.setCellValueFactory(
	                new PropertyValueFactory<DateTime, SimpleStringProperty>("Lunedì")
	            );
	        gridWeek.setItems(data);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void next() {
		this.first = this.first.plusDays(7);
		this.last = this.last.plusDays(7);
		actualWeek.setText(this.first.getDayOfMonth() + "-" + this.last.getDayOfMonth() + ", " + this.first.getMonth());
	}

	@FXML
	private void previous() {
		this.first = this.first.minusDays(7);
		this.last = this.last.minusDays(7);
		actualWeek.setText(this.first.getDayOfMonth() + "-" + this.last.getDayOfMonth() + ", " + this.first.getMonth());
	}
	
}