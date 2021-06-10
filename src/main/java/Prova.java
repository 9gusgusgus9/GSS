import java.sql.SQLException;

import entity.Society;
import entity.Sport;

public class Prova {

	public static void main(String[] args) throws SQLException {
		
		Society rick = new Society("12345678902","TOMMI",Sport.CALCIO);
		rick.delete();
	}
}
