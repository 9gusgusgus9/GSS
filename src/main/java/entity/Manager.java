package entity;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import utilities.DateTime;
import utilities.Utilities;

public class Manager extends Entity {

	private static final String TABLENAME = "dirigente";
	private static final String COLUMNS = "(CF, CodRuoloDirigente)";
	
	private String codRuolo;
	private Person person;
	
	public Manager(String codiceFiscale, String nome, String cognome, DateTime data, int codPagamento, String codSesso, String codPartitaIva,
			int image, int matricola, String codRuolo) {
		person = new Person(codiceFiscale, nome, cognome, data, codPagamento, codSesso, codPartitaIva, image, matricola);
		this.codRuolo = codRuolo;
	}

	public Manager(String codiceFiscale, String nome, String cognome, DateTime data, int codPagamento, String codSesso, String codPartitaIva,
			int image, String codRuolo) {
		person = new Person(codiceFiscale, nome, cognome, data, codPagamento, codSesso, codPartitaIva, image);
		this.codRuolo = codRuolo;
	}

	public String getCodRuolo() {
		return codRuolo;
	}

	@Override
	public String getColumnList() {
		return Manager.COLUMNS;
	}
	
	@Override
	public String getTableName() {
		return Manager.TABLENAME;
	}
	
	@Override
	public String getValues() {
		return "('" + this.getPrimaryKey() + "', '" + this.codRuolo + "')";
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

}
