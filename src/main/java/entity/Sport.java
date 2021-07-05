package entity;

public enum Sport {
	CALCIO(1),PALLAVOLO(3),BASKET(2), NONSELEZIONATO(4);
	
	private int codice;
	
	Sport(int codice) {
		this.codice=codice;
	}
	
	public int getCodice(){
		return this.codice;
	}
}
