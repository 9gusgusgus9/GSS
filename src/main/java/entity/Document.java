package entity;

public class Document extends Entity{
	public final static String TABLENAME="possesso";
	private final static String COLUMNS="(CodDocumento, CF, CodImmagine)";
	
	private int codDocumento;
	private String cF;
	private int codImmagine;
	
	@Override
	public String getTableName() {
		return this.TABLENAME;
	}

	@Override
	public Object getPrimaryKey() {
		// TODO Auto-generated method stub
		return ;
	}

	@Override
	public String getColumnList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNamePrimaryKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		// TODO Auto-generated method stub
		
	}
}
