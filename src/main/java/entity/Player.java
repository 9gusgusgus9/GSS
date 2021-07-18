package entity;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import javafx.beans.property.SimpleStringProperty;
import utilities.DateTime;
import utilities.Utilities;

public class Player extends Entity {
	public final static String TABLENAME = "giocatore";
	private final static String COLUMNS = "(CF , Peso, Altezza, Data_scadenza_certificato, CodRuoloGiocatore, CodCategoria, CodPreferenza)";

	private Person person;
	private String peso;
	private String altezza;
	private DateTime data_scadenza_certificato;
	private String codRuolo;
	private int codCategoria;
	private String codPreferenza;

	public Player(String codiceFiscale, String nome, String cognome, DateTime data, int codPagamento, String codSesso,
			String codPartitaIva, Immagine image, int matricola, String peso, String altezza,
			DateTime data_scadenza_certificato, String codRuolo, int codCategoria, String codPreferenza) {
		person = new Person(codiceFiscale, nome, cognome, data, codPagamento, codSesso, codPartitaIva, image,
				matricola);
		this.peso = peso;
		this.altezza = altezza;
		this.data_scadenza_certificato = data_scadenza_certificato;
		this.codRuolo = codRuolo;
		this.codCategoria = codCategoria;
		this.codPreferenza = codPreferenza;

	}

	public Player(String codiceFiscale, String nome, String cognome, DateTime data, int codPagamento, String codSesso,
			String codPartitaIva, Immagine image, String peso, String altezza, DateTime data_scadenza_certificato,
			String codRuolo, int codCategoria, String codPreferenza) {
		person = new Person(codiceFiscale, nome, cognome, data, codPagamento, codSesso, codPartitaIva, image);
		this.peso = peso;
		this.altezza = altezza;
		this.data_scadenza_certificato = data_scadenza_certificato;
		this.codRuolo = codRuolo;
		this.codCategoria = codCategoria;
		this.codPreferenza = codPreferenza;

	}

	public Player(String codiceFiscale, String nome, String cognome, DateTime data, int codPagamento, String codSesso,
			String codPartitaIva, int matricola, String peso, String altezza, DateTime data_scadenza_certificato,
			String codRuolo, int codCategoria, String codPreferenza) {
		person = new Person(codiceFiscale, nome, cognome, data, codPagamento, codSesso, codPartitaIva, matricola);
		this.peso = peso;
		this.altezza = altezza;
		this.data_scadenza_certificato = data_scadenza_certificato;
		this.codRuolo = codRuolo;
		this.codCategoria = codCategoria;
		this.codPreferenza = codPreferenza;

	}

	public Player(String codiceFiscale, String nome, String cognome, DateTime data, int codPagamento, String codSesso,
			String codPartitaIva, String peso, String altezza, DateTime data_scadenza_certificato, String codRuolo,
			int codCategoria, String codPreferenza) {
		person = new Person(codiceFiscale, nome, cognome, data, codPagamento, codSesso, codPartitaIva);
		this.peso = peso;
		this.altezza = altezza;
		this.data_scadenza_certificato = data_scadenza_certificato;
		this.codRuolo = codRuolo;
		this.codCategoria = codCategoria;
		this.codPreferenza = codPreferenza;

	}

	public Person getPersona() {
		return person;
	}

	@Override
	public String getColumnList() {
		return Player.COLUMNS;
	}

	public String getPeso() {
		return this.peso;
	}

	public String getAltezza() {
		return this.altezza;
	}

	public DateTime getData_scadenza_certificato() {
		return this.data_scadenza_certificato;
	}

	public String getCodRuolo() {
		return this.codRuolo;
	}

	public int getCodCategoria() {
		return this.codCategoria;
	}

	public String getCodPreferenza() {
		return this.codPreferenza;
	}

	@Override
	public String getValues() {
		return "('" + this.person.getPrimaryKey() + "', '" + this.peso + "', '" + this.altezza + "', '"
				+ this.data_scadenza_certificato.getDate() + "', '" + this.codRuolo + "', " + this.codCategoria + ", '"
				+ this.codPreferenza + "') ";
	}

	@Override
	public String getTableName() {
		return Player.TABLENAME;
	}

	@Override
	public void insert() {
		person.insert();
		try {
			Utilities.insertEntity(this);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object getPrimaryKey() {
		return this.person.getPrimaryKey();
	}

	@Override
	public String getNamePrimaryKey() {
		return this.person.getNamePrimaryKey();
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		// TODO Auto-generated method stub

	}
}
