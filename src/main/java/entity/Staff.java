package entity;

import utilities.DateTime;

public class Staff extends Person {

	private static final String TABLENAME = "staff";
	private static final String COLUMNS = ", codCategoria, codRuolo)";
	
	private String codRuolo;
	private int codCategoria;
	
	public Staff(String codiceFiscale, String nome, String cognome, DateTime data, String codSesso, int image,
			int matricola, String codRuolo, int codCategoria) {
		super(codiceFiscale, nome, cognome, data, codSesso, image, matricola);
		this.codCategoria = codCategoria;
		this.codRuolo = codRuolo;
	}

	public Staff(String codiceFiscale, String nome, String cognome, DateTime data, String codSesso,
			int image, String codRuolo, int codCategoria) {
		super(codiceFiscale, nome, cognome, data, codSesso, image);
		this.codCategoria = codCategoria;
		this.codRuolo = codRuolo;
	}
	
	@Override
	public String getColumnList() {
		return super.getColumnList() + Staff.COLUMNS;
	}
	
	@Override
	public String getTableName() {
		return Staff.TABLENAME;
	}
	
	@Override
	public String getValues() {
		return super.getValues() + ", '" + this.codCategoria + "', " + this.codRuolo + ")";
	}

}
