package view;

import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

import java.io.IOException;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import entity.Category;
import entity.Evento;
import entity.Society;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import utilities.DateTime;
import utilities.Pair;
import utilities.Utilities;

public class CategoryView extends ViewImpl {

	@FXML
	private Label nameLabel;

	@FXML
	private ImageView logo;

	@FXML
	private Rectangle color1;

	@FXML
	private Rectangle color2;

	@FXML
	private ImageView image1;

	@FXML
	private Label label1;

	private int flag1 = 0;

	@FXML
	private ImageView image2;

	@FXML
	private Label label2;

	private int flag2 = 0;

	@FXML
	private ImageView image3;

	@FXML
	private Label label3;

	private int flag3 = 0;

	@FXML
	private ImageView image4;

	@FXML
	private Label label4;

	private int flag4 = 0;

	@FXML
	private ImageView image5;

	@FXML
	private Label label5;

	private int flag5 = 0;

	@FXML
	private ImageView image6;

	@FXML
	private Label label6;

	private int flag6 = 0;

	@FXML
	private ImageView image7;

	@FXML
	private Label label7;

	private int flag7 = 0;

	@FXML
	private ImageView image8;

	@FXML
	private Label label8;

	private int flag8 = 0;

	@FXML
	private ImageView image9;

	@FXML
	private Label label9;

	private int flag9 = 0;

	@FXML
	private ImageView image10;

	@FXML
	private Label label10;

	private int flag10 = 0;

	@FXML
	private ImageView image11;

	@FXML
	private Label label11;

	private int flag11 = 0;

	@FXML
	private ImageView image12;

	@FXML
	private Label label12;

	private int flag12 = 0;

	@FXML
	private ImageView image13;

	@FXML
	private Label label13;

	private int flag13 = 0;

	@FXML
	private ImageView image14;

	@FXML
	private Label label14;

	private int flag14 = 0;

	@FXML
	private ImageView image15;

	@FXML
	private Label label15;

	private int flag15 = 0;

	@FXML
	private Button prec, succ, nuova;
	
	@FXML
	TableView<Evento> lunedi;
	
	@FXML
	TableView<Evento> martedi;
	
	@FXML
	TableView<Evento> mercoledi;
	
	@FXML
	TableView<Evento> giovedi;
	
	@FXML
	TableView<Evento> venerdi;
	
	@FXML
	TableView<Evento> sabato;
	
	@FXML
	TableView<Evento> domenica;
	
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
	
	@FXML 
	Button refresh;

	private LocalDate first;
	private LocalDate last;
	private int currentPage;

	@Override
	public void init() {
		this.setSociety();
		this.prec.setDisable(true);
		this.currentPage = 0;
		this.loadCategories(currentPage);
        this.printWeekCalendar();
        this.setOnMouseClick();
	}

	private void loadCategories(int page) {
		List<Pair<Category, Image>> list = new LinkedList<>();
		try {
			list = Utilities.getCategories();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<Pair<Category, Image>> iter = list.iterator();
		int c = 1;
		if (page == 0) {
			this.getLabel(c).setText("Dirigenti");
			try {
				this.getImageView(c).setImage(Utilities.getImage(2));
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.getImageView(c).setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event arg0) {
					Utilities.setCategoria(0);
					ViewSwitcher.getInstance().switchView(new Stage(), ViewType.DIRIGENT);
				}
			});
			c++;
			if(list.size() <= 14) {
				this.succ.setDisable(true);
			} else {
				this.succ.setDisable(false);
			}
		} else {
			this.prec.setDisable(false);
			int count  = 0;
			if(page == 1) {
				count++;
			}
			while(iter.hasNext() && count < (page * 15) ) {
				iter.next();
				count++;
			}
		}
		while (iter.hasNext() && c<=15) {
			Pair<Category, Image> next = (Pair<Category, Image>) iter.next();
			this.getLabel(c).setText(next.getX().getNome());
			this.getImageView(c).setImage(next.getY());
			this.setFlag(c, (int) next.getX().getPrimaryKey());
			this.getImageView(c).setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event arg0) {
					Utilities.setCategoria((int) next.getX().getPrimaryKey());
					ViewSwitcher.getInstance().switchView(new Stage(), ViewType.SINGLECATEGORY);
				}
			});
			c++;
		}
		while(c<=15) {
			this.getLabel(c).setText("");
			this.getImageView(c).setImage(null);
			this.setFlag(c, 0);
			this.getImageView(c).setOnMouseClicked(null);
			c++;
		}
		if(list.size() < ((page + 1)*15 - 1)) {
			this.succ.setDisable(true);
		}
		if(this.currentPage == 0) {
			this.prec.setDisable(true);
		}
	}

	private void setSociety() {
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
		switch (x) {
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
		}
		return new Label();
	}

	private ImageView getImageView(int x) {
		switch (x) {
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
		}
		return new ImageView();
	}

	private int getFlag(int x) {
		switch (x) {
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
		}
		return 0;
	}

	private void setFlag(int x, int id) {
		switch (x) {
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
		}
	}

	@FXML
	private void nextPage() {
		this.currentPage++;
		this.loadCategories(currentPage);
	}

	@FXML
	private void prevPage() {
		this.currentPage--;
		this.loadCategories(currentPage);
	}
	
	@FXML
	private void insertNew() {
		Stage stage = new Stage();
		ViewSwitcher.getInstance().switchView(stage, ViewType.INSERTCATEGORY);
		
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
	
	private DayOfWeek getDayOfWeek(Evento e) {
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
    		for(Evento e : Utilities.getEvents(new DateTime(this.first.getYear(), this.first.getMonthValue(), this.first.getDayOfMonth()), new DateTime(this.last.getYear(), this.last.getMonthValue(), this.last.getDayOfMonth()))) {
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
		    TableRow<Evento> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            Utilities.setEvent(row.getItem());
		            ViewSwitcher.getInstance().switchView(new Stage(), ViewType.SINGLEEVENT);
		        }
		    });
		    return row ;
		});
		
		martedi.setRowFactory( tv -> {
		    TableRow<Evento> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            Utilities.setEvent(row.getItem());
		            ViewSwitcher.getInstance().switchView(new Stage(), ViewType.SINGLEEVENT);
		        }
		    });
		    return row ;
		});
		
		mercoledi.setRowFactory( tv -> {
		    TableRow<Evento> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            Utilities.setEvent(row.getItem());
		            ViewSwitcher.getInstance().switchView(new Stage(), ViewType.SINGLEEVENT);
		        }
		    });
		    return row ;
		});
		
		giovedi.setRowFactory( tv -> {
		    TableRow<Evento> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            Utilities.setEvent(row.getItem());
		            ViewSwitcher.getInstance().switchView(new Stage(), ViewType.SINGLEEVENT);
		        }
		    });
		    return row ;
		});
		
		venerdi.setRowFactory( tv -> {
		    TableRow<Evento> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            Utilities.setEvent(row.getItem());
		            ViewSwitcher.getInstance().switchView(new Stage(), ViewType.SINGLEEVENT);
		        }
		    });
		    return row ;
		});
		
		sabato.setRowFactory( tv -> {
		    TableRow<Evento> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            Utilities.setEvent(row.getItem());
		            ViewSwitcher.getInstance().switchView(new Stage(), ViewType.SINGLEEVENT);
		        }
		    });
		    return row ;
		});
		
		domenica.setRowFactory( tv -> {
		    TableRow<Evento> row = new TableRow<>();
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
	
	@FXML
	private void refreshStage() {
		init();
	}

}
