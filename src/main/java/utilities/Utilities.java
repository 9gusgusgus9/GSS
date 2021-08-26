package utilities;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
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
import entity.Evento;
import entity.Immagine;
import entity.Payment;
import entity.Person;
import entity.Player;
import entity.Manager;
import entity.Staff;
import entity.Society;
import entity.Sport;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class Utilities {

	private static int idCategoria=0;
	private static String cf = null;
	private static Evento actualEvent;
	
	private static Connection conn;
	private static Statement stmt;
	private static String password;
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

	
	/*Metodo per l'inserimento nel database di ogni entit�*/
	
	public static void insertEntity(Entity entity){
		dbConnection();
		
		String query = "INSERT INTO " + entity.getTableName() + " " + entity.getColumnList() + " VALUES "
				+ entity.getValues();
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query = "SELECT * FROM " + entity.getTableName() + " ORDER BY " + entity.getNamePrimaryKey() + " DESC LIMIT 1";
		ResultSet rs;
		try {
			rs = stmt.executeQuery(query);
			if (rs.next() && (entity.getClass().equals(Category.class) || entity.getClass().equals(Evento.class)
					|| entity.getClass().equals(Payment.class))) {
				entity.setPrimaryKey(Integer.parseInt(rs.getString(1)));
			}
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	/*Metodo che popola il database con i ruoli corretti per lo sport selezionato al primo avvio*/
	public static void insertSport(int codSport){
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
		try {
			stmt.executeUpdate(query);
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/*Metodo che inserisce l'immagine in input*/
	public static void insertImage(Immagine image){
		dbConnection();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("INSERT INTO immagine" + image.getColumnList() + " VALUES " + image.getValues());
			ps.setBlob(1, image.getImage());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query = "SELECT * FROM " + image.getTableName() + " ORDER BY " + image.getNamePrimaryKey()
				+ " DESC LIMIT 1";
		ResultSet rs;
		try {
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				image.setPrimaryKey(Integer.parseInt(rs.getString(1)));
			}
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*Metodo per l'inserimento dei convocati presi dalla lista in input, ed invitati all'evento in input*/
	public static void insertConvocati(List<Object> convocati, Object codEvent){
		dbConnection();
		String query = null;
		for (int i = 0; i < convocati.size(); i++) {
			query = "INSERT INTO convocazioni (codEvento, CF) VALUES (" + (int)codEvent + ",'"
					+ (String) convocati.get(i) + "')";
			try {
				stmt.executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insertDocument(String cf, Immagine document, int tipoDocumento) {
		document.insert();
		dbConnection();
		String query = "INSERT INTO possesso (CodDocumento, CF, CodImmagine) VALUES (" + tipoDocumento + ", '" + cf + "', " + (int) document.getPrimaryKey() + ")";
		try {
			stmt.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*Metodo per invitare tutte le persone appartenenti alla categoria in input*/
	public static void inviteFromCategory(int codEvento, int codCategoria) {
		dbConnection();
		List<String> convocati =  Utilities.getCategoryCfFromCod(codCategoria);
		String query = "INSERT INTO convocazioni (CodEvento, CF, Presenza) VALUES ";
		Iterator<String> iter = convocati.iterator();
		while(iter.hasNext()) {
			query += "(" + codEvento + ", '" + iter.next() + "', false), ";
		}
		query = "" + query.subSequence(0, query.length() - 2);
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<Pair<File, String>> getAllDocumentsFromCF(String cf){
		dbConnection();
		List<Pair<File, String>> out = new LinkedList<>();
		String query = "SELECT q2.Nome AS Tipo, q1.DatiFile, q1.Nome, q1.TipoFile FROM (SELECT * FROM immagine) AS q1 INNER JOIN (SELECT * FROM team_management.possesso AS p INNER JOIN tipo_documento AS t ON p.CodDocumento = t.IdDocumento WHERE p.CF = '" + cf + "') AS q2 WHERE q2.CodImmagine = q1.IdImmagine";
		try {
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				Blob blob = rs.getBlob("DatiFile");
				
			    byte [] array = blob.getBytes( 1, ( int ) blob.length() );
			    File file;
				try {
					file = File.createTempFile(rs.getString("Nome"), rs.getString("TipoFile"), new File(System.getProperty("java.io.tmpdir")));
					
					FileOutputStream fileOut = new FileOutputStream( file );
				    fileOut.write( array );
				    fileOut.close();
				    out.add(new Pair<>(file, rs.getString("Tipo")));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			    
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
	}
	
	/*Metodo che restituisce la lista dei ruoli relativi allo sport selezionato al primo avvio*/
	public static List<String> getSport(){
		dbConnection();
		List<String> list = new LinkedList<>();
		String query = "SELECT * FROM ruolo_giocatore" ;
		ResultSet rs;
		try {
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				list.add(rs.getString("IdRuoloGiocatore"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Pair<Integer, String>> getDocumentsType(){
		dbConnection();
		List<Pair<Integer, String>> list = new LinkedList<>();
		String query = "SELECT * FROM tipo_documento";
		ResultSet rs;
		try {
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				list.add(new Pair<>(rs.getInt("IdDocumento"), rs.getString("Nome")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/*Metodo che si occupa di modificare le colonne richieste in input con il nuovo dato in input*/
	public static void update(Entity entity, List<Pair<String, String>> fields){
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
		try {
			stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};

	/*Metodo che restituisce l'immagine richiesta in input*/
	public static Image getImage(int x){
		dbConnection();
		String query = "SELECT DatiFile FROM Immagine WHERE IdImmagine=" + x;
		ResultSet rs;
		try {
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				InputStream image = rs.getBlob(1).getBinaryStream();
				BufferedImage imagen = ImageIO.read(image);
				Image out = SwingFXUtils.toFXImage(imagen, null);
				return out;
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*Metodo che controlla se � gia presente o meno una societ� all'interno del database*/
	public static boolean isTheFirstStart(){
		dbConnection();
		String query = "SELECT * FROM societa";
		ResultSet rs;
		try {
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/*Metodo che restituisce una lista contenente tutte le categorie e le ralitive immagini*/
	public static List<Pair<Category, Image>> getCategories(){
		dbConnection();
		List<Pair<Category, Image>> out = new LinkedList<>();
		String query = "SELECT * FROM categoria ORDER BY Nome";
		ResultSet rs;
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				int codImage = rs.getInt("CodImmagine");
				Image image = Utilities.getImage(codImage);
				Category category = new Category(rs.getInt("IdCategoria"), rs.getString("Nome"),
						rs.getString("CodPartitaIVA"), codImage);
				out.add(new Pair<>(category, image));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
	}

	/*Metodo che restituisce l'unica societ� presente nel database*/
	public static Pair<Image, Society> getSociety(){
		dbConnection();
		String query = "SELECT * FROM societa";
		ResultSet rs;
		Society out = null;
		Image image = null;
		try {
			rs = stmt.executeQuery(query);
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Pair<Image, Society> society = new Pair<>(image, out);
		return society;
	}

	/*Metodo che restituisce tutti gli eventi compresi tra le date in input*/
	public static List<Evento> getEvents(DateTime lunedi, DateTime domenica){
		dbConnection();
		String query = "SELECT * FROM evento";
		List<Evento> events = new LinkedList<>();
		ResultSet rs;
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String[] inizio = rs.getString("Inizio").split("/");
				String[] fine = rs.getString("Fine").split("/");
					if(rs.getString("NomeAvversario") != null) {
						Evento e = new Evento(new DateTime(Integer.parseInt(inizio[2]), Integer.parseInt(inizio[1]), Integer.parseInt(inizio[0])), new DateTime(Integer.parseInt(fine[2]), Integer.parseInt(fine[1]), Integer.parseInt(fine[0])), rs.getString("CodPartitaIVA"), rs.getString("NomeAvversario"), rs.getInt("CodCategoria"), rs.getString("Risultato"));
						e.setPrimaryKey(rs.getInt("IdEvento"));
						events.add(e);
					} else if (rs.getString("CodCategoria") != null){
						Evento e = new Evento(new DateTime(Integer.parseInt(inizio[2]), Integer.parseInt(inizio[1]), Integer.parseInt(inizio[0])), new DateTime(Integer.parseInt(fine[2]), Integer.parseInt(fine[1]), Integer.parseInt(fine[0])), rs.getString("CodPartitaIVA"), rs.getInt("CodCategoria"));
						e.setPrimaryKey(rs.getInt("IdEvento"));
						events.add(e);
					} else {
						Evento e = new Evento(new DateTime(Integer.parseInt(inizio[2]), Integer.parseInt(inizio[1]), Integer.parseInt(inizio[0])), new DateTime(Integer.parseInt(fine[2]), Integer.parseInt(fine[1]), Integer.parseInt(fine[0])), rs.getString("CodPartitaIVA"), rs.getString("Descrizione_generico"));
						e.setPrimaryKey(rs.getInt("IdEvento"));
						events.add(e);
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<Evento> filteredEvents = events.stream()
				.filter(d -> d.getInizio().compareDate(lunedi) >= 0 && d.getInizio().compareDate(domenica) <= 0)
				.collect(Collectors.toList());
		return filteredEvents;
	}

	/*Metodo che restituisce la categoria in input*/
	public static Pair<Image, Category> getCategory(int idCategoria){
		dbConnection();
		String query = "SELECT * FROM categoria WHERE IdCategoria=" + idCategoria;
		ResultSet rs;
		Category out = null;
		Image image = null;
		try {
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				out = new Category(rs.getString("Nome"), rs.getString("CodPartitaIva"));
				image = Utilities.getImage(rs.getInt("CodImmagine"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Pair<Image, Category> category = new Pair<>(image, out);
		return category;
	}

	/*Metodo che restituisce il player in input*/
	public static Pair<Image,Player> getPlayer(String cf){
		dbConnection();
		String query = "SELECT * FROM giocatore WHERE CF='" + cf + "'";
		ResultSet rs;
		Player out = null;
		Image image = null;
		String peso = null;
		String altezza = null;
		DateTime data = null;
		String ruolo = null;
		int codCategoria = 0;
		String preferenza = null;
		try {
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				peso = rs.getString("Peso");
				altezza = rs.getString("Altezza");
				data = new DateTime(rs.getString("Data_scadenza_certificato"));
				ruolo = rs.getString("CodRuoloGiocatore");
				codCategoria = rs.getInt("CodCategoria");
				preferenza = rs.getString("CodPreferenza");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String query1 = "SELECT * FROM persona WHERE CF='" + cf + "'";
		ResultSet rs1;
		try {
			rs1 = stmt.executeQuery(query1);
			if(rs1.next()) {
				out = new Player(cf, rs1.getString("Nome"), rs1.getString("Cognome"),new DateTime(rs1.getString("Data")), rs1.getInt("CodPagamento"),rs1.getString("CodSesso"), rs1.getString("CodPartitaIva"), rs1.getInt("Matricola_tesserino"), peso, altezza, data, ruolo, codCategoria, preferenza);
				image = Utilities.getImage(rs1.getInt("CodImmagine"));
			}
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Pair<>(image, out);
	}

	/*Metodo che restituisce il dirigente in input*/
	public static Pair<Image,Manager> getDirigent(String cf){
		dbConnection();
		String query = "SELECT * FROM dirigente WHERE CF='" + cf + "'";
		ResultSet rs;
		Manager out = null;
		Image image = null;
		String ruolo = null;
		try {
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				ruolo = rs.getString("CodRuoloDirigente");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query1 = "SELECT * FROM persona WHERE CF='" + cf + "'";
		ResultSet rs1;
		try {
			rs1 = stmt.executeQuery(query1);
			if(rs1.next()) {
				out = new Manager(cf, rs1.getString("Nome"), rs1.getString("Cognome"),new DateTime(rs1.getString("Data")), rs1.getInt("CodPagamento"),rs1.getString("CodSesso"), rs1.getString("CodPartitaIva"), rs1.getInt("Matricola_tesserino"), ruolo);
				image = Utilities.getImage(rs1.getInt("CodImmagine"));
			}
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Pair<>(image, out);
	}

	/*Metodo che restituisce lo staff in input*/
	public static Pair<Image,Staff> getStaff(String cf){
		dbConnection();
		String query = "SELECT * FROM staff WHERE CF='" + cf + "'";
		Staff out = null;
		Image image = null;
		int categoria = 0;
		String ruolo = null;
		try {
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				ruolo = rs.getString("CodRuoloStaff");
				categoria = rs.getInt("CodCategoria");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query1 = "SELECT * FROM persona WHERE CF='" + cf + "'";
		ResultSet rs1;
		try {
			rs1 = stmt.executeQuery(query1);
			if(rs1.next()) {
				out = new Staff(cf, rs1.getString("Nome"), rs1.getString("Cognome"),new DateTime(rs1.getString("Data")), rs1.getInt("CodPagamento"),rs1.getString("CodSesso"), rs1.getString("CodPartitaIva"), rs1.getInt("Matricola_tesserino"), ruolo, categoria);
				image = Utilities.getImage(rs1.getInt("CodImmagine"));
			}
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Pair<>(image, out);
	}
	
	/*Metodo che restituisce la categoria richiesta in input*/
	public static Category getOnlyCategory(int idCategoria){
		return Utilities.getCategory(idCategoria).getY();
	}
	
	/*Metodo che ritorna true se il cf in input non � presente nel database*/
	public static boolean isFreeCF(String cf){
		dbConnection();
		String query = "SELECT * FROM persona WHERE CF = '" + cf + "'";
		ResultSet rs;
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	/*Metodo che restituisce una ObservableList di CF invitati all'evento in input*/
	public static ObservableList<Person> getConvocati(int idEvento){
		ObservableList<Person> convocati = FXCollections.observableArrayList();
		dbConnection();
		String qPlayer = "SELECT q1.CF , q1.Nome , q1.Cognome, q2.CodRuoloGiocatore , q2.CodCategoria FROM (SELECT * FROM giocatore) AS q2 INNER JOIN (SELECT c.CF , p.Nome , p.Cognome FROM convocazioni AS c INNER JOIN persona AS p ON c.CF = p.CF WHERE c.CodEvento="+idEvento+") AS q1 ON q1.CF = q2.CF";
		String qManager = "SELECT q1.CF , q1.Nome , q1.Cognome , q2.CodRuoloDirigente FROM (SELECT * FROM dirigente) AS q2 INNER JOIN (SELECT c.CF , p.Nome , p.Cognome FROM convocazioni AS c INNER JOIN persona AS p ON c.CF = p.CF WHERE c.CodEvento="+idEvento+") AS q1 ON q1.CF = q2.CF";
		String qStaff = "SELECT q1.CF , q1.Nome , q1.Cognome , q2.CodRuoloStaff , q2.CodCategoria FROM (SELECT * FROM staff) AS q2 INNER JOIN (SELECT c.CF , p.Nome , p.Cognome FROM convocazioni AS c INNER JOIN persona AS p ON c.CF = p.CF WHERE c.CodEvento="+idEvento+") AS q1 ON q1.CF = q2.CF";
		ResultSet rsPlayer;
		try {
			rsPlayer = stmt.executeQuery(qPlayer);
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return convocati;
	}
	
	/*Metodo che restituisce il ruolo della persona in input*/
	public static SimpleStringProperty getCodRuoloByCF(String CF){
		dbConnection();
		String qPlayer = "SELECT rg.Descrizione AS CodRuoloGiocatore FROM giocatore AS g INNER JOIN ruolo_giocatore AS rg ON g.CodRuoloGiocatore = rg.IdRuoloGiocatore WHERE g.CF='"+CF+"'";
		String qManager = "SELECT rd.Descrizione AS CodRuoloDirigente FROM dirigente AS d INNER JOIN ruolo_dirigente AS rd ON d.CodRuoloDirigente = rd.IdRuoloDirigente WHERE d.CF='"+CF+"'";
		String qStaff = "SELECT rs.Descrizione AS CodRuoloStaff FROM staff AS s INNER JOIN ruolo_staff  AS rs ON s.CodRuoloStaff = rs.IdRuoloStaff WHERE s.CF='"+CF+"'";
		ResultSet rsPlayer;
		try {
			rsPlayer = stmt.executeQuery(qPlayer);
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*Metodo che restituisce la categoria a cui appartiene la persona con il CF in input*/
	public static SimpleStringProperty getNomeCategoriaByCF(String CF){
		dbConnection();
		String qPlayer = "SELECT q2.Nome FROM (SELECT CodCategoria FROM giocatore WHERE CF='"+CF+"') AS q1 INNER JOIN categoria AS q2 ON q1.CodCategoria = q2.IdCategoria";
		String qStaff = "SELECT q2.Nome FROM (SELECT CodCategoria FROM staff WHERE CF='"+CF+"') AS q1 INNER JOIN categoria AS q2 ON q1.CodCategoria = q2.IdCategoria";
		ResultSet rsPlayer;
		try {
			rsPlayer = stmt.executeQuery(qPlayer);
			if(rsPlayer.next()) {
				return new SimpleStringProperty(rsPlayer.getString("q2.Nome"));
			}
			ResultSet rsStaff = stmt.executeQuery(qStaff);
			if(rsStaff.next()) {
				return new SimpleStringProperty(rsStaff.getString("q2.Nome"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new SimpleStringProperty("Dirigente");
	}

	/*Metgodo che restituisce l'evento richiesto in input*/
	public static Pair<Evento, Optional<String>> getEvent(int id){
		dbConnection();
		String query = "SELECT * FROM event WHERE IdEvento = " + id;
		ResultSet rs;
		Evento event = null;
		Optional<String> result = Optional.empty();
		try {
			rs = stmt.executeQuery(query);
			rs.next();
			if(rs.getString("NomeAvversario") != null) {
				event = new Evento(new DateTime(rs.getString("Inizio")), new DateTime(rs.getString("Fine")), rs.getString("CodPartitaIVA"), rs.getString("Avversario"), rs.getInt("CodCategoria"), rs.getString("Risultato"));
				result = Optional.of(rs.getString("Risultato"));
			} else if(rs.getString("Descrizione_generico") != null) {
				event = new Evento(new DateTime(rs.getString("Inizio")), new DateTime(rs.getString("Fine")), rs.getString("CodPartitaIVA"), rs.getString("Descrizione_generico"));
			} else {
				event = new Evento(new DateTime(rs.getString("Inizio")), new DateTime(rs.getString("Fine")), rs.getString("CodPartitaIVA"), rs.getString("CodCategoria"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Pair<>(event, result);
	}
	
	/*Metodo che restituisce tutti i CF realtivi alla categoria in input*/
	public static List<String> getCategoryCfFromCod(int codCategoria){
		dbConnection();
		List<String> out = new LinkedList<>();
		String query = "SELECT CF FROM giocatore WHERE CodCategoria=" + codCategoria;
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()) {
				out.add(rs.getString("CF"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query = "SELECT CF FROM staff WHERE CodCategoria=" + codCategoria;
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()) {
				out.add(rs.getString("CF"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
	}
	
	/*Metodo che restituisce una ObservableList contenente tutte le persone iscritte nel database*/
	public static ObservableList<Person> getAllPeople(){
		ObservableList<Person> out = FXCollections.observableArrayList();
		dbConnection();
		String query = "SELECT CF, Nome, Cognome FROM persona ORDER BY Cognome DESC";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()) {
				out.add(new Person(rs.getString("CF"), rs.getString("Nome"), rs.getString("Cognome")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
	}
	
	/*Metodo che restituisce la categoria e il ruolo in base al CF in input*/
	public static SimpleStringProperty getMansionAndCategoryByCF(String cf) {
		dbConnection();
		String query = "SELECT c.Nome FROM giocatore AS g INNER JOIN categoria AS c ON g.CodCategoria = c.IdCategoria WHERE g.CF='" + cf + "'";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(rs.next()) {
				String ruolo = "Giocatore (" + rs.getString("c.Nome") + ")";
				return new SimpleStringProperty(ruolo);
			} else {
				query = "SELECT c.Nome FROM staff AS s INNER JOIN categoria AS c ON s.CodCategoria = c.IdCategoria WHERE s.CF='" + cf + "'";
				rs = stmt.executeQuery(query);
				if(rs.next()) {
					String ruolo = "Staff (" + rs.getString("c.Nome") + ")";
					return new SimpleStringProperty(ruolo);
				} else {
					return new SimpleStringProperty("Dirigente");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new SimpleStringProperty("");
	}
	
	/*Metodo che restituisce il ruolo a seconda del CF in input*/
	public static String getMansionByCF(String cf) {
		dbConnection();
		String query = "SELECT * FROM giocatore WHERE CF='" + cf + "'";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(rs.next()) {
				return "Giocatore";
			} else {
				query = "SELECT * FROM staff WHERE CF='" + cf + "'";
				rs = stmt.executeQuery(query);
				if(rs.next()) {
					return "Staff";
				} else {
					return "Dirigente";
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/*Metodo che elimina l'entita in input*/
	public static void deleteEntity(Entity entity){
		dbConnection();
		
		try {
			if (entity.getPrimaryKey().getClass() == int.class) {
				stmt.executeUpdate("DELETE FROM " + entity.getTableName() + " AS e WHERE e." + entity.getNamePrimaryKey() + "= " + entity.getPrimaryKey());
			} else {
				stmt.executeUpdate("DELETE FROM " + entity.getTableName() + " AS e WHERE e." + entity.getNamePrimaryKey() + "= '" + entity.getPrimaryKey() + "'");
			}
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*Metodo che elimina le convocazioni relative all'evento in input*/
	public static void deleteConvocazioni(int codEvento) {
		dbConnection();
		String query = "DELETE FROM convocazioni WHERE CodEvento="+codEvento;
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteDocumentsFromCf(String cf) {
		dbConnection();
		String query = "SELECT * FROM possesso WHERE CF = '" + cf + "'";
		try {
			ResultSet rs = stmt.executeQuery(query);
			List<Integer> documents = new LinkedList<>();
			while(rs.next()) {
				documents.add(rs.getInt("CodImmagine"));
			}
			Iterator<Integer> iter = documents.iterator();
			query = "DELETE FROM possesso WHERE CF = '" + cf + "'";
			stmt.executeUpdate(query);
			while(iter.hasNext()) {
				query = "DELETE FROM immagine WHERE IdImmagine = " + iter.next();
				stmt.executeUpdate(query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void deletePersonInConvene(Person persona) {
		dbConnection();
		try {
			stmt.executeUpdate("DELETE FROM convocazioni AS c WHERE c." + persona.getNamePrimaryKey() + "= '" + persona.getPrimaryKey() + "'");
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*Metodi utili per recuperare l'evento/la categoria /la persona da visualizzare*/
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
	
	public static Evento getEvent() {
		return actualEvent;
	}
	
	public static void setEvent(Evento e) {
		actualEvent = e;
	}
	
}
