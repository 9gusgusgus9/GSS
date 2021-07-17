package view;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;

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
	TableView<Event> gridWeek;
	
	@FXML
	Label actualWeek;
	
	@FXML
	Button nextWeek;
	
	@FXML
	Button previousWeek;
	
	@FXML
	TableColumn<Event, String> lunedi;
	
	@FXML
	TableColumn<Event, String> martedi;
	
	@FXML
	TableColumn<Event, String> mercoledi;
	
	@FXML
	TableColumn<Event, String> giovedi;
	
	@FXML
	TableColumn<Event, String> venerdi;
	
	@FXML
	TableColumn<Event, String> sabato;
	
	@FXML
	TableColumn<Event, String> domenica;
	
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
	
	private DayOfWeek getDayOfWeek(Event e) {
	    LocalDate localDate = LocalDate.of(e.getInizio().getAnno(), e.getInizio().getMese(), e.getInizio().getGiorno());
	    DayOfWeek dayOfWeek = localDate.getDayOfWeek();
		return dayOfWeek;
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
		for(TableColumn<Event, ?> col : gridWeek.getColumns()) {
			col.setEditable(false);
			String[] day = col.getText().split(",");
			col.setText(day[0] + ", " + first);
			first += 1;
		}
	}
	
	private void setEventOnDay() {
    	try {
			for(Event p : Utilities.getEvents(new DateTime(first.getYear(), first.getMonthValue(), first.getDayOfMonth()), new DateTime(last.getYear(), last.getMonthValue(), last.getDayOfMonth()))) {
				DayOfWeek day = this.getDayOfWeek(p);
				if (day == DayOfWeek.MONDAY) {
//					lunedi.setCellValueFactory(new PropertyValueFactory<Event, String>("monday"));
					lunedi.setCellFactory(col -> new TableCell<Event, String>() {
					    @Override
					    public void updateItem(String s, boolean empty) {
					        super.updateItem(s, empty);
					        if (empty) {
					            setText(null);
					        } else {
					            setText(p.getEvento());
					        }
					    }
					});
				} else if (day == DayOfWeek.TUESDAY) {
//		    		martedi.setCellValueFactory(new PropertyValueFactory<Event, String>("tuesday"));
					martedi.setCellFactory(col -> new TableCell<Event, String>() {
					    @Override
					    public void updateItem(String s, boolean empty) {
					        super.updateItem(s, empty);
					        if (empty) {
					            setText(null);
					        } else {
					            setText(p.getEvento());
					        }
					    }
					});
				} else if (day == DayOfWeek.WEDNESDAY) {
//		    		mercoledi.setCellValueFactory(new PropertyValueFactory<Event, String>("wednesday"));
					mercoledi.setCellFactory(col -> new TableCell<Event, String>() {
					    @Override
					    public void updateItem(String s, boolean empty) {
					        super.updateItem(s, empty);
					        if (empty) {
					            setText(null);
					        } else {
					            setText(p.getEvento());
					        }
					    }
					});
				} else if (day == DayOfWeek.THURSDAY) {
//					giovedi.setCellValueFactory(new PropertyValueFactory<Event, String>("thursday"));
					giovedi.setCellFactory(col -> new TableCell<Event, String>() {
					    @Override
					    public void updateItem(String s, boolean empty) {
					        super.updateItem(s, empty);
					        if (empty) {
					            setText(null);
					        } else {
					            setText(p.getEvento());
					        }
					    }
					});
				} else if (day == DayOfWeek.FRIDAY) {
//					venerdi.setCellValueFactory(new PropertyValueFactory<Event, String>("friday"));
					venerdi.setCellFactory(col -> new TableCell<Event, String>() {
					    @Override
					    public void updateItem(String s, boolean empty) {
					        super.updateItem(s, empty);
					        if (empty) {
					            setText(null);
					        } else {
					            setText(p.getEvento());
					        }
					    }
					});
				} else if (day == DayOfWeek.SATURDAY) {
//					sabato.setCellValueFactory(new PropertyValueFactory<Event, String>("saturday"));
					sabato.setCellFactory(col -> new TableCell<Event, String>() {
					    @Override
					    public void updateItem(String s, boolean empty) {
					        super.updateItem(s, empty);
					        if (empty) {
					            setText(null);
					        } else {
					            setText(p.getEvento());
					        }
					    }
					});
				} else {
//					domenica.setCellValueFactory(new PropertyValueFactory<Event, String>("sunday"));
					domenica.setCellFactory(col -> new TableCell<Event, String>() {
					    @Override
					    public void updateItem(String s, boolean empty) {
					        super.updateItem(s, empty);
					        if (empty) {
					            setText(null);
					        } else {
					            setText(p.getEvento());
					        }
					    }
					});
				}
			}
			gridWeek.setItems(FXCollections.observableArrayList(Utilities.getEvents(new DateTime(first.getYear(), first.getMonthValue(), first.getDayOfMonth()), new DateTime(last.getYear(), last.getMonthValue(), last.getDayOfMonth()))));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}