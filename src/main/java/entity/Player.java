package entity;

import java.awt.image.BufferedImage;

import utilities.DateTime;

public class Player extends Person {

	public Player(String codiceFiscale, String nome, String cognome, DateTime data, String codSesso,
			BufferedImage image, int matricola) {
		super(codiceFiscale, nome, cognome, data, codSesso, image, matricola);
	}

	public Player(String codiceFiscale, String nome, String cognome, DateTime data, String codSesso,
			BufferedImage image) {
		super(codiceFiscale, nome, cognome, data, codSesso, image);
	}
	
	
	
}
