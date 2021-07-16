import java.io.IOException;
import java.sql.SQLException;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.Utilities;

public class ProvaView extends javafx.application.Application {

	

	public static void main(String[] args) throws SQLException, IOException { 
		System.out.println(Utilities.getCategory(1).getY().getNome());
		}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
