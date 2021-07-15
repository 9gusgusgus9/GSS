
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Category;
import entity.Event;
import entity.Finanze;
import entity.Immagine;
import entity.Manager;
import entity.Payment;
import entity.Player;
import entity.Sesso;
import entity.Society;
import entity.Sport;
import entity.Staff;
import utilities.DateTime;
import utilities.Pair;
import utilities.Utilities;

public class Prova {

	public static void main(String[] args) throws SQLException, FileNotFoundException {
////		// Utilities.tableEmpty();
//		List<Object> convocati = new ArrayList<>();
//		InputStream immagine = new FileInputStream("src/main/resources/img/pallavolo.jpg");
//		Immagine image = new Immagine(immagine, "pallavolo", ".jpg");
////		//la image.insert() non va pi� richiamata, la fanno automaticamente i costruttori, dopo potete cancellare
////		//image.insert();
//		Society rick = new Society("12345678923", "TOMMI", Sport.CALCIO, image);
//		rick.insert();
//		Category allievi = new Category("pulcini", rick.getPrimaryKey(), image);
//		allievi.insert();
//		Event allenamento = new Event(new DateTime(4, 2, 3), new DateTime(4, 3, 4), rick.getPrimaryKey(), 4);
//		Event generico = new Event(new DateTime(6, 2, 3), new DateTime(6, 3, 4), rick.getPrimaryKey(),
//				"Cena di squadra");
//		Event partita = new Event(new DateTime(2, 2, 3), new DateTime(2, 3, 4), rick.getPrimaryKey(), "Bubano", 4);
////		partita.setRisultato("0-1");
//		Payment pagamento = new Payment(1000, false, Finanze.QUOTA);
//		pagamento.insert();
//		Staff rambo = new Staff("ojubfjwneoufnw86", "rambo", "rambo", new DateTime(1701, 02, 29),
//				(int) pagamento.getPrimaryKey(), Sesso.MASCHIO.getKey(), (String) rick.getPrimaryKey(), image, "All",
//				(int) allievi.getPrimaryKey());
//		rambo.insert();
//		Player ronaldo = new Player("CR777712CR771215", "Cristiano", "Ronaldo", new DateTime(1701, 02, 29),
//				(int) pagamento.getPrimaryKey(), Sesso.ALTRO.getKey(), (String) rick.getPrimaryKey(), image, 0, "123",
//				"123", new DateTime(2021, 12, 31), "ATT", (int) allievi.getPrimaryKey(), "DS");
//		ronaldo.insert();
//		Manager gus = new Manager("ojubfjwneoufnw90", "gus", "gus", new DateTime(1701, 02, 29),
//				(int) pagamento.getPrimaryKey(), Sesso.FEMMINA.getKey(), (String) rick.getPrimaryKey(), image, "DS");
//		gus.insert();
//		convocati.add(rambo.getPrimaryKey());
//		convocati.add(ronaldo.getPrimaryKey());
//		convocati.add(gus.getPrimaryKey());
//		generico.setConvocati(convocati);
//		partita.insert();
//		allenamento.insert();
//		generico.insert();
//		Immagine image = new Immagine("src/main/resources/img/default/stemma.jpg");
//		image.insert();
		List<Pair<DateTime, String>> list = Utilities.getEvents(new DateTime(6,2,2), new DateTime(6,2,4));
		System.out.println(list.get(0).getY());
	}
}
