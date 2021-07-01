package entity;

public class Document extends Entity{
	public final static String TABLENAME="possesso";
	private final static String COLUMNS="(CodDocumento, CF, CodImmagine)";
	
	private int codDocumento;
	private String cF;
	private int codImmagine;
	
	public Document(int codDocumento, String cF, Immagine image) {
		super();
		this.codDocumento = codDocumento;
		this.cF = cF;
		image.insert();
		this.codImmagine = (int) image.getPrimaryKey();
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
		return Document.COLUMNS;
	}

	@Override
	public String getValues() {
		return "(" + this.codDocumento+", " + this.cF + ", " + this.codImmagine + ")";
	}

	@Override
	public String getNamePrimaryKey() {
		return Document.COLUMNS;
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		
	}
}
