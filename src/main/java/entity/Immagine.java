package entity;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;

import utilities.Utilities;

public class Immagine extends Entity {

	private static final String TABLENAME = "immagine";
	private static final String COLUMNS = "(Nome, TipoFile, DatiFile)";

	private int idImmagine;
	private InputStream image;
	private String nome;
	private String ext;

	public Immagine(InputStream image, String nome, String ext) {
		this.image = image;
		this.nome = nome;
		this.ext = ext;
	}

	public InputStream getImage() {
		return image;
	}

	@Override
	public String getTableName() {
		return Immagine.TABLENAME;
	}

	@Override
	public Object getPrimaryKey() {
		return this.idImmagine;
	}

	@Override
	public String getColumnList() {
		return Immagine.COLUMNS;
	}

	@Override
	public String getValues() {
		return "('" + this.nome + "', '" + this.ext + "', ?)";
	}

	@Override
	public String getNamePrimaryKey() {
		// TODO Auto-generated method stub
		return "IdImmagine";
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		this.idImmagine = primaryKey;
	}
	
	@Override
	public void insert() {
		try {
			Utilities.insertImage(this);
		} catch (FileNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
