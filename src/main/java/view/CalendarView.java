package view;

import java.time.DayOfWeek;
import java.time.LocalDate;

import entity.Event;

import static java.time.temporal.TemporalAdjusters.previousOrSame;

import java.sql.SQLException;

import static java.time.temporal.TemporalAdjusters.nextOrSame;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import utilities.DateTime;
import utilities.Utilities;

public class CalendarView extends ViewImpl{
	
	private LocalDate first;
	private LocalDate last;
	
	@FXML
	Label nameLabel;
	
	@FXML
	Button newEvento;
	
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
	
	@Override
	public void init(){
        this.printWeekCalendar();
        this.setOnMouseClick();
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
    		this.setOnChangeWeek();
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
	
	private void setOnChangeWeek() {
		lunedi.setItems(null);
		martedi.setItems(null);
		mercoledi.setItems(null);
		giovedi.setItems(null);
		venerdi.setItems(null);
		sabato.setItems(null);
		domenica.setItems(null);
		this.setOnMouseClick();
	}
	
	private void setOnMouseClick() {
		lunedi.setRowFactory( tv -> {
		    TableRow<Event> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            Utilities.setEvent(row.getItem());
		            ViewSwitcher.getInstance().switchView(new Stage(), ViewType.SINGLEEVENT);
		        }
		    });
		    return row ;
		});
		
		martedi.setRowFactory( tv -> {
		    TableRow<Event> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            Utilities.setEvent(row.getItem());
		            ViewSwitcher.getInstance().switchView(new Stage(), ViewType.SINGLEEVENT);
		        }
		    });
		    return row ;
		});
		
		mercoledi.setRowFactory( tv -> {
		    TableRow<Event> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            Utilities.setEvent(row.getItem());
		            ViewSwitcher.getInstance().switchView(new Stage(), ViewType.SINGLEEVENT);
		        }
		    });
		    return row ;
		});
		
		giovedi.setRowFactory( tv -> {
		    TableRow<Event> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            Utilities.setEvent(row.getItem());
		            ViewSwitcher.getInstance().switchView(new Stage(), ViewType.SINGLEEVENT);
		        }
		    });
		    return row ;
		});
		
		venerdi.setRowFactory( tv -> {
		    TableRow<Event> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            Utilities.setEvent(row.getItem());
		            ViewSwitcher.getInstance().switchView(new Stage(), ViewType.SINGLEEVENT);
		        }
		    });
		    return row ;
		});
		
		sabato.setRowFactory( tv -> {
		    TableRow<Event> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            Utilities.setEvent(row.getItem());
		            ViewSwitcher.getInstance().switchView(new Stage(), ViewType.SINGLEEVENT);
		        }
		    });
		    return row ;
		});
		
		domenica.setRowFactory( tv -> {
		    TableRow<Event> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            Utilities.setEvent(row.getItem());
		            ViewSwitcher.getInstance().switchView(new Stage(), ViewType.SINGLEEVENT);
		        }
		    });
		    return row ;
		});
	}
	
	@FXML
	private void switchToInsertEvent() {
		ViewSwitcher.getInstance().switchView(new Stage(), ViewType.INSERTEVENT);
	}
}