import java.io.IOException;
import java.sql.SQLException;

import entity.Player;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utilities.Pair;
import utilities.Utilities;
import view.ViewSwitcher;
import view.ViewType;

public class ProvaSingleCategory{

	public static void main(String[] args) throws SQLException, IOException {
		Pair<Image, Player> player = null;
		try {
			player = Utilities.getPlayer("1234567890123456");
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(player.getY().getAltezza());
	}

}
