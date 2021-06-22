package entity;

import utilities.DateTime;

public class Staff extends Person {

	public Staff(String codiceFiscale, String nome, String cognome, DateTime data, String codSesso, int image,
			int matricola) {
		super(codiceFiscale, nome, cognome, data, codSesso, image, matricola);
		// TODO Auto-generated constructor stub
	}

	public Staff(String codiceFiscale, String nome, String cognome, DateTime data, String codSesso,
			int image) {
		super(codiceFiscale, nome, cognome, data, codSesso, image);
		// TODO Auto-generated constructor stub
	}

}
