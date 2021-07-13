package entity;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import utilities.Utilities;

public class Society extends Entity {

	private final static String TABLENAME = "societa";
	private final static String COLUMNS = "(PartitaIVA, Nome, CodSport, CodImmagine)";
	private final static String COLUMNS_COLOR = "(PartitaIVA, Nome, CodSport, CodImmagine, Color1, Color2)";
	private String partitaIVA;
	private String nome;
	private Sport sport;
	private int codImmagine;
	private String color1;
	private String color2;

	public Society(String partitaIVA, String nome, Sport sport) {
		this.partitaIVA = partitaIVA;
		this.nome = nome;
		this.sport = sport;
		this.codImmagine = 1;
		this.color1 = null;
		this.color2 = null;
	}

	public Society(String partitaIVA, String nome, Sport sport, Immagine image) {
		this.partitaIVA = partitaIVA;
		this.nome = nome;
		this.sport = sport;
		image.insert();
		this.codImmagine = (int) image.getPrimaryKey();
		this.color1 = null;
		this.color2 = null;
	}
	
	public Society(String partitaIVA, String nome, Sport sport, String color1, String color2) {
		this.partitaIVA = partitaIVA;
		this.nome = nome;
		this.sport = sport;
		this.codImmagine = 1;
		this.color1 = color1;
		this.color2 = color2;
	}

	public Society(String partitaIVA, String nome, Sport sport, Immagine image, String color1, String color2) {
		this.partitaIVA = partitaIVA;
		this.nome = nome;
		this.sport = sport;
		image.insert();
		this.codImmagine = (int) image.getPrimaryKey();
		this.color1 = color1;
		this.color2 = color2;
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

	public String getColor1() {
		return this.color1;
	}
	
	public String getColor2() {
		return this.color2;
	}
	
	@Override
	public String getTableName() {
		return Society.TABLENAME;
	}

	@Override
	public String getColumnList() {
		if(this.color1 == null) {
			return Society.COLUMNS;	
		} else {
			return Society.COLUMNS_COLOR;
		}
	}

	@Override
	public String getValues() {
		if(this.color1 == null) {
			return "('" + this.getPrimaryKey() + "', '" + this.getNome() + "', '" + this.getSport().getCodice() + "', " + this.codImmagine + ")";
		} else {	
			return "('" + this.getPrimaryKey() + "', '" + this.getNome() + "', '" + this.getSport().getCodice() + "', " + this.codImmagine + ", '" + this.color1 + "', '" + this.color2 + "')";
		}
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
