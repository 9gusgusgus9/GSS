package entity;

import utilities.DateTime;

public class Player extends Person {
	
	private final static String COLUMNS=Person.COLUMNS + ", Peso, Altezza, Data_scadenza_certificato, CodRuoloGiocatore, CodCategoria, CodPreferenza)";
	
	private String peso;
	private String altezza;
	private DateTime data_scadenza_certificato;
	private String codRuolo;
	private int codCategoria;
	private String codPreferenza;

	public Player(String codiceFiscale, String nome, String cognome, DateTime data, String codSesso,
			int image, int matricola, String peso, String altezza, DateTime data_scadenza_certificato, String codRuolo, int codCategoria, String codPreferenza) {
		super(codiceFiscale, nome, cognome, data, codSesso, image, matricola);
		this.peso = peso;
		this.altezza = altezza;
		this.data_scadenza_certificato = data_scadenza_certificato;
		this.codRuolo = codRuolo;
		this.codCategoria = codCategoria;
		this.codPreferenza = codPreferenza;

	}

	public Player(String codiceFiscale, String nome, String cognome, DateTime data, String codSesso,
			int image, String peso, String altezza, DateTime data_scadenza_certificato, String codRuolo, int codCategoria, String codPreferenza) {
		super(codiceFiscale, nome, cognome, data, codSesso, image);
		this.peso = peso;
		this.altezza = altezza;
		this.data_scadenza_certificato = data_scadenza_certificato;
		this.codRuolo = codRuolo;
		this.codCategoria = codCategoria;
		this.codPreferenza = codPreferenza;

	}

	@Override
	public String getColumnList() {
		return COLUMNS;
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
		return super.getValues() +  ", '" + this.peso + "', '" + this.altezza + "', '" + this.data_scadenza_certificato.getDate() + " " + this.data_scadenza_certificato.getTime()+ "', '" + this.codRuolo + "', " + this.codCategoria + ", '" + this.codPreferenza + "') ";
	}
	
}
