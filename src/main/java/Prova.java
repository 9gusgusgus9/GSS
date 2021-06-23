
import java.sql.SQLException;

import entity.Category;
import entity.Finanze;
import entity.Manager;
import entity.Payment;
import entity.Sesso;
import entity.Society;
import entity.Sport;
import utilities.DateTime;

public class Prova {

	public static void main(String[] args) throws SQLException {
		
		Society rick = new Society("12345678920","TOMMI",Sport.CALCIO);
		rick.insert();		
		Category allievi = new Category("allievi","12345678920", 1);
//		allievi.setPrimaryKey(100);
		allievi.insert();		
//		Event allenamento = new Event(new DateTime(4,2,3), new DateTime(4,3,4), "12345678902", 4);
//		Event generico = new Event(new DateTime(6,2,3), new DateTime(6,3,4), "12345678902", "Cena di squadra");
//		Event partita = new Event(new DateTime(2,2,3), new DateTime(2,3,4), "12345678902", "Bubano", 4);
//		partita.insert();
//		allenamento.insert();
//		generico.insert();
//		partita.setRisultato("0-1");
//		
//		List<Pair<String, String>> list = new ArrayList<>();
//		list.add(new Pair<>("Risultato", partita.getRisultato()));
//		list.add(new Pair<>("NomeAvversario", "Medicina"));
//		
//		partita.update(list);
		Payment pagamento = new Payment(1000, false , Finanze.QUOTA);
		pagamento.insert();
//		Staff rambo = new Staff("ojubfjwneoufnw85", "rambo", "rambo", new DateTime(1701, 02, 29), (int) pagamento.getPrimaryKey(), Sesso.MASCHIO.getKey(),  (String) rick.getPrimaryKey(), 1, "All", (int) allievi.getPrimaryKey());
//		rambo.insert();
//		Player ronaldo = new Player("CR777712CR771213", "Cristiano", "Ronaldo", new DateTime(1701, 02, 29),(int) pagamento.getPrimaryKey(), Sesso.ALTRO.getKey(), (String) rick.getPrimaryKey(), 1, 0, "123", "123", new DateTime(2021, 12, 31), "DIF",(int) allievi.getPrimaryKey(), "DS");
//		ronaldo.insert();	}
		Manager gus = new Manager("ojubfjwneoufnw89", "gus", "gus", new DateTime(1701, 02, 29), (int) pagamento.getPrimaryKey(), Sesso.FEMMINA.getKey(),  (String) rick.getPrimaryKey(), 1, "DS");
		gus.insert();
	}
}
