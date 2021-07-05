package entity;

import java.io.FileInputStream;
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

	public Immagine(String path, String nome, String ext) {
		InputStream image;
		try {
			image = new FileInputStream(path);
			this.image = image;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.nome = nome;
		this.ext = ext;
	}

	public Immagine(String path) {
		InputStream image;
		try {
			image = new FileInputStream(path);
			this.image = image;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.setNameFromPath(path);
		this.setExtFromPath(path);
	}
	
	public InputStream getImage() {
		return image;
	}

	private void setNameFromPath(String path) {
		char[] arr = path.toCharArray();
		int length = path.length();
		int count = 1;
		String out = "";
		while(arr[length-count]!='.') {
			count++;
		}
		count++;
		char slash = '\\';
		while(length-count==0 && (arr[length-count]!='/' || arr[length-count]!=slash)) {
			System.out.println(arr[length-count]);
			out = arr[length-count] + out;
			count++;
		}
		this.nome = out;
	}
	
	private void setExtFromPath(String path) {
		char[] arr = path.toCharArray();
		int length = path.length();
		int count = 1;
		String out = "";
		while(arr[length-count]!='.') {
			out = arr[length-count] + out;
			count++;
		}
		out = '.' + out;
		this.ext = out;
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
