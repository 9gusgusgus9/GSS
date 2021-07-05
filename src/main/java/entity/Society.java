package entity;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import utilities.Utilities;

public class Society extends Entity {

	private final static String TABLENAME = "societa";
	private final static String COLUMNS = "(PartitaIVA, Nome, CodSport, CodImmagine)";
	private String partitaIVA;
	private String nome;
	private Sport sport;
	private int codImmagine;

	public Society(String partitaIVA, String nome, Sport sport) {
		this.partitaIVA = partitaIVA;
		this.nome = nome;
		this.sport = sport;
		this.codImmagine = 1;
	}

	public Society(String partitaIVA, String nome, Sport sport, Immagine image) {
		this.partitaIVA = partitaIVA;
		this.nome = nome;
		this.sport = sport;
		image.insert();
		this.codImmagine = (int) image.getPrimaryKey();
	}

	public String getPrimaryKey() {
		return this.partitaIVA;
	}

	public String getNome() {
		return nome;
	}

	public Sport getSport() {
		return sport;
	}

	public int getImage() {
		return this.codImmagine;
	}

	@Override
	public String getTableName() {
		return Society.TABLENAME;
	}

	@Override
	public String getColumnList() {
		return Society.COLUMNS;
	}

	@Override
	public String getValues() {
		return "('" + this.getPrimaryKey() + "', '" + this.getNome() + "', '" + this.getSport().getCodice() + "', " + this.codImmagine + ")";
	}

	@Override
	public String getNamePrimaryKey() {
		return "PartitaIVA";
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		
	}

	@Override
	public void insert() {
		try {
			Utilities.insertEntity(this);
			Utilities.insertSport(this.sport.getCodice());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
