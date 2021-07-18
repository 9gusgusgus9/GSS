package utilities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DateTime {
	private int anno, mese, giorno, ora, minuto;
	private StringProperty property = new SimpleStringProperty("lunedi");
	
	public DateTime(int anno, int mese, int giorno, int ora, int minuto) {
		this.anno = anno;
		this.mese = mese;
		this.giorno = giorno;
		this.ora = ora;
		this.minuto = minuto;
	}
	
	public DateTime(int anno, int mese, int giorno) {
		this.anno = anno;
		this.mese = mese;
		this.giorno = giorno;
		this.ora = 0;
		this.minuto = 0;
	}
	
	public DateTime(String string) {
		this.giorno = Integer.parseInt(string.substring(0,2));
		this.mese = Integer.parseInt(string.substring(3,4));
		this.anno = Integer.parseInt(string.substring(5));
	}
	
	public int getAnno() {
		return anno;
	}

	public int getMese() {
		return mese;
	}

	public int getGiorno() {
		return giorno;
	}

	public int getOra() {
		return ora;
	}

	public int getMinuto() {
		return minuto;
	}

	public String getDate() {
		return this.giorno+"/"+this.mese+"/"+this.anno;
	}
	
	public StringProperty getProperty() {
		return this.property;
	}
	
	public String getTime() {
		return this.ora+":"+this.minuto;
	}
	
	public int compareDate(DateTime data) {
		if(this.anno == data.getAnno()) {
			if(this.mese == data.getMese()) {
				if(this.giorno == data.getGiorno()) {
					if(this.ora == data.getOra()) {
						if(this.minuto == data.getMinuto()) {
							return 0;
						} else {
							return this.minuto - data.getMinuto();
						}
					}  else {
						return this.ora - data.getOra();
					}
				}  else {
					return this.giorno - data.getGiorno();
				}
			}  else {
				return this.mese - data.getMese();
			}
		} else {
			return this.anno - data.getAnno();
		}
	}
	
	
}
