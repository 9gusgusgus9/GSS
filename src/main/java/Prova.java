import java.sql.SQLException;

import entity.Category;
import entity.Society;
import entity.Sport;

public class Prova {

	public static void main(String[] args) throws SQLException {
		
		//Society rick = new Society("12345678902","TOMMI",Sport.CALCIO);
		//rick.insert();
		
		//Category pulcini = new Category(1, "pulcini", "12345678902", 1);
		//pulcini.delete();
		Category allievi = new Category("allievi","12345678902", 1);
		allievi.setPrimaryKey(10);
		allievi.delete();
	}
}
