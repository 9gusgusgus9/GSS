package entity;

import utilities.DateTime;

public class Manager extends Person {

	public Manager(String codiceFiscale, String nome, String cognome, DateTime data, String codSesso,
			int image, int matricola) {
		super(codiceFiscale, nome, cognome, data, codSesso, image, matricola);
	}

	public Manager(String codiceFiscale, String nome, String cognome, DateTime data, String codSesso,
			int image) {
		super(codiceFiscale, nome, cognome, data, codSesso, image);
	}

}
