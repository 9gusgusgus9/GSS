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
	
	public static Sport getSport(int cod) {
		if(cod == Sport.CALCIO.getCodice()) {
			return Sport.CALCIO;
		} else if(cod == Sport.BASKET.getCodice()) {
			return Sport.BASKET;
		} else if(cod == Sport.PALLAVOLO.getCodice()){
			return Sport.PALLAVOLO;
		} else {
			return Sport.NONSELEZIONATO;
		}
	}
}
