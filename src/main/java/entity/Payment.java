package entity;

public class Payment extends Entity {
	public final static String TABLENAME="pagamento";
	public final static String COLUMNS="(Pagato, ";
	private int idPagamento;
	private int quantita;
	private boolean pagato;
	private Finanze tipo;
	
	public Payment(int quantita, boolean pagato, Finanze tipo) {
		this.quantita = quantita;
		this.pagato = pagato;
		this.tipo = tipo;
	}

	public boolean isPagato() {
		return pagato;
	}

	public void setPagato(boolean pagato) {
		this.pagato = pagato;
	}

	public int getQuantita() {
		return quantita;
	}

	public Finanze getTipo() {
		return tipo;
	}

	@Override
	public String getTableName() {
		return Payment.TABLENAME;
	}

	@Override
	public Object getPrimaryKey() {
		return this.idPagamento;
	}

	@Override
	public String getColumnList() {
		if(this.tipo.equals(Finanze.QUOTA)) {
			return Payment.COLUMNS + "Quota)";
		} else {
			return Payment.COLUMNS + "Stipendio)";	
		}
	}

	@Override
	public String getValues() {
		return "(" + this.pagato + ", " + this.quantita + ")";
	}

	@Override
	public String getNamePrimaryKey() {
		return "IdPagamento";
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		this.idPagamento = primaryKey;
	}
}
