import java.sql.SQLException;

import entity.Category;
import entity.Society;
import entity.Sport;

public class Prova {

	public static void main(String[] args) throws SQLException {
		
//		Society rick = new Society("12345678902","TOMMI",Sport.CALCIO);
//		rick.delete();
		
		Category pulcini = new Category(1, "pulcini", "000012343", 1);
		pulcini.insert();
	}
}
