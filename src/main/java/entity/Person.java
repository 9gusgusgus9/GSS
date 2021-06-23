package entity;

import utilities.DateTime;

public class Person extends Entity{
	public final static String TABLENAME="persona";
	public final static String PRIMARY_K_NAME = "CF";
	public final static String COLUMNS="(CF, CodPagamento, Nome, Cognome, Data, Matricola_tesserino, CodSesso, CodPartitaIVA, CodImmagine, CodRuoloPersona";
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private DateTime data;
	private String codSesso;
	private int matricola;
	private int codImmagine;
	
	
		
	public Person(String codiceFiscale, String nome, String cognome, DateTime data, String codSesso,
			int image) {
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.data = data;
		this.codSesso = codSesso;
		this.codImmagine = image;
		this.matricola = 0;
	}

	public Person(String codiceFiscale, String nome, String cognome, DateTime data, String codSesso,
			int image, int matricola) {
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.data = data;
		this.codSesso = codSesso;
		this.codImmagine = image;
		this.matricola = matricola;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public DateTime getData() {
		return data;
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
		return null;
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
		return "('" + this.codiceFiscale + "', '" + this.nome + "', '" + this.cognome + "', '" + this.data.getDate() + " " + this.data.getTime() + "', '" + this.matricola + "', '" + this.codSesso + "', " + this.codImmagine;
	}

	@Override
	public String getNamePrimaryKey() {
		// TODO Auto-generated method stub
		return Player.PRIMARY_K_NAME;
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		// TODO Auto-generated method stub
		
	}
}
