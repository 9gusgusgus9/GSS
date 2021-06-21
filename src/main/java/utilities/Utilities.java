package utilities;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.Entity;

public class Utilities {

	private static Connection conn;
	private static Statement stmt;

	static {

	}

	public static void insertEntity(Entity entity) throws SQLException, FileNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management", "root",
					"Tommasocalcio10");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String query = "INSERT INTO " + entity.getTableName() + " " + entity.getColumnList() + " VALUES "
				+ entity.getValues();
		stmt.executeUpdate(query);
		query = "SELECT * FROM " + entity.getTableName() + " ORDER BY " + entity.getNamePrimaryKey() + " DESC LIMIT 1";
		ResultSet rs = stmt.executeQuery(query);
		if(rs.next()) {
			entity.setPrimaryKey(Integer.parseInt(rs.getString(1)));
		}
		conn.close();
		stmt.close();

	};

	public static void deleteEntity(Entity entity) throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management", "root",
					"Tommasocalcio10");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		stmt.executeUpdate("DELETE FROM " + entity.getTableName() + " AS e WHERE e." + entity.getNamePrimaryKey() + "=" + entity.getPrimaryKey());
		conn.close();
		stmt.close();
	}

	// InputStream is = new FileInputStream("C:\\Users\\tbrin\\Desktop\\b1SB.png");
		// PreparedStatement ps = conn.prepareStatement("INSERT INTO immagine
		// (IdImmagine, Nome, TipoFile, DatiFimmagineimmagineile) VALUES ('1', 'bomber',
		// '.png', ?)");
		// ps.setBlob(1, is);
		// ps.execute();
		// stmt.executeUpdate("INSERT INTO IMMAGINE(IdImmagine,Nome,TipoFile)");
}
