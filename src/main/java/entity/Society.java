package entity;

import java.awt.image.BufferedImage;


public class Society extends Entity {

	public final static String TABLENAME="societa";
	private String partitaIVA;
	private String nome;
	private Sport sport;
	private BufferedImage image;
	
	public Society(String partitaIVA, String nome, Sport sport){
		this.partitaIVA=partitaIVA;
		this.nome=nome;
		this.sport=sport;
	}
	
	public Society(String partitaIVA, String nome, Sport sport, BufferedImage image){
		this.partitaIVA=partitaIVA;
		this.nome=nome;
		this.sport=sport;
		this.image=image;
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

	public BufferedImage getImage() {
		return image;
	}

	@Override
	public String getTableName() {
		return Society.TABLENAME;
	}

	@Override
	public String getColumnList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValues() {
		// TODO Auto-generated method stub
		return null;
	}

}
