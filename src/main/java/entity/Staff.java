package entity;

import utilities.DateTime;
import utilities.Utilities;

public class Staff extends Entity {

	private static final String TABLENAME = "staff";
	private static final String COLUMNS = "(CF, CodCategoria, CodRuoloStaff)";
	
	private String codRuolo;
	private int codCategoria;
	private Person person;
	
	public Staff(String codiceFiscale, String nome, String cognome, DateTime data, String codSesso, String codPartitaIva, Immagine image,
			int matricola, String codRuolo, int codCategoria) {
		person = new Person(codiceFiscale, nome, cognome, data, codSesso, codPartitaIva, image, matricola);
		this.codCategoria = codCategoria;
		this.codRuolo = codRuolo;
	}

	public Staff(String codiceFiscale, String nome, String cognome, DateTime data, String codSesso, String codPartitaIva,
			Immagine image, String codRuolo, int codCategoria) {
		person = new Person(codiceFiscale, nome, cognome, data, codSesso, codPartitaIva, image);
		this.codCategoria = codCategoria;
		this.codRuolo = codRuolo;
	}

	public Staff(String codiceFiscale, String nome, String cognome, DateTime data, String codSesso, String codPartitaIva, int matricola, String codRuolo, int codCategoria) {
		person = new Person(codiceFiscale, nome, cognome, data, codSesso, codPartitaIva, matricola);
		this.codCategoria = codCategoria;
		this.codRuolo = codRuolo;
	}

	public Staff(String codiceFiscale, String nome, String cognome, DateTime data, String codSesso, String codPartitaIva,
			String codRuolo, int codCategoria) {
		person = new Person(codiceFiscale, nome, cognome, data, codSesso, codPartitaIva);
		this.codCategoria = codCategoria;
		this.codRuolo = codRuolo;
	}
	
	public Person getPersona() {
		return this.person;
	}
	
	public int getCodCategoria() {
		return codCategoria;
	}
	
	public String getCodRuolo() {
		return codRuolo;
	}

	@Override
	public String getColumnList() {
		return Staff.COLUMNS;
	}
	
	@Override
	public String getTableName() {
		return Staff.TABLENAME;
	}
	
	@Override
	public String getValues() {
		return "('" + this.person.getPrimaryKey() + "', " + this.codCategoria + ", '" + this.codRuolo + "')";
	}

	@Override
	public Object getPrimaryKey() {
		return this.person.getPrimaryKey();
	}

	@Override
	public String getNamePrimaryKey() {
		// TODO Auto-generated method stub
		return this.person.getNamePrimaryKey();
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void insert() {
		person.insert();
		Utilities.insertEntity(this);
	}

}
