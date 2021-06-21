import java.sql.SQLException;

import entity.Category;
import entity.Event;
import entity.Society;
import entity.Sport;
import utilities.DateTime;

public class Prova {

	public static void main(String[] args) throws SQLException {
		
//		Society rick = new Society("12345678902","TOMMI",Sport.CALCIO);
//		rick.insert();
//		
//		Category allievi = new Category("allievi","12345678902", 1);
//		allievi.setPrimaryKey(100);
//		allievi.insert();
		
		Event partita = new Event(new DateTime(2,2,3), new DateTime(2,3,4), "12345678902", "Bubano", 4);
		Event allenamento = new Event(new DateTime(4,2,3), new DateTime(4,3,4), "12345678902", 4);
		Event generico = new Event(new DateTime(6,2,3), new DateTime(6,3,4), "12345678902", "Cena di squadra");
		partita.insert();
		allenamento.insert();
		generico.insert();
		
		
		
	}
}
