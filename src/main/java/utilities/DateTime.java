package utilities;

public class DateTime {
	private int anno, mese, giorno, ora, minuto;
	
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
	
	public String getDate() {
		return this.giorno+"/"+this.mese+"/"+this.anno;
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
