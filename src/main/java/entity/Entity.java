package entity;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import utilities.Pair;
import utilities.Utilities;

public abstract class Entity {
	
	public void insert() {
		Utilities.insertEntity(this);
	}
	
	public void delete() {
		Utilities.deleteEntity(this);
	}
	
	public void update(List<Pair<String, String>> list) {
		Utilities.update(this, list);
	}
	
	public abstract String getTableName();
	
	public abstract Object getPrimaryKey();

	public abstract String getColumnList();
	
	public abstract String getValues();
	
	public abstract String getNamePrimaryKey();

	public abstract void setPrimaryKey(int primaryKey);
	
}
