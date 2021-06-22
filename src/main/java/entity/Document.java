package entity;

public class Document extends Entity{
	public final static String TABLENAME="possesso";
	private final static String COLUMNS="(CodDocumento, CF, CodImmagine)";
	
	private int codDocumento;
	private String cF;
	private int codImmagine;
	
	public Document(int codDocumento, String cF, int codImmagine) {
		super();
		this.codDocumento = codDocumento;
		this.cF = cF;
		this.codImmagine = codImmagine;
	}

	@Override
	public String getTableName() {
		return Document.TABLENAME;
	}

	@Override
	public Object getPrimaryKey() {
		return this.getValues();
	}

	@Override
	public String getColumnList() {
		// TODO Auto-generated method stub
		return Document.COLUMNS;
	}

	@Override
	public String getValues() {
		// TODO Auto-generated method stub
		return "(" + this.codDocumento+", " + this.cF + ", " + this.codImmagine + ")";
	}

	@Override
	public String getNamePrimaryKey() {
		// TODO Auto-generated method stub
		return Document.COLUMNS;
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		// TODO Auto-generated method stub
		
	}
}
