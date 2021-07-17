package entity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Method;

import javafx.beans.property.SimpleStringProperty;
import utilities.DateTime;
import utilities.Pair;
import utilities.Utilities;

public class Event extends Entity {
	public final static String TABLENAME="evento";
	private final static String MATCH_COLUMN="(Inizio, Fine, CodPartitaIva, NomeAvversario, Risultato, CodCategoria)";
	private final static String TRAINING_COLUMN="(Inizio, Fine, CodPartitaIva, CodCategoria)";
	private final static String EVENT_COLUMN="(Inizio, Fine, CodPartitaIva, Descrizione_generico)";
	// Inserire le colonne per i tre tipi di eventi e testarlo
	
	
	private int idEvento;
	private DateTime inizio;
	private DateTime fine;
	private String codPartitaIva = null;
	private String descrizione_generico = null;
	private String nomeAvversario = null;
	private String risultato = null;
	private int codCategoria = 0;
	private List<Object> convocati = new ArrayList<>();
	private SimpleStringProperty eventoLunedi = new SimpleStringProperty();	
	private SimpleStringProperty eventoMartedi = new SimpleStringProperty();	
	private SimpleStringProperty eventoGiovedi = new SimpleStringProperty();	
	private SimpleStringProperty eventoMercoledi = new SimpleStringProperty();
	private SimpleStringProperty eventoVenerdi = new SimpleStringProperty();
	private SimpleStringProperty eventoSabato = new SimpleStringProperty();	
	private SimpleStringProperty eventoDomenica = new SimpleStringProperty();
	
	
	//EVENTO GENERICO
	public Event(DateTime inizio, DateTime fine, String codPartitaIva, String descrizione){
		this.inizio=inizio;
		this.fine=fine;
		this.codPartitaIva=codPartitaIva;
		this.descrizione_generico=descrizione;
		this.setDayOfWeek(this.descrizione_generico);
	}
	
	//PARTITA
	public Event(DateTime inizio, DateTime fine, String codPartitaIva, String avversario, int codiceCategoria){
		this.inizio=inizio;
		this.fine=fine;
		this.codPartitaIva=codPartitaIva;
		this.codCategoria=codiceCategoria;
		this.nomeAvversario=avversario;
		this.risultato="ND";
		try {
			this.setDayOfWeek(Utilities.getOnlyCategory(this.codCategoria).getNome() + " vs " + this.nomeAvversario);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	//ALLENAMENTO
	public Event(DateTime inizio, DateTime fine, String codPartitaIva, int codCategoria){
		this.inizio=inizio;
		this.fine=fine;
		this.codPartitaIva=codPartitaIva;
		this.codCategoria=codCategoria;
		try {
		this.setDayOfWeek("Allenamento "+ Utilities.getOnlyCategory(this.codCategoria).getNome());
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getMonday() {
		return this.eventoLunedi.get();
	}
	public String getTuesday() {
		return this.eventoMartedi.get();
	}
	public String getWednesday() {
		return this.eventoMercoledi.get();
	}
	public String getThursday() {
		return this.eventoGiovedi.get();
	}
	public String getFriday() {
		return this.eventoVenerdi.get();
	}
	public String getSaturday() {
		return this.eventoSabato.get();
	}
	public String getSunday() {
		return this.eventoDomenica.get();
	}
	
	public void setDayOfWeek(String e) {
		DayOfWeek day = this.getDayOfWeek(this);
		if (day == DayOfWeek.MONDAY) {
			this.eventoLunedi = new SimpleStringProperty(e);
		} else if (day == DayOfWeek.TUESDAY) {
			this.eventoMartedi = new SimpleStringProperty(e);
		} else if (day == DayOfWeek.WEDNESDAY) {
			this.eventoMercoledi = new SimpleStringProperty(e);
		} else if (day == DayOfWeek.THURSDAY) {
			this.eventoGiovedi = new SimpleStringProperty(e);
		} else if (day == DayOfWeek.FRIDAY) {
			this.eventoVenerdi = new SimpleStringProperty(e);
		} else if (day == DayOfWeek.SATURDAY) {
			this.eventoSabato = new SimpleStringProperty(e);
		} else {
			this.eventoDomenica = new SimpleStringProperty(e);
		}
	}
	
	private DayOfWeek getDayOfWeek(Event e) {
	    LocalDate localDate = LocalDate.of(e.getInizio().getAnno(), e.getInizio().getMese(), e.getInizio().getGiorno());
	    DayOfWeek dayOfWeek = localDate.getDayOfWeek();
		return dayOfWeek;
	}
	
	@Override
	public String getTableName() {
		return Event.TABLENAME;
	}

	@Override
	public Object getPrimaryKey() {
		return this.idEvento;
	}

	@Override
	public String getColumnList() {
		if (this.codCategoria == 0) {
			return Event.EVENT_COLUMN;
		} else if (this.nomeAvversario != null) {
			return Event.MATCH_COLUMN;
		} else {
			return Event.TRAINING_COLUMN;
		}
	}

	public void setRisultato(String risultato) {
		this.risultato=risultato;
		List<Pair<String, String>> list = new ArrayList<>();
		list.add(new Pair<>("Risultato", this.risultato));
		this.update(list);
	}
	
	public String getRisultato() {
		return this.risultato;
	}
	
	@Override
	public String getValues() {
		if (this.codCategoria == 0) {
			return "('" + this.inizio.getDate() + "', '" + this.fine.getDate() + "', '" + this.codPartitaIva + "', '" + this.descrizione_generico + "')";
		} else if (this.nomeAvversario != null) {
			return "('" + this.inizio.getDate() + "', '" + this.fine.getDate() + "', '" + this.codPartitaIva + "', '" + this.nomeAvversario + "', '" + this.risultato + "', '" + this.codCategoria + "')";
		} else {
			return "('" + this.inizio.getDate() + "', '" + this.fine.getDate() + "', '" + this.codPartitaIva + "', '" + this.codCategoria + "')";
		}
	}

	@Override
	public String getNamePrimaryKey() {
		return "IdEvento";
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		this.idEvento=primaryKey;
	}
	
	public void setConvocati(List<Object> list) {
		this.convocati = list;
	}
	
	@Override
	public void insert() {
		try {
			Utilities.insertEntity(this);
			Utilities.insertConvocati(this.convocati, this.getPrimaryKey());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public DateTime getInizio() {
		return this.inizio;
	}
	
	public DateTime getFine() {
		return this.fine;
	}
	
}
