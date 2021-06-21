package entity;

import utilities.DateTime;

public class Event extends Entity {
	public final static String TABLENAME="eventi";
	private final static String COLUMNS="(Inizio, Fine, CodPartitaIva, NomeAvversario, Risultato, Descrizione_generico, CodCategoria)";
	
	// Inserire le colonne per i tre tipi di eventi e testarlo
	
	
	private int idEvento;
	private DateTime inizio;
	private DateTime fine;
	private String codPartitaIva = null;
	private String descrizione_generico = null;
	private String nomeAvversario = null;
	private String risultato = null;
	private int codCategoria = 0;
	
	//EVENTO GENERICO
	public Event(DateTime inizio, DateTime fine, String codPartitaIva, String descrizione){
		this.inizio=inizio;
		this.fine=fine;
		this.codPartitaIva=codPartitaIva;
		this.descrizione_generico=descrizione;
	}
	
	//PARTITA
	public Event(DateTime inizio, DateTime fine, String codPartitaIva, String avversario, int codiceCategoria){
		this.inizio=inizio;
		this.fine=fine;
		this.codPartitaIva=codPartitaIva;
		this.codCategoria=codiceCategoria;
		this.nomeAvversario=avversario;
		this.risultato="ND";
	}
	
	//ALLENAMENTO
	public Event(DateTime inizio, DateTime fine, String codPartitaIva, int codCategoria){
		this.inizio=inizio;
		this.fine=fine;
		this.codPartitaIva=codPartitaIva;
		this.codCategoria=codCategoria;
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
		return Event.COLUMNS;
	}

	public void setRisultato(String risultato) {
		this.risultato=risultato;
	}
	
	@Override
	public String getValues() {
		return "('" + this.inizio.getDate() + "', '" + this.fine.getDate() + "', '" + this.codPartitaIva + "', '" + this.nomeAvversario + "', '" + this.risultato + "', '" + this.descrizione_generico + "', '" + this.codCategoria + "')";
	}

	@Override
	public String getNamePrimaryKey() {
		return "IdEvento";
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		this.idEvento=primaryKey;
	}
}
