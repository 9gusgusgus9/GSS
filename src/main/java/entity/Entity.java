package entity;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import utilities.Pair;
import utilities.Utilities;

public abstract class Entity {
	
	public void insert() {
		try {
			Utilities.insertEntity(this);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	public void delete() {
		try {
			Utilities.deleteEntity(this);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(List<Pair<String, String>> list) {
		try {
			Utilities.update(this, list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public abstract String getTableName();
	
	public abstract Object getPrimaryKey();

	public abstract String getColumnList();
	
	public abstract String getValues();
	
	public abstract String getNamePrimaryKey();

	public abstract void setPrimaryKey(int primaryKey);
	
}
