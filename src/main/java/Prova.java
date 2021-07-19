
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Category;
import entity.Evento;
import entity.Finanze;
import entity.Immagine;
import entity.Manager;
import entity.Payment;
import entity.Player;
import entity.Society;
import entity.Sport;
import entity.Staff;
import entity.Sesso;
import utilities.DateTime;
import utilities.Utilities;

public class Prova {

	public static void main(String[] args) throws SQLException, FileNotFoundException {
////		// Utilities.tableEmpty();
		List<Object> convocati = new ArrayList<>();
//		InputStream immagine = new FileInputStream("src/main/resources/img/pallavolo.jpg");
		Immagine image = new Immagine("src/main/resources/img/pallavolo.jpg", "pallavolo", ".jpg");
////		//la image.insert() non va pi� richiamata, la fanno automaticamente i costruttori, dopo potete cancellare
////		//image.insert();
////		image.insert();
		String partitaIVA = "12345678924";
//		Society rick = new Society(partitaIVA,"piedi", Sport.BASKET);
//		rick.insert();
		Category allievi = new Category("pulcini", partitaIVA);
		allievi.insert();
		Evento allenamento = new Evento(new DateTime(2021, 7, 16), new DateTime(2021, 7, 16), partitaIVA, (int)allievi.getPrimaryKey());
		Evento generico = new Evento(new DateTime(2021, 7, 21), new DateTime(2021, 7, 21), partitaIVA,"Cena di squadra");
		Evento partita = new Evento(new DateTime(2021, 7, 18), new DateTime(2021, 7, 18), partitaIVA, "Bubano",(int) allievi.getPrimaryKey());
		partita.setRisultato("0-1");
		Payment pagamento = new Payment(1000, false, Finanze.QUOTA);
		pagamento.insert();
		Staff rambo = new Staff("ojubfjwneoufnw88", "rambo", "rambo", new DateTime(1701, 02, 29),
				(int) pagamento.getPrimaryKey(), Sesso.MASCHIO.getKey(), partitaIVA, image, "All",
				(int) allievi.getPrimaryKey());
		rambo.insert();
		Player ronaldo = new Player("CR777712CR771219", "Cristiano", "Ronaldo", new DateTime(1701, 02, 29),
				(int) pagamento.getPrimaryKey(), Sesso.ALTRO.getKey(), partitaIVA, image, 0, "123",
				"123", new DateTime(2021, 12, 31), "AG", (int) allievi.getPrimaryKey(), "DS");
		ronaldo.insert();
		Manager gus = new Manager("ojubfjwneoufnw93", "gus", "gus", new DateTime(1701, 02, 29),
				(int) pagamento.getPrimaryKey(), Sesso.FEMMINA.getKey(), partitaIVA, image, "DS");
		gus.insert();
		convocati.add(rambo.getPrimaryKey());
		convocati.add(ronaldo.getPrimaryKey());
		convocati.add(gus.getPrimaryKey());
		generico.setConvocati(convocati);
		partita.setConvocati(convocati);
		allenamento.setConvocati(convocati);
		partita.insert();
		allenamento.insert();
		generico.insert();
//		Immagine image = new Immagine("src/main/resources/img/default/stemma.jpg");
//		image.insert();
//		List<Pair<DateTime, String>> list = Utilities.getEvents(new DateTime(6,2,2), new DateTime(6,2,4));
//		System.out.println(list.get(0).getY());
	}
}
