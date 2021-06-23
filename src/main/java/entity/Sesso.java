package entity;

public enum Sesso {
	MASCHIO("Maschio"),
	FEMMINA("Femmina"),
	ALTRO("Altro");
	
	private String key;

	Sesso(String string) {
		this.key = string;
	}
	
	public String getKey(){
		return this.key;
	}
	
	
}
