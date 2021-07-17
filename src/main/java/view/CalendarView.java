package view;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import entity.Event;

import static java.time.temporal.TemporalAdjusters.previousOrSame;

import java.sql.SQLException;

import static java.time.temporal.TemporalAdjusters.nextOrSame;

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
	
	private List<TableColumn<Event, String>> list = Arrays.asList(lun, mar, mer, giov, ven,sab,dom);
	
	@Override
	public void init(){
        this.printWeekCalendar();
	}

	public void printWeekCalendar() {
		this.first = LocalDate.now().with(previousOrSame(DayOfWeek.MONDAY)); 
		this.last = LocalDate.now().with(nextOrSame(DayOfWeek.SUNDAY));
		actualWeek.setText(first.getDayOfMonth() + "-" + last.getDayOfMonth() + ", " + this.first.getMonth());
		this.setDayNumber();
		this.setEventOnDay();
	}
	
	@FXML
	private void next() {
		this.first = this.first.plusDays(7);
		this.last = this.last.plusDays(7);
		actualWeek.setText(this.first.getDayOfMonth() + "-" + this.last.getDayOfMonth() + ", " + this.first.getMonth());
		this.setDayNumber();
		this.setEventOnDay();
	}

	@FXML
	private void previous() {
		this.first = this.first.minusDays(7);
		this.last = this.last.minusDays(7);
		actualWeek.setText(this.first.getDayOfMonth() + "-" + this.last.getDayOfMonth() + ", " + this.first.getMonth());
		this.setDayNumber();
		this.setEventOnDay();
	}
	
	private void setDayNumber() {
		int first = this.first.getDayOfMonth();
		for(TableColumn<Event, String> col : list) {
			String[] day = col.getText().split(",");
			col.setText(day[0] + ", " + first);
			first += 1;
		}
	}
	
	private void setEventOnDay() {
    	try {
			lunedi.setItems(FXCollections.observableArrayList(Utilities.getEvents(new DateTime(first.getYear(), first.getMonthValue(), first.getDayOfMonth()), new DateTime(last.getYear(), last.getMonthValue(), last.getDayOfMonth()))));
			lun.setCellValueFactory(new PropertyValueFactory<Event, String>("monday"));
			mar.setCellValueFactory(new PropertyValueFactory<Event, String>("tuesday"));
			dom.setCellValueFactory(new PropertyValueFactory<Event, String>("sunday"));
			sab.setCellValueFactory(new PropertyValueFactory<Event, String>("saturday"));
			ven.setCellValueFactory(new PropertyValueFactory<Event, String>("friday"));
			giov.setCellValueFactory(new PropertyValueFactory<Event, String>("thursday"));
			mer.setCellValueFactory(new PropertyValueFactory<Event, String>("wednesday"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}