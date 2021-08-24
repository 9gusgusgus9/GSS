package entity;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import utilities.DateTime;
import utilities.Pair;
import utilities.Utilities;

public class Evento extends Entity {
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
	private SimpleStringProperty evento;	
	private String tipoEvento;
	
	//EVENTO GENERICO
	public Evento(DateTime inizio, DateTime fine, String codPartitaIva, String descrizione){
		this.inizio=inizio;
		this.fine=fine;
		this.codPartitaIva=codPartitaIva;
		this.descrizione_generico=descrizione;
		this.evento = new SimpleStringProperty(this.descrizione_generico);
		this.tipoEvento = "Generico";
	}
	
	//PARTITA
	public Evento(DateTime inizio, DateTime fine, String codPartitaIva, String avversario, int codiceCategoria, String risultato){
		this.inizio=inizio;
		this.fine=fine;
		this.codPartitaIva=codPartitaIva;
		this.codCategoria=codiceCategoria;
		this.nomeAvversario=avversario;
		if(risultato==null) {
			this.risultato="ND";
		} else {
			this.risultato=risultato;
		}
		this.tipoEvento = "Partita";
		this.evento = new SimpleStringProperty(Utilities.getOnlyCategory(this.codCategoria).getNome() + " vs " + this.nomeAvversario);
	}
	
	//ALLENAMENTO
	public Evento(DateTime inizio, DateTime fine, String codPartitaIva, int codCategoria){
		this.inizio=inizio;
		this.fine=fine;
		this.codPartitaIva=codPartitaIva;
		this.codCategoria=codCategoria;
		this.tipoEvento = "Allenamento";
		this.evento = new SimpleStringProperty("Allenamento "+ Utilities.getOnlyCategory(this.codCategoria).getNome());
	}
	
	public String getEvent() {
		return this.evento.get();
	}
	
	@Override
	public String getTableName() {
		return Evento.TABLENAME;
	}

	@Override
	public Object getPrimaryKey() {
		return this.idEvento;
	}

	@Override
	public String getColumnList() {
		if (this.codCategoria == 0) {
			return Evento.EVENT_COLUMN;
		} else if (this.nomeAvversario != null) {
			return Evento.MATCH_COLUMN;
		} else {
			return Evento.TRAINING_COLUMN;
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
	
	public String getTipoEvento() {
		return this.tipoEvento;
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
		Utilities.insertEntity(this);
		Utilities.insertConvocati(this.convocati, this.getPrimaryKey());
		
	}
	
	public DateTime getInizio() {
		return this.inizio;
	}
	
	public DateTime getFine() {
		return this.fine;
	}
	
}
