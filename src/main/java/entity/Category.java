package entity;

public class Category extends Entity {
	
	private final static String TABLENAME="categoria";
	private final static String COLUMNS="(Nome, CodPartitaIVA, CodImmagine)";
	private String codPartitaIVA;
	private int codImmagine;
	private int idCategoria;
	private String nome;
	
	public Category(String nome, String codPartitaIVA, Immagine image) {
		super();
		this.codPartitaIVA = codPartitaIVA;
		image.insert();
		this.codImmagine = (int) image.getPrimaryKey();
		this.nome = nome;
	}
	
	public Category(int idCategoria, String nome, String codPartitaIVA, Immagine image) {
		super();
		this.idCategoria=idCategoria;
		this.codPartitaIVA = codPartitaIVA;
		image.insert();
		this.codImmagine =(int) image.getPrimaryKey();
		this.nome = nome;
	}
	
	public Category(String nome, String codPartitaIVA, int codImage) {
		super();
		this.codPartitaIVA = codPartitaIVA;
		this.codImmagine = codImage;
		this.nome = nome;
	}
	
	public Category(int idCategoria, String nome, String codPartitaIVA, int codImage) {
		super();
		this.idCategoria=idCategoria;
		this.codPartitaIVA = codPartitaIVA;
		this.codImmagine = codImage;
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}
	
	public Object getPrimaryKey() {
		return this.idCategoria;
	}
	
	@Override
	public String getTableName() {
		return Category.TABLENAME;
	}

	@Override
	public String getColumnList() {
		return Category.COLUMNS;
	}
	
	@Override
	public void setPrimaryKey(int primaryKey) {
		this.idCategoria=primaryKey;
	}

	@Override
	public String getValues() {
		return "('" + this.getNome() + "', '" + this.codPartitaIVA + "', '" + this.codImmagine + "')";
	}

	@Override
	public String getNamePrimaryKey() {
		return "IdCategoria";
	}
}
