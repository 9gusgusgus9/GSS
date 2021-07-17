package view;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import entity.Event;

import static java.time.temporal.TemporalAdjusters.previousOrSame;

import java.sql.SQLException;

import static java.time.temporal.TemporalAdjusters.nextOrSame;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableHeaderRow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import utilities.DateTime;
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
	TableView<Event> lunedi;
	
	@FXML
	TableView<Event> martedi;
	
	@FXML
	TableView<Event> mercoledi;
	
	@FXML
	TableView<Event> giovedi;
	
	@FXML
	TableView<Event> venerdi;
	
	@FXML
	TableView<Event> sabato;
	
	@FXML
	TableView<Event> domenica;
	
	@FXML
	Label actualWeek;
	
	@FXML
	Button nextWeek;
	
	@FXML
	Button previousWeek;
	
	@FXML
	TableColumn<Event, String> lun;
	
	@FXML
	TableColumn<Event, String> mar;
	
	@FXML
	TableColumn<Event, String> mer;
	
	@FXML
	TableColumn<Event, String> giov;
	
	@FXML
	TableColumn<Event, String> ven;
	
	@FXML
	TableColumn<Event, String> sab;
	
	@FXML
	TableColumn<Event, String> dom;
	
	private List<TableColumn<Event, String>> list = new ArrayList<>();
	
	@Override
	public void init(){
        this.printWeekCalendar();
	}

	public void printWeekCalendar() {
		this.first = LocalDate.now().with(previousOrSame(DayOfWeek.MONDAY)); 
		this.last = LocalDate.now().with(nextOrSame(DayOfWeek.SUNDAY));
		actualWeek.setText(first.getDayOfMonth() + "-" + last.getDayOfMonth() + ", " + this.first.getMonth());
		this.setEventOnDay();
	}
	
	@FXML
	private void next() {
		this.first = this.first.plusDays(7);
		this.last = this.last.plusDays(7);
		actualWeek.setText(this.first.getDayOfMonth() + "-" + this.last.getDayOfMonth() + ", " + this.first.getMonth());
		this.setEventOnDay();
	}
	
	private DayOfWeek getDayOfWeek(Event e) {
	    LocalDate localDate = LocalDate.of(e.getInizio().getAnno(), e.getInizio().getMese(), e.getInizio().getGiorno());
	    DayOfWeek dayOfWeek = localDate.getDayOfWeek();
		return dayOfWeek;
	}

	@FXML
	private void previous() {
		this.first = this.first.minusDays(7);
		this.last = this.last.minusDays(7);
		actualWeek.setText(this.first.getDayOfMonth() + "-" + this.last.getDayOfMonth() + ", " + this.first.getMonth());
		this.setEventOnDay();
	}
	
	
	private void setEventOnDay() {
    	try {
    		lunedi.setItems(null);
    		martedi.setItems(null);
    		mercoledi.setItems(null);
    		giovedi.setItems(null);
    		venerdi.setItems(null);
    		sabato.setItems(null);
    		domenica.setItems(null);
    		for(Event e : Utilities.getEvents(new DateTime(this.first.getYear(), this.first.getMonthValue(), this.first.getDayOfMonth()), new DateTime(this.last.getYear(), this.last.getMonthValue(), this.last.getDayOfMonth()))) {
				DayOfWeek day = this.getDayOfWeek(e);
				if (day == DayOfWeek.MONDAY) {
					lunedi.setItems(FXCollections.observableArrayList(e));
					lun.setCellValueFactory(new PropertyValueFactory<Event, String>("event"));
				} else if (day == DayOfWeek.TUESDAY) {
					martedi.setItems(FXCollections.observableArrayList(e));
					mar.setCellValueFactory(new PropertyValueFactory<Event, String>("event"));
				} else if (day == DayOfWeek.WEDNESDAY) {
					mercoledi.setItems(FXCollections.observableArrayList(e));
					mer.setCellValueFactory(new PropertyValueFactory<Event, String>("event"));
				} else if (day == DayOfWeek.THURSDAY) {
					giovedi.setItems(FXCollections.observableArrayList(e));
					giov.setCellValueFactory(new PropertyValueFactory<Event, String>("event"));
				} else if (day == DayOfWeek.FRIDAY) {
					venerdi.setItems(FXCollections.observableArrayList(e));
					ven.setCellValueFactory(new PropertyValueFactory<Event, String>("event"));
				} else if (day == DayOfWeek.SATURDAY) {
					sabato.setItems(FXCollections.observableArrayList(e));
					sab.setCellValueFactory(new PropertyValueFactory<Event, String>("event"));
				} else {
					domenica.setItems(FXCollections.observableArrayList(e));
					dom.setCellValueFactory(new PropertyValueFactory<Event, String>("event"));
				}
    		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}