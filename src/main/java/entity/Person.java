package entity;

import java.sql.SQLException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import utilities.DateTime;
import utilities.Utilities;

public class Person extends Entity{
	public final static String TABLENAME="persona";
	public final static String PRIMARY_K_NAME = "CF";
	public final static String COLUMNS="(CF, CodPagamento, Nome, Cognome, Data, Matricola_tesserino, CodSesso, CodPartitaIVA, CodImmagine)";
	private String codiceFiscale;
	private int codPagamento;
	private StringProperty nome;
	private StringProperty cognome;
	private DateTime data;
	private String codSesso;
	private int matricola;
	private int codImmagine;
	private String codPartitaIva;
		
	public Person(String codiceFiscale, String nome, String cognome, DateTime data, int codPagamento, String codSesso, String codPartitaIva,
			Immagine image) {
		this.codiceFiscale = codiceFiscale;
		this.nome = new SimpleStringProperty(nome);
		this.cognome = new SimpleStringProperty(cognome);
		this.data = data;
		this.codPagamento = codPagamento;
		this.codSesso = codSesso;
		image.insert();
		this.codImmagine = (int) image.getPrimaryKey();
		this.matricola = 0;
		this.codPartitaIva=codPartitaIva;
	}

	public Person(String codiceFiscale, String nome, String cognome, DateTime data, int codPagamento, String codSesso, String codPartitaIva,
			Immagine image, int matricola) {
		this.codiceFiscale = codiceFiscale;
		this.nome = new SimpleStringProperty(nome);
		this.cognome = new SimpleStringProperty(cognome);
		this.data = data;
		this.codPagamento = codPagamento;
		this.codSesso = codSesso;
		image.insert();
		this.codImmagine = (int) image.getPrimaryKey();
		this.matricola = matricola;
		this.codPartitaIva=codPartitaIva;
	}

	public Person(String codiceFiscale, String nome, String cognome, DateTime data, int codPagamento, String codSesso, String codPartitaIva) {
		this.codiceFiscale = codiceFiscale;
		this.nome = new SimpleStringProperty(nome);
		this.cognome = new SimpleStringProperty(cognome);
		this.data = data;
		this.codPagamento = codPagamento;
		this.codSesso = codSesso;
		this.codImmagine=0;
		this.matricola = 0;
		this.codPartitaIva=codPartitaIva;
	}

	public Person(String codiceFiscale, String nome, String cognome, DateTime data, int codPagamento, String codSesso, String codPartitaIva,
			int matricola) {
		this.codiceFiscale = codiceFiscale;
		this.nome = new SimpleStringProperty(nome);
		this.cognome = new SimpleStringProperty(cognome);
		this.data = data;
		this.codPagamento = codPagamento;
		this.codSesso = codSesso;
		this.codImmagine = 3;
		this.matricola = matricola;
		this.codPartitaIva=codPartitaIva;
	}
	
	public Person(String codiceFiscale, String nome, String cognome) {
		this.codiceFiscale = codiceFiscale;
		this.nome = new SimpleStringProperty(nome);
		this.cognome = new SimpleStringProperty(cognome);
	}
	
	public String getNome() {
		return nome.get();
	}

	public String getCognome() {
		return cognome.get();
	}
	
	public String getCodRuolo() throws SQLException {
		return Utilities.getCodRuoloByCF(this.codiceFiscale).get();
	}
	
	public String getCodCategoria() throws SQLException {
		return Utilities.getNomeCategoriaByCF(this.codiceFiscale).get();
	}

	public DateTime getData() {
		return data;
	}

	public int getCodPagamento() {
		return codPagamento;
	}

	public String getCodSesso() {
		return codSesso;
	}

	public int getMatricola() {
		return matricola;
	}

	public int getImage() {
		return codImmagine;
	}

	@Override
	public String getTableName() {
		return Person.TABLENAME;
	}

	@Override
	public Object getPrimaryKey() {
		return this.codiceFiscale;
	}

	@Override
	public String getColumnList() {
		return Person.COLUMNS;
		
	}

	@Override
	public String getValues() {
		return "('" + this.codiceFiscale + "', '" + this.codPagamento + "', '" + this.nome.get() + "', '" + this.cognome.get() + "', '" + this.data.getDate() + "', '" + this.matricola + "', '" + this.codSesso + "', '" + this.codPartitaIva + "', " + this.codImmagine + ")";
	}

	@Override
	public String getNamePrimaryKey() {
		return Person.PRIMARY_K_NAME;
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		// TODO Auto-generated method stub
		
	}
}
