package utilities;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import entity.Category;
import entity.Entity;
import entity.Event;
import entity.Immagine;
import entity.Payment;
import entity.Player;
import entity.Manager;
import entity.Staff;
import entity.Person;
import entity.Society;
import entity.Sport;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;


public class Utilities {

	private static int idCategoria=0;
	private static Event actualEvent;
	
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

//		InputStream is = new FileInputStream("src/main/resources/img/pallavolo.jpg");
//		PreparedStatement ps = conn.prepareStatement("INSERT INTO immagine(IdImmagine, Nome, TipoFile, DatiFile) VALUES ('1', 'bomber','.jpg', ?)");
//		ps.setBlob(1, is);
//		ps.execute();
//		

		String query = "INSERT INTO " + entity.getTableName() + " " + entity.getColumnList() + " VALUES "
				+ entity.getValues();
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

	public static void insertSport(int codSport) throws SQLException, FileNotFoundException {
		dbConnection();
		String query="";
		switch(codSport) {
		case 2: 
			query = "INSERT INTO ruolo_giocatore (IdRuoloGiocatore, Descrizione) VALUES ('PM','Playmaker'),('GU','Guardia'),('AP','Ala Piccola'),('AG','Ala Grande'),('CEN','Centro')";
			break;
		case 1:
			query = "INSERT INTO ruolo_giocatore (IdRuoloGiocatore, Descrizione) VALUES ('POR','Portiere'),('DIF','Difensore'),('CEN','Centrocampista'),('ATT','Attaccante')";
			break;
		case 3:
			query = "INSERT INTO ruolo_giocatore (IdRuoloGiocatore, Descrizione) VALUES ('PAL','Palleggiatore'),('CENT','Centrale'),('SL','Schiacciatore Laterale'),('SO','Schiacciatore Opposto'),('LIB','Libero')";
			break;
		}
		
		stmt.executeUpdate(query);
		conn.close();
		stmt.close();
	}
	
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
		String query = "DELETE TABLE dirigente";
		stmt.executeUpdate(query);
		query = "DELETE TABLE team_management.staff";
		stmt.executeUpdate(query);
		query = "DELETE TABLE team_management.giocatore";
		stmt.executeUpdate(query);
		query = "DELETE TABLE team_management.convocazioni";
		stmt.executeUpdate(query);
		query = "DELETE TABLE team_management.eventi";
		stmt.executeUpdate(query);
		query = "DELETE TABLE team_management.persona";
		stmt.executeUpdate(query);
		query = "DELETE TABLE team_management.pagamento";
		stmt.executeUpdate(query);
		query = "DELETE TABLE team_management.categoria";
		stmt.executeUpdate(query);
		query = "DELETE TABLE team_management.societa";
		stmt.executeUpdate(query);
		conn.close();
		stmt.close();
	}

	public static void insertImage(Immagine image) throws FileNotFoundException, SQLException {
		dbConnection();
		PreparedStatement ps = conn
				.prepareStatement("INSERT INTO immagine" + image.getColumnList() + " VALUES " + image.getValues());
		ps.setBlob(1, image.getImage());
		ps.execute();
		String query = "SELECT * FROM " + image.getTableName() + " ORDER BY " + image.getNamePrimaryKey()
				+ " DESC LIMIT 1";
		ResultSet rs = stmt.executeQuery(query);
		if (rs.next()) {
			image.setPrimaryKey(Integer.parseInt(rs.getString(1)));
		}
		conn.close();
		stmt.close();

	}
	
	public static Image getImage(int x) throws IOException, SQLException {
		dbConnection();
		String query = "SELECT DatiFile FROM IMMAGINE WHERE IdImmagine=" + x;
		System.out.println(query);
		ResultSet rs = stmt.executeQuery(query);
		if(rs.next()) {
			InputStream image = rs.getBlob(1).getBinaryStream();
			BufferedImage imagen = ImageIO.read(image);
			Image out = SwingFXUtils.toFXImage(imagen, null);
			return out;
		}
		return null;
	}
	
	public static void insertConvocati(List<Object> convocati, Object codEvent) throws SQLException {
		dbConnection();
		String query = null;
		for(int i=0; i<convocati.size(); i++) {
			query = "INSERT INTO convocazioni (codEvento, CF) VALUES (" + (int) codEvent + ",'" + (String) convocati.get(i) + "')";
			stmt.executeUpdate(query);
			System.out.println(query);
		}
		conn.close();
		stmt.close();
	}
	
	public static boolean isTheFirstStart() throws SQLException {
		dbConnection();
		String query = "SELECT * FROM societa";
		ResultSet rs = stmt.executeQuery(query);
		if(rs.next()) {
			return false;
		} else {
			return true;
		}
	}
	
	public static List<Pair<Category, Image>> getCategories() throws SQLException, IOException{
		dbConnection();
		String query = "SELECT * FROM categoria ORDER BY Nome";
		ResultSet rs = stmt.executeQuery(query);
		List<Pair<Category, Image>> out = new LinkedList<>();
		while(rs.next()) {
			int codImage = rs.getInt("CodImmagine");
			Image image = Utilities.getImage(codImage);
			Category category = new Category(rs.getInt("IdCategoria"),rs.getString("Nome"),rs.getString("CodPartitaIVA"), codImage);
			out.add(new Pair<>(category, image));
		}
		return out;
	}

	public static Pair<Image,Society> getSociety() throws SQLException, IOException {
		dbConnection();
		String query = "SELECT * FROM societa";
		ResultSet rs = stmt.executeQuery(query);
		Society out = null;
		Image image = null;
		if(rs.next()) {
			if(rs.getString("Color1").equals(null)) {
				out = new Society(rs.getString("PartitaIVA"),rs.getString("Nome"), Sport.getSport(rs.getInt("CodSport")));
			} else {
				out = new Society(rs.getString("PartitaIVA"),rs.getString("Nome"), Sport.getSport(rs.getInt("CodSport")), rs.getString("Color1"), rs.getString("Color2"));
			}
			image = Utilities.getImage(rs.getInt("CodImmagine"));
		}
		Pair<Image, Society> society = new Pair<>(image, out);
		return society;
	}
	

	public static List<Event> getEvents(DateTime lunedi, DateTime domenica) throws SQLException {
		dbConnection();
		String query = "SELECT * FROM evento";
		List<Event> events = new LinkedList<>();
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()) {
			String[] inizio = rs.getString("Inizio").split("/");
			String[] fine = rs.getString("Fine").split("/");
			if(rs.getString("NomeAvversario") != null) {
				Event e = new Event(new DateTime(Integer.parseInt(inizio[2]), Integer.parseInt(inizio[1]), Integer.parseInt(inizio[0])), new DateTime(Integer.parseInt(fine[0]), Integer.parseInt(fine[1]), Integer.parseInt(fine[2])), rs.getString("CodPartitaIVA"), rs.getString("NomeAvversario"), rs.getInt("CodCategoria"));
				e.setPrimaryKey(rs.getInt("IdEvento"));
				events.add(e);
			} else if (rs.getString("CodCategoria") != null){
				Event e = new Event(new DateTime(Integer.parseInt(inizio[2]), Integer.parseInt(inizio[1]), Integer.parseInt(inizio[0])), new DateTime(Integer.parseInt(fine[0]), Integer.parseInt(fine[1]), Integer.parseInt(fine[2])), rs.getString("CodPartitaIVA"), rs.getInt("CodCategoria"));
				e.setPrimaryKey(rs.getInt("IdEvento"));
				events.add(e);
			} else {
				Event e = new Event(new DateTime(Integer.parseInt(inizio[2]), Integer.parseInt(inizio[1]), Integer.parseInt(inizio[0])), new DateTime(Integer.parseInt(fine[0]), Integer.parseInt(fine[1]), Integer.parseInt(fine[2])), rs.getString("CodPartitaIVA"), rs.getString("Descrizione_generico"));
				e.setPrimaryKey(rs.getInt("IdEvento"));
				events.add(e);
			}
		}
		List<Event> filteredEvents = events.stream().filter(d -> d.getInizio().compareDate(lunedi) >= 0 && d.getInizio().compareDate(domenica) <= 0).collect(Collectors.toList());
		return filteredEvents;
	}
		
	public static Pair<Image,Category> getCategory(int idCategoria) throws SQLException, IOException {
		dbConnection();
		String query = "SELECT * FROM categoria WHERE IdCategoria="+idCategoria;
		ResultSet rs = stmt.executeQuery(query);
		Category out = null;
		Image image = null;
		if(rs.next()) {
			out = new Category(rs.getString("Nome"), rs.getString("CodPartitaIva"));
			image = Utilities.getImage(rs.getInt("CodImmagine"));
		}
		Pair<Image, Category> category = new Pair<>(image, out);
		return category;
	}
	
	public static void setCategoria(int id) {
		idCategoria = id;
	}
	
	public static int getCategoria() {
		return idCategoria;
	}
	
	public static Event getEvent() {
		return actualEvent;
	}
	
	public static void setEvent(Event e) {
		actualEvent = e;
	}
	
	public static Category getOnlyCategory(int idCategoria) throws SQLException, IOException {
		dbConnection();
		String query = "SELECT * FROM categoria WHERE IdCategoria="+idCategoria;
		ResultSet rs = stmt.executeQuery(query);
		Category out = null;
		if(rs.next()) {
			out = new Category(rs.getString("Nome"), rs.getString("CodPartitaIva"));
		}
		Category category = out;
		return category;
	}
	
	public static boolean checkPersona(String cf) throws SQLException {
		dbConnection();
		String query = "SELECT * FROM persona";
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()) {
			if(rs.getString("CF").equals(cf)) {
				return false;
			}
		}
		return true;
	}
	
	public static ObservableList<Person> getConvocati(int idEvento) throws SQLException {
		ObservableList<Person> convocati = FXCollections.observableArrayList();
		dbConnection();
		String qPlayer = "SELECT q1.CF , q1.Nome , q1.Cognome, q2.CodRuoloGiocatore , q2.CodCategoria FROM (SELECT * FROM giocatore) AS q2 INNER JOIN (SELECT c.CF , p.Nome , p.Cognome FROM convocazioni AS c INNER JOIN persona AS p ON c.CF = p.CF WHERE c.CodEvento="+idEvento+") AS q1 ON q1.CF = q2.CF";
		String qManager = "SELECT q1.CF , q1.Nome , q1.Cognome , q2.CodRuoloDirigente FROM (SELECT * FROM dirigente) AS q2 INNER JOIN (SELECT c.CF , p.Nome , p.Cognome FROM convocazioni AS c INNER JOIN persona AS p ON c.CF = p.CF WHERE c.CodEvento="+idEvento+") AS q1 ON q1.CF = q2.CF";
		String qStaff = "SELECT q1.CF , q1.Nome , q1.Cognome , q2.CodRuoloStaff , q2.CodCategoria FROM (SELECT * FROM staff) AS q2 INNER JOIN (SELECT c.CF , p.Nome , p.Cognome FROM convocazioni AS c INNER JOIN persona AS p ON c.CF = p.CF WHERE c.CodEvento="+idEvento+") AS q1 ON q1.CF = q2.CF";
		ResultSet rsPlayer = stmt.executeQuery(qPlayer);
		while(rsPlayer.next()) {
			Person player = new Person(rsPlayer.getString("q1.CF"), rsPlayer.getString("q1.Nome"), rsPlayer.getString("q1.Cognome"));
			convocati.add(player);
		}
		ResultSet rsManager = stmt.executeQuery(qManager);
		while(rsManager.next()) {
			Person manager = new Person(rsManager.getString("q1.CF"), rsManager.getString("q1.Nome"), rsManager.getString("q1.Cognome"));
			convocati.add(manager);
		}
		ResultSet rsStaff = stmt.executeQuery(qStaff);
		while(rsStaff.next()) {
			Person staff = new Person(rsStaff.getString("q1.CF"), rsStaff.getString("q1.Nome"), rsStaff.getString("q1.Cognome"));
			convocati.add(staff);
		}
		return convocati;
	}
	
	public static SimpleStringProperty getCodRuoloByCF(String CF) throws SQLException {
		dbConnection();
		String qPlayer = "SELECT CodRuoloGiocatore FROM giocatore WHERE CF='"+CF+"'";
		String qManager = "SELECT CodRuoloDirigente FROM dirigente WHERE CF='"+CF+"'";
		String qStaff = "SELECT CodRuoloStaff FROM staff WHERE CF='"+CF+"'";
		ResultSet rsPlayer = stmt.executeQuery(qPlayer);
		if(rsPlayer.next()) {
			return new SimpleStringProperty(rsPlayer.getString("CodRuoloGiocatore"));
		}
		ResultSet rsManager = stmt.executeQuery(qManager);
		if(rsManager.next()) {
			return new SimpleStringProperty(rsManager.getString("CodRuoloDirigente"));
		}
		ResultSet rsStaff = stmt.executeQuery(qStaff);
		if(rsStaff.next()) {
			return new SimpleStringProperty(rsStaff.getString("CodRuoloStaff"));
		}
		return null;
	}
	
	public static SimpleStringProperty getNomeCategoriaByCF(String CF) throws SQLException {
		dbConnection();
		String qPlayer = "SELECT q2.Nome FROM (SELECT CodCategoria FROM giocatore WHERE CF='"+CF+"') AS q1 INNER JOIN categoria AS q2 ON q1.CodCategoria = q2.IdCategoria";
		String qStaff = "SELECT q2.Nome FROM (SELECT CodCategoria FROM staff WHERE CF='"+CF+"') AS q1 INNER JOIN categoria AS q2 ON q1.CodCategoria = q2.IdCategoria";
		ResultSet rsPlayer = stmt.executeQuery(qPlayer);
		if(rsPlayer.next()) {
			return new SimpleStringProperty(rsPlayer.getString("q2.Nome"));
		}
		ResultSet rsStaff = stmt.executeQuery(qStaff);
		if(rsStaff.next()) {
			return new SimpleStringProperty(rsStaff.getString("q2.Nome"));
		}
		return new SimpleStringProperty("Dirigente");
	}
	
	
}
