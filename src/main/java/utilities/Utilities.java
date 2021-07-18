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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import entity.Category;
import entity.Entity;
import entity.Event;
import entity.Immagine;
import entity.Payment;
import entity.Player;
import entity.Society;
import entity.Sport;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class Utilities {

	private static int idCategoria=0;
	private static String cf = null;
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
		String query = "";
		switch (codSport) {
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
	
	public static List<String> getSport() throws SQLException{
		dbConnection();
		String query = "SELECT * FROM ruolo_giocatore" ;
		ResultSet rs = stmt.executeQuery(query);
		List<String> list = new LinkedList<>();
		while(rs.next()) {
			list.add(rs.getString("IdRuoloGiocatore"));
		}
		return list;
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
		if (rs.next()) {
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
		for (int i = 0; i < convocati.size(); i++) {
			query = "INSERT INTO convocazioni (codEvento, CF) VALUES (" + (int) codEvent + ",'"
					+ (String) convocati.get(i) + "')";
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
		if (rs.next()) {
			return false;
		} else {
			return true;
		}
	}

	public static List<Pair<Category, Image>> getCategories() throws SQLException, IOException {
		dbConnection();
		String query = "SELECT * FROM categoria ORDER BY Nome";
		ResultSet rs = stmt.executeQuery(query);
		List<Pair<Category, Image>> out = new LinkedList<>();
		while (rs.next()) {
			int codImage = rs.getInt("CodImmagine");
			Image image = Utilities.getImage(codImage);
			Category category = new Category(rs.getInt("IdCategoria"), rs.getString("Nome"),
					rs.getString("CodPartitaIVA"), codImage);
			out.add(new Pair<>(category, image));
		}
		return out;
	}

	public static Pair<Image, Society> getSociety() throws SQLException, IOException {
		dbConnection();
		String query = "SELECT * FROM societa";
		ResultSet rs = stmt.executeQuery(query);
		Society out = null;
		Image image = null;
		if (rs.next()) {
			if (rs.getString("Color1").equals(null)) {
				out = new Society(rs.getString("PartitaIVA"), rs.getString("Nome"),
						Sport.getSport(rs.getInt("CodSport")));
			} else {
				out = new Society(rs.getString("PartitaIVA"), rs.getString("Nome"),
						Sport.getSport(rs.getInt("CodSport")), rs.getString("Color1"), rs.getString("Color2"));
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
		while (rs.next()) {
			String[] inizio = rs.getString("Inizio").split("/");
			String[] fine = rs.getString("Fine").split("/");
			if (rs.getString("NomeAvversario") != null) {
				events.add(new Event(
						new DateTime(Integer.parseInt(inizio[2]), Integer.parseInt(inizio[1]),
								Integer.parseInt(inizio[0])),
						new DateTime(Integer.parseInt(fine[0]), Integer.parseInt(fine[1]), Integer.parseInt(fine[2])),
						rs.getString("CodPartitaIVA"), rs.getString("NomeAvversario"), rs.getInt("CodCategoria")));
			} else if (rs.getString("CodCategoria") != null) {
				events.add(new Event(
						new DateTime(Integer.parseInt(inizio[2]), Integer.parseInt(inizio[1]),
								Integer.parseInt(inizio[0])),
						new DateTime(Integer.parseInt(fine[0]), Integer.parseInt(fine[1]), Integer.parseInt(fine[2])),
						rs.getString("CodPartitaIVA"), rs.getInt("CodCategoria")));
			} else {
				events.add(new Event(
						new DateTime(Integer.parseInt(inizio[2]), Integer.parseInt(inizio[1]),
								Integer.parseInt(inizio[0])),
						new DateTime(Integer.parseInt(fine[0]), Integer.parseInt(fine[1]), Integer.parseInt(fine[2])),
						rs.getString("CodPartitaIVA"), rs.getString("Descrizione_generico")));
			}
		}
		List<Event> filteredEvents = events.stream()
				.filter(d -> d.getInizio().compareDate(lunedi) >= 0 && d.getInizio().compareDate(domenica) <= 0)
				.collect(Collectors.toList());
		return filteredEvents;
	}

	public static Pair<Image, Category> getCategory(int idCategoria) throws SQLException, IOException {
		dbConnection();
		String query = "SELECT * FROM categoria WHERE IdCategoria=" + idCategoria;
		ResultSet rs = stmt.executeQuery(query);
		Category out = null;
		Image image = null;
		if (rs.next()) {
			out = new Category(rs.getString("Nome"), rs.getString("CodPartitaIva"));
			image = Utilities.getImage(rs.getInt("CodImmagine"));
		}
		Pair<Image, Category> category = new Pair<>(image, out);
		return category;
	}
	
	public static Pair<Image,Player> getPlayer(String cf) throws SQLException, IOException {
		dbConnection();
		String query = "SELECT * FROM giocatore WHERE CF='" + cf + "'";
		ResultSet rs = stmt.executeQuery(query);
		Player out = null;
		Image image = null;
		String peso = null;
		String altezza = null;
		DateTime data = null;
		String ruolo = null;
		int codCategoria = 0;
		String preferenza = null;
		if(rs.next()) {
			peso = rs.getString("Peso");
			altezza = rs.getString("Altezza");
			data = new DateTime(rs.getString("Data_scadenza_certificato"));
			ruolo = rs.getString("CodRuoloGiocatore");
			codCategoria = rs.getInt("CodCategoria");
			preferenza = rs.getString("CodPreferenza");
		}
		String query1 = "SELECT * FROM persona WHERE CF="+cf;
		ResultSet rs1 = stmt.executeQuery(query1);
		if(rs1.next()) {
			out = new Player(cf, rs1.getString("Nome"), rs1.getString("Cognome"),new DateTime(rs1.getString("Data")), rs1.getInt("CodPagamento"),rs1.getString("CodSesso"), rs1.getString("CodPartitaIva"), rs1.getInt("Matricola_tesserino"), peso, altezza, data, ruolo, codCategoria, preferenza);
			image = Utilities.getImage(rs1.getInt("CodImmagine"));
		}
		Pair<Image, Player> player = new Pair<>(image, out);
		conn.close();
		stmt.close();
		return player;
	}
	
	public static void setCategoria(int id) {
		idCategoria = id;
	}

	public static int getCategoria() {
		return idCategoria;
	}
	
	public static void setCF(String CF) {
		cf = CF;
	}

	public static String getCF() {
		return cf;
	}
	
	public static Event getEvent() {
		return actualEvent;
	}
	
	public static void setEvent(Event e) {
		actualEvent = e;
	}
	
	public static Category getOnlyCategory(int idCategoria) throws SQLException, IOException {
		dbConnection();
		String query = "SELECT * FROM categoria WHERE IdCategoria=" + idCategoria;
		ResultSet rs = stmt.executeQuery(query);
		Category out = null;
		rs.next();
		out = new Category(rs.getString("Nome"), rs.getString("CodPartitaIva"));
		Category category = out;
		return category;
	}

	public static boolean checkPersona(String cf) throws SQLException {
		dbConnection();
		String query = "SELECT * FROM persona";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			if (rs.getString("CF").equals(cf)) {
				return false;
			}
		}
		return true;
	}

	public static Pair<Event, Optional<String>> getEvent(int id) throws SQLException {
		dbConnection();
		String query = "SELECT * FROM event WHERE IdEvento = " + id;
		ResultSet rs = stmt.executeQuery(query);
		rs.next();
		Event event;
		Optional<String> result = Optional.empty();
		if(rs.getString("NomeAvversario") != null) {
			event = new Event(new DateTime(rs.getString("Inizio")), new DateTime(rs.getString("Fine")), rs.getString("CodPartitaIVA"), rs.getString("Avversario"), rs.getInt("CodCategoria"));
			result = Optional.of(rs.getString("Risultato"));
		} else if(rs.getString("Descrizione_generico") != null) {
			event = new Event(new DateTime(rs.getString("Inizio")), new DateTime(rs.getString("Fine")), rs.getString("CodPartitaIVA"), rs.getString("Descrizione_generico"));
		} else {
			event = new Event(new DateTime(rs.getString("Inizio")), new DateTime(rs.getString("Fine")), rs.getString("CodPartitaIVA"), rs.getString("CodCategoria"));
		}
		return new Pair(event, result);
	}
}
