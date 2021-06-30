package view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilities.Utilities;

public class StartView {

@FXML
private ImageView calcio;

@FXML
private ImageView pallavolo;

@FXML
private ImageView basket;

public void init() {
	
}

public void getFoto() throws IOException, SQLException {
	BufferedImage imagen = ImageIO.read(Utilities.getImage());
	Image image = SwingFXUtils.toFXImage(imagen, null );
	calcio.setImage(image);
}

}
