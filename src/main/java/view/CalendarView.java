package view;

import java.time.DayOfWeek;
import java.time.LocalDate;

import entity.Event;

import static java.time.temporal.TemporalAdjusters.previousOrSame;

import java.sql.SQLException;

import static java.time.temporal.TemporalAdjusters.nextOrSame;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
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
	TableView<Event> gridWeek;
	
	@FXML
	Label actualWeek;
	
	@FXML
	Button nextWeek;
	
	@FXML
	Button previousWeek;
	
	@FXML
	TableColumn<Event, String> lunedi;
	
	@Override
	public void init(){
	        printWeekCalendar();
	}

	public void printWeekCalendar() {
		this.first = LocalDate.now().with(previousOrSame(DayOfWeek.MONDAY)); 
		this.last = LocalDate.now().with(nextOrSame(DayOfWeek.SUNDAY));
		System.out.println(first);
		actualWeek.setText(first.getDayOfMonth() + "-" + last.getDayOfMonth() + ", " + this.first.getMonth());
	    	try {
	    		ObservableList<Event> list = FXCollections.observableArrayList();
				for(Event p : Utilities.getEvents(new DateTime(first.getYear(), first.getMonthValue(), first.getDayOfMonth())
				        		, new DateTime(last.getYear(), last.getMonthValue(), last.getDayOfMonth()))) {
					System.out.println(p.getTipoEvento());
					lunedi.setCellValueFactory(new Callback<CellDataFeatures<Event, String>, ObservableValue<String>>() {
					    public ObservableValue<String> call(CellDataFeatures<Event, String> q) {
					        // p.getValue() returns the Person instance for a particular TableView row
					        return q.getValue().getTipoEvento();
					    }
					 });
					list.add(p);
				}
				gridWeek.setItems(list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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