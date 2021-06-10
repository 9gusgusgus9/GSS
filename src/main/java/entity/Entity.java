package entity;

import java.io.FileNotFoundException;
import java.sql.SQLException;

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
	
	public abstract String getTableName();
	
	public abstract Object getPrimaryKey();

	public abstract String getColumnList();
	
	public abstract String getValues();
}
