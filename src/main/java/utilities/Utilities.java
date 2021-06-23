package utilities;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import entity.Category;
import entity.Entity;
import entity.Event;
import entity.Payment;

public class Utilities {

	private static Connection conn;
	private static Statement stmt;
	private static String password = null;
	static {

	}

	private static void dbConnection() {
		try {
			password.equals(null);
		} catch (NullPointerException e) {
			System.out.println("Inserire la password di root:");
			BufferedReader variabile = new BufferedReader(new InputStreamReader(System.in));
			try {
				password = variabile.readLine();
			} catch (IOException e1) {
				e.printStackTrace();
			}
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management", "root", password);
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertEntity(Entity entity) throws SQLException, FileNotFoundException {
		dbConnection();
//
//		InputStream is = new FileInputStream("src/main/resources/img/pallavolo.jpg");
//		PreparedStatement ps = conn.prepareStatement("INSERT INTO immagine(IdImmagine, Nome, TipoFile, DatiFile) VALUES ('1', 'bomber','.jpg', ?)");
//		ps.setBlob(1, is);
//		ps.execute();
//		

		String query = "INSERT INTO " + entity.getTableName() + " " + entity.getColumnList() + " VALUES "
				+ entity.getValues();
		System.out.println(query);
		stmt.executeUpdate(query);
		query = "SELECT * FROM " + entity.getTableName() + " ORDER BY " + entity.getNamePrimaryKey() + " DESC LIMIT 1";
		ResultSet rs = stmt.executeQuery(query);
		if (rs.next() && (entity.getClass().equals(Category.class) || entity.getClass().equals(Event.class)
				|| entity.getClass().equals(Payment.class))) {
			entity.setPrimaryKey(Integer.parseInt(rs.getString(1)));
		}
		conn.close();
		stmt.close();

	};

	public static void deleteEntity(Entity entity) throws SQLException {
		dbConnection();

		stmt.executeUpdate("DELETE FROM " + entity.getTableName() + " AS e WHERE e." + entity.getNamePrimaryKey() + "="
				+ entity.getPrimaryKey());
		conn.close();
		stmt.close();
	}

	public static void update(Entity entity, List<Pair<String, String>> fields)
			throws SQLException, FileNotFoundException {
		dbConnection();

		Iterator<Pair<String, String>> it = fields.iterator();

		String query = "UPDATE " + entity.getTableName() + " SET";

		boolean check = it.hasNext();

		while (check) {
			Pair<String, String> nxt = it.next();
			query += " " + nxt.getX() + " = '" + nxt.getY() + "'";
			check = it.hasNext();
			if (check) {
				query += ",";
			}
		}

		query += " WHERE " + entity.getNamePrimaryKey() + " = '" + entity.getPrimaryKey() + "'";
		stmt.executeUpdate(query);
		stmt.close();

	};

	public static void tableEmpty() throws SQLException {
		dbConnection();
		String query = "TRUNCATE TABLE team_management.dirigente";
		stmt.executeUpdate(query);
		query = "TRUNCATE TABLE team_management.staff";
		stmt.executeUpdate(query);
		query = "TRUNCATE TABLE team_management.giocatore";
		stmt.executeUpdate(query);
		query = "TRUNCATE TABLE team_management.convocazioni";
		stmt.executeUpdate(query);
		query = "TRUNCATE TABLE team_management.eventi";
		stmt.executeUpdate(query);
		query = "TRUNCATE TABLE team_management.persona";
		stmt.executeUpdate(query);
		query = "TRUNCATE TABLE team_management.pagamento";
		stmt.executeUpdate(query);
		query = "TRUNCATE TABLE team_management.categoria";
		stmt.executeUpdate(query);
		query = "TRUNCATE TABLE team_management.societa";
		stmt.executeUpdate(query);
		
	}

}
