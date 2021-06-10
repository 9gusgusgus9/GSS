package entity;
import java.awt.image.BufferedImage;

import utilities.DateTime;

public class Person extends Entity{
	public final static String TABLENAME="persona";
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private DateTime data;
	private String codSesso;
	private int matricola;
	private BufferedImage image;
	
	
		
	public Person(String codiceFiscale, String nome, String cognome, DateTime data, String codSesso,
			BufferedImage image) {
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.data = data;
		this.codSesso = codSesso;
		this.image = image;
		this.matricola = 0;
	}

	public Person(String codiceFiscale, String nome, String cognome, DateTime data, String codSesso,
			BufferedImage image, int matricola) {
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.data = data;
		this.codSesso = codSesso;
		this.image = image;
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

	public BufferedImage getImage() {
		return image;
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
		if(this.matricola == 0) {
			return "(CF, Nome, Cognome, Data, CodSesso, CodImmagine, ";
		} else {
			return "(CF, Nome, Cognome, Matricola, ";
		}
		
	}

	@Override
	public String getValues() {
		return this.codiceFiscale + ", " + this.nome + ", " + this.cognome + ", ";
	}
}
